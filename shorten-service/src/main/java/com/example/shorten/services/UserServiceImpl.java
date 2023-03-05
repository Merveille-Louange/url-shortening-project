package com.example.shorten.services;

import com.example.shorten.dto.UserDTO;
import com.example.shorten.dto.request.LoginRequestDTO;
import com.example.shorten.dto.request.SignupRequestDTO;
import com.example.shorten.dto.response.GenericResponse;
import com.example.shorten.dto.response.UserResponseDTO;
import com.example.shorten.dto.response.UserUrlDTO;
import com.example.shorten.exception.UserExistException;
import com.example.shorten.interfaces.IUserService;
import com.example.shorten.interfaces.UrlManager;
import com.example.shorten.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.ResponseCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    /*@Autowired
    private RedisTemplate<String, UserDTO> redisTemplate;*/
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${spring.application.baseurl}")
    private String appBaseUrl;
    @Autowired
    private UrlManager urlManager;
    Logger LOG = LogManager.getLogger();

    @Override
    public GenericResponse signUp(SignupRequestDTO signupRequestDTO) {
        GenericResponse response = new GenericResponse();
        // Check if the email exist
        if (userRepository.existsById(signupRequestDTO.getEmail())) {
            LOG.info("User already exist with this email {}", signupRequestDTO.getEmail());
            throw  new UserExistException("User account already exist", ResponseCode.USER_ALREADY_EXIST);
        }
        // Check if the password exist
        if (!signupRequestDTO.getPassword().equals(signupRequestDTO.getConfirmPassword())){
            LOG.info("Password and confirm password doesn't match");
            throw  new UserExistException("Password mismatch", ResponseCode.PASSWORD_DONT_MATCH);
        }

        UserDTO userDTO = new UserDTO(signupRequestDTO.getEmail(), signupRequestDTO.getEmail(), signupRequestDTO.getName(),
                passwordEncoder.encode(signupRequestDTO.getPassword()), new ArrayList<>());
        userRepository.save(userDTO);

        response.setErc(ResponseCode.SUCCESS);
        response.setMessage("Account created");
        return response;
    }

    @Override
    public GenericResponse<UserResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        GenericResponse<UserResponseDTO> response = new GenericResponse();
        if (!userRepository.existsById(loginRequestDTO.getEmail())) {
            LOG.info("Unknown email");
            throw new UserExistException("Username or password doesn't exist", ResponseCode.LOGIN_ERROR);
        }
        Optional<UserDTO> userDTOOptional = userRepository.findById(loginRequestDTO.getEmail());
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), userDTOOptional.get().getPassword())) {
            throw new UserExistException("Username or password doesn't exist", ResponseCode.LOGIN_ERROR);
        }
        UserDTO user = userDTOOptional.get();
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(userDTOOptional.get().getEmail());
        userResponseDTO.setName(userDTOOptional.get().getName());
        userResponseDTO.setUrls(new ArrayList<>());
        if (user.getShortenKey() != null && !user.getShortenKey().isEmpty()) {
            for (String key : user.getShortenKey()) {
                try {
                    String longUrls = urlManager.getUrlByKey(key);
                    userResponseDTO.getUrls()
                            .add(UserUrlDTO.builder().code(key).full_short_link(longUrls).short_link(appBaseUrl+key).build());
                } catch (RuntimeException exception){
                    LOG.info(exception.getMessage());
                    continue;
                }
            }
        }
        response.setData(userResponseDTO);
        response.setErc(ResponseCode.SUCCESS);
        return response;
    }


}

package com.example.shorten.services;

import com.example.shorten.dto.ShortenUrlReqDTO;
import com.example.shorten.dto.UserDTO;
import com.example.shorten.repository.UserRepository;
import util.ResponseCode;
import com.example.shorten.dto.UrlDTO;
import com.example.shorten.interfaces.UrlManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.NonNull;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UrlMangerImpl implements UrlManager {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${spring.application.baseurl}")
    private String appBaseUrl;
    @Value("${spring.redis.shorturl.key}")
    private String redisShortUrlKey;
    @Value("${application.default.redirecturl}")
    private String defaultRedirectUrl;
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    UserRepository userRepository;

    /**
     * Get the corresponding long url for the specified key
     *
     * @param key
     * @return
     */
    @Override
    public String getUrlByKey(@NonNull String key) {
        if (redisTemplate.opsForHash().hasKey(redisShortUrlKey, key)) {
            String data = (String) redisTemplate.opsForHash().get(redisShortUrlKey, key);
            UrlDTO obj = null;
            try {
                obj = mapper.readValue(data, UrlDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return obj.getUrl();
        } else {
            throw new RuntimeException("Unknown");
        }
    }

    /**
     * Validates and shorted the provided url
     *
     * @param url
     * @return
     */
    @Override
    public UrlDTO shortenUrl(@NonNull String url) throws URISyntaxException {
        UrlDTO urlDTO;
        // validating the url
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(url)) {
            throw new IllegalArgumentException("the url provided is invalid");
        }
        // Check that this provided url doesn't start with appbaseurl
        if (url.startsWith(appBaseUrl)) {
            urlDTO = UrlDTO.builder().key(getKeyFromURI(url)).shortUrl(url).createdAt(LocalDateTime.now()).url(url).build();
            urlDTO.setErc(ResponseCode.ERROR);
            urlDTO.setMessage("URL domain not accepted");
            return urlDTO;
        }


        // generating murmur3 based hash key as short URL
        String key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
        if (redisTemplate.opsForHash().hasKey(redisShortUrlKey, key)) {
            try {
                return mapper.readValue((String) redisTemplate.opsForHash().get(redisShortUrlKey, key), UrlDTO.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        urlDTO = UrlDTO.builder().key(key).shortUrl(appBaseUrl.concat(key)).createdAt(LocalDateTime.now()).url(url).build();
        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        try {
            hashOperations.put(redisShortUrlKey, key, mapper.writeValueAsString(urlDTO));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        urlDTO.setErc(ResponseCode.SUCCESS);
        urlDTO.setMessage("Success");
        return urlDTO;
    }

    @Override
    public UrlDTO shortenUrl(@NonNull ShortenUrlReqDTO urlReqDTO) throws URISyntaxException {
        UrlDTO urlDTO = shortenUrl(urlReqDTO.getUrl());
        if (urlDTO.getErc() != ResponseCode.ERROR && urlReqDTO.getEmail()!=null && !urlReqDTO.getEmail().isEmpty()){
            Optional<UserDTO> userDTOOptional = userRepository.findById(urlReqDTO.getEmail());
            userDTOOptional.ifPresent(userDTO -> {
                if (userDTO.getShortenKey()== null){
                    userDTO.setShortenKey(new ArrayList<>());
                }
                userDTO.getShortenKey().add(urlDTO.getKey());
                userRepository.save(userDTO);
            });
        }
        return urlDTO;
    }


    private String getKeyFromURI(String stringUri) throws URISyntaxException {
        URI uri = new URI(stringUri);
        String path = uri.getPath();
        if (!StringUtils.hasText(path))
            return null;
        String[] pathList = path.split("/");
        if (pathList.length >= 2) return pathList[1];
        return null;

    }
}

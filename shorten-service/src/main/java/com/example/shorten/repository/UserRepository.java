package com.example.shorten.repository;

import com.example.shorten.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, String> {

    boolean existsByEmail(String email);
}

package com.example.shorten.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("UserDTO")
public class UserDTO implements Serializable {

    String id;
    String email;
    String name;
    String password;
    List<String> shortenKey;
}

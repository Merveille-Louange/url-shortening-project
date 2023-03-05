package com.example.shorten.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;


@Data
public class ShortenUrlReqDTO {
    @NotEmpty
    private String url;
    private String email;
}

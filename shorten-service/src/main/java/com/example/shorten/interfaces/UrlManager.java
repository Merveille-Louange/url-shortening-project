package com.example.shorten.interfaces;

import com.example.shorten.dto.ShortenUrlReqDTO;
import com.example.shorten.dto.UrlDTO;
import lombok.NonNull;

import java.net.URISyntaxException;

public interface UrlManager {
    public String getUrlByKey(@NonNull String key);
    public UrlDTO shortenUrl(@NonNull String url) throws URISyntaxException;
    public UrlDTO shortenUrl(@NonNull ShortenUrlReqDTO urlReqDTO) throws URISyntaxException;

}

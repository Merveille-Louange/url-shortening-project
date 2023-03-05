package com.example.shorten.controllers;

import com.example.shorten.dto.ShortenUrlReqDTO;
import com.example.shorten.dto.UrlDTO;
import com.example.shorten.dto.response.UserUrlDTO;
import com.example.shorten.interfaces.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "")
public class URLShortenerController {


    /**
     * Returns a short URL.
     */
    @Autowired
    private UrlManager urlManager;

    @PostMapping("/shorten")
    @ResponseBody
    public ResponseEntity shortenUrl(@Valid @RequestBody ShortenUrlReqDTO urlReqDTO) {
        try {
            UrlDTO shortUrlEntry = urlManager.shortenUrl(urlReqDTO);
            return ResponseEntity.ok(UserUrlDTO.builder().code(shortUrlEntry.getKey())
                    .short_link(shortUrlEntry.getShortUrl()).full_short_link(shortUrlEntry.getUrl()).build());

        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/{key}")
    public ModelAndView getUrl(@PathVariable String key, ModelMap model) {
        String url = urlManager.getUrlByKey(key);
        return new ModelAndView("redirect:"+url, model);
    }}
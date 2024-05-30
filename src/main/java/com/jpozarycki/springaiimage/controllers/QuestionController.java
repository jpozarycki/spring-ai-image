package com.jpozarycki.springaiimage.controllers;

import com.jpozarycki.springaiimage.model.Question;
import com.jpozarycki.springaiimage.services.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final OpenAIService openAIService;

    @PostMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestBody Question question) {
        return openAIService.getImage(question);
    } 
}

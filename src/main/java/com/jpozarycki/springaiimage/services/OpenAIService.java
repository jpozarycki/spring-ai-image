package com.jpozarycki.springaiimage.services;

import com.jpozarycki.springaiimage.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface OpenAIService {
    byte[] getImage(Question question);
    String getDescription(MultipartFile multipartFile);
}

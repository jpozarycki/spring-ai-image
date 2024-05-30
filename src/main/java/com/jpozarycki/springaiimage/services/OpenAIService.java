package com.jpozarycki.springaiimage.services;

import com.jpozarycki.springaiimage.model.Question;

public interface OpenAIService {
    byte[] getImage(Question question);
}

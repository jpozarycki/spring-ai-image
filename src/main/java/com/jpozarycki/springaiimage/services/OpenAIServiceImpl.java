package com.jpozarycki.springaiimage.services;

import com.jpozarycki.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {
    @Override
    public byte[] getImage(Question question) {
        return new byte[0];
    }
}

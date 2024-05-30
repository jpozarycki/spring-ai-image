package com.jpozarycki.springaiimage.services;

import com.jpozarycki.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.stereotype.Service;

import java.util.Base64;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {
    private final OpenAiImageModel imageClient;
    @Override
    public byte[] getImage(Question question) {
        var imageOptions = ImageOptionsBuilder.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withResponseFormat("b64_json")
                .build();
        var b64string = imageClient.call(new ImagePrompt(question.question(), imageOptions))
                .getResult()
                .getOutput()
                .getB64Json();
        return Base64.getDecoder().decode(b64string);
    }
}

package com.jpozarycki.springaiimage.services;

import com.jpozarycki.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

import static org.springframework.ai.openai.api.OpenAiApi.ChatModel.GPT_4_VISION_PREVIEW;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {
    private final OpenAiImageModel imageClient;
    private final ChatModel chatClient;
    @Override
    public byte[] getImage(Question question) {
        var imageOptions = OpenAiImageOptions.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withResponseFormat("b64_json")
                .withModel("dall-e-3")
                .withQuality("hd")
                .build();
        var b64string = imageClient.call(new ImagePrompt(question.question(), imageOptions))
                .getResult()
                .getOutput()
                .getB64Json();
        return Base64.getDecoder().decode(b64string);
    }

    @Override
    public String getDescription(MultipartFile multipartFile) {
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder()
                .withModel(GPT_4_VISION_PREVIEW)
                .build();
        var userMessage = new UserMessage(
                "What is in this image?",
                List.of(new Media(MimeTypeUtils.IMAGE_JPEG, multipartFile.getResource())));
        return chatClient.call(new Prompt(userMessage, chatOptions))
                .getResult()
                .getOutput()
                .getContent();
    }
}

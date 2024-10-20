package service.hobbyservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AIServIce {

    private final ChatClient chatClient;



    public String generateText(String prompt) {
        Prompt aiPrompt = new Prompt(prompt);
        ChatResponse response = chatClient.call(aiPrompt);
        return response.getResult().getOutput().getContent();
    }

    public String answerQuestion(String question) {
        String prompt = "Please answer the following question: " + question;
        return generateText(prompt);
    }

    public String summarizeText(String text) {
        String prompt = "Please summarize the following text:\n\n" + text;
        return generateText(prompt);
    }
}

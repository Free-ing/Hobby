package service.hobbyservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.hobbyservice.dto.request.SurveyResultDto;
import service.hobbyservice.service.AIServIce;
import service.hobbyservice.service.OpenAiService;

@RestController
@RequiredArgsConstructor
public class OpenAiController {
    private final OpenAiService openAiService;
    private final AIServIce aiService;


    @PostMapping("/generate")
    public String generateResponse(@RequestBody SurveyResultDto.surveyResultDto surveyResultDto) {
        System.out.println(surveyResultDto);
        return openAiService.generateHobbyRecommendations(surveyResultDto);
    }


    @PostMapping("/generate1")
    public String generateText(@RequestBody String prompt) {
        return aiService.generateText(prompt);
    }

    @PostMapping("/answer")
    public String answerQuestion(@RequestBody String question) {
        return aiService.answerQuestion(question);
    }

    @PostMapping("/summarize")
    public String summarizeText(@RequestBody String text) {
        return aiService.summarizeText(text);
    }
}

package service.hobbyservice.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.hobbyservice.dto.request.SurveyResultDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAiService {
    private final ChatClient chatClient;

    public String generateHobbyRecommendations(SurveyResultDto.surveyResultDto surveyResult) {
        String systemPromptContent = "사용자의 설문조사 결과를 바탕으로 4~8개의 취미 활동을 추천해야 합니다. " +
                "1. 사용자의 선호도와 특성을 반영 " +
                "3. 사용자의 스트레스 해소 방식과 예산을 고려. " +
                "4. 새로운 활동에 대한 사용자의 태도를 반영. " +
                "5. 실내/야외 선호도를 고려. " +
                "6. 응답은 반드시 4개 이상의 추천 항목을 JSON 형식으로 반환하고 hobbyName, explanation만 작성해줘 " +
                "7. 설명은 간결하게 작성하되, 해당 취미가 사용자의 설문조사가 어떻게 부합하는지 간단하게 설명."+
                "8. 해당 취미가 왜 스트레스 해소와 관련이 있는지도 설명";

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPromptContent);
        Message systemMessage = systemPromptTemplate.createMessage();
                String survey1 = surveyResult.getLeisureActivities();
                String survey2 = surveyResult.getStressReliefActivities();
                String survey3 = surveyResult.getHobbyPreference();
                String survey4 =surveyResult.getActivityLocation();
                String survey5 =surveyResult.getStressResponse();
                String survey6 =surveyResult.getNewActivityPreference();
                String survey7 =surveyResult.getBudget();
                String survey = survey1 + survey2 + survey3 + survey4 + survey5 + survey6 + survey7;
        String userMessageContent = String.format("설문조사 결과를 바탕으로 취미 활동을 추천: %s", survey);

        UserMessage userMessage = new UserMessage(userMessageContent);

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));

        ChatResponse response = chatClient.call(prompt);

        return response.getResult().getOutput().getContent();
    }
}
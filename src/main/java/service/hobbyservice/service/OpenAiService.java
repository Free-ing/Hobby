package service.hobbyservice.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;
import service.hobbyservice.dto.request.SurveyResultDto;
import service.hobbyservice.dto.response.HobbyResponseDto;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;
    public List<HobbyResponseDto.AiHobbyResponseDto> generateHobbyRecommendations(SurveyResultDto.surveyResultDto surveyResult) {

        String survey1 = surveyResult.getLeisureActivities();
        String survey2 = surveyResult.getStressReliefActivities();
        String survey3 = surveyResult.getHobbyPreference();
        String survey4 = surveyResult.getActivityLocation();
        String survey5 = surveyResult.getStressResponse();
        String survey6 = surveyResult.getNewActivityPreference();
        String survey7 = surveyResult.getBudget();
        String survey = survey1 + survey2 + survey3 + survey4 + survey5 + survey6 + survey7;

        String userMessageContent = String.format("설문조사 결과를 바탕으로 취미 활동 추천 이유를 간단하게 제시해줘.: %s", survey);

        String jsonResponse = chatClient.prompt()
                .user(userMessageContent)
                .call()
                .content();

        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode recommendationsNode = rootNode.get("recommendations");
            if (recommendationsNode != null && recommendationsNode.isArray()) {
                return objectMapper.convertValue(recommendationsNode,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, HobbyResponseDto.AiHobbyResponseDto.class));
            } else {
                throw new RuntimeException("Unexpected response structure: 'recommendations' array not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response: " + e.getMessage(), e);
        }
    }
}

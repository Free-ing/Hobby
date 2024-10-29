package service.hobbyservice.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    ChatClient chatClient (ChatClient.Builder builder){

        return builder
                .defaultSystem("당신은 취미 추천 전문가입니다. 사용자의 설문조사 결과를 바탕으로 4개의 취미 활동을 추천해야 합니다. 반드시 다음 형식으로 반환해줘.\n" +
                        "        {\n" +
                        "            \"recommendations\": [\n" +
                        "                {\n" +
                        "                    \"hobbyName\": \"취미1\",\n" +
                        "                    \"explanation\": \"취미 설명\"\n" +
                        "                },              \n" +
                        "                {\n" +
                        "                    \"hobbyName\": \"취미2\",\n" +
                        "                    \"explanation\": \"취미 설명\"\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }")
                .build();
    }
}

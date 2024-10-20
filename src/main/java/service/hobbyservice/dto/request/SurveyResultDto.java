package service.hobbyservice.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;


public class SurveyResultDto {


    @AllArgsConstructor
    @Getter
    public static class surveyResultDto{

        @JsonProperty("leisureActivities")
        private String leisureActivities;

        @JsonProperty("stressReliefActivities")
        private String stressReliefActivities;

        @JsonProperty("hobbyPreference")
        private String hobbyPreference;

        @JsonProperty("activityLocation")
        private String activityLocation;

        @JsonProperty("stressResponse")
        private String stressResponse;

        @JsonProperty("newActivityPreference")
        private String newActivityPreference;

        @JsonProperty("budget")
        private String budget;

    }

}

package com.ellachihwa.lapa.dto;


import java.util.List;


public class SubscriptionRequestDto {

    String topicName;
    List<String> tokens;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
}
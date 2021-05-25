package com.ellachihwa.lapa.service;


import com.ellachihwa.lapa.dto.NotificationDto;
import com.ellachihwa.lapa.dto.SubscriptionRequestDto;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;


@Service
public class NotificationService {

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfig;

    private FirebaseApp firebaseApp;

    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream())).build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {

            System.out.println("Create FirebaseApp Error: " + e.getMessage());
        }
    }

    public void subscribeToTopic(String token) {
        SubscriptionRequestDto subscriptionRequestDto = new SubscriptionRequestDto("all-devices", Arrays.asList(token));
        try {
            FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequestDto.getTokens(),
                    subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            System.out.println("Firebase subscribe to topic fail: " + e.getMessage());
        }
    }

    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequestDto.getTokens(),
                    subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            System.out.println("Firebase unsubscribe to topic fail: " + e.getMessage());
        }
    }

    public String sendPnsToDevice(NotificationDto notificationDto) {
        Message message = Message.builder()
                .setToken(notificationDto.getTarget())
                .setNotification(new Notification(notificationDto.getTitle(), notificationDto.getBody()))
                .putData("content", notificationDto.getTitle())
                .putData("body", notificationDto.getBody())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            System.out.println("Fail to send firebase notification: " + e.getMessage());

        }

        return response;
    }

    public String sendPnsToTopic(NotificationDto notificationDto) {
        Message message = Message.builder()
                .setTopic(notificationDto.getTarget())
                .setNotification(new Notification(notificationDto.getTitle(), notificationDto.getBody()))
                .putData("content", notificationDto.getTitle())
                .putData("body", notificationDto.getBody())
                .build();

        String response = null;
        try {
           response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            System.out.println("Fail to send firebase notification: " + e.getMessage());

        }

        return response;
    }
}
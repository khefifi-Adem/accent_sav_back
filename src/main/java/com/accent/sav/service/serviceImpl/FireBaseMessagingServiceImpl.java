package com.accent.sav.service.serviceImpl;

import com.accent.sav.dto.NotificationMessageDto;
import com.accent.sav.service.FireBaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireBaseMessagingServiceImpl implements FireBaseMessagingService {
    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public FireBaseMessagingServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    private static final Logger logger = LoggerFactory.getLogger(FireBaseMessagingServiceImpl.class);

    public String sendNotificationByToken(NotificationMessageDto notificationMessageDto) {
        Notification notification = Notification
                .builder()
                .setTitle(notificationMessageDto.getTitle())
                .setBody(notificationMessageDto.getBody())
                .build();
        Message message = Message
                .builder()
                .setToken(notificationMessageDto.getReceipientToken())
                .setNotification(notification)
                .build();

        try{
            firebaseMessaging.send(message);
            logger.info("Success Send Notification");
            return "Success Send Notification";
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            logger.error("Error Sending Notification", e);
            return "Error Sending Notification";
        }
    }
}

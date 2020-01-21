package com.magikarpll.java.sms.module.sms.controller;

import com.auth0.jwt.internal.org.bouncycastle.util.encoders.Base64Encoder;
import com.messagebird.MessageBirdClient;
import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.UnauthorizedException;
import com.messagebird.objects.MessageResponse;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.telesign.MessagingClient;
import com.telesign.RestClient;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@RestController
public class SendController {

    private static final String receiver = "+8619925304048";

    public static void main(String[] args) throws IOException, NexmoClientException, UnauthorizedException, GeneralException {

//        //second
//        sendNexmoSms();

//        //default
//        sendTwilioSms();
//
        //bake
//        sendTeleSignSms();

    }

    @RequestMapping("/sendNexmoSms")
    public static void sendNexmoSms() throws IOException, NexmoClientException {
        NexmoClient client = new NexmoClient.Builder()
                .apiKey("bdd1bcc8")
                .apiSecret("9krOzyeYFlu8T2cs")
                .build();

        String messageText = "Hello from Nexmo";
        TextMessage message = new TextMessage("Nexmo", receiver, messageText);

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
            System.out.println(responseMessage);
        }
    }

    @RequestMapping("/sendTwilioSms")
    public static void sendTwilioSms() throws IOException, NexmoClientException {

          final String ACCOUNT_SID = "ACe29c2dfbac2467ceedfbcd128fded082";
          final String AUTH_TOKEN = "461ba0c86a348b8a0d1dac744ae8ecca";

        com.twilio.Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
                .creator(new com.twilio.type.PhoneNumber(receiver), // to
                        new com.twilio.type.PhoneNumber("+15205829775"), // from
                        "Where's Wallace?")
                .create();

        System.out.println(message.getSid());

    }

    @RequestMapping("/sendTeleSignSms")
    public static void sendTeleSignSms() throws IOException, NexmoClientException {

        String customerId = "B5811AF0-F024-4695-9289-F5CE5320869F";
        String apiKey = "FpQJPDqU+9Zp+E3DJ9xBqkY4FsSBhRWhk8WvV36LwJEZWiDiwb2HqiCXxXB4Nr5zOvRP8E04OhwHZylrGFPBsg==";
        String phoneNumber = receiver;
        String message = "You're scheduled for a dentist appointment at 2:30PM.";
        String messageType = "ARN";

        try {
            MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
            RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
            System.out.println("Your reference id is: "+telesignResponse.json.get("reference_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/sendMessageBirdSms")
    public static void sendMessageBirdSms() throws IOException, NexmoClientException, UnauthorizedException, GeneralException {

        // Create a MessageBirdService
        MessageBirdService messageBirdService = new MessageBirdServiceImpl("EFuNjDOkPa0XReSNHqCqliagG");
// Add the service to the client
        MessageBirdClient messageBirdClient = new MessageBirdClient(messageBirdService);
// Convert String number into acceptable format
        List<BigInteger> phones = Arrays.asList(new BigInteger("+8613006189736"));
// Send the message.
        MessageResponse response = messageBirdClient.sendMessage("+8613006189736", "MESSAGE", phones);

        System.out.println(response.getBody());
    }

}

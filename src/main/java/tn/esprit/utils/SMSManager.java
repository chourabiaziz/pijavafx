package tn.esprit.utils;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;


public class SMSManager {
    // Your Twilio Account SID and Auth Token
    private static final String ACCOUNT_SID = "AC504c2e997f7eb3b082a71282a3c9c9c1";
    private static final String AUTH_TOKEN = "131f8b8bdc42800d92d5916aad63a6d2";

    // Your Twilio phone number
    private static final String TWILIO_PHONE_NUMBER = "+15029128188";

    // Method to send an SMS
    public void sendSMS(String recipientPhoneNumber, String message) {
        // Initialize Twilio client with your credentials
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the SMS message
        Message twilioMessage = Message.creator(
                new com.twilio.type.PhoneNumber(recipientPhoneNumber),
                new com.twilio.type.PhoneNumber(TWILIO_PHONE_NUMBER),
                message
        ).create();

        System.out.println("SMS sent successfully with SID: " + twilioMessage.getSid());
    }
}


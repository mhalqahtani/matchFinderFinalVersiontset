package util;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Map;
public class smsSender {
    public static final String ACCOUNT_SID = "AC9accf727b4d0c1c8846c96e20d6ba467";
    public static final String AUTH_TOKEN = "90f97c8fbed44c9cd739e5412d4ed464";
    public static final PhoneNumber FROM_NUMBER = new PhoneNumber("+18452432474"); // The Twilio number you own and will send messages from
    public static final PhoneNumber TO_NUMBER = new PhoneNumber("+966503995592"); // The number you want to send messages to

    public static void Send(String matchInfo) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                TO_NUMBER,
                FROM_NUMBER,
                matchInfo // Body of the SMS
        ).create();
        System.out.println("Sent message with SID: " + message.getSid());


    }

}

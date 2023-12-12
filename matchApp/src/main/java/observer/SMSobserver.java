package observer;

import java.util.Map;
import util.smsSender;
public class SMSobserver extends observer{
    public SMSobserver(String recipient){
        super.setRecipient(recipient);
    }


    @Override
    public void update(String message) {
        smsSender.Send(message);
    }
}

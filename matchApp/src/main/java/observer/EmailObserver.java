package observer;

import java.util.Map;

public class EmailObserver extends observer{
    public EmailObserver(String recipent){
        super.setRecipient(recipent);
    }



    @Override
    public void update(String message) {

    }
}

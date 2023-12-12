package observer;
import java.util.Map;

public abstract class observer {
    private String recipient;

    public String getRecipent(){
        return this.recipient;
    }

    public void setRecipient(String recipient){
        this.recipient = recipient;
    }

    public abstract void update(String message);

}

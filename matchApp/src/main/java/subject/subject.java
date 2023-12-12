package subject;

import java.util.Map;
import observer.observer;

public abstract interface subject {
    void addSubscriber(observer Observer);
    void removeSubscriber(observer Observer);
    void notifySubscribers(String message);
}

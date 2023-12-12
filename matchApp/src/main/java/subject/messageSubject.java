package subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import observer.observer;
public class messageSubject implements subject{
    List<observer> observers;
    public messageSubject(){
        this.observers = new ArrayList<observer>();
    }
    @Override
    public void addSubscriber(observer observer) {
        this.observers.add(observer);
    }
    // remove observer object to the list of observers
    @Override
    public void removeSubscriber(observer observer) {
        this.observers.remove(observer);
    }
    // notify observer
    @Override
    public void notifySubscribers(String message) {
        observers.getFirst().update(message);

    }
}

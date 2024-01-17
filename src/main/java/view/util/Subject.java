package view.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This interface is used for helping the program to be based on MVC model.
 * Alls classes implementing this interface will be identify as a model.
 * @see Subject
 */
public abstract class Subject {
    protected List<Observer> attached;

    protected Subject() {
        attached = new ArrayList<>();
    }

    public void attach(Observer obs) {
        if (! attached.contains(obs)) {
            attached.add(obs);
        }
    }

    public void detach(Observer obs) {
        if (attached.contains(obs)) {
            attached.remove(obs);
        }
    }

    protected void notifyObservers() {
        for (Observer o : attached) {
            o.update(this);
        }
    }

    protected void notifyObservers(Object data) {
        for (Observer o : attached) {
            o.update(this, data);
        }
    }

}

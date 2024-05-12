package utils;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

    private List<Updatable> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public void attach(Updatable observer) {
        this.observers.add(observer);
    }

    public void notifyObservers() {
        this.observers.forEach(Updatable::update);
    }

}
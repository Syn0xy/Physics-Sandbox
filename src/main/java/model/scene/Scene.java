package model.scene;

import java.util.Timer;
import java.util.TimerTask;

import utils.Subject;
import utils.Updatable;

public abstract class Scene extends Subject implements Updatable {

    public void start(int framesPerSecond) {
        new Timer().schedule(getTask(), 0, 1000 / framesPerSecond);
        // new Thread(getRunnable()).start();
    }

    private TimerTask getTask() {
        return new TimerTask() {
            @Override
            public void run() {
                update();
                notifyObservers();
            }
        };
    }

    // private Runnable getRunnable() {
    //     return new Runnable() {
    //         @Override
    //         public void run() {
    //             while (true) {
    //                 update();
    //                 notifyObservers();
    //             }
    //         }
    //     };
    // }

}
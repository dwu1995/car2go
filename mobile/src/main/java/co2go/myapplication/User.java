package co2go.myapplication;

/**
 * Created by Derek on 3/14/2015.
 */

import java.util.ArrayList;

public class User {
    private String name;
    private Model car;

    public User(String name, Model car) {
        this.name = name;
        this.car = car;

    }
    public void changeCar(Model newModel) {
        car = newModel;
    }

    public String getName() {
        return name;
    }

    public Model getModel() {
        return car;
    }
    public void changeName(String name) {
        this.name = name;
    }
}

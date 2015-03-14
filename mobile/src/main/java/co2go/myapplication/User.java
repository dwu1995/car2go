package co2go.myapplication;

/**
 * Created by Derek on 3/14/2015.
 */

import java.util.ArrayList;

public class User {
    private String name;
    private Manufacturer manufacturer;
    private Model car;
    private ArrayList<TimeStamp> TimeStamps;
    public User(String name,  Manufacturer manufact, Model car) {
        this.name = name;
        this.manufacturer = manufact;
        this.car = car;
        TimeStamps = new ArrayList<TimeStamp>();
    }
    public void changeCar(Manufacturer newManufacturer, Model newModel) {
        manufacturer = newManufacturer;
        car = newModel;
    }

    public String getName() {
        return name;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public Model getModel(){
        return car;
    }

    public void addTimeStamp(double longitude, double latitude){
        TimeStamp stampToAdd = new TimeStamp(longitude,latitude);
        TimeStamps.add(stampToAdd);
    }
}

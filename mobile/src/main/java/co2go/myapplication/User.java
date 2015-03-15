package co2go.myapplication;

/**
 * Created by Derek on 3/14/2015.
 */

import java.util.ArrayList;

public class User {
    private String name;
    private Model car;
    private ArrayList<TimeStamp> TimeStamps;
    private TimeStamp lastTimeStamp;

    public User(String name, Model car) {
        this.name = name;
        this.car = car;
        TimeStamps = new ArrayList<TimeStamp>();
    }
    public void changeCar(Model newModel) {
        car = newModel;
    }

    public String getName() {
        return name;
    }

    public Model getModel(){
        return car;
    }

    public void addTimeStamp(double longitude, double latitude){
        TimeStamp stampToAdd = new TimeStamp(longitude,latitude);
        TimeStamp latestTimeStamp = lastTimeStamp;
        double timeDiff = stampToAdd.getTime().getTime() - latestTimeStamp.getTime().getTime();
        timeDiff = timeDiff / (60 * 60 * 1000) % 24;
        double distanceDiff = distanceTraveled(stampToAdd, latestTimeStamp);
        double speed = distanceDiff/timeDiff;

        if(speed > 30) {
            TimeStamps.add(stampToAdd);
        }
        lastTimeStamp = stampToAdd;
    }

    public double calculateEmission() {
        double distanceSum = 0;
        double totalEmission;
        for(int i = 0; i < TimeStamps.size()-1;i++){
            distanceSum += distanceTraveled(TimeStamps.get(i), TimeStamps.get(i+1));
        }

        totalEmission = distanceSum * car.getEmission();
        return totalEmission;
    }

    public void changeName(String name){
        this.name = name;
    }
    private double distanceTraveled(TimeStamp firstStamp, TimeStamp secondStamp) {
        double longitudeDifference;
        double latitudeDifference;
        double distance;

        longitudeDifference = (firstStamp.getLongitude() -
                secondStamp.getLongitude()) * 110.320;

        latitudeDifference = (firstStamp.getLatitude() -
                secondStamp.getLatitude()) * 110.54;

        distance = Math.sqrt(Math.pow(latitudeDifference,2) +
                Math.pow(longitudeDifference,2));

        return distance;
    }

}

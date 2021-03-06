package co2go.myapplication;

/**
 * Created by Derek on 3/14/2015.
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeStamp {
    private Date time;
    private double longitude, latitude;

    public TimeStamp(double longitude, double latitude){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        time = new Date();
        dateFormat.format(time);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    //constructor without location
    public TimeStamp(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        time = new Date();
        dateFormat.format(time);
        this.longitude = 0;
        this.latitude = 0;
    }
    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public Date getTime(){
        return time;
    }

    // returns the time difference in seconds between the timestamps
    public long getTimeDifference( TimeStamp old) {
        return ( ( this.time.getTime() - old.time.getTime() ) / 1000 );
    }
}

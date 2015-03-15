package co2go.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.*;



public class MainActivity extends ActionBarActivity implements LocationListener{
    Button settingButton;
    Button historyButton;
    Button playButton;
    LocationManager locationmanager;
    User user;
    TimeStamp initial = new TimeStamp();
    boolean recording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recording = false;
        super.onCreate(savedInstanceState);
        getUserInformation();
        setContentView(R.layout.activity_main);
        settingButton = (Button) findViewById(R.id.settings);
      //  historyButton = (Button) findViewById(R.id.userHistory);
        playButton = (Button) findViewById(R.id.button);
        playButton.setTag(1);

        locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria cri = new Criteria();
        String provider = locationmanager.getBestProvider(cri, false);

        if (provider != null & !provider.equals("")) {
            Location location = locationmanager.getLastKnownLocation(provider);
            locationmanager.requestLocationUpdates(provider, 2000, 1, this);
            if (location != null) {
                onLocationChanged(location);
            } else {
                Toast.makeText(getApplicationContext(), "location not found", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Provider is null", Toast.LENGTH_LONG).show();
        }
    }

    public void sendUserMessage(View view) {
        Intent intent = new Intent(this, userSettings.class);
        startActivity(intent);
    }
    public void sendHistoryMessage(View view) {
        Intent intent = new Intent(this, EmissionHistory.class);
        startActivity(intent);
    }

    public void sendStartMessage(View view) {
        final int status = (Integer) view.getTag();
        TimeStamp after;
        TextView printEmissions = (TextView) findViewById(R.id.print);

        if(status == 1) {
            recording = true;
            playButton.setText("STOP");
            view.setTag(0);
            initial = new TimeStamp();
            user.addTimeStamp(0,0);
            printEmissions.setText("Trip CO2 Emissions:   ");
        }
        else {
            recording = false;
            playButton.setText("START TRIP NOW");
            view.setTag(1);
            after = new TimeStamp();
            user.addTimeStamp(20,20);
            long tripTime = after.getTimeDifference(initial); //tripTime = trip time in seconds
            printEmissions.setText("Trip CO2 Emissions:   " + user.calculateEmission()+ " grams");
            user.clearTimeStamps();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
  @Override
  public void onLocationChanged(Location location) {
      if(recording) {
          user.addTimeStamp(location.getLongitude(), location.getLatitude());
      }
}
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }
    @Override
    public void onProviderEnabled(String s) {
    }
    @Override
    public void onProviderDisabled(String s) {
    }

    public void getCarInformation(){

    }

    public void getUserInformation() {
        JSONObject userData;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new
                    File(getFilesDir()+File.separator+"userData.json")));
            String read;
            StringBuilder builder = new StringBuilder("");


            while((read = bufferedReader.readLine()) != null){
                builder.append(read);
            }
            bufferedReader.close();

            userData = parseString(builder.toString());
        } catch (IOException e) {
            try {
                userData = parseUserData();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new
                        File(getFilesDir()+File.separator+"userData.json"),false));
                bufferedWriter.write(userData.toString());
                bufferedWriter.flush();
                bufferedWriter.close();

            } catch (IOException c){
            }
            userData = parseUserData();
        }

        String name = "";
        String manufacturer = "";
        String modelNumber = "";
        int emission = 0;
        try {
            name = userData.getString("Name");
            modelNumber = userData.getString("Model");
            manufacturer = userData.getString("Manufacturer");
            emission = Integer.parseInt(userData.getString("AVG(CO2_gkm)"));
            Model model = new Model(emission, modelNumber);
            User parsedUser = new User(name, model);
            user = parsedUser;
        } catch (JSONException e) {
        }
    }


    public JSONObject parseUserData() {
        String JSONString = null;
        JSONObject JSONObject = null;
        try {

            //open the inputStream to the file
            InputStream inputStream = getAssets().open("user.json");

            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            JSONString = new String(bytes, "UTF-8");
            JSONObject = new JSONObject(JSONString);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return JSONObject;
    }
    public JSONObject parseCarData() {
        String JSONString = null;
        JSONObject JSONObject = null;
        try {

            //open the inputStream to the file
            InputStream inputStream = getAssets().open("CarData.json");

            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            JSONString = new String(bytes, "UTF-8");
            JSONObject = new JSONObject(JSONString);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return JSONObject;
    }

    public JSONObject parseString(String input) {
        String JSONString = null;
        JSONObject JSONObject = null;
        try {

            //open the inputStream to the file
            InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));


            int sizeOfJSONFile = inputStream.available();

            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            inputStream.read(bytes);

            //close the input stream
            inputStream.close();

            JSONString = new String(bytes, "UTF-8");
            JSONObject = new JSONObject(JSONString);

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (JSONException x) {
            x.printStackTrace();
            return null;
        }
        return JSONObject;
    }


}

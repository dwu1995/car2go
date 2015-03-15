package co2go.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.*;

import java.io.IOException;
import java.io.InputStream;


public class userSettings extends ActionBarActivity implements View.OnClickListener {
    Button nameButton;
    Button manufacturerButton;
    Button modelButton;

    EditText nameSet;
    EditText manufacturerSet;
    EditText modelSet;

    JSONObject carData;
    JSONArray  brandData;
    boolean manufactureKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);
        carData = parseCarData();
        nameButton = (Button) findViewById(R.id.button_name);
        nameButton.setOnClickListener(this);
        manufacturerButton = (Button) findViewById(R.id.button_name2);
        manufacturerButton.setOnClickListener(this);
        modelButton = (Button) findViewById(R.id.button_name3);
        modelButton.setOnClickListener(this);
        nameSet = (EditText) findViewById(R.id.userName);
        manufacturerSet = (EditText) findViewById(R.id.car_manufacturer);
        modelSet = (EditText) findViewById(R.id.car_model);
        nameSet.setTag(0);
        manufacturerSet.setTag(1);
        modelSet.setTag(2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_user_settings, menu);
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
    public void onClick(View v) {
        try {
            String text = new String();
            switch (v.getId()) {
                case R.id.button_name:
                    nameSet.setText(nameSet.getText());
                    text = nameSet.getText().toString();
                    new AlertDialog.Builder(this)
                            .setTitle("Success!")
                            .setMessage("Updated: " + text)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                    break;
                case R.id.button_name2:
                    manufacturerSet.setText(manufacturerSet.getText());
                    text = manufacturerSet.getText().toString();
                    brandData = carData.getJSONArray(text.toUpperCase());
                    if(text.length() > 0) {
                        new AlertDialog.Builder(this)
                                .setTitle("Success!")
                                .setMessage("Updated: " + text)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                        manufactureKey = true;
                        modelButton.setText("UPDATE");
                    }
                    else {
                        manufactureKey = false;
                        modelButton.setText("ENTER CAR MANUFACTURER FIRST");
                    }
                    break;
                case R.id.button_name3:
                    modelSet.setText(modelSet.getText());
                    text = modelSet.getText().toString();
                    if(manufactureKey) {
                        for (int i = 0; i < brandData.length(); i++) {
                            JSONObject carInfo = (JSONObject) brandData.get(i);
                            if (carInfo.getString("Model").toUpperCase() == text.toUpperCase()) {
                                new AlertDialog.Builder(this)
                                        .setTitle("Success!")
                                        .setMessage("Updated: " + text)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();
                                break;
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
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
}

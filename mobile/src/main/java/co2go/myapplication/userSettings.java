package co2go.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class userSettings extends ActionBarActivity implements View.OnClickListener {
    Button nameButton;
    Button manufacturerButton;
    Button modelButton;

    EditText nameSet;
    EditText manufacturerSet;
    EditText modelSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

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
        String text = new String();
        switch (v.getId()) {
            case R.id.button_name:
                nameSet.setText("hello " + nameSet.getText());
                text = nameSet.getText().toString();
                break;
            case R.id.button_name2:
                manufacturerSet.setText("hello " + manufacturerSet.getText());
                text = manufacturerSet.getText().toString();
                break;
            case R.id.button_name3:
                modelSet.setText("hello " + modelSet.getText());
                text = modelSet.getText().toString();
                break;
            default:
                break;
        }

    }
}

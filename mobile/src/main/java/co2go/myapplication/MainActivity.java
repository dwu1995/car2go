package co2go.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
    Button settingButton;
    Button historyButton;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        settingButton = (Button) findViewById(R.id.settings);
        historyButton = (Button) findViewById(R.id.userHistory);
        playButton = (Button) findViewById(R.id.button);
        playButton.setTag(1);
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
        if(status ==1) {
            playButton.setText("STOP");
            view.setTag(0);
        }
        else {
            playButton.setText("START TRIP NOW");
            view.setTag(1);
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
}

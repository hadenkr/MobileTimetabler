package au.edu.vu.timetable4519560;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

/**
 * Created by Choongyeol Kim on 21/08/2015.
 */
public class MainActivity extends AppCompatActivity {

    private List<Appointment> appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
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

    public void populateSchedule() {

    }

    public void createNewAppointment(View view) {
        Intent intent = new Intent(this, NewAppointmentActivity.class);
        startActivity(intent);
    }

    public void deleteAppointment(View view) {
        Intent intent = new Intent(this, DeleteAppointmentActivity.class);
        startActivity(intent);
    }
}

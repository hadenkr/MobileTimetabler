package au.edu.vu.timetable4519560;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Choongyeol Kim on 21/08/2015.
 */
public class MainActivity extends AppCompatActivity {
    private List<AppointmentGroup> mGroupList;
    private AppointmentGroupAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d(this.getClass().getName(), "Create Start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResources();

        //Log.d(this.getClass().getName(), "Create End");
    }

    @Override
    public void onResume() {
        //Log.d(this.getClass().getName(), "Resume Start");

        super.onResume();

        getAllAppointments();
        mListAdapter.notifyDataSetChanged();

        //Log.d(this.getClass().getName(), "Resume End");
    }

    private void initResources() {
        mGroupList = new ArrayList<>();
        for (DayClass day : DayClass.values()) {
            mGroupList.add(new AppointmentGroup(day));
        }

        mListAdapter = new AppointmentGroupAdapter(this, R.layout.appointment_group_row, 0, mGroupList);
        final ListView listView = (ListView) findViewById(R.id.listViewMain);
        listView.setAdapter(mListAdapter);
    }

    private void getAllAppointments() {
        //Log.d(this.getClass().getName(), "getAllAppointments Start");
        AppointmentDataSource.getInstance().getAllGroupAppointments(mGroupList);
        //Log.d(this.getClass().getName(), "getAllAppointments End");
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

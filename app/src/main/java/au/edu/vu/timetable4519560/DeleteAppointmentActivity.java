package au.edu.vu.timetable4519560;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Choongyeol Kim on 21/08/2015.
 */
public class DeleteAppointmentActivity extends AppCompatActivity {
    private List<Appointment> mAppointmentList;
    private AppointmentAdapter mListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d(this.getClass().getName(), "Create Start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_appointment);

        initResources();

        //Log.d(this.getClass().getName(), "Create End");
    }

    @Override
    protected void onResume() {
        //Log.d(this.getClass().getName(), "Resume Start");

        super.onResume();

        getAllAppointments();
        mListAdapter.notifyDataSetChanged();

        //Log.d(this.getClass().getName(), "Resume End");
    }

    private void initResources() {
        mAppointmentList = new ArrayList<>();
        mListAdapter = new AppointmentAdapter(this, R.layout.appointment_row, R.id.checkboxAppointment, mAppointmentList);
        final ListView listView = (ListView) findViewById(R.id.listViewDelete);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(mListAdapter);
    }

    public void onClickBack(View view) {
        finish();
    }

    public void onClickDelete(View view) {
        //Log.d(this.getClass().getName(), "Del Start");

        Set<Integer> checkedList = mListAdapter.getCheckedList();
        if (!checkedList.isEmpty()) {
            StringBuilder sb = new StringBuilder(checkedList.toString());
            String strCheckedIdList = sb.substring(1, sb.length() - 1);
            AppointmentDataSource.getInstance().deleteAppointment(strCheckedIdList);
            Toast.makeText(DeleteAppointmentActivity.this, "Appointment(s) deleted", Toast.LENGTH_SHORT).show();
            //Log.d(this.getClass().getName(), "Del End");
            finish();
        } else {
            Toast.makeText(DeleteAppointmentActivity.this, "Appointment(s) should be selected", Toast.LENGTH_SHORT).show();
            //Log.d(this.getClass().getName(), "Del End");
        }
    }

    public void getAllAppointments() {
        //Log.d(this.getClass().getName(), "getAllAppointments Start");
        AppointmentDataSource.getInstance().getAllAppointments(mAppointmentList);
        //Log.d(this.getClass().getName(), "getAllAppointments End");
    }
}

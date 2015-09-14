package au.edu.vu.timetable4519560;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Choongyeol Kim on 21/08/2015.
 */
public class NewAppointmentActivity extends AppCompatActivity {
    private Appointment mAppointment;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d(this.getClass().getName(), "Create Start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        initResources();

        //Log.d(this.getClass().getName(), "Create End");
    }

    @Override
    public void onResume() {
        //Log.d(this.getClass().getName(), "Resume Start");

        super.onResume();

        //Log.d(this.getClass().getName(), "Resume End");
    }

    private void initResources() {
        mAppointment = new Appointment();
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    public void onClickBack(View view) {
        finish();
    }

    @Nullable
    private DayClass getSelectedDay() {
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButton1:
                return DayClass.MONDAY;
            case R.id.radioButton2:
                return DayClass.TUESDAY;
            case R.id.radioButton3:
                return DayClass.WEDNESDAY;
            case R.id.radioButton4:
                return DayClass.THURSDAY;
            case R.id.radioButton5:
                return DayClass.FRIDAY;
            default:
                //Log.d(this.getClass().getName(), "Unknown RadioButton Clicked");
                return null;
        }
    }

    public void onClickCreate(View view) {
        //Log.d(this.getClass().getName(), "New Start");

        final EditText editTextTime = (EditText) findViewById(R.id.editTextTime);
        final EditText editTextDuration = (EditText) findViewById(R.id.editTextDuration);
        final EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);

        mAppointment.setId(0); // Dummy Value : 0
        mAppointment.setDay(getSelectedDay());
        mAppointment.setTime(editTextTime.getText().toString().trim());
        mAppointment.setDuration(editTextDuration.getText().toString().trim());
        mAppointment.setDescription(editTextDescription.getText().toString().trim());

        if (mAppointment.isValid()) {
            AppointmentDataSource.getInstance().insertAppointment(mAppointment);
            Toast.makeText(NewAppointmentActivity.this, "Appointment created", Toast.LENGTH_SHORT).show();
            //Log.d(this.getClass().getName(), "New End");
            finish();
        } else {
            Toast.makeText(NewAppointmentActivity.this, "Day should be selected", Toast.LENGTH_SHORT).show();
            //Log.d(this.getClass().getName(), "New End");
        }
    }
}

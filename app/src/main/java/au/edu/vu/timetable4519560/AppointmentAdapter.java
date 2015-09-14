package au.edu.vu.timetable4519560;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Choongyeol Kim on 2/09/2015.
 * This class is used for DeleteAppointmentActivity.
 */
public class AppointmentAdapter extends CustomAdapter<Appointment> implements OnCheckedChangeListener {
    private Map<Integer, Boolean> mCheckedList;

    public AppointmentAdapter(Context context, int resource, int textViewResourceId, List<Appointment> objects) {
        super(context, resource, textViewResourceId, objects);
        this.mCheckedList = new HashMap<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        CheckBox checkBox;
        Appointment appointment = getItem(position); // appointment is not null

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);
            //Log.d(this.getClass().getName(), "null convertView pos : " + position + " , app_id : " + appointment.getId());

            checkBox = (CheckBox) view.findViewById(mFieldId);
            view.setTag(checkBox);
            //Log.d(this.getClass().getName(), "checkBox pos : " + position + " , app_id : " + appointment.getId());
        }
        else {
            view = convertView;
            checkBox = (CheckBox) view.getTag();
            //Log.d(this.getClass().getName(), "convertView pos : " + position + " , app_id : " + appointment.getId());
        }
        checkBox.setText(appointment.toString());
        checkBox.setTag(appointment);
        //checkBox.setChecked(((ListView) parent).isItemChecked(position));
        Boolean isChecked = mCheckedList.get(appointment.getId());
        if (isChecked != null && isChecked) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        checkBox.setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        try {
            Integer id = ((Appointment) buttonView.getTag()).getId();
            if (isChecked)
                mCheckedList.put(id, true);
            else
                mCheckedList.remove(id);
        } catch (NullPointerException e) {
            Log.e(this.getClass().getName(), "Null Pointer" );
        }
        buttonView.setChecked(isChecked);
    }

    Set<Integer> getCheckedList() {
        return mCheckedList.keySet();
    }
}

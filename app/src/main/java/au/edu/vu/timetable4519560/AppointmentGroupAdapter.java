package au.edu.vu.timetable4519560;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Choongyeol Kim on 1/09/2015.
 * This class is used for MainActivity.
 */
public class AppointmentGroupAdapter extends CustomAdapter<AppointmentGroup> {
    public AppointmentGroupAdapter(Context context, int resource, int textViewResourceId, List<AppointmentGroup> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView day;
        TextView detail;
        AppointmentGroup group = getItem(position); // group is not null

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);

            day = (TextView) view.findViewById(R.id.textViewGroupDay);
            detail = (TextView) view.findViewById(R.id.textViewGroupSchedule);
            view.setTag(new GroupViewHolder(day, detail));
        } else {
            view = convertView;
            GroupViewHolder holder = (GroupViewHolder) view.getTag();
            day = holder.day;
            detail = holder.detail;
        }

        if (day != null) day.setText(group.getDayName());
        if (detail != null) detail.setText(group.getSchedules());

        return view;
    }

    private final class GroupViewHolder {
        TextView day;
        TextView detail;

        GroupViewHolder(TextView day, TextView detail) {
            this.day = day;
            this.detail = detail;
        }
    }
}

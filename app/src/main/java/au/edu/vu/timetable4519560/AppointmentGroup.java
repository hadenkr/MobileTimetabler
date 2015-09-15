package au.edu.vu.timetable4519560;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Choongyeol Kim on 1/09/2015.
 */
public class AppointmentGroup {
    private DayClass mDay;
    private List<Appointment> mList;

    AppointmentGroup(final DayClass day) {
        this.mDay = day;
        mList = new ArrayList<>();
    }

    public String getDayName() {
        switch (mDay) {
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            case FRIDAY:
                return "Friday";
            default:
                return "Unknown Day";
        }
    }

    public boolean add(Appointment appointment) {
        //if (!mList.isEmpty()) {
            for (Appointment element : mList) {
                if (appointment.getId() == element.getId())
                    return false;
            }
        //}
        mList.add(appointment);
        return true;
    }

    public void clear() {
        mList.clear();
    }

    public String getSchedules() {
        if (mList.isEmpty()) {
            return "No appointments";
        }

        StringBuilder sb = new StringBuilder("");
        Iterator<Appointment> iterator = mList.iterator();
        boolean initialLoop = true;
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();

            if (initialLoop) initialLoop = false;
            else sb.append("\n");
            sb.append(appointment.toString());
        }

        return sb.toString();
    }
}

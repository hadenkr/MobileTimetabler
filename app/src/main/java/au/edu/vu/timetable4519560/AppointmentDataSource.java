package au.edu.vu.timetable4519560;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Choongyeol Kim on 22/08/2015.
 */
public class AppointmentDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns;

    public AppointmentDataSource(Context context) {

    }

    public void open() {

    }

    public void close() {

    }

    public void insertAppointment(Appointment appointment) {

    }

    public void deleteAppointment(List<Long> list) {

    }

    public List<Appointment> getAllAppointments() {
        return null;
    }

    public Appointment cursorToAppointment(Cursor cursor) {
        return null;
    }
}

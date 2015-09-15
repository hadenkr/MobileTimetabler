package au.edu.vu.timetable4519560;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Choongyeol Kim on 22/08/2015.
 * Data Access Object
 */
public class AppointmentDataSource extends Application {
    private static AppointmentDataSource mAppInstance;
    private volatile static MySQLiteHelper mHelperInstance;
    private SQLiteDatabase mDatabase;

    private static final String DELIMITER_1 = ", ";
    private static final String DELIMITER_2 = "', '";

    @Override
    public void onCreate() {
        //Log.d(this.getClass().getName(), "Create Start");

        super.onCreate();

        initResources();

        //Log.d(this.getClass().getName(), "Create End");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //Log.d(this.getClass().getName(), "Terminate");
        try {
            mDatabase.close();
            mHelperInstance.close();
        } catch (Exception e) {
            Log.d(this.getClass().getName(), "Terminate Exception");
        }
    }

    private void initResources() {
        mAppInstance = this;
        // DCL (Double Checking Locking) >= JDK 1.4
        if (mHelperInstance == null) {
            synchronized (MySQLiteHelper.class) {
                if (mHelperInstance == null) mHelperInstance = new MySQLiteHelper(getApplicationContext());
            }
        }
        mDatabase = mHelperInstance.getWritableDatabase();
    }

    public static AppointmentDataSource getInstance() {
        return mAppInstance;
    }

    public void open() {
        if (!mDatabase.isOpen()) mHelperInstance.onOpen(mDatabase);
    }

    private void insert1(Appointment appointment) {
        String sqlStr = "INSERT INTO " +
                MySQLiteHelper.TABLE_APPOINTMENTS + " (" +
                MySQLiteHelper.COLUMN_DAY_NAME + DELIMITER_1 +
                MySQLiteHelper.COLUMN_TIME_NAME + DELIMITER_1 +
                MySQLiteHelper.COLUMN_DURATION_NAME + DELIMITER_1 +
                MySQLiteHelper.COLUMN_DESCRIPTION_NAME + ") VALUES ('" +
                DayClass.toInteger(appointment.getDay()) + DELIMITER_2 +
                appointment.getTime() + DELIMITER_2 +
                appointment.getDuration() + DELIMITER_2 +
                appointment.getDescription() + "')";

        Log.d(this.getClass().getName(), sqlStr);
        Log.d(this.getClass().getName(), "insertAppointment End");
        mDatabase.execSQL(sqlStr);
    }

    private void insert2(Appointment appointment) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_DAY_NAME, DayClass.toInteger(appointment.getDay()));
        values.put(MySQLiteHelper.COLUMN_TIME_NAME, appointment.getTime());
        values.put(MySQLiteHelper.COLUMN_DURATION_NAME, appointment.getDuration());
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION_NAME, appointment.getDescription());
        mDatabase.insert(MySQLiteHelper.TABLE_APPOINTMENTS, null, values);
    }

    public void insertAppointment(Appointment appointment) {
        //Log.d(this.getClass().getName(), "insertAppointment Start");

        insert2(appointment);
    }

    private void delete1(String list) {
        String sqlStr = "DELETE FROM " +
                MySQLiteHelper.TABLE_APPOINTMENTS + " WHERE " +
                MySQLiteHelper.COLUMN_ID_NAME + " IN (" +
                list + ")";
        Log.d(this.getClass().getName(), sqlStr);
        mDatabase.execSQL(sqlStr);
    }

    private void delete2(String list) {
        String sqlStr = MySQLiteHelper.COLUMN_ID_NAME + " IN (" + list + ")";
        //Log.d(this.getClass().getName(), sqlStr);

        mDatabase.delete(MySQLiteHelper.TABLE_APPOINTMENTS, sqlStr, null);
    }
    public void deleteAppointment(String list) {
        if (!list.isEmpty()) {
            delete2(list);
        }

        //Log.d(this.getClass().getName(), "deleteAppointment End");
    }

    private Appointment cursorToAppointment(Cursor cursor, Appointment appointment) {
        if (appointment == null) appointment = new Appointment();

        appointment.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_ID_NAME)));
        appointment.setDay(DayClass.fromInteger(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_DAY_NAME)))));
        appointment.setTime(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_TIME_NAME)));
        appointment.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_DURATION_NAME)));
        appointment.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_DESCRIPTION_NAME)));

        return appointment;
    }

    public void getAllGroupAppointments(List<AppointmentGroup> groupList) {
        //Log.d(this.getClass().getName(), "getAllGroupAppointments Start");
        for (AppointmentGroup group : groupList) {
            group.clear();
        }

        // Read-Only SQLiteDatabase doesn't need to be closed. See Android API
        SQLiteDatabase db = mHelperInstance.getReadableDatabase();
        Cursor cursor = db.query(MySQLiteHelper.TABLE_APPOINTMENTS, null, null, null, null, null, MySQLiteHelper.ORDER_CONDITION);
        Appointment appointment = null;
        while (cursor.moveToNext()) {
            appointment = cursorToAppointment(cursor, appointment);

            DayClass day = appointment.getDay();
            if (day != null) {
                AppointmentGroup group = groupList.get(DayClass.toInteger(day));
                if (group != null && group.add(appointment)) appointment = null;
            }
        }
        cursor.close();
        //Log.d(this.getClass().getName(), "getAllGroupAppointments End");
    }

    public void getAllAppointments(List<Appointment> appointmentList) {
        //Log.d(this.getClass().getName(), "getAllAppointments Start");
        // Read-Only SQLiteDatabase doesn't need to be closed. See Android API
        SQLiteDatabase db = mHelperInstance.getReadableDatabase();
        Cursor cursor = db.query(MySQLiteHelper.TABLE_APPOINTMENTS, null, null, null, null, null, MySQLiteHelper.ORDER_CONDITION);
        while (cursor.moveToNext()) {
            Appointment appointment = cursorToAppointment(cursor, null);
            if (appointment.getDay() != null && appointmentList != null) appointmentList.add(appointment);
        }
        cursor.close();
        //Log.d(this.getClass().getName(), appointmentList.toString());
        //Log.d(this.getClass().getName(), "getAllAppointments End");
    }

    private final class MySQLiteHelper extends SQLiteOpenHelper {
        private static final String TABLE_APPOINTMENTS = "appointments";
        private static final String COLUMN_ID_NAME = "id";
        private static final String COLUMN_DAY_NAME = "app_day";
        private static final String COLUMN_TIME_NAME = "app_time";
        private static final String COLUMN_DURATION_NAME = "duration";
        private static final String COLUMN_DESCRIPTION_NAME = "description";

        private static final String COLUMN_ID = "integer primary key";
        private static final String COLUMN_DAY = "integer not null";
        private static final String COLUMN_TIME = "text not null";
        private static final String COLUMN_DURATION = "text not null";
        private static final String COLUMN_DESCRIPTION = "text not null";

        private static final String DATABASE_NAME = "timetable.db";
        private static final String DATABASE_CREATE = "CREATE TABLE ";
        private static final String ORDER_CONDITION = "app_day, app_time, id";
        private static final int DATABASE_VERSION = 1;

        private MySQLiteHelper(final Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sqlStr = DATABASE_CREATE +
                    TABLE_APPOINTMENTS + " (" +
                    COLUMN_ID_NAME + " " + COLUMN_ID + ", " +
                    COLUMN_DAY_NAME + " " + COLUMN_DAY + ", " +
                    COLUMN_TIME_NAME + " " + COLUMN_TIME + ", " +
                    COLUMN_DURATION_NAME + " " + COLUMN_DURATION + ", " +
                    COLUMN_DESCRIPTION_NAME + " " + COLUMN_DESCRIPTION + ")";
            db.execSQL(sqlStr);

            //Log.d(this.getClass().getName(), sqlStr);
            //Log.d(this.getClass().getName(), "Create End");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sqlStr = "DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS;
            db.execSQL(sqlStr);
            onCreate(db);

            //Log.d(this.getClass().getName(), sqlStr);
            //Log.d(this.getClass().getName(), "Upgrade End");
        }
    }
}

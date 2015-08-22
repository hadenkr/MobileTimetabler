package au.edu.vu.timetable4519560;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Choongyeol Kim on 22/08/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TABLE_APPOINTMENTS = "appointments";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DAY = "app_day";
    private static final String COLUMN_TIME = "app_time";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String DATABASE_NAME = "timetable.db";
    private static final String DATABASE_CREATE = " (id INTEGER PRIMARY KEY AUTOINCREMENT, app_day INTEGER NOT NULL, app_time VARCHAR(10) NOT NULL, duration VARCHAR(20), description VARCHAR(255));";
    private static final int DATABASE_VERSION = 1;

    private volatile static MySQLiteHelper instance;

    public static MySQLiteHelper getInstance(final Context context) {
        // DCL(Double-Checking Locking) >= JDK 1.4
        if (instance == null) {
            synchronized (MySQLiteHelper.class) {
                if (instance == null) {
                    instance = new MySQLiteHelper(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private MySQLiteHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        sb.append(TABLE_APPOINTMENTS).append(DATABASE_CREATE);
        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ");
        sb.append(TABLE_APPOINTMENTS).append(";");
        db.execSQL(sb.toString());
        onCreate(db);
    }
}

package au.edu.vu.timetable4519560;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Choongyeol Kim on 22/08/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String TABLE_APPOINTMENTS = "";
    private static final String COLUMN_ID = "";
    private static final String COLUMN_DAY = "";
    private static final String COLUMN_TIME = "";
    private static final String COLUMN_DURATION = "";
    private static final String COLUMN_DESCRIPTION = "";
    private static final String DATABASE_NAME = "timetable.db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "";

    private static MySQLiteHelper instance;// = new MySQLiteHelper();

    //public static MySQLiteHelper getInstance() { return instance; }
    public static MySQLiteHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MySQLiteHelper(context.getApplicationContext());
        }
        return instance;
    }

    private MySQLiteHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package au.edu.vu.timetable4519560;

/**
 * Created by Choongyeol Kim on 22/08/2015.
 */
public class Appointment {
    private static final String DELIMITER = ", ";
    private static final String DEFAULT_TIME = "No time";
    private static final String DEFAULT_DURATION = "No duration";
    private static final String DEFAULT_DESCRIPTION = "No description";
    private int mId;
    private DayClass mDay;
    private String mTime;
    private String mDuration;
    private String mDescription;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public DayClass getDay() {
        return mDay;
    }

    public void setDay(DayClass day) {
        this.mDay = day;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        if (time == null || time.isEmpty())
            this.mTime = DEFAULT_TIME;
        else
            this.mTime = time;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        if (duration == null || duration.isEmpty())
            this.mDuration = DEFAULT_DURATION;
        else
            this.mDuration = duration;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty())
            this.mDescription = DEFAULT_DESCRIPTION;
        else
            this.mDescription = description;
    }

    public boolean isValid() {
        if (mDay != null && mTime != null && mDuration != null) {
            if (!mTime.isEmpty() && !mDuration.isEmpty())
                return true;
        }

        return false;
    }

    public String toString() {
        return mTime + DELIMITER + mDuration + DELIMITER + mDescription;
    }
}

package au.edu.vu.timetable4519560;

/**
 * Created by Choongyeol Kim on 22/08/2015.
 */
public class Appointment {
    private long id;
    private int day;
    private String time;
    private String duration;
    private String description;

    Appointment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    void display() {

    }
}

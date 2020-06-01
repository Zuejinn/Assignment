package au.edu.utas.mekvall.assignment;

import android.icu.util.Calendar;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;

public class Raffle {
    private int mRaffleID;
    private String name;
    private String description;
    private int winners;
    private String person;
    private String location;
    private long start;
    private long end;
    private int max;
    private String ImageAddress;

    public String getImageAddress() { return ImageAddress; }

    public void setImageAddress(String imageAddress) { ImageAddress = imageAddress; }

    public int getMax() { return max; }

    public void setMax(int max) { this.max = max; }

    public int getWinners() {
        return winners;
    }

    public void setWinners(int winners) {
        this.winners = winners;
    }

    public int getmRaffleID() {
        return mRaffleID;
    }

    public String getName() {
        return name;
    }

    public void setRaffleID(int raffleID) {
        this.mRaffleID = raffleID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerson() { return person; }

    public void setPerson(String person) { this.person = person; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location;}

    public long getStart() { return this.start; }
    public void setStart(long start) { this.start = start; }
    @RequiresApi(api = Build.VERSION_CODES.N)
    // c/o https://stackoverflow.com/posts/47900485/timeline

    public String getStartString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.start);
        return formatter.format(calendar.getTime());
    }

    public long getEnd() { return this.end; }
    public void setEnd(long end) { this.end = end; }
    @RequiresApi(api = Build.VERSION_CODES.N)
    // c/o https://stackoverflow.com/posts/47900485/timeline
    public String getEndString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.end);
        return formatter.format(calendar.getTime());
    }


}

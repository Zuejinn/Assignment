package au.edu.utas.mekvall.assignment;


import android.os.Build;
import androidx.annotation.RequiresApi;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Ticket {
    private int mTicketID;
    private String name;
    private int number;
    private int raffle_id;
    private int phone;
    private int winner_place;
    private long date;
    private float price;

    public int getWinner_place() {
        return winner_place;
    }

    public void setWinner_place(int winner_place) {
        this.winner_place = winner_place;
    }
    public int getmTicketID() {
        return mTicketID;
    }

    public void setmTicketID(int mTicketID) {
        this.mTicketID = mTicketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRaffle_id() {
        return raffle_id;
    }

    public void setRaffle_id(int raffle_id) {
        this.raffle_id = raffle_id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public float getPrice() { return this.price; }
    public void setPrice(float price) {this.price = price; }

    public long getDate() { return this.date; }
    public void setDate(long date) { this.date = date; }
    @RequiresApi(api = Build.VERSION_CODES.N)
    // c/o https://stackoverflow.com/posts/47900485/timeline
    public String getDateString(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.date);
        return formatter.format(calendar.getTime());
    }

}

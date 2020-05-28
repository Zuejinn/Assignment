package au.edu.utas.mekvall.assignment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

public class TicketTable {
    public static final String TABLE_NAME   ="Tickets";
    public static final String KEY_TICKET_ID   ="ticket_id";
    public static final String KEY_TICKET_NUM   ="number";
    public static final String KEY_NAME   = "name";
    public static final String KEY_PHONE   = "phone";
    public static final String KEY_WIN_PLACE = "win_place";
    public static final String KEY_DATE = "date";
    public static final String KEY_FOREIGN = RaffleTable.KEY_RAFFLE_ID;

    public static final String CREATE_STATEMENT  = "CREATE TABLE " +
            TABLE_NAME
            + "     ("
            + KEY_TICKET_ID + " integer primary key autoincrement not null, "
            + KEY_NAME + " string not null, "
            + KEY_PHONE + " int, "
            + KEY_TICKET_NUM + " int, "
            + KEY_DATE + " int not null, "
            + KEY_WIN_PLACE + " int DEFAULT 0, "
            + KEY_FOREIGN + " int not null, "
            + "FOREIGN KEY (" + KEY_FOREIGN + ") REFERENCES Raffles("
            + KEY_FOREIGN + ") "
            + ");";

    public static void insert(SQLiteDatabase db, Ticket t){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, t.getName());
        values.put(KEY_FOREIGN, t.getRaffle_id());
        values.put(KEY_TICKET_NUM, t.getNumber());
        values.put(KEY_PHONE, t.getPhone());
        values.put(KEY_DATE, t.getDate());

        db.insert(TABLE_NAME, null, values);
    }

    public static Ticket createFromCursor(Cursor c) {
        if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
            return null;
        }
        else {
            Ticket t = new Ticket();
            t.setmTicketID(c.getInt(c.getColumnIndex(KEY_TICKET_ID)));
            t.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            t.setNumber(c.getInt(c.getColumnIndex(KEY_TICKET_NUM)));
            t.setWinner_place(c.getInt(c.getColumnIndex(KEY_WIN_PLACE)));
            t.setRaffle_id(c.getInt(c.getColumnIndex(KEY_FOREIGN)));
            t.setPhone(c.getInt(c.getColumnIndex(KEY_PHONE)));
            t.setDate(c.getLong(c.getColumnIndex(KEY_DATE)));
            return t;
        }
    }

    public static ArrayList<Ticket> selectRaffleTickets(SQLiteDatabase db, Raffle r){
        ArrayList<Ticket> results = new ArrayList<Ticket>();
        int raffleID = r.getmRaffleID();

        Cursor c = db.query(TABLE_NAME, null, null , null, null, null, null);

        if (c != null) {
            c.moveToFirst();

            while (!c.isAfterLast()) {
                Ticket t = createFromCursor(c);
                if(t.getRaffle_id() == raffleID) {
                    results.add(t);
                }

                c.moveToNext();
            }
        }

        return results;
    }

    public static void update(SQLiteDatabase db, Ticket t) {
        ContentValues values = new ContentValues();
        values.put(KEY_TICKET_ID, t.getmTicketID());
        values.put(KEY_NAME, t.getName());
        values.put(KEY_FOREIGN, t.getRaffle_id());
        values.put(KEY_TICKET_NUM, t.getNumber());
        values.put(KEY_PHONE, t.getPhone());
        values.put(KEY_WIN_PLACE, t.getWinner_place());
        values.put(KEY_DATE, t.getDate());

        db.update(TABLE_NAME, values, KEY_TICKET_ID+"= ?",
                new String[]{ "" + t.getmTicketID()});
    }
}

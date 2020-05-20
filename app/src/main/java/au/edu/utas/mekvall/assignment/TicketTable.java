package au.edu.utas.mekvall.assignment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TicketTable {
    public static final String TABLE_NAME   ="Tickets";
    public static final String KEY_TICKET_ID   ="ticket_id";
    public static final String KEY_TICKET_NUM   ="number";
    public static final String KEY_NAME   ="name";

    public static final String CREATE_STATEMENT  = "CREATE TABLE " +
            TABLE_NAME
            + "     ("
            + KEY_TICKET_ID + " integer primary key autoincrement, "
            + KEY_NAME + " string not null "
            + KEY_TICKET_NUM + " int"
            + ");";

    public static void insert(SQLiteDatabase db, Ticket r){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, r.getName());

        db.insert(TABLE_NAME, null, values);
    }

    public static Ticket createFromCursor(Cursor c) {
        if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
            return null;
        }
        else {
            Ticket r = new Ticket();
            r.setName(c.getString(c.getColumnIndex(KEY_NAME)));

            return r;
        }
    }

    public static ArrayList<Ticket> selectAll(SQLiteDatabase db){
        ArrayList<Ticket> results = new ArrayList<Ticket>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();

            while (!c.isAfterLast()) {
                Ticket r = createFromCursor(c);
                results.add(r);

                c.moveToNext();
            }
        }

        return results;
    }

    public static void update(SQLiteDatabase db, Ticket r) {
        ContentValues values = new ContentValues();
        values.put(KEY_TICKET_ID, r.getmTicketID());
        values.put(KEY_NAME, r.getName());

        db.update(TABLE_NAME, values, KEY_TICKET_ID+"= ?",
                new String[]{ "" + r.getmTicketID()});
    }
}

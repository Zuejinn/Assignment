package au.edu.utas.mekvall.assignment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import au.edu.utas.mekvall.assignment.Raffle;

public class RaffleTable {
    public static final String TABLE_NAME   ="property";
    public static final String KEY_RAFFLE_ID   ="raffle_id";
    public static final String KEY_NAME   ="name";

    public static final String CREATE_STATEMENT  = "CREATE TABLE " +
            TABLE_NAME
            + "     ("
            + KEY_RAFFLE_ID + " integer primary key autoincrement, "
            + KEY_NAME + " string not null, "
            + ");";

    public static void insert(SQLiteDatabase db, Raffle r){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, r.getName());

        db.insert(TABLE_NAME, null, values);
    }

    public static Raffle createFromCursor(Cursor c) {
        if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
            return null;
        }
        else {
            Raffle r = new Raffle();
            r.setName(c.getString(c.getColumnIndex(KEY_NAME)));

            return r;
        }
    }

    public static ArrayList<Raffle> selectAll(SQLiteDatabase db){
        ArrayList<Raffle> results = new ArrayList<Raffle>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (c != null) {
            c.moveToFirst();

            while (!c.isAfterLast()) {
                Raffle r = createFromCursor(c);
                results.add(r);

                c.moveToNext();
            }
        }

        return results;
    }

    public static void update(SQLiteDatabase db, Raffle r) {
        ContentValues values = new ContentValues();
        values.put(KEY_RAFFLE_ID, r.getmRaffleID());
        values.put(KEY_NAME, r.getName());

        db.update(TABLE_NAME, values, KEY_RAFFLE_ID+"= ?",
                new String[]{ "" + r.getmRaffleID()});
    }
}

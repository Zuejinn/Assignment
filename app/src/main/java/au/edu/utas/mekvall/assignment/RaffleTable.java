package au.edu.utas.mekvall.assignment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RaffleTable {
    public static final String TABLE_NAME   ="Raffles";
    public static final String KEY_RAFFLE_ID   ="raffle_id";
    public static final String KEY_NAME   ="name";
    public static final String KEY_DESC   ="desc";
    public static final String KEY_WINNERS   ="winners";

    public static final String CREATE_STATEMENT  = "CREATE TABLE "
            + TABLE_NAME + "     ("
            + KEY_RAFFLE_ID + " integer primary key autoincrement not null, "
            + KEY_NAME + " string not null, "
            + KEY_WINNERS + " int DEFAULT 0, "
            + KEY_DESC + " string "
            + ");";

    public static void insert(SQLiteDatabase db, Raffle r){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, r.getName());
        values.put(KEY_DESC, r.getDescription());

        db.insert(TABLE_NAME, null, values);
        Cursor c = db.rawQuery("SELECT * FROM Raffles", null);
    }

    public static Raffle createFromCursor(Cursor c) {
        if (c == null || c.isAfterLast() || c.isBeforeFirst()) {
            return null;
        }
        else {
            Raffle r = new Raffle();
            r.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            r.setDescription(c.getString(c.getColumnIndex(KEY_DESC)));
            r.setRaffleID(c.getInt(c.getColumnIndex(KEY_RAFFLE_ID)));
            r.setWinners(c.getInt(c.getColumnIndex(KEY_WINNERS)));

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
        values.put(KEY_DESC, r.getDescription());
        values.put(KEY_WINNERS, r.getWinners());

        db.update(TABLE_NAME, values, KEY_RAFFLE_ID+"= ?",
                new String[]{ "" + r.getmRaffleID()});
    }
}

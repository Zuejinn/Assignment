package au.edu.utas.mekvall.assignment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RaffleTable {
    public static final String TABLE_NAME       ="Raffles";
    public static final String KEY_RAFFLE_ID    ="raffle_id";
    public static final String KEY_NAME         ="name";
    public static final String KEY_DESC         ="desc";
    public static final String KEY_WINNERS      ="winners";
    public static final String KEY_PERSON       = "person";
    public static final String KEY_LOCATION     = "location";
    public static final String KEY_START        = "start";
    public static final String KEY_END          = "end";
    public static final String KEY_MAX          = "max";
    public static final String KEY_RAFFLING     = "raffling";


    public static final String CREATE_STATEMENT  = "CREATE TABLE "
            + TABLE_NAME + "     ("
            + KEY_RAFFLE_ID + " integer primary key autoincrement not null, "
            + KEY_NAME + " string not null, "
            + KEY_WINNERS + " int DEFAULT 0, "
            + KEY_DESC + " string not null, "
            + KEY_PERSON + " string, "
            + KEY_LOCATION + " string, "
            + KEY_START + " long, "
            + KEY_END + " long, "
            + KEY_RAFFLING + " string, "
            + KEY_MAX + " int DEFAULT 1000 "
            + ");";

    public static void insert(SQLiteDatabase db, Raffle r){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, r.getName());
        values.put(KEY_DESC, r.getDescription());
        values.put(KEY_PERSON, r.getPerson());
        values.put(KEY_LOCATION, r.getLocation());
        values.put(KEY_START, r.getStart());
        values.put(KEY_END, r.getEnd());
        values.put(KEY_MAX, r.getMax());
        values.put(KEY_RAFFLING, r.getImageAddress());

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
            r.setPerson(c.getString(c.getColumnIndex(KEY_PERSON)));
            r.setLocation(c.getString(c.getColumnIndex(KEY_LOCATION)));
            r.setStart(c.getLong(c.getColumnIndex(KEY_START)));
            r.setEnd(c.getLong(c.getColumnIndex(KEY_END)));
            r.setMax(c.getInt(c.getColumnIndex(KEY_MAX)));
            r.setImageAddress(c.getString(c.getColumnIndex(KEY_RAFFLING)));

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

    public static void delete(SQLiteDatabase db, Raffle r) {
        String sel = KEY_RAFFLE_ID+"=?";
        int del = db.delete(TABLE_NAME, sel, new String[]{"" + r.getmRaffleID()});
    }


    public static void update(SQLiteDatabase db, Raffle r) {
        ContentValues values = new ContentValues();
        values.put(KEY_RAFFLE_ID, r.getmRaffleID());
        values.put(KEY_NAME, r.getName());
        values.put(KEY_DESC, r.getDescription());
        values.put(KEY_WINNERS, r.getWinners());
        values.put(KEY_PERSON, r.getPerson());
        values.put(KEY_LOCATION, r.getLocation());
        values.put(KEY_START, r.getStart());
        values.put(KEY_END, r.getEnd());
        values.put(KEY_MAX, r.getMax());
        values.put(KEY_RAFFLING, r.getImageAddress());

        db.update(TABLE_NAME, values, KEY_RAFFLE_ID+"= ?", new String[]{ "" + r.getmRaffleID()});
    }
}

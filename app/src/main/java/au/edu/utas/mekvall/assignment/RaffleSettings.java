package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RaffleSettings extends AppCompatActivity {
    private static Raffle current;

    public static Raffle getCurrent() {
        return current;
    }

    public static void setCurrent(Raffle r) {
        current = r;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle_settings);
    }

    public void deleteRaffle(View view) {
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();
        Raffle r = RaffleMenu.getCurrent();
        RaffleTable.delete(db, r);

        openRaffleList(view);
    }

    public void openRaffleList(View view) {
        startActivity(new Intent(RaffleSettings.this, RaffleList.class));
    }

}

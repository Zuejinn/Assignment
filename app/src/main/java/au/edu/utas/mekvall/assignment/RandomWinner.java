package au.edu.utas.mekvall.assignment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class RandomWinner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_winner);

        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        final ArrayList<Ticket> tickets = TicketTable.selectRaffleTickets(db, RaffleMenu.getCurrent());

        // reduce array until non winner has been found
        Ticket win_ticket;
        do {
            Random r = new Random();
            int windex = r.nextInt(tickets.size());
            win_ticket = tickets.get(windex);
            tickets.remove(win_ticket);

            Log.d("WIN INT: ", ""+win_ticket.getWinner_place());
            if(win_ticket.getWinner_place() == 0) {
                win_ticket.setWinner_place(1);
            }
        } while (win_ticket.getWinner_place() == 0);

        TicketTable.update(db, win_ticket); // push changes to db

        TextView lblWin = findViewById(R.id.lblWinTickNum);
        lblWin.setText("" + win_ticket.getNumber());

        TextView lblWinName = findViewById(R.id.lblWinTickName);
        lblWinName.setText("" + win_ticket.getName());

    }
}

package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TicketList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        setTitle("Ticket List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();


        //List parts!
        ListView ticketsList = findViewById(R.id.ticketListings);

        final ArrayList<Ticket> tickets = TicketTable.selectRaffleTickets(db, RaffleMenu.getCurrent());

        final TicketAdapter ticketAdapter;
        ticketAdapter = new TicketAdapter(getApplicationContext(), R.layout.ticket_listing, tickets);
        ticketsList.setAdapter(ticketAdapter);

        ticketsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Ticket t = tickets.get(i);

                //TicketMenu.setCurrent(t);
                //startActivity(new Intent(TicketList.this, TicketMenu.class));

            }
        });
    }
}

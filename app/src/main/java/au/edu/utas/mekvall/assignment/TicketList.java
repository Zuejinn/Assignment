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

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();


        //List parts!
        ListView ticketsList = findViewById(R.id.ticketListings);

        Ticket ticket1 = new Ticket();
        ticket1.setName("Big Bash Charity Ticket");
        ticket1.setRaffle_id(2);
        ticket1.setName("Joe Wiz");
        ticket1.setNumber(69);

        Ticket ticket2 = new Ticket();
        ticket2.setName("Carpet Ticket");
        ticket2.setRaffle_id(1);
        ticket2.setName("Elmer Fudd");
        ticket2.setNumber(32);

        TicketTable.insert(db, ticket1);
        TicketTable.insert(db, ticket2);


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

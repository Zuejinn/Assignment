package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class TicketList extends AppCompatActivity {

    private ArrayList<Ticket> tickets;

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

        Ticket ticket1 = new Ticket();
        ticket1.setName("Big Bash Charity Ticket");
        ticket1.setRaffle_id(RaffleMenu.getCurrent().getmRaffleID());
        ticket1.setName("Joe Wiz");
        ticket1.setNumber(123);

        Ticket ticket2 = new Ticket();
        ticket2.setName("Carpet Ticket");
        ticket2.setRaffle_id(RaffleMenu.getCurrent().getmRaffleID());
        ticket2.setName("Elmer Fudd");
        ticket2.setDate(Calendar.getInstance().getTimeInMillis());
        ticket2.setNumber(1);

        TicketTable.insert(db, ticket1);
        TicketTable.insert(db, ticket2);

        RadioButton radNum = findViewById(R.id.radNum);
        radNum.toggle();

        tickets = TicketTable.selectRaffleTickets(db, RaffleMenu.getCurrent());

        final TicketAdapter ticketAdapter;
        ticketAdapter = new TicketAdapter(getApplicationContext(), R.layout.ticket_listing, tickets);
        ticketsList.setAdapter(ticketAdapter);

        ticketsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Ticket t = tickets.get(i);

                TicketMenu.setCurrent(t);
                startActivity(new Intent(TicketList.this, TicketMenu.class));

            }
        });

    }


    public void sortName(View view) {
        Collections.sort(tickets, new Comparator<Ticket>(){

            @Override
            public int compare(Ticket o1, Ticket o2) {
                if (o1.getName().compareTo(o2.getName()) <= 0) {
                    return -1;
                } else return 1;
            }
        });
        ListView myList = findViewById(R.id.ticketListings);
        final TicketAdapter ticketAdapter;
        ticketAdapter = new TicketAdapter(getApplicationContext(), R.layout.ticket_listing, tickets);
        myList.setAdapter(ticketAdapter);
        ticketAdapter.notifyDataSetChanged();
    }

    public void sortNum(View view) {
        Collections.sort(tickets, new Comparator<Ticket>(){

            @Override
            public int compare(Ticket o1, Ticket o2) {
                if (o1.getNumber() <= o2.getNumber()) {
                    return -1;
                } else return 1;
            }
        });
        ListView myList = findViewById(R.id.ticketListings);
        final TicketAdapter ticketAdapter;
        ticketAdapter = new TicketAdapter(getApplicationContext(), R.layout.ticket_listing, tickets);
        myList.setAdapter(ticketAdapter);
        ticketAdapter.notifyDataSetChanged();
    }


}

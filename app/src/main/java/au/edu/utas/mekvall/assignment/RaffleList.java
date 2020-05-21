package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RaffleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle_list);

        setTitle("Raffle List");

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        //List parts!
        ListView myList = findViewById(R.id.raffleListings);

        //db.execSQL("DELETE FROM Raffles");

        Raffle raffle1 = new Raffle();
        raffle1.setName("Big Bash Charity Raffle");
        raffle1.setDescription("Raffle for my boiz");

        Raffle raffle2 = new Raffle();
        raffle2.setName("Carpet Raffle");
        raffle2.setDescription("Who wants some carpet????");

        RaffleTable.insert(db, raffle1);
        RaffleTable.insert(db, raffle2);



        final ArrayList<Raffle> raffles = RaffleTable.selectAll(db);

        final RaffleAdapter raffleAdapter;
        raffleAdapter = new RaffleAdapter(getApplicationContext(), R.layout.raffle_listing, raffles);
        myList.setAdapter(raffleAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Raffle r = raffles.get(i);

                RaffleMenu.setCurrent(r);
                startActivity(new Intent(RaffleList.this, RaffleMenu.class));

            }
        });
    }
}

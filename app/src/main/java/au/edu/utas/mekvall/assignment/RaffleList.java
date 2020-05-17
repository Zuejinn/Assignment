package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RaffleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();


        //List parts!
        ListView myList = findViewById(R.id.myList);

        Raffle raffle1 = new Raffle();
        raffle1.setName("Big Bash Charity Raffle");

        Raffle raffle2 = new Raffle();
        raffle2.setName("Carpet Raffle");

        RaffleTable.insert(db, raffle1);
        RaffleTable.insert(db, raffle2);


        final ArrayList<Raffle> raffles = RaffleTable.selectAll(db);

        for(int i = 0; i < raffles.size(); i++) {
            Log.d("" + raffles.get(i).getmRaffleID(), raffles.get(i).getName());
        }
        Log.d("ENTRIES ", "" + raffles.size());

        final RaffleAdapter raffleAdapter =
                new RaffleAdapter(getApplicationContext(), R.layout.my_list_item, raffles);
        myList.setAdapter(raffleAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Raffle p = raffles.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(RaffleList.this);
                builder.setTitle("Update Property Address");

                final EditText input = new EditText(RaffleList.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setText(p.getName());
                builder.setView(input);

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        p.setName(input.getText().toString());

                        RaffleTable.update(db, p);
                        raffleAdapter.notifyDataSetChanged();
                    }
                });

                builder.create().show();
            }
        });
    }
}

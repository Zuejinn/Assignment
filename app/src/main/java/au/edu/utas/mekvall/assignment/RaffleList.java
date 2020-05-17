package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class RaffleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle_list);

        Database databaseconnection = new Database(this);
        final SQLiteDatabase db = databaseconnection.open();

        ListView myList = findViewById(R.id.myList);

        final RaffleAdapter propertyListAdapter = new RaffleAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, properties);
        myList.setAdapter(RaffleAdapter);
    }
}

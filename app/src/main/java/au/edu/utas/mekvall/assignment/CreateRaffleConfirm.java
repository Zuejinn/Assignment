package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateRaffleConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_raffle_confirm);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        TextView lblEnteredText = findViewById(R.id.lblEnteredName);

        // Initilises the variables with the data user entered on the previous page
        Bundle extras = getIntent().getExtras();
        String enteredName = extras.getString(NewRaffle.NAME_KEY);
        String enteredDesc = extras.getString(NewRaffle.DESC_KEY);

        // sets the title of the page to the new raffle's name
        lblEnteredText.setText(enteredName);

        // Enters the users data into the database
        Raffle raffle = new Raffle();
        raffle.setName(enteredName);
        raffle.setDescription(enteredDesc);
        RaffleTable.insert(db, raffle);

        // Return Button, returns to the main menu
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateRaffleConfirm.this, MainActivity.class);
                startActivity(i);
            }
        });

        // New Raffle Button, Returns to the add raffle page to create a raffle
        Button btnAnother = findViewById(R.id.btnAnother);
        btnAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateRaffleConfirm.this, NewRaffle.class);
                startActivity(i);
            }
        });
    }


}

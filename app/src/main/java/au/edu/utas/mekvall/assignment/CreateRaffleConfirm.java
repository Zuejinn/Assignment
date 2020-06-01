package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

public class CreateRaffleConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_raffle_confirm);
        setTitle("Success!!!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        TextView lblEnteredText = findViewById(R.id.lblEnteredName);

        // Initilises the variables with the data user entered on the previous page
        Bundle extras = getIntent().getExtras();
        String enteredName = extras.getString(NewRaffle.NAME_KEY);
        String enteredDesc = extras.getString(NewRaffle.DESC_KEY);
        String[] commands = extras.getString(NewRaffle.COMMANDS_KEY).split(",");

        // sets the title of the page to the new raffle's name
        lblEnteredText.setText("Raffle: " + enteredName);

        // Enters the users data into the database
        Raffle raffle = new Raffle();
        raffle.setName(enteredName);
        raffle.setDescription(enteredDesc);
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equals("PERSON")) {
                String person = extras.getString(NewRaffle.PERSON_KEY);
                raffle.setPerson(person);
            }
            if (commands[i].equals("LOCATION")) {
                String location = extras.getString(NewRaffle.LOCATION_KEY);
                raffle.setLocation(location);
            }
            if (commands[i].equals("START")) {
                long start = extras.getLong(NewRaffle.START_KEY);
                raffle.setStart(start);
            }
            if (commands[i].equals("END")) {
                long end = extras.getLong(NewRaffle.END_KEY);
                raffle.setEnd(end);
            }
            if (commands[i].equals("MAX")) {
                int max = extras.getInt(NewRaffle.MAX_KEY);
                raffle.setMax(max);

            }
            if (commands[i].equals("RAFFLEURI")) {
                String raffleURI = extras.getString(NewRaffle.RAFFLEURI_KEY);
                raffle.setImageAddress(raffleURI);

                Uri selectedImageUri = Uri.parse(raffleURI);

                ImageView imgRaffle = findViewById(R.id.raffleImageView);
                imgRaffle.setImageURI(selectedImageUri);
            }
        }
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

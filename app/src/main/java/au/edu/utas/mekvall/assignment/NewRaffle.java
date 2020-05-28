package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NewRaffle extends AppCompatActivity {
    public static String NAME_KEY = "RAFFLENAME";
    public static String DESC_KEY = "DESC";
    public static String PERSON_KEY = "PERSON";
    public static String LOCATION_KEY = "LOCATION";
    public static String START_KEY = "START";
    public static String END_KEY = "END";
    public static String MAX_KEY = "MAX";
    public static String COMMANDS_KEY = "COMMANDS";
    private EditText inputRaffleName;
    private EditText inputDesc;
    private EditText inputPerson;
    private EditText inputLocation;
    private EditText inputStart;
    private EditText inputEnd;
    private EditText inputMaximumTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_raffle);
        setTitle("New Raffle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputRaffleName = findViewById(R.id.inputRaffleName);
        inputDesc = findViewById(R.id.inputDesc);
        inputPerson = findViewById(R.id.inputPerson);
        inputLocation = findViewById(R.id.inputLocation);
        inputStart = findViewById(R.id.inputStart);
        inputEnd = findViewById(R.id.inputEnd);
        inputMaximumTickets = findViewById(R.id.inputMaximumTickets);

        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputRaffleName.length() > 0 && inputDesc.length() > 0) {
                    Intent i = new Intent(NewRaffle.this, CreateRaffleConfirm.class);

                    String saleCommands = "";
                    String name = inputRaffleName.getText().toString();
                    String desc = inputDesc.getText().toString();
                    i.putExtra(NAME_KEY, name);
                    i.putExtra(DESC_KEY, desc);

                    if (inputPerson.length() > 0) {
                        String person = inputPerson.getText().toString();
                        i.putExtra(PERSON_KEY, person);
                        saleCommands += "PERSON,";
                    }
                    if (inputLocation.length() > 0) {
                        String location = inputLocation.getText().toString();
                        i.putExtra(LOCATION_KEY, location);
                        saleCommands += "LOCATION,";
                    }
                    if (inputStart.length() > 0) {
                        long start = Long.parseLong(inputStart.getText().toString());
                        i.putExtra(START_KEY, start);
                        saleCommands += "START,";
                    }
                    if (inputEnd.length() > 0) {
                        long end = Long.parseLong(inputEnd.getText().toString());
                        i.putExtra(END_KEY, end);
                        saleCommands += "END,";
                    }
                    if (inputMaximumTickets.length() > 0) {
                        int max = Integer.parseInt(inputMaximumTickets.getText().toString());
                        i.putExtra(MAX_KEY, max);
                        saleCommands += "MAX,";
                    } else {
                        int max = 1000;
                        i.putExtra(MAX_KEY, max);
                        saleCommands += "MAX,";
                    }

                    i.putExtra(COMMANDS_KEY, saleCommands);
                    startActivity(i);

                } else {
                    //popup error message to fill in name and desc
                }
            }
        });
    }
}

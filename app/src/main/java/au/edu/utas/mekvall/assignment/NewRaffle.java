package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class NewRaffle extends AppCompatActivity {
    public static String NAME_KEY = "RAFFLENAME";
    public static String DESC_KEY = "DESC";
    public static String PERSON_KEY = "PERSON";
    public static String LOCATION_KEY = "LOCATION";
    public static String START_KEY = "START";
    public static String END_KEY = "END";
    private EditText inputRaffleName;
    private EditText inputDesc;
    private EditText inputPerson;
    private EditText inputLocation;
    private EditText inputStart;
    private EditText inputEnd;

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

        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputRaffleName.length() > 0 && inputDesc.length() > 0) {
                    String name = inputRaffleName.getText().toString();
                    String desc = inputDesc.getText().toString();
                    String person = inputPerson.getText().toString();
                    String location = inputLocation.getText().toString();
                    String start = inputStart.getText().toString();
                    String end = inputEnd.getText().toString();

                    Intent i = new Intent(NewRaffle.this, CreateRaffleConfirm.class);
                    i.putExtra(NAME_KEY, name);
                    i.putExtra(DESC_KEY, desc);

                    startActivity(i);

                } else {
                    //popup error message to fill in name and desc
                }
            }
        });
    }
}

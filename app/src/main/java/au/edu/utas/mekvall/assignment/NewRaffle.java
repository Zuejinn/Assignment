package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewRaffle extends AppCompatActivity {
    public static String USERNAME_KEY = "USERNAME";
    public static String RAFFLENAME_KEY = "RAFFLENAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_raffle);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewRaffle.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputRaffleName = findViewById(R.id.inputRaffleName);
                EditText inputMarginMax = findViewById(R.id.inputMarginMax);
                EditText inputMarginMin = findViewById(R.id.inputMarginMin);
                EditText inputMaximum = findViewById(R.id.inputMaximum);
                EditText inputTickets = findViewById(R.id.inputTickets);

                String enteredName = inputRaffleName.getText().toString();
                String enteredMarginMax = inputMarginMax.getText().toString();
                String enteredMarginMin = inputMarginMin.getText().toString();
                String enteredMaximum = inputMaximum.getText().toString();
                String enteredTickets = inputTickets.getText().toString();

                Intent i = new Intent(NewRaffle.this, CreateRaffleConfirm.class);
                i.putExtra(RAFFLENAME_KEY, enteredName);
                i.putExtra(USERNAME_KEY, enteredMarginMax);
                i.putExtra(USERNAME_KEY, enteredMarginMin);
                i.putExtra(USERNAME_KEY, enteredMaximum);
                i.putExtra(USERNAME_KEY, enteredTickets);
                startActivity(i);
            }
        });
    }
}

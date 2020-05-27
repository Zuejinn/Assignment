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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_raffle);
        setTitle("New Raffle");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnConfirm = findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText inputRaffleName = findViewById(R.id.inputRaffleName);
                String enteredName = inputRaffleName.getText().toString();
                EditText inputDesc = findViewById(R.id.inputDesc);
                String enteredDesc = inputDesc.getText().toString();


                Intent i = new Intent(NewRaffle.this, CreateRaffleConfirm.class);
                i.putExtra(NAME_KEY, enteredName);
                i.putExtra(DESC_KEY, enteredDesc);
                startActivity(i);
            }
        });


    }
}

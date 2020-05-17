package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateRaffleConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_raffle_confirm);

        TextView lblEnteredText = findViewById(R.id.lblEnteredName);
        Bundle extras = getIntent().getExtras();

        lblEnteredText.setText(extras.getString(NewRaffle.RAFFLENAME_KEY));

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateRaffleConfirm.this, MainActivity.class);
                startActivity(i);
            }
        });

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

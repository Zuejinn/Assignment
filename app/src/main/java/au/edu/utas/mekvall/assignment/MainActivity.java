package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNewRaffleActivity = findViewById(R.id.btnNewRaffleActivity);
        Button btnOngoing = findViewById(R.id.btnOngoing);

        btnNewRaffleActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewRaffle.class);
                startActivity(i);
            }
        });


        btnOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RaffleList.class);
                startActivity(i);
            }
        });

    }


}

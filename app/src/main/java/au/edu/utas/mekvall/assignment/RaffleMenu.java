package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RaffleMenu extends AppCompatActivity {
    private static Raffle current;

    public static Raffle getCurrent() {
        return current;
    }

    public static void setCurrent(Raffle r) {
        current = r;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle_menu);
        setTitle("Menu: " + current.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnSalePage = findViewById(R.id.btnSalePage);

        btnSalePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaleWindow.setCurrent(current);
                Intent i = new Intent(RaffleMenu.this, SaleWindow.class);
                startActivity(i);
            }
        });
    }

    public void openTicketList(View view) {
        startActivity(new Intent(RaffleMenu.this, TicketList.class));
    }

    public void drawWinner(View view) {
        startActivity(new Intent(RaffleMenu.this, RandomWinner.class));
    }

    public void openRaffleSettings(View view) {
        startActivity(new Intent(RaffleMenu.this, RaffleSettings.class));
    }


}

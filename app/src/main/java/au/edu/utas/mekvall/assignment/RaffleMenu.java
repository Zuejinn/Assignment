package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;

public class RaffleMenu extends AppCompatActivity {
    private static Raffle current;

    public static Raffle getCurrent() {
        return current;
    }

    public static void setCurrent(Raffle r) {
        current = r;
    }
    private TextView lblRemainingNumber;
    private ImageView imageSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle_menu);
        setTitle("Raffle: " + current.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lblRemainingNumber = findViewById(R.id.lblRemainingNumber);
        lblRemainingNumber.setText(String.valueOf(current.getMax()));
        imageSelected = findViewById(R.id.imageViewRaffle);

        if (current.getImageAddress() != null) {
            String test = current.getImageAddress();
            Uri fromFileImage = Uri.parse(test);
            imageSelected.setImageURI(fromFileImage);
        }

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

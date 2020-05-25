package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class SaleWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_window);

        Bundle extras = getIntent().getExtras();

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        Button btnBack = findViewById(R.id.btnBack);
        Button btnPurchase = findViewById(R.id.btnPurchase);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SaleWindow.this, RaffleList.class);
                startActivity(i);
            }
        });

        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView inputPrice = findViewById(R.id.inputPrice);
                TextView inputNumber = findViewById(R.id.inputNumber);
                TextView inputMobile = findViewById(R.id.inputMobile);
                TextView inputName = findViewById(R.id.inputName);


                int cost = Integer.parseInt(inputPrice.getText().toString());
                int number = Integer.parseInt(inputNumber.getText().toString());
                int mobile = Integer.parseInt(inputMobile.getText().toString());
                String name = inputName.getText().toString();
                Date date = new Date();

                // Enters the users data into the database
                Ticket ticket = new Ticket();
                ticket.setName(name);
                ticket.setNumber(number);
                ticket.setPhone(mobile);
                ticket.setPrice(cost);
                ticket.setDate(date);

                TicketTable.insert(db, ticket);

                inputMobile.setText("");
                inputName.setText("");
                inputNumber.setText("");
                inputPrice.setText("");
            }
        });


    }
}

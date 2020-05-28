package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class CreateSaleConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sale_confirm);
        setTitle("Success!!!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        TextView lblSaleName = findViewById(R.id.lblSaleName);
        TextView lblSaleNumber = findViewById(R.id.lblSaleNumber);
        TextView lblSalePrice = findViewById(R.id.lblSalePrice);

        // Initilises the variables with the data user entered on the previous page
        Bundle extras = getIntent().getExtras();
        String saleName = extras.getString(SaleWindow.SALENAME_KEY);
        int saleNumber = extras.getInt(SaleWindow.SALENUM_KEY);
        int saleMobile = extras.getInt(SaleWindow.SALEMOBILE_KEY);
        int saleCost = extras.getInt(SaleWindow.SALECOST_KEY);
        int saleRaffleID = extras.getInt(SaleWindow.SALERAFFLEID_KEY);
        int saleQuantity =extras.getInt(SaleWindow.SALEQUANTITY_KEY);

        // sets the title of the page to the new raffle's name
        lblSaleName.setText(saleName);
        lblSaleNumber.setText("Ticket:" + String.valueOf(saleNumber));
        lblSalePrice.setText("Total: $" + String.valueOf(saleCost));


        // Enters the users data into the database
        Ticket ticket = new Ticket();
        Date date = new Date();

        // Enters the users data into the database
        ticket.setName(saleName);

        ticket.setRaffle_id(saleRaffleID);
        ticket.setPhone(saleMobile);
        ticket.setDate(date);
        ticket.setPrice(saleCost);
        for (int i = 0; i < saleQuantity; i++) {
            ticket.setNumber(saleNumber + i);
            TicketTable.insert(db, ticket);
        }


        // Return Button, returns to the main menu
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateSaleConfirm.this, MainActivity.class);
                startActivity(i);
            }
        });

        // New Raffle Button, Returns to the add raffle page to create a raffle
        Button btnAnother = findViewById(R.id.btnAnother);
        btnAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateSaleConfirm.this, SaleWindow.class);
                startActivity(i);
            }
        });
    }


}

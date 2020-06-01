package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
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

        final TextView lblSaleName = findViewById(R.id.lblSaleName);
        final TextView lblSaleNumber = findViewById(R.id.lblSaleNumber);
        final TextView lblSalePrice = findViewById(R.id.lblSalePrice);
        TextView lblSaleMobile = findViewById(R.id.lblSaleMobile);

        // Initilises the variables with the data user entered on the previous page
        Bundle extras = getIntent().getExtras();
        final String saleName = extras.getString(SaleWindow.SALENAME_KEY);
        final int saleNumber = extras.getInt(SaleWindow.SALENUM_KEY);
        final int saleMobile = extras.getInt(SaleWindow.SALEMOBILE_KEY);
        final int saleCost = extras.getInt(SaleWindow.SALECOST_KEY);
        final int saleRaffleID = extras.getInt(SaleWindow.SALERAFFLEID_KEY);
        final int saleQuantity =extras.getInt(SaleWindow.SALEQUANTITY_KEY);

        // sets the title of the page to the new raffle's name
        lblSaleName.setText("Customer Name: " + saleName);
        lblSaleMobile.setText("Mobile Number: " + saleMobile);
        lblSaleNumber.setText("Ticket: " + String.valueOf(saleNumber));
        lblSalePrice.setText("Total: $" + String.valueOf(saleCost * saleQuantity));


        // Enters the users data into the database
        Ticket ticket = new Ticket();
        final Date date = new Date();

        long ms =  Calendar.getInstance().getTimeInMillis();

        // Enters the users data into the database
        ticket.setName(saleName);

        ticket.setRaffle_id(saleRaffleID);
        ticket.setPhone(saleMobile);
        ticket.setDate(ms);
        ticket.setPrice(saleCost);
        for (int i = 0; i < saleQuantity; i++) {
            ticket.setNumber(saleNumber + i);
            TicketTable.insert(db, ticket);
        }

        // New Raffle Button, Returns to the add raffle page to create a raffle
        Button btnAnother = findViewById(R.id.btnAnother);
        btnAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateSaleConfirm.this, SaleWindow.class);
                startActivity(i);
            }
        });

        // Shares a text field with another application
        // Reference: https://developer.android.com/training/sharing/send#java
        Button btnSendText = findViewById(R.id.btnSendText);
        btnSendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, saleName + "," + "Ticket " + String.valueOf(saleNumber) + " -> " + (saleNumber + saleQuantity) + ", Mobile: " + saleMobile + ", $" + String.valueOf(saleCost * saleQuantity) + ", Purchased " + date.toString());
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
    }


}

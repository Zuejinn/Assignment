package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class SaleWindow extends AppCompatActivity {
    private static Raffle saleCurrent;
    public static String SALENAME_KEY = "SALENAME";
    public static String SALENUM_KEY = "SALENUM";
    public static String SALEMOBILE_KEY = "MOBILE";
    public static String SALECOST_KEY = "COST";
    public static String SALERAFFLEID_KEY = "RAFFLEID";

    public static Raffle getCurrent() {
        return saleCurrent;
    }

    public static void setCurrent(Raffle r) {
        saleCurrent = r;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_window);
        setTitle("Ticket Sale: " + saleCurrent.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnPurchase = findViewById(R.id.btnPurchase);
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

                Intent i = new Intent(SaleWindow.this, CreateSaleConfirm.class);
                i.putExtra(SALENAME_KEY, name);
                i.putExtra(SALENUM_KEY, number);
                i.putExtra(SALEMOBILE_KEY, mobile);
                i.putExtra(SALECOST_KEY, cost);
                i.putExtra(SALERAFFLEID_KEY, saleCurrent.getmRaffleID());
                startActivity(i);
            }
        });


    }
}

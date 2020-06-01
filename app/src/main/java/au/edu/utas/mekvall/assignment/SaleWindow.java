package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SaleWindow extends AppCompatActivity {
    private static Raffle saleCurrent;
    public static String SALENAME_KEY = "SALENAME";
    public static String SALENUM_KEY = "SALENUM";
    public static String SALEMOBILE_KEY = "MOBILE";
    public static String SALECOST_KEY = "COST";
    public static String SALERAFFLEID_KEY = "RAFFLEID";
    public static String SALEQUANTITY_KEY = "QUANTITY";
    private TextView inputPrice;
    private TextView inputNumber;
    private TextView inputMobile;
    private TextView inputName;
    private TextView inputQuantity;
    private TextView lblCost;
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
        inputPrice = findViewById(R.id.inputPrice);
        inputNumber = findViewById(R.id.inputNumber);
        inputMobile = findViewById(R.id.inputMobile);
        inputName = findViewById(R.id.inputName);
        inputQuantity = findViewById(R.id.inputQuantity);
        lblCost = findViewById(R.id.lblCost);

        inputQuantity.addTextChangedListener(textWatcher);
        inputPrice.addTextChangedListener(textWatcher);

        lblCost.setText("Total Cost: $0");

        Button btnPurchase = findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (inputName.length() > 0 && inputMobile.length() > 0 && inputNumber.length() > 0 && inputPrice.length() > 0 && inputQuantity.length() > 0) {
                    int cost = Integer.parseInt(inputPrice.getText().toString());
                    int number = Integer.parseInt(inputNumber.getText().toString());
                    int mobile = Integer.parseInt(inputMobile.getText().toString());
                    int quantity = Integer.parseInt(inputQuantity.getText().toString());
                    String name = inputName.getText().toString();

                    Intent i = new Intent(SaleWindow.this, CreateSaleConfirm.class);
                    i.putExtra(SALENAME_KEY, name);
                    i.putExtra(SALENUM_KEY, number);
                    i.putExtra(SALEMOBILE_KEY, mobile);
                    i.putExtra(SALECOST_KEY, cost);
                    i.putExtra(SALERAFFLEID_KEY, saleCurrent.getmRaffleID());
                    i.putExtra(SALEQUANTITY_KEY, quantity);
                    startActivity(i);
                } else {
                    //popup error message
                }


            }
        });
    }

    // Reference: https://stackoverflow.com/questions/29672982/how-can-i-call-a-function-as-i-type-in-an-android-edittext
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            if (inputPrice.length() > 0 && inputQuantity.length() > 0) {
                lblCost.setText("Total Cost: $" + String.valueOf(Integer.parseInt(inputQuantity.getText().toString()) * Integer.parseInt(inputPrice.getText().toString())) );
            }else{

            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


}

package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TicketMenu extends AppCompatActivity {
    private static Ticket current;

    public static Ticket getCurrent() {
        return current;
    }

    public static void setCurrent(Ticket r) {
        current = r;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Ticket Menu");


        TextView textNum = findViewById(R.id.lblTicketNum);
        TextView textWin = findViewById(R.id.lblTicketWinner);
        TextView textDate = findViewById(R.id.lblTicketDate);
        EditText editName = findViewById(R.id.editTicketName);
        EditText editNumber = findViewById(R.id.editTicketDesc);

        textNum.setText("" + current.getNumber());
        editName.setText(current.getName());
        editNumber.setText("" + current.getPhone());
        textDate.setText("" + current.getDateString());
        if (current.getWinner_place() == 0) textWin.setText("No");
        else textWin.setText("Yes");
    }

    public void saveTicket(View v) {
        EditText editName = findViewById(R.id.editTicketName);
        EditText editDesc = findViewById(R.id.editTicketDesc);

        current.setName(editName.getText().toString());
        current.setPhone(Integer.parseInt(editDesc.getText().toString()));

        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        TicketTable.update(db, current);
        openTicketList(v);
    }

    public void openTicketList(View v) {
        startActivity(new Intent(TicketMenu.this, TicketList.class));
    }
}

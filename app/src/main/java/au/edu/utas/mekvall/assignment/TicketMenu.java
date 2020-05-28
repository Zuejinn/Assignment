package au.edu.utas.mekvall.assignment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textNum = findViewById(R.id.lblTicketNum);
        EditText editName = findViewById(R.id.editTicketName);
        EditText editNumber = findViewById(R.id.editTicketDesc);

        textNum.setText("" + current.getNumber());
        editName.setText(current.getName());
        editNumber.setText("" + current.getPhone());
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

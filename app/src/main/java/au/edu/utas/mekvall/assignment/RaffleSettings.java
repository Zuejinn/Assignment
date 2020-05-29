package au.edu.utas.mekvall.assignment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class RaffleSettings extends AppCompatActivity {
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
        setContentView(R.layout.activity_raffle_settings);
        setTitle("Settings: " + RaffleMenu.getCurrent().getName());

        Raffle r = RaffleMenu.getCurrent();
        EditText editName = findViewById(R.id.editRaffleName);
        EditText editDesc = findViewById(R.id.editRaffleDesc);

        editName.setText(r.getName());
        editDesc.setText(r.getDescription());
    }

    public void deleteRaffle(View view) {
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();
        Raffle r = RaffleMenu.getCurrent();

        int tickets = TicketTable.selectRaffleTickets(db, r).size();

        if(tickets > 0) {
            DialogFragment delAlert = new cantDelete();
            delAlert.show(getSupportFragmentManager(), "Can't Delete");
        } else {
            RaffleTable.delete(db, r);
            openRaffleList(view);
        }

    }
    public void openRaffleList(View view) {
        startActivity(new Intent(RaffleSettings.this, RaffleList.class));
    }
    public void openRaffleMenu(View view) {
        startActivity(new Intent(RaffleSettings.this, RaffleMenu.class));
    }

    // Alert Dialogue
    public static class cantDelete extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Raffles which have been started cannot be deleted")
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            return builder.create();
        }
    }

    public void saveRaffle(View v) {
        Raffle r = RaffleMenu.getCurrent();

        EditText editName = findViewById(R.id.editRaffleName);
        EditText editDesc = findViewById(R.id.editRaffleDesc);

        r.setName(editName.getText().toString());
        r.setDescription(editDesc.getText().toString());

        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        RaffleTable.update(db, r);
        openRaffleMenu(v);
    }


}

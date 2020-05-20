package au.edu.utas.mekvall.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        TextView title = findViewById(R.id.inputRaffleName);
        setTitle("Menu: " + current.getName());
    }
}

package au.edu.utas.mekvall.assignment;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class TicketAdapter extends ArrayAdapter<Ticket> {
    private int mLayoutResourceID;
    public TicketAdapter(Context context, int resource, ArrayList<Ticket> objects){
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        Ticket t = this.getItem(position);
        TextView lblName = row.findViewById(R.id.lblName);

        lblName.setTextColor(Color.BLACK);
        lblName.setText(t.getRaffle_id() + ": " + t.getName());
        TextView lblNum = row.findViewById(R.id.lblNum);
        lblNum.setTextColor(Color.BLACK);
        lblNum.setText("" + t.getNumber());

        return row;
    }
}

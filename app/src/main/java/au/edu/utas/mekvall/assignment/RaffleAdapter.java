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


public class RaffleAdapter extends ArrayAdapter<Raffle> {
    private int mLayoutResourceID;
    public RaffleAdapter(Context context, int resource, ArrayList<Raffle> objects){
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        Raffle r = this.getItem(position);
        TextView lblName = row.findViewById(R.id.lblName);
        lblName.setText(r.getName());
        lblName.setTextColor(Color.BLACK);
        lblName.setText(r.getName());
        TextView lblDesc = row.findViewById(R.id.lblDesc);
        lblDesc.setText(r.getDescription());
        lblDesc.setTextColor(Color.BLACK);

        return row;
    }
}

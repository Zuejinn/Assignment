package au.edu.utas.mekvall.assignment;

import android.app.Service;
import android.content.Context;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class RaffleAdapter extends ArrayAdapter<Property> {
    private int mLayoutResourceID;
    public RaffleAdapter(Context context, int resource, List<Property> objects){
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        Raffle r = this.getItem(position);
        TextView textView = row.findViewById(android.R.id.text1);
        textView.setText(r.getmRaffleID() + ": " + r.getName());

        return row;
    }
}

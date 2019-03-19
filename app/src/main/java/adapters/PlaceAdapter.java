package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.placestovisit.R;

import java.util.ArrayList;
import java.util.List;

import models.Places;

public class PlaceAdapter extends ArrayAdapter<Places> {

    Context mContext;
    List<Places> places;

    public PlaceAdapter(Context context, List<Places> places) {
        super(context, R.layout.place_item, places);

        this.places = places;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.place_item, parent, false);
        }

        Places place = getItem(position);
        // txtNetworkRowName
        TextView name = (TextView) convertView.findViewById(R.id.txtNetworkRowName);
        name.setText(place.getName());


        return  convertView;
    }

}

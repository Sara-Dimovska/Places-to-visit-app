package adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.placestovisit.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import models.Places;

public class PlaceAdapter extends ArrayAdapter<Places> {

    Context mContext;
    List<Places> places;

    TextView name,address;
    ImageView image;
    RatingBar stars;


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

        name = convertView.findViewById(R.id.txtNetworkRowName);
        name.setText(place.getName());

        image =convertView.findViewById(R.id.imgIcon);
        Picasso.get().load(place.getImage()).into(image);

        stars = convertView.findViewById(R.id.starBar);
        stars.setRating(3.5f);

        address = convertView.findViewById(R.id.place_address);
        address.setText(place.getAddress());

        return  convertView;
    }

    @Override
    public Places getItem(int position)
    {
        return places.get(position);
    }

}

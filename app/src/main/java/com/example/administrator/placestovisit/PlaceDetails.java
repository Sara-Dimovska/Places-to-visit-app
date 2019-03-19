package com.example.administrator.placestovisit;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import java.util.UUID;

public class PlaceDetails extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        UUID seizureId = (UUID)getIntent()
                .getSerializableExtra("UUID");

        TextView txt  =  findViewById(R.id.mtext);

        Seizure seizure = SeizureSingleton.getInstance(this).getSeizure(seizureId);


        txt.setText(seizure.getDate().toString());

    }
}
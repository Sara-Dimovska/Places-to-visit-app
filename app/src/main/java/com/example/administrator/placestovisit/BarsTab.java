package com.example.administrator.placestovisit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import adapters.PlaceAdapter;
import helpers.RetrofitClient;
import models.Places;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.BarsService;
import services.PlaceService;

public class BarsTab extends Fragment {

    List<Places> bars_list;
    ListView listViewBars;
    PlaceAdapter barAdapter;
    Context context;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_bars, container, false);
        context = getActivity().getApplicationContext();

        mSwipeRefreshLayout = rootView.findViewById(R.id.barsTab);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        listViewBars = rootView.findViewById(R.id.listViewBars);
        registerForContextMenu(listViewBars);

        populateBars();

        listViewBars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Places place = (Places) listViewBars.getAdapter().getItem(i);

                Intent intent = new Intent(context, PlaceDetailsActivity.class);
                intent.putExtra("PlaceID",place.getId());
                getActivity().startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //handling swipe refresh
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        listViewBars.setAdapter(null);
                        populateBars();
                        barAdapter.notifyDataSetChanged();
                        listViewBars.smoothScrollToPosition(0);
                    }
                }, 2000);
            }
        });
        return rootView;
    }

    public void populateBars() {
        BarsService barsAPI = RetrofitClient.getClient().create(BarsService.class);
        Call<List<Places>> getBars = barsAPI.get_bars();

        getBars.enqueue(new Callback<List<Places>>() {
            @Override
            public void onResponse(Call<List<Places>> call, Response<List<Places>> response) {
                bars_list =  response.body();
                barAdapter = new PlaceAdapter(context, bars_list);
                listViewBars.setAdapter(barAdapter);
            }

            @Override
            public void onFailure(Call<List<Places>> call, Throwable t) {
                Log.d("BARS API FAILES", t.getMessage());
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(1, v.getId(), 0, "Edit");
        menu.add(1, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item){
        if( getUserVisibleHint() == false )
        {
            return false;
        }

        PlaceService placeAPI = RetrofitClient.getClient().create(PlaceService.class);

            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int index = info.position;
            Places bar_clicked = (Places) listViewBars.getAdapter().getItem(index);


            if (item.getTitle() == "Edit") {
                Intent myIntent =  new Intent(getActivity().getApplicationContext(), EditPlaceActivity.class);
                myIntent.putExtra("Edit_ID", bar_clicked.getId());
                myIntent.putExtra("Edit_place_type", "bar");
                startActivity(myIntent);
            }

            if (item.getTitle() == "Delete") {
                final String placeName = bar_clicked.getName();
                Call<ResponseBody> deletePlace = placeAPI.delete_place(bar_clicked.getId());
                deletePlace.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(getContext(), placeName + " have been deleted", Toast.LENGTH_LONG).show();
                        populateBars();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable throwable) {

                    }
                });
            }

        return true;
    }
}

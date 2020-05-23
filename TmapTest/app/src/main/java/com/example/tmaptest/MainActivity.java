package com.example.tmaptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapTapi;
import com.skt.Tmap.TMapView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    private EditText form;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable workRunnable;
    private final long DELAY = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TMapView tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xx4620fe9b1c2445dda41257f430567fb2");

        layoutInit();

        /*
        //LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);
        tMapView.setSKTMapApiKey("l7xx4620fe9b1c2445dda41257f430567fb2");
        //linearLayoutTmap.addView( tMapView );


        String strData="노량진역";
        TMapData tmapdata = new TMapData();
        System.out.println("또이러네");
        tmapdata.findAllPOI("노량진역", new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList poiItem) {
                for(int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem item = (TMapPOIItem) poiItem.get(i);
                    Log.d("POI Name: ", item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "")  + ", " +
                            "Point: " + item.getPOIPoint().toString());
                }
            }
        });*/
    }


    private void layoutInit() {
        form=(EditText)findViewById(R.id.edt_search);
        recyclerView = (RecyclerView)findViewById(R.id.rl_listview);
        form.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String keyword = s.toString();

                handler.removeCallbacks(workRunnable);
                workRunnable = new Runnable() {
                    @Override
                    public void run() {
                       adapter.filter(keyword);
                    }
                };
                handler.postDelayed(workRunnable, DELAY);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}

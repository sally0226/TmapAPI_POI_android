package com.example.tmaptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<PosItem> itemLists = new ArrayList<>(); //SearchEntity는 어떻게 대체해야 하는가?

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView address;

        public CustomViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            address = (TextView) itemView.findViewById(R.id.item_address);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poiitem_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final int ItemPosition = position;

        holder.title.setText(itemLists.get(position).getTitle());
        holder.address.setText(itemLists.get(position).getAddress());


    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public void setData(ArrayList<PosItem> itemLists) {
        this.itemLists = itemLists;
    }



    public void filter(String keyword) {
        if (keyword.length() >= 2) {
            try{
                TmapPOI parser = new TmapPOI(this);
                parser.getAutoComplete(keyword);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}


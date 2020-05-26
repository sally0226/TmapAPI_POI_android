package com.example.tmaptest;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    private ArrayList<PosItem> itemLists = new ArrayList<>(); //SearchEntity는 어떻게 대체해야 하는가?

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView address;

        public CustomViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        PosItem clicked = itemLists.get(pos);
                        String[] splited =clicked.getAddress().toString().split(" ");
                        String add1=splited[0]; //도,시
                        String add2=splited[1]; //시군구

                        //intent 해서 SePosSetting화면으로 넘겨줘야 할듯

                        //일단 AlterDialog로 선택된 장소의 내용 확인
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("선택된 장소 :"+Integer.toString(pos)).setMessage(add1+" "+add2);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }

            });
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


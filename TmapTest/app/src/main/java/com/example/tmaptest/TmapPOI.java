package com.example.tmaptest;

import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TmapPOI  {

    private ArrayList<PosItem> mListData;
    private RecyclerViewAdapter mAdapter;

    public TmapPOI(RecyclerViewAdapter adapter) {
        this.mAdapter = adapter;
        mListData = new ArrayList<PosItem>();
    }

    public void getAutoComplete(String word) throws InterruptedException {

        //tmapview 는 메인액티비티에서 만들고 여기서 한번 불러보는걸로 해보자,, 안되면 나중에 생각해봐야지,,
        TMapData tmapdata= new TMapData();

        tmapdata.findAllPOI(word, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList poiItem) {
                //long beforeTime = System.currentTimeMillis();
                mListData.clear();
                if (poiItem.size()==0){
                    mListData.add(new PosItem("결과가 없습니다",null,null));
                }
                for(int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem item = (TMapPOIItem) poiItem.get(i);
                    /*Log.d("POI Name: ", item.getPOIName().toString() + ", " +
                            "Address: " + item.getPOIAddress().replace("null", "")  + ", " +
                            "Point: " + item.getPOIPoint().toString());*/
                    mListData.add(new PosItem(item.getPOIName().toString(),item.getPOIAddress().replace("null","").toString(),item.getPOIPoint()));
                }
                /*
                long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                long secDiffTime = (afterTime - beforeTime); //두 시간에 차 계산
                System.out.println("시간차이(ms) : "+secDiffTime);*/
            }
        }); //검색결과가 0개인경우,, 따로 처리를 해줘야하는지 확인해보기
        TimeUnit.MILLISECONDS.sleep(400);
        mAdapter.setData(mListData);
        mAdapter.notifyDataSetChanged();

    }
}

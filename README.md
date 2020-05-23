# TmapAPI_POI_android
TMapAPI를 이용한 POI검색이 가능한 android app

참고한 자료 
https://github.com/pennya/SearchView/blob/master/app/src/main/java/com/example/kjh/searchview/RecyclerViewAdapter.java
- 제일 많이 참고했으나, http connection을 이용하면서 Asynctask를 이용하신 점에서 차이가 발생하여 많이 뜯어고쳤다. 
- 또한 이 분은 v7에서 지원하는 Recycler view를 사용하셨고, 나는 androidx.recyclerview.widget.RecyclerView를 사용하였다. 

https://webnautes.tistory.com/1214
- androidx.recyclerview.widget.RecyclerView를 어떻게 사용하면 좋을지 참고하였다. 

코드 설명
MainActivity.java
- app의 메인 액티비티이다

PosItem.java
- 검색된 장소 하나하나를 담는 class

RecyclerViewAdapter.java
- RecyclerView의 Adapter 이다.

TmapPOI.java
- TmapAPI를 호출하고 결과를 받아오는 class

activity_main.xml
- 메인 레이아웃
poiitem_layout.xml
- 아이템 하나하나의 레이아웃 


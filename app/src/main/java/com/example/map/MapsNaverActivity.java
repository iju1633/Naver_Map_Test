package com.example.map;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.map.dto.ProductItem;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Align;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class MapsNaverActivity extends Activity implements OnMapReadyCallback, Overlay.OnClickListener {

    private MapView mapView;
    Button button_to_list;

    // 마커들의 정보가 들어갈 arrayList
    ArrayList<String> attraction_name = new ArrayList<>();
    ArrayList<String> address_doro = new ArrayList<>();
    ArrayList<Double> latitude = new ArrayList<>();
    ArrayList<Double> longitude = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps_naver);
        mapView = findViewById(R.id.mapView);
        mapView.getMapAsync(this);

        button_to_list = findViewById(R.id.button_to_list);

        button_to_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MapsNaverActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap){

        try {
            InputStream is = getBaseContext().getResources().getAssets().open("safe_attraction_list_2020.xls");
            Workbook wb = Workbook.getWorkbook(is);
            
            // 가져올 목록
            // column(3) : 관광명소 이름 : Marker 누르면 나오게 할 것
            // column(12) : 도로명 주소 : Toast로 나오게 할 것
            // column(20) : 위도 : 마커 찍는 용도
            // column(21) : 경도 : 마커 찍는 용도

            if(wb != null) {
                Sheet sheet = wb.getSheet(0);   // 시트 불러오기
                if(sheet != null) {
                    int colTotal = sheet.getColumns();    // 전체 컬럼
                    int rowIndexStart = 1;                  // row 인덱스 시작(0번 째는 제목들!)
                    int rowTotal = sheet.getColumn(colTotal-1).length; // 컬럼의 길이 = 행의 크기

                    StringBuilder sb;
                    for(int row=rowIndexStart;row<rowTotal;row++) {
                        sb = new StringBuilder();
                        for(int col=0;col<colTotal;col++) {
                            switch (col) {
                                case 3 :
                                    String attractions = sheet.getCell(col, row).getContents();
                                    sb.append("col"+col+" : "+attractions+" , ");
                                    attraction_name.add(attractions);
                                    break;
                                case 12 :
                                    String addresses = sheet.getCell(col, row).getContents();
                                    sb.append("col"+col+" : "+addresses+" , ");
                                    address_doro.add(addresses);
                                    break;
                                case 20 :
                                    String latitudes = sheet.getCell(col, row).getContents();
                                    sb.append("col"+col+" : "+latitudes+" , ");
                                    latitude.add(Double.parseDouble(latitudes));
                                    break;
                                case 21 : String longitudes = sheet.getCell(col, row).getContents();
                                    sb.append("col"+col+" : "+longitudes+" , ");
                                    longitude.add(Double.parseDouble(longitudes));
                                    break;
                            }
                        }
                        Log.i("test", sb.toString());
                    }

                    for(int i=0; i<latitude.size(); i++){
                        Marker marker = new Marker();
                        marker.setPosition(new LatLng(latitude.get(i), longitude.get(i)));
                        marker.setOnClickListener(this);
//                        marker.setCaptionText(attraction_name.get(i));
                        marker.setTag(i);
                        marker.setMap(naverMap);
                    }
                }
            }
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) { // 마커를 click하면, 마커 위에 관광명소 이름을 띄우고 Toast로는 도로면 주소를 출력함
        if(overlay instanceof Marker){
            // 여기에 이벤트 추가할 것!
            ProductItem productItem = new ProductItem(attraction_name.get(Integer.parseInt(overlay.getTag().toString())), address_doro.get(Integer.parseInt(overlay.getTag().toString())));

            ((Marker) overlay).setCaptionAligns(Align.Top);
            ((Marker) overlay).setCaptionText(attraction_name.get(Integer.parseInt(overlay.getTag().toString())));
            Toast.makeText(getApplicationContext(), address_doro.get(Integer.parseInt(overlay.getTag().toString())), Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}


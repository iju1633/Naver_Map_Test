package com.example.map;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import com.example.map.dto.Item;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Align;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;

import java.io.IOException;
import java.io.InputStream;
import java.security.Permission;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class MapsNaverActivity extends Activity implements OnMapReadyCallback, Overlay.OnClickListener {

    private MapView mapView;
    Button button_to_list;
    Item item;
    public static ArrayList<Item> searchedList = new ArrayList<>(); // 정적변수로 설정해서 Adapter 클래스(함수 외부)에서도 쓸 수 있도록 함!
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };


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

        // 리스트 조회하기 버튼에 이벤트 등록하기
        button_to_list = findViewById(R.id.button_to_list);
        button_to_list.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MapsNaverActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });

        locationSource = new FusedLocationSource(this,PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap){

        try {
            //NaverMap 객체 받아서 위치 소스 지정
            this.naverMap = naverMap;
            naverMap.setLocationSource(locationSource);

            // 엑셀 파일 읽기!
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
                        marker.setTag(i);
                        marker.setMap(naverMap);
                    }
                }
            }
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE);
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onClick(@NonNull Overlay overlay) { // 마커를 click하면, 마커 위에 관광명소 이름을 띄우고 Toast로는 도로면 주소를 출력함

        if(overlay instanceof Marker){ // searchedList가 이 함수 안에서 생성된다면 클릭될 때마다 새로운 객체가 생성되니 뷰에 하나만 나오겠지?(이해)

            if(!attraction_name.contains(((Marker) overlay).getCaptionText())) { // 마커 클릭할 때 캡션이 생기도록 했기 때문에 같은 마커를 또 누르면 contain한 걸로 됨
            // caption이 마커 위로 뜨고, 내용은 관광명소 이름이며 Toast로 도로명 주소가 출력된다
                ((Marker) overlay).setCaptionAligns(Align.Top);
                ((Marker) overlay).setCaptionText(attraction_name.get(Integer.parseInt(overlay.getTag().toString())));
                Toast.makeText(getApplicationContext(), address_doro.get(Integer.parseInt(overlay.getTag().toString())), Toast.LENGTH_SHORT).show();

                // 마커 클릭될 때마다 searchedList에 Item 객체 저장
                item = new Item(attraction_name.get(Integer.parseInt(overlay.getTag().toString())), address_doro.get(Integer.parseInt(overlay.getTag().toString())));
                item.setAttraction_name(attraction_name.get(Integer.parseInt(overlay.getTag().toString())));
                item.setAddress_doro(address_doro.get(Integer.parseInt(overlay.getTag().toString())));
//              item.setLatitude(latitude.get(Integer.parseInt(overlay.getTag().toString())));
//              item.setLongitude(longitude.get(Integer.parseInt(overlay.getTag().toString())));

                // click했을 때 마커 색깔, 캡션 크기 증가
                ((Marker) overlay).setCaptionTextSize(15.0F);
                ((Marker) overlay).setIconTintColor(Color.BLUE);

                searchedList.add(item);
            } else Toast.makeText(getApplicationContext(), "이미 선택하신 마커입니다!", Toast.LENGTH_SHORT).show();

        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // requestcode와 권한획득 여부 확인
        if(requestCode == PERMISSION_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }
        }
    }
}


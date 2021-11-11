package com.example.map;

import static com.example.map.MapsNaverActivity.searchedList;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.dto.Item;

import java.util.ArrayList;

/**
 * 데이터  ->  어댑터  ->  뷰
 * 데이터 목록을 아이템 단위의 뷰로 구성하여 화면에 표시하기 위해 어댑터를 사용한다!
 * 어댑터를 통해 만들어진 각 아이템 뷰는 뷰 홀더 객체에 저장되어 화면에 표시된다
 */

public class RecyclerActivity extends AppCompatActivity {
// 여기가 문제인 것 같으니 여길 고쳐보자!!! + static으로 바꿨다잉!(참고)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

//        MapsNaverActivity mapsNaverActivity = new MapsNaverActivity();
        searchedList = new ArrayList<>();

        Adapter adapter = new Adapter(searchedList);

        Toast.makeText(getApplicationContext(),"잘 되고 있다",Toast.LENGTH_LONG);

//        for(int i = 0; i< searchedList.size(); i++) {
//            adapter.addItem(searchedList.get(i));
//        }

        adapter.setData(searchedList);

        recyclerView.setAdapter(adapter); // 이거 없었음!


    }
}

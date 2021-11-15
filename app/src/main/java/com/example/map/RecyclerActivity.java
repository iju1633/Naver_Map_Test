package com.example.map;



import static com.example.map.MapsNaverActivity.searchedList;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.map.dto.Item;

import java.util.ArrayList;


/**
 * 데이터  ->  어댑터  ->  뷰
 * 데이터 목록을 아이템 단위의 뷰로 구성하여 화면에 표시하기 위해 어댑터를 사용한다!
 * 어댑터를 통해 만들어진 각 아이템 뷰는 뷰 홀더 객체에 저장되어 화면에 표시된다
 */

public class RecyclerActivity extends AppCompatActivity {
// 여기가 문제인 것 같으니 여길 고쳐보자!!! + static으로 바꿨다잉!(참고)

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Item> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        RecyclerView recyclerView = findViewById(R.id.recyclerView); // 아이디 연결
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>(); // Item 객체를 담을 어레이 리스트(어댑터 쪽으로)

        //이제 데이터 받아와야 함
        list.addAll(searchedList);

        adapter = new Adapter(list, this); // 데이터와 어댑터 연결
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결


    }
}

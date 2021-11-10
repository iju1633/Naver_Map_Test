package com.example.map;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 데이터  ->  어댑터  ->  뷰
 */

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        RecyclerView recyclerView = findViewById(R.id.navermap_recycler_view);

        MapsNaverActivity mapsNaverActivity = new MapsNaverActivity();
        Adapter adapter = new Adapter();
        for(int i=0; i<mapsNaverActivity.searchedList.size(); i++) {
            adapter.addItem(mapsNaverActivity.searchedList.get(i));
        }

        adapter.notifyDataSetChanged();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }
}

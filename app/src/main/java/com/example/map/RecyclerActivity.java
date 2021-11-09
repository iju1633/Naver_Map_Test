package com.example.map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.map.adapter.RecyclerAdapter;
import com.example.map.dto.ProductItem;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;


public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<ProductItem> productItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        /* initiate adapter */
        recyclerAdapter = new RecyclerAdapter();

        /* initiate recyclerview */
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
        


        /* adapt data */
        recyclerAdapter.setFriendList(productItems);
    }
}

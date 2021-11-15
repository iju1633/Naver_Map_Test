package com.example.map;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.map.dto.Item;

@Database(entities = {Item.class}, version = 1) // version은 변경사항에 대한 관리
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();

}

package com.example.map;

/*
Data Access Object
 */

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.map.dto.Item;

import java.util.List;

@Dao
public interface ItemDao {

    @Insert // 삽입
    void setInsertItem(Item item);

    @Delete // 삭제
    void setDeleteItem(Item item);

    // 조회 쿼리
    @Query("Select * from Item")
    List<Item> getItemAll();

}

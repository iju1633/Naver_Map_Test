package com.example.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.dto.Item;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    ArrayList<Item> list = new ArrayList<>();

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        return new Holder(view); // itemView를 가지고 있는 뷰 홀더를 만들어서 반환!
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
        holder.onBind(list.get(position));
        // 이렇게 하면 list에 있는 데이터를 알 수 있게 되어 뷰홀더 안에 있는 뷰에 데이터를 설정해줄 수 있다.
    }

//    public void setFriendList(ArrayList<Item> list){
//        this.list = list;
//        notifyDataSetChanged();
//    }

    public void addItem(Item item){
        list.add(item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView attraction_name, address_doro, latitude, longitude;

        public Holder(@NonNull View itemView){
            super(itemView);

            attraction_name = itemView.findViewById(R.id.attraction_name);
            address_doro = itemView.findViewById(R.id.address_doro);
            latitude = itemView.findViewById(R.id.latitude);
            longitude = itemView.findViewById(R.id.longitude);
        }

        public void onBind(Item item){

            attraction_name.setText(item.getAttraction_name());
            address_doro.setText(item.getAddress_doro());
            latitude.setText(Double.toString(item.getLatitude()));
            longitude.setText(Double.toString(item.getLongitude()));
        }
    }


}


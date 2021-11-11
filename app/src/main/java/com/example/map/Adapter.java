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

    private ArrayList<Item> list;
    private Context context; // 추가된 것

    public Adapter(ArrayList<Item> list, Context context) { // 수정된 것
        this.list = list;
        this.context = context;
    }

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
        holder.attraction_name.setText(list.get(position).getAttraction_name());
        holder.address_doro.setText(list.get(position).getAddress_doro());
//        holder.latitude.setText(String.valueOf(list.get(position).getLatitude()));
//        holder.longitude.setText(String.valueOf(list.get(position).getLongitude()));
        // 이렇게 하면 list에 있는 데이터를 알 수 있게 되어 뷰홀더 안에 있는 뷰에 데이터를 설정해줄 수 있다.
    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView attraction_name, address_doro, latitude, longitude;

        public Holder(@NonNull View itemView){
            super(itemView);

            this.attraction_name = itemView.findViewById(R.id.attraction_name);
            this.address_doro = itemView.findViewById(R.id.address_doro);
//            this.latitude = itemView.findViewById(R.id.latitude);
//            this.longitude = itemView.findViewById(R.id.longitude);
        }
    }


}


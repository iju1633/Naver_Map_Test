package com.example.map.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.map.dto.ProductItem;
import com.example.map.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<ProductItem> productItem;

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(productItem.get(position));
    }

    public void setFriendList(ArrayList<ProductItem> list){
        this.productItem = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView attraction_name, address_doro, latitude, longitude;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            attraction_name = (TextView) itemView.findViewById(R.id.attraction_name);
            address_doro = (TextView) itemView.findViewById(R.id.address_doro);
            latitude = (TextView) itemView.findViewById(R.id.latitude);
            longitude = (TextView) itemView.findViewById(R.id.longitude);
        }

        void onBind(ProductItem item){

            attraction_name.setText(item.getAttraction_name());
            address_doro.setText(item.getAddress_doro());
            latitude.setText(item.getLatitude());
            longitude.setText(item.getLongitude());
        }
    }
}


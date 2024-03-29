package com.example.workwithapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private List<Post> itemList;
    public ItemAdapter(Context context, List<Post> itemList){
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inorder to send id component to the item view holder function
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Post item = itemList.get(position);
        holder.title.setText(item.getEmail());
        holder.subTitle.setText(item.getBody());

    }

    @Override
    public int getItemCount() {
        if (itemList != null){
            return itemList.size();
        }
        return 0;

    }

    //lazy load
    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView subTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            subTitle = itemView.findViewById(R.id.tvSubtitle);
        }

    }
}

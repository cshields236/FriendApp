package com.example.assignment2;
import android.graphics.Movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Friend> friends;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, number;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            number = (TextView) view.findViewById(R.id.number);
        }
    }


    public MyAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.name.setText(friend.getUsername());
        holder.email.setText(friend.getEmail());
        holder.number.setText(String.valueOf(friend.getPhoneNum()));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }
}
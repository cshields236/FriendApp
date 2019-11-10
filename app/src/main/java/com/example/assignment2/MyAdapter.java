package com.example.assignment2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Friend> friends;
    private OnFriendListener onFriendListener;


    public MyAdapter(List<Friend> friends, OnFriendListener onFriendListener) {
        this.friends = friends;
        this.onFriendListener = onFriendListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);

        return new MyViewHolder(itemView, onFriendListener);
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


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, email, number;
        OnFriendListener onFriendListener;
        public MyViewHolder(View view, OnFriendListener onFriendListener) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            number = (TextView) view.findViewById(R.id.number);
            this.onFriendListener = onFriendListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onFriendListener.onFriendClick(getAdapterPosition());

        }
    }
    public interface OnFriendListener{
        void onFriendClick(int position);
    }

}
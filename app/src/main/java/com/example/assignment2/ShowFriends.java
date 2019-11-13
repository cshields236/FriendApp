package com.example.assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class ShowFriends extends AppCompatActivity implements MyAdapter.OnFriendListener {
    private static final String TAG = "CLICKED";
    DatabseHelper myDB;
    Friend friend;
    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = new DatabseHelper(this);

        setContentView(R.layout.activity_show_friends);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("All Your Friends!");





        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Cursor res = myDB.getAllData();
        if (res.getCount() == 0) {
            //show message
            showMessage("ERROR", "Nothing Found");

            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                Friend friend = new Friend(res.getString(1), res.getString(2), res.getString(3));
                friends.add(friend);
            }
        }

        MyAdapter adapter = new MyAdapter(friends, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);


    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    @Override
    public void onFriendClick(int position) {
        friends.get(position);

       showMessage("Success, " +  friends.get(position).getUsername() + " Found!", "Username: " + friends.get(position).getUsername() + "\nEmail: " + friends.get(position).getEmail() + "\nPhone Number: " + friends.get(position).getPhoneNum());
    }

    @Override
    public void onFriendLongClick(int position) {
        friends.get(position);

        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("selected_friend", friends.get(position));

        startActivity(intent);
    }

}

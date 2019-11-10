package com.example.assignment2;

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

import java.util.ArrayList;

public class ShowFriends extends AppCompatActivity implements MyAdapter.OnFriendListener {
    private static final String TAG = "CLICKED";
    DatabseHelper myDB;
    Friend friend;
    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);
        myDB = new DatabseHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Cursor res = myDB.getAllData();
        if(res.getCount()==0)
        {
            //show message
            showMessage("ERROR", "Nothing Found");
            return;
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
//                buffer.append("ID:" + res.getString(0)+ "\n");
//                buffer.append("USERNAME:" + res.getString(1)+ "\n");
//                buffer.append("EMAIL:" + res.getString(2)+ "\n");
//                buffer.append("PHONENUM:" + res.getString(3)+ "\n \n");
                //showMessage("SUCCESS", buffer.toString());
                Friend friend = new Friend( res.getString(1),res.getString(2),Integer.parseInt(res.getString(3)));
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

        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("selected_friend", friends.get(position));

        startActivity(intent);

        Log.d(TAG, "onFriendClick: " + friends.get(position).getUsername());
    }
}

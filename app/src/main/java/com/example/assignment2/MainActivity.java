package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Friend friend;
    ArrayList<Friend> friends = new ArrayList<>();
    DatabseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabseHelper(this);


    }

    public void register(View v) {


        TextView un = findViewById(R.id.txtUsername);
        TextView em = findViewById(R.id.txtEmail);
        TextView pn = findViewById(R.id.txtNumber);

        String username = un.getText().toString();
        String email = em.getText().toString();
        int phoneNum = Integer.parseInt(pn.getText().toString());

        friend = new Friend(username, email, phoneNum);
        friends.add(friend);

        boolean isIns = myDB.insertData(username, email, phoneNum);

        if (isIns = true) {
            Toast.makeText(this, "Friend Added ", Toast.LENGTH_LONG).show();
            un.setText("");
            em.setText("");
            pn.setText("");

        } else {
            Toast.makeText(this, "Data Not Inserted ", Toast.LENGTH_LONG).show();
        }


    }






}

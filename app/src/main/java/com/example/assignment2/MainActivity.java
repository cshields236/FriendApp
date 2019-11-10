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


//TODO
// Add validation that details are unique and not already in database
public class MainActivity extends AppCompatActivity {
    Friend friend;
    ArrayList<Friend> friends = new ArrayList<>();
    ArrayList<Friend> friendsFromDB = new ArrayList<>();
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
        String phoneNum = pn.getText().toString();


        Cursor res = myDB.getAllData();
        if (res.getCount() == 0) {

            Toast.makeText(this, "Nothing Found", Toast.LENGTH_LONG).show();
            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                Friend friend = new Friend(res.getString(1), res.getString(2), Integer.parseInt(res.getString(3)));
                friendsFromDB.add(friend);
            }


            if (username.isEmpty() || email.isEmpty() || phoneNum.isEmpty()) {
                Toast.makeText(this, "Data Not Inserted, Please enter all details  ", Toast.LENGTH_LONG).show();

            } else if (username.contains(" ") || email.contains(" ")) {
                Toast.makeText(this, "Data Not Inserted, username or email must not contain spaces", Toast.LENGTH_LONG).show();

            } else if (!email.matches("^(.+)@(.+)")) {
                Toast.makeText(this, "Data Not Inserted, email must be formatted correctly", Toast.LENGTH_LONG).show();
                em.setError("Email must be formatted correctly");
            } else {
                Friend f1 = new Friend(username, email, Integer.parseInt(phoneNum));
                friends.add(f1);
                if (friendsFromDB.contains(f1)) {

                    Toast.makeText(this, "Friend already in database", Toast.LENGTH_LONG).show();
                } else {
                    myDB.insertData(username, email, Integer.parseInt(phoneNum));
                    Toast.makeText(this, username + " Added to your Friends List!", Toast.LENGTH_LONG).show();
                    un.setText("");
                    em.setText("");
                    pn.setText("");
                }
            }


        }


    }
}
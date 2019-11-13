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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


//TODO
// Add validation that details are unique and not already in database
public class MainActivity extends AppCompatActivity {
    Friend friend;
    ArrayList<Friend> friends = new ArrayList<>();

    DatabseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabseHelper(this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Friends!");
    }

    public void register(View v) {


        TextView un = findViewById(R.id.txtUsername);
        TextView em = findViewById(R.id.txtEmail);
        TextView pn = findViewById(R.id.txtNumber);

        String username = un.getText().toString();
        String email = em.getText().toString();
        String phoneNum = pn.getText().toString();



        DatabseHelper databaseHelper = new DatabseHelper(getApplicationContext());
        List<Friend> byNumber = databaseHelper.searchByNumber(phoneNum);


        List<Friend> byUsername = databaseHelper.searchByUsername(username);

        List<Friend> byEmail = databaseHelper.searchByEmail(email);


        if (username.isEmpty() || email.isEmpty() || phoneNum.isEmpty()) {
            Toast.makeText(this, "Data Not Inserted, Please enter all details  ", Toast.LENGTH_LONG).show();

        } else if (username.contains(" ") || email.contains(" ")) {
            Toast.makeText(this, "Data Not Inserted, username or email must not contain spaces", Toast.LENGTH_LONG).show();

        } else if (!email.matches("^(.+)@(.+)")) {
            Toast.makeText(this, "Data Not Inserted, email must be formatted correctly", Toast.LENGTH_LONG).show();
            em.setError("Email must be formatted correctly");
        } else {
            Friend f1 = new Friend(username, email,phoneNum);
            friends.add(f1);
            if (byUsername != null) {

              un.setError("Friend With This Username Already Added");
            }else if (byNumber != null){
                pn.setError( "Number already In use");
            }else if (byEmail != null){
                em.setError("Email Already In Use");
            }
            else {
                myDB.insertData(username, email,phoneNum);
                Toast.makeText(this, username + " Added to your Friends List!", Toast.LENGTH_LONG).show();
                un.setText("");
                em.setText("");
                pn.setText("");
            }
        }


    }

}



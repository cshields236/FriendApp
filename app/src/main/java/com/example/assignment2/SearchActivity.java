package com.example.assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    DatabseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        myDB = new DatabseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Search for Friends");
    }

    public void findByUsername(View view) {
        EditText uSearch = findViewById(R.id.unSearch);
        String username = uSearch.getText().toString();
        DatabseHelper databaseHelper = new DatabseHelper(getApplicationContext());
        List<Friend> friendList = databaseHelper.searchByUsername(username);
        if (friendList != null) {
            showMessage("Success! Friend Found ", "Username: " + friendList.get(0).getUsername() + "\nEmail: " + friendList.get(0).getEmail() + "\nPhone Number: " + friendList.get(0).getPhoneNum());
        } else {
            showMessage("Failure", "You do not have a friend that matches these details ");
        }


    }

    public void findByNumber(View view) {
        EditText uNumber = findViewById(R.id.numSearch);
        String number = uNumber.getText().toString();
        DatabseHelper databaseHelper = new DatabseHelper(getApplicationContext());
        List<Friend> friendList = databaseHelper.searchByNumber(number);
        if (friendList != null) {
            showMessage("Success! Friend Found ", "Username: " + friendList.get(0).getUsername() + "\nEmail: " + friendList.get(0).getEmail() + "\nPhone Number: " + friendList.get(0).getPhoneNum());
        } else {
            showMessage("Failure", "You do not have a friend that matches these details ");
        }


    }



    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

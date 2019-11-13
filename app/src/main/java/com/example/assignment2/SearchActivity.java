package com.example.assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontStyle;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static android.graphics.fonts.FontStyle.FONT_WEIGHT_BOLD;

public class SearchActivity extends AppCompatActivity {
    DatabseHelper myDB;
    List<Friend> friendList;

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
        Button b = findViewById(R.id.ed);
        Button b1 = findViewById(R.id.searchBtn);
        EditText uNumber = findViewById(R.id.numSearch);
        Button b2 = findViewById(R.id.numberSearch);
        TextView t = findViewById(R.id.textView2);
        DatabseHelper databaseHelper = new DatabseHelper(getApplicationContext());
        friendList = databaseHelper.searchByUsername(username);


        if (friendList != null) {
            t.setText("User Profile:\nUsername: " + friendList.get(0).getUsername() + "\n\n\nEmail: " + friendList.get(0).getEmail() + "\n\n\nPhone Number: " + friendList.get(0).getPhoneNum());
            b.setVisibility(View.VISIBLE);
            uSearch.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            uNumber.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);


        } else {
            showMessage("Failure", "You do not have a friend that matches these details ");
        }


    }

    public void findByNumber(View view) {
        EditText uSearch = findViewById(R.id.unSearch);
        Button b = findViewById(R.id.ed);
        Button b1 = findViewById(R.id.searchBtn);
        EditText uNumber = findViewById(R.id.numSearch);
        Button b2 = findViewById(R.id.numberSearch);
        TextView t = findViewById(R.id.textView2);

        String number = uNumber.getText().toString();
        DatabseHelper databaseHelper = new DatabseHelper(getApplicationContext());
        friendList = databaseHelper.searchByNumber(number);
        if (friendList != null) {
            t.setText("User Profile:\n\nUsername: " + friendList.get(0).getUsername() + "\n\n\nEmail: " + friendList.get(0).getEmail() + "\n\n\nPhone Number: " + friendList.get(0).getPhoneNum());
            b.setVisibility(View.VISIBLE);
            uSearch.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            uNumber.setVisibility(View.INVISIBLE);
            b2.setVisibility(View.INVISIBLE);

        } else {
            showMessage("Failure", "You do not have a friend that matches these details ");
        }


    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setIcon(R.drawable.friendship);

        builder.show();
    }

    public void edit(View v) {

        Intent i = new Intent(SearchActivity.this, UpdateActivity.class);
        i.putExtra("selected_friend", friendList.get(0));
        startActivity(i);
    }
}

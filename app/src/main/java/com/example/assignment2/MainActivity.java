package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Friend friend;
    ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

    }
}

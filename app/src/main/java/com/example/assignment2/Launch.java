package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        ImageView add = findViewById(R.id.imageView3);
        ImageView view = findViewById(R.id.imageView6);
        ImageView uodate = findViewById (R.id.imageView16);
        ImageView search  = findViewById(R.id.imageView15);




    }
    public void Add (View view){
        Intent intent;
        startActivity(intent = new Intent(this, MainActivity.class));
    }
    public void View (View view){
        Intent intent;
        startActivity(intent = new Intent(this, ShowFriends.class));
    }
    public void Update (View view){
        Intent intent;
        startActivity(intent = new Intent(this, UpdateActivity.class));
    }
    public void Search (View view){
        Intent intent;
        startActivity(intent = new Intent(this, SearchActivity.class));
    }

}

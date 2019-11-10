package com.example.assignment2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    DatabseHelper myDB;
    TextView un;
    TextView em;
    TextView pn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDB = new DatabseHelper(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Update Your Friends Details!");
        un = findViewById(R.id.txtUsername);
        em = findViewById(R.id.txtEmail);
        pn = findViewById(R.id.txtNumber);
        if (getIntent().hasExtra("selected_friend")) {
            Friend f = getIntent().getParcelableExtra("selected_friend");


            un.setText(f.getUsername());
            em.setText(f.getEmail());
            pn.setText(String.valueOf(f.getPhoneNum()));


        }

    }

    public void updateData(View view) {

        un = findViewById(R.id.txtUsername);
        em = findViewById(R.id.txtEmail);
        pn = findViewById(R.id.txtNumber);
        boolean isUpdate = myDB.updateData(
                un.getText().toString(),
                em.getText().toString(),
                pn.getText().toString());

        if (isUpdate == true) {
            Toast.makeText(UpdateActivity.this, "Data Update", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, ShowFriends.class));
        } else {
            Toast.makeText(UpdateActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
        }
    }

}

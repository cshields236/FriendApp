package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    DatabseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        myDB = new DatabseHelper(this);


    }

    public void updateData(View view) {

        TextView un = findViewById(R.id.txtUsername);
        TextView em = findViewById(R.id.txtEmail);
        TextView pn = findViewById(R.id.txtNumber);
        boolean isUpdate = myDB.updateData(
                un.getText().toString(),
                em.getText().toString(),
                pn.getText().toString());
        if (isUpdate == true)
            Toast.makeText(UpdateActivity.this, "Data Update", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(UpdateActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
    }

}

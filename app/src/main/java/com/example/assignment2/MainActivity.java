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
        String phoneNum = pn.getText().toString();
        boolean isIns = false;

        Cursor res = myDB.getAllData();
        if (res.getCount() == 0) {
            //show message

            return;
        } else {
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
//                buffer.append("ID:" + res.getString(0)+ "\n");
//                buffer.append("USERNAME:" + res.getString(1)+ "\n");
//                buffer.append("EMAIL:" + res.getString(2)+ "\n");
//                buffer.append("PHONENUM:" + res.getString(3)+ "\n \n");
                //showMessage("SUCCESS", buffer.toString());
                Friend friend = new Friend(res.getString(1), res.getString(2), Integer.parseInt(res.getString(3)));
                friends.add(friend);
            }
        }


            if (username.isEmpty() || email.isEmpty() || phoneNum.isEmpty()) {
                Toast.makeText(this, "Please Enter all Details", Toast.LENGTH_LONG).show();
                isIns = false;
            } else if (username.contains(" ") || email.contains(" ")) {
                Toast.makeText(this, "Please Enter all Details", Toast.LENGTH_LONG).show();
                isIns = false;
//            } else if (f.getUsername().equalsIgnoreCase(username) || f.getEmail().equalsIgnoreCase(email) || f.getPhoneNum() == Integer.parseInt(phoneNum)) {
//                Toast.makeText(this, "User already in database", Toast.LENGTH_LONG).show();
//                isIns = false;
//            } else {
                friend = new Friend(username, email, Integer.parseInt(phoneNum));
                friends.add(friend);

                isIns = myDB.insertData(username, email, Integer.parseInt(phoneNum));
            }

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

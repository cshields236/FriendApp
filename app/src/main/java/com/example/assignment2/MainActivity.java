package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

        if (isIns = true){
            Toast.makeText(this, "Data Inserted ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Data Not Inserted ", Toast.LENGTH_LONG).show();}

    }

    public void viewAll(View view){
        Cursor res = myDB.getAllData();
        if(res.getCount()==0)
        {
            //show message
            showMessage("ERROR", "Nothing Found");
            return;
        }
        else{
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("ID:" + res.getString(0)+ "\n");
                buffer.append("USERNAME:" + res.getString(1)+ "\n");
                buffer.append("EMAIL:" + res.getString(2)+ "\n");
                buffer.append("PHONENUM:" + res.getString(3)+ "\n \n");
                showMessage("SUCCESS", buffer.toString());
            }
        }
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

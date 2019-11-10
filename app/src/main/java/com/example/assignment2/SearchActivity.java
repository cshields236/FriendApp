package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

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
    }

    public void findByUsername(View view) {
        EditText uSearch = findViewById(R.id.unSearch);
         String username = uSearch.getText().toString();
        DatabseHelper databaseHelper = new DatabseHelper(getApplicationContext());
        List<Friend> friendList = databaseHelper.search(username);
        if (friendList != null) {
            showMessage("Success! Friend Found ", "Username: " + friendList.get(0).getUsername() + "\nEmail: "+ friendList.get(0).getEmail() + "\nPhone Number: "+  friendList.get(0).getPhoneNum()  );
        }
        else{showMessage("Failure", "You do not have a friend that matches these details ");}
    }
//    public void findByUsername(View view){
//
//
//
//        EditText uSearch = findViewById(R.id.unSearch);
//        String username = uSearch.getText().toString();
//        Cursor res = myDB.searchByUsername(username);
//
//        if(res.getCount()==0)
//        {
//            //show message
//            showMessage("ERROR", "Nothing Found");
//            return;
//        }
//        else{
//            StringBuffer buffer = new StringBuffer();
//            while(res.moveToNext()){
//                buffer.append("ID:" + res.getString(0)+ "\n");
//                buffer.append("USERNAME:" + res.getString(1)+ "\n");
//                buffer.append("EMAIL:" + res.getString(2)+ "\n");
//                buffer.append("PHONENUM:" + res.getString(3)+ "\n \n");
//                showMessage("SUCCESS", buffer.toString());
//
//            }
//        }
//
//    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

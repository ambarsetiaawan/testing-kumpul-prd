package com.example.todo_list;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {


    //INITIALIZING THE VARIABLES...
    Button b1,b2,b3;
    EditText e1;
    String text,u;
    String un[]={"","","","","","","","","","","","","","","",""};
    String pas;
    Cursor c;
    int i=0;
    String s,sqlquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.editText1);

        //ON ADDING ANY TASK OR WORK...

        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                //CONVERTING THE TEXT IN TO STRING...
                text=e1.getText().toString();

                //CREATING A DATABASE OBJECT..HERE db is SQLITEDATABASE OBJECT AND
                // todo is our database name AND
                //WE ARE USING IT IN WRITE MODE
                SQLiteDatabase db=openOrCreateDatabase("todo", Context.MODE_WORLD_WRITEABLE, null);

                text="'"+text+"'";

                //SQL QUERY TO CREATE TABLE
                s="CREATE TABLE if not exists TODOLIST"+" ("+ "list" +" VARCHAR(100)"+");";
                db.execSQL(s);

                // QUERY TO INSERT THE DATA INTO TABLE
                sqlquery="INSERT INTO TODOLIST"+ " VALUES"+"("+text+");";
                db.execSQL(sqlquery);
                e1.setText("");

                Toast.makeText(getApplicationContext(), "TASK ADDED IN LIST", Toast.LENGTH_SHORT).show();
            }
        });

        //THIS WILL CALL ANOTHER ACTIVITY NAMED AS DETAIL.JAVA
        b2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                startActivity(new Intent(getApplicationContext(),Detail.class));
            }
        });
    }
}
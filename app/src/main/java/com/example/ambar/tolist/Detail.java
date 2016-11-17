package com.example.todo_list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Detail extends Activity {

    Button b, clr, dlt;
    ListView lv;
    String query;
    Cursor c;
    String uns[] = { "", "", "", "", "", "", "","","",""};
    int i = 0;
    int t = 0;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        b = (Button) findViewById(R.id.btn);
        clr = (Button) findViewById(R.id.clear);
        lv = (ListView) findViewById(R.id.listView1);
        dlt = (Button) findViewById(R.id.Delete);

        // Declaring arrayadapter to store the items and return them as a view
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, uns);

        db = openOrCreateDatabase("todo", Context.MODE_WORLD_READABLE, null);

        //READING THE DATA FROM THE TABLE TODOLIST AND SETTING IN AN ARRAYADAPTER...HERE C IS A CURSOR...
        //WHICH POINTS TO A ROW..AND MOVE TO THE NEXT ROW....
        c = db.rawQuery("SELECT * FROM TODOLIST; ", null);
        i = 0;
        while (c.moveToNext()) {

            uns[i] = c.getString(0);
            i++;
        }
        t = i;

        lv.setAdapter(adapter);

        // clear all the data from table..
        clr.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                db = openOrCreateDatabase("todo", Context.MODE_WORLD_WRITEABLE,
                        null);

                //QUERY TO DELETE ALL THE DATA FROM TABLE
                db.delete("TODOLIST", null, null);

                db.close();

                for (int j = 0; j < t; j++) {
                    uns[j] = "";
                    lv.setAdapter(adapter);
                }

            }
        });

        //GOING BACK TO MAIN ACTIVITY
        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(getApplicationContext(),
                        MainActivity.class));
            }
        });

        // delete the current task
        dlt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                db = openOrCreateDatabase("todo", Context.MODE_WORLD_WRITEABLE,
                        null);

                //QUERY TO DELETE ALL THE DATA FROM TABLE
                db.delete("TODOLIST", null, null);

                db.close();

            }
        });


    }

}
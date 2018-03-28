package com.example.mahe.test2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText a1;
    EditText a2;
    String name;
    String password;
    public SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("form",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student(name VARCHAR primary key,password VARCHAR);");


    }
    public void OnClick(View View) {
        a1 = findViewById(R.id.editText);
        a2 = findViewById(R.id.editText2);
        String name = a1.getText().toString();
        String password = a2.getText().toString();
        Cursor resultSet = sqLiteDatabase.rawQuery("select  *from Student where name='" + name + "'", null);
        resultSet.moveToFirst();
        if (resultSet.getCount() == 0)
            sqLiteDatabase.execSQL("INSERT INTO Student VALUES('" + name + "','" + password + "');");
        Toast.makeText(this, "registered", Toast.LENGTH_LONG).show();
    }
    public void OnClick2(View view)
    {

        a1 = findViewById(R.id.editText);
        a2 = findViewById(R.id.editText2);
        String name = a1.getText().toString();
        String password = a2.getText().toString();
        Cursor resultSet2 = sqLiteDatabase.rawQuery("select  *from Student where name='" + name + "'", null);
        resultSet2.moveToFirst();
        String p = resultSet2.getString(1);
        if (p.equals(password))
            Toast.makeText(this, "successful login", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "unsuccessful login", Toast.LENGTH_LONG).show();
    }
    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    }

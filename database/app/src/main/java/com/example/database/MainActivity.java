package com.example.database;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
    EditText name, usn, marks;
    Button insert,delete,update,view,viewall;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.name);
        usn = (EditText)findViewById(R.id.usn);
        marks = (EditText)findViewById(R.id.marks);
        insert = (Button)findViewById(R.id.insert);
        delete = (Button)findViewById(R.id.delete);
        update = (Button)findViewById(R.id.update);
        view = (Button)findViewById(R.id.view);
        viewall = (Button)findViewById(R.id. viewall);
    }
}

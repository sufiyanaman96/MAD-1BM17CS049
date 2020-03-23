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
        insert.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
        view.setOnClickListener(this);
        viewall.setOnClickListener(this);
        db = openOrCreateDatabase("StudentDB",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(usn VARCHAR , name VARCHAR , marks VARCHAR);");
    }
    @Override
    public void onClick(View view) {
        if(view==insert)
        {
            if((usn.getText().toString().trim().length()==0) || (name.getText().toString().trim().length()==0) || (marks.getText().toString().trim().length()==0))
            {
                showMessage("Error","Please enter all the values");
                return;
            }
            db.execSQL("INSERT INTO student VALUES ("+usn.getText()+","+name.getText()+","+marks.getText()+");");
            showMessage("Success","Record added");
            clearText();
        }
        if(view==delete)
        {
            if((usn.getText().toString().trim().length()==0) || (name.getText().toString().trim().length()==0)) {
                showMessage("Error", "Please Enter the Roll no ");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM student WHERE usn="+usn.getText()+";",null);
            if(c.moveToFirst())
            {
                db.execSQL("DELETE FROM student WHERE usn = "+usn.getText()+";");
                showMessage("Success","Record Deleted");
            }
            else
                showMessage("Error","Invalid Roll no");
            clearText();
        }
        if(view == update)
        {
            if(usn.getText().toString().trim().length()==0)
            {
                showMessage("Error","Please enter all the values");
                return;
            }
            Cursor c = db.rawQuery("select * from student where usn="+usn.getText()+";",null);
            if(c.moveToFirst())
            {
                db.execSQL("update student set name ="+name.getText()+",marks ="+marks.getText()+" where usn = "+usn.getText()+";");
                showMessage("Success","Record Modifies");
            }
            else
                showMessage("Error","Invalid Roll no");
            clearText();
        }
        if(view == view)
        {
            if(usn.getText().toString().trim().length()==0)
            {
                showMessage("Error","Please Enter the Roll number");
                return;
            }
            Cursor c = db.rawQuery("select * from student where usn="+usn.getText()+";",null);
            if(c.moveToFirst())
            {
                name.setText(c.getString(1));
                marks.setText(c.getString(2));
            }
            else
            {
                showMessage("Error","No records found");
                clearText();
            }
        }
        if(view==viewall)
        {
            Cursor c = db.rawQuery("select * from student ;",null);
            if(c.getCount()==0)
            {
                showMessage("Error","No records found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("usn : "+c.getString(0)+"\n");
                buffer.append("name : "+c.getString(1)+"\n");
                buffer.append("Marks : "+c.getString(2)+"\n\n");
            }
            showMessage("Student Details",buffer.toString());
        }
    }
    private void clearText()
    {
        usn.setText("");
        name.setText("");
        marks.setText("");
        usn.requestFocus();
    }
    private void showMessage(String title, String message)
    {
        Builder builder = new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}


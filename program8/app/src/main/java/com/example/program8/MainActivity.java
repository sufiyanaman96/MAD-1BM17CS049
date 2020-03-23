package com.example.program8;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button write,read,clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1= (EditText) findViewById(R.id.editText);
        write= (Button) findViewById(R.id.button);
        read= (Button) findViewById(R.id.button2);
        clear= (Button) findViewById(R.id.button3);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=e1.getText().toString();

                try

                {
                    File path = getExternalFilesDir(null);
                    File f = new File(path, "my-file-name.txt");


                    f.createNewFile();

                    FileOutputStream fout = new FileOutputStream(f);

                    fout.write(message.getBytes());

                    fout.close();

                    Toast.makeText(getBaseContext(),"Data Written in SDCARD",Toast.LENGTH_LONG).show();

                }

                catch(Exception e)

                {

                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message;

                String buf = "";

                try

                {
                    File path = getExternalFilesDir(null);
                    File f = new File(path, "my-file-name.txt");


                    FileInputStream fin = new FileInputStream(f);

                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));

                    while((message = br.readLine()) != null)

                    {

                        buf += message;

                    }

                    e1.setText(buf);

                    br.close();

                    fin.close();

                    Toast.makeText(getBaseContext(),"Data Recived from SDCARD",Toast.LENGTH_LONG).show();

                }

                catch(Exception e)

                {

                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
            }
        });
    }
}

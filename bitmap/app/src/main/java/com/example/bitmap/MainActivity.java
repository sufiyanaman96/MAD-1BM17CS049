package com.example.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap bit=Bitmap.createBitmap(720,1280, Bitmap.Config.ARGB_8888);
        ImageView i=findViewById(R.id.imageView);
        i.setBackgroundDrawable(new BitmapDrawable(bit));
        Canvas canvas=new Canvas(bit);
        Paint paint1=new Paint();
        paint1.setColor(Color.BLACK);
        Paint paint2=new Paint();
        paint2.setColor(Color.BLUE);
        paint1.setTextSize(40);
        canvas.drawText("Line",480,800,paint1);
        canvas.drawLine(520,850,520,1150,paint2);

        canvas.drawText("Rectangle",420,150,paint1);
        canvas.drawRect(400,200,650,700,paint2);

        canvas.drawText("Circle",200,150,paint1);
        canvas.drawCircle(200,350,150,paint2);

        canvas.drawText("Square",120,800,paint1);
        canvas.drawRect(50,850,350,1150,paint2);

    }
}

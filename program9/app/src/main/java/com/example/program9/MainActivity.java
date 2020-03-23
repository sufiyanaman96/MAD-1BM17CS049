package com.example.program9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    Button notify;
    EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notify= (Button) findViewById(R.id.button);
        e= (EditText) findViewById(R.id.editText);
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Notification.Builder builder =
                        new Notification.Builder(MainActivity.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Notifications Example")
                                .setContentText("This is a test notification");

                Intent notificationIntent = new Intent(MainActivity.this, secondActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);

                // Add as notification
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, builder.build());
            }
        });
    }
}

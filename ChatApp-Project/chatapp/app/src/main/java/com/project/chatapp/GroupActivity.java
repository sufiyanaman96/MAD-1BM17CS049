package com.project.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.koddev.chatapp.R;
import com.project.chatapp.Adapter.MessAdapter;
import com.project.chatapp.Model.AllMethods;
import com.project.chatapp.Model.User;
import com.project.chatapp.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference messagedb;
    MessAdapter messageAdapter;
    User u;
    List<Message> messages;
    RecyclerView rvMessage;
    EditText etMessage;
    ImageButton imageButton;
    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_activity);
        init();
    }
    private void init(){
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        u= new User();
        rvMessage=(RecyclerView)findViewById(R.id.recycler_view1);
        etMessage=(EditText)findViewById(R.id.text_send1);
        imageButton =(ImageButton)findViewById(R.id.btn_send1);
        imageButton.setOnClickListener(this);
        messages = new ArrayList<>();
    }
    @Override
    public void onClick(View view) {
        if (!TextUtils.isEmpty(etMessage.getText().toString())){
            Message message = new Message (etMessage.getText().toString(),u.getUsername());
            etMessage.setText("");
            messagedb.push().setValue(message);
        }
        else{
            Toast.makeText(getApplicationContext(),"You cant send blank message ",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            auth.signOut();
            finish();
            startActivity(new Intent(GroupActivity.this, StartActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            return true;
        } return  false;
    }

    @Override
    protected void onStart(){
        super.onStart();
        final FirebaseUser currentUser = auth.getCurrentUser();
        u.setId(currentUser.getUid());
        u.setEmail(currentUser.getEmail());
        database.getReference("Users").child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                u=dataSnapshot.getValue(User.class);
                u.setId(currentUser.getUid());
                AllMethods.name=u.getUsername();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        messagedb = database.getReference("messages");
        messagedb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message=dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                messages.add(message);
                displayMessages(messages);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessages = new ArrayList<Message>();
                for (Message m:messages){
                    if (m.getKey().equals(message.getKey())){
                        newMessages.add(message);
                    }
                    else{
                        newMessages.add(m);
                    }
                }
                messages=newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                List<Message> newMessages = new ArrayList<Message>();
                for (Message m:messages){
                    if (!m.getKey().equals(message.getKey())){
                        newMessages.add(m);
                    }
                }
                messages = newMessages;
                displayMessages(messages);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        messages=new ArrayList<>();
    }

    private void displayMessages(List<Message> messages) {
        rvMessage.setLayoutManager(new LinearLayoutManager(GroupActivity.this));
        messageAdapter = new MessAdapter(GroupActivity.this,messages,messagedb);
        rvMessage.setAdapter(messageAdapter);
    }
}

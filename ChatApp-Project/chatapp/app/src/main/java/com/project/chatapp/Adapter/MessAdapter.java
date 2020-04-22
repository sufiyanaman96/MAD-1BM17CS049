package com.project.chatapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.koddev.chatapp.R;
import com.project.chatapp.Model.AllMethods;
import com.project.chatapp.Model.Message;

import java.util.ConcurrentModificationException;
import java.util.List;

public class MessAdapter extends RecyclerView.Adapter<MessAdapter.MessAdapterViewHolder> {
    Context context;
    List<Message> messages;
    DatabaseReference messageDb;
    public MessAdapter(Context context, List<Message> messages, DatabaseReference messageDb){
        this.context = context;
        this.messages= messages;
        this.messageDb = messageDb;
    }
    @NonNull
    @Override
    public MessAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message,parent,false);
        return new MessAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessAdapterViewHolder holder, int position) {
        Message message = messages.get(position);
        if (message.getName().equals(AllMethods.name)){
            holder.tvTitle.setText("You:" + message.getMessage());
            holder.tvTitle.setGravity(Gravity.START);
            holder.l1.setBackgroundColor(Color.parseColor("Blue"));
        }
        else {
            holder.tvTitle.setText(message.getName() + ":" +message.getMessage());
            holder.l1.setBackgroundColor(Color.parseColor("Red"));
            holder.ibDelete.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageButton ibDelete;
        LinearLayout l1;
        public MessAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            ibDelete = (ImageButton)itemView.findViewById(R.id.img_delete);
            l1 = (LinearLayout)itemView.findViewById(R.id.l1Message);
            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    messageDb.child(messages.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }
}

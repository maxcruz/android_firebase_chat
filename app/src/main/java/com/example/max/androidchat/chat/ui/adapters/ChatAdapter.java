package com.example.max.androidchat.chat.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.max.androidchat.R;
import com.example.max.androidchat.entities.ChatMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private final Context context;
    private List<ChatMessage> chatMessages;

    public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        String msg = chatMessage.getMsg();
        holder.txtMessage.setText(msg);
        int background = R.drawable.bubble_incoming;
        int vertical = context.getResources().getDimensionPixelSize(R.dimen.message_bubble_vertical);
        int start = context.getResources().getDimensionPixelSize(R.dimen.message_bubble_start);
        int end = context.getResources().getDimensionPixelSize(R.dimen.message_bubble_end);
        holder.txtMessage.setPadding(start, vertical, end, vertical);
        int gravity = Gravity.START;
        if (!chatMessage.isSentByMe()) {
            background = R.drawable.bubble_outgoing;
            holder.txtMessage.setPadding(end, vertical, start, vertical);
            gravity = Gravity.END;
        }
        holder.txtMessage.setBackgroundResource(background);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
        params.gravity = gravity;
        holder.txtMessage.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public void add(ChatMessage msg) {
        if (!chatMessages.contains(msg)) {
            chatMessages.add(msg);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtMessage)
        TextView txtMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}

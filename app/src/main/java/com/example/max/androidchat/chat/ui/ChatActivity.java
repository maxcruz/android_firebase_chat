package com.example.max.androidchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.max.androidchat.R;
import com.example.max.androidchat.chat.ChatPresenter;
import com.example.max.androidchat.chat.ChatPresenterImpl;
import com.example.max.androidchat.chat.ui.adapters.ChatAdapter;
import com.example.max.androidchat.domain.AvatarHelper;
import com.example.max.androidchat.entities.ChatMessage;
import com.example.max.androidchat.lib.GlideImageLoader;
import com.example.max.androidchat.lib.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txtUser)
    TextView txtUser;
    @BindView(R.id.txtStatus)
    TextView txtStatus;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.messageRecyclerView)
    RecyclerView messageRecyclerView;
    @BindView(R.id.editTxtMessage)
    EditText editTxtMessage;
    @BindView(R.id.btnSendMessage)
    ImageButton btnSendMessage;

    private ChatPresenter presenter;
    private ChatAdapter adapter;
    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setUpAdapter();
        setUpRecyclerView();
        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();
        setUpToolbar(getIntent());
    }

    private void setUpToolbar(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        presenter.setChatRecepient(recipient);
        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;
        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);
        ImageLoader imageLoader = new GlideImageLoader(this);
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setUpRecyclerView() {
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        messageRecyclerView.scrollToPosition(adapter.getItemCount() -1);
    }

    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        presenter.sendMessage(editTxtMessage.getText().toString());
        editTxtMessage.setText("");
    }

}

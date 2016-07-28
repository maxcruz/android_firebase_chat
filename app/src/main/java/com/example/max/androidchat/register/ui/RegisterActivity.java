package com.example.max.androidchat.register.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.max.androidchat.R;
import com.example.max.androidchat.login.ui.LoginActivity;
import com.example.max.androidchat.register.RegisterPresenter;
import com.example.max.androidchat.register.RegisterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.inputEmail)
    EditText inputEmail;
    @BindView(R.id.inputPassword)
    EditText inputPassword;
    @BindView(R.id.inputConfirm)
    EditText inputConfirm;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        this.registerPresenter = new RegisterPresenterImpl(this);
        this.registerPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        this.registerPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnSignUp)
    public void handleSignUp() {
        if (inputPassword.getText().toString().equals(inputConfirm.getText().toString())) {
            registerPresenter.registerNewUser(inputEmail.getText().toString(),
                    inputPassword.getText().toString());
        } else {
            inputConfirm.setError(getString(R.string.register_error_message_confirm));
        }
    }

    @OnClick(R.id.btnCancel)
    public void handleCancel() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void newUserSuccess() {
        Toast.makeText(this, R.string.register_notice_message_signup, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        inputConfirm.setText("");
        String msgError = String.format(getString(R.string.register_error_message_signup), error);
        inputEmail.setError(msgError);
    }

    private void setInputs(boolean enable) {
        inputEmail.setEnabled(enable);
        inputPassword.setEnabled(enable);
        inputConfirm.setEnabled(enable);
        btnCancel.setEnabled(enable);
        btnSignUp.setEnabled(enable);
    }

}

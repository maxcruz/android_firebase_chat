package com.example.max.androidchat.addcontact.ui;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.max.androidchat.R;
import com.example.max.androidchat.addcontact.AddContactPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddContactFragment extends DialogFragment implements AddContactView, DialogInterface.OnShowListener {


    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    AddContactPresenter presenter;

    public AddContactFragment() {
        presenter = new AddContactPresenterImpl(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.addcontact_message_title)
                .setPositiveButton(R.string.addcontact_message_add, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                })
                .setNegativeButton(R.string.addcontact_message_cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_add_contact, null);
        ButterKnife.bind(this, view);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    @Override
    public void showInput() {
        editTxtEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideInput() {
        editTxtEmail.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void contactAdded() {
        Toast.makeText(getActivity(), R.string.addcontact_message_contactAdded, Toast.LENGTH_SHORT).show();
        dismiss();
    }

    @Override
    public void contactNotAdded() {
        editTxtEmail.setText("");
        editTxtEmail.setError(getString(R.string.addcontact_error_message));
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        final AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog != null) {
            Button positiveButton = dialog.getButton((Dialog.BUTTON_POSITIVE));
            Button negativeButton = dialog.getButton((Dialog.BUTTON_NEGATIVE));
            positiveButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    presenter.addContact(editTxtEmail.getText().toString());
                }

            });
            negativeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    dismiss();
                }

            });
        }
        presenter.onShow();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}

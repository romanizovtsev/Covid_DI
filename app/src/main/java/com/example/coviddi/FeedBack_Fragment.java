package com.example.coviddi;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class FeedBack_Fragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("FeedBack")
                .setIcon(android.R.drawable.ic_lock_lock)
                .setView(R.layout.feedback)
                .setPositiveButton("OK", null)
                .create();
    }
}

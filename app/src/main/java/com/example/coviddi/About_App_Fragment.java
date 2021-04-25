package com.example.coviddi;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class About_App_Fragment extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("About App")
                .setIcon(android.R.drawable.ic_lock_lock)
                .setView(R.layout.about_app)
                .setPositiveButton("OK", null)
                .create();
    }
}

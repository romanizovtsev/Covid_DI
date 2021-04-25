package com.example.coviddi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Test_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test);
    }

    @Override
    public void onBackPressed() {
        try{
            Intent intent=new Intent(Test_activity.this,  MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){}
    }

}


package com.example.coviddi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Test_activity extends AppCompatActivity {
    ImageView Back_Settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.test);
    }

    @Override
    public void onBackPressed() {
        try{
            //есть намерение перейти из Game_levels, втором мы сейчас в Level1
            Intent intent=new Intent(Test_activity.this,  MainActivity.class);
            //реализуем это намерение
            startActivity(intent);
            //закраем предыдущее окно (Game_levels)
            finish();
        }catch(Exception e){}
    }

}


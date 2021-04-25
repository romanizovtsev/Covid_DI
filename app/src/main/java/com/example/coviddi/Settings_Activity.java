package com.example.coviddi;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.Locale;

public class Settings_Activity extends AppCompatActivity {
    Dialog dialog;//Окно диалога
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        ImageView Back_Settings=(ImageView)findViewById(R.id.back_set);
        ImageView About_us=(ImageView)findViewById(R.id.about_ap);
        ImageView Feedback=(ImageView)findViewById(R.id.Feed_back);
        Button rus=(Button)findViewById(R.id.rus);
        Button en=(Button)findViewById(R.id.en);
        About_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FragmentManager manager = getSupportFragmentManager();
                    About_App_Fragment myDialogFragment = new About_App_Fragment();
                    myDialogFragment.show(manager, "myDialog");

                }catch (Exception e){
                }
            }
        });

        Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FragmentManager manager = getSupportFragmentManager();
                    FeedBack_Fragment myDialogFragment = new FeedBack_Fragment();
                    myDialogFragment.show(manager, "myDialog");

                }catch (Exception e){
                }
            }
        });

        rus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                }catch (Exception e){
                }
            }
        });

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Locale locale = new Locale("en");
                }catch (Exception e){
                }
            }
        });
        Window w= getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Back_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Settings_Activity.this,  MainActivity.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                }
            }

        });

    }



 @Override
    public void onBackPressed() {
        try{
            Intent intent=new Intent(Settings_Activity.this,  MainActivity.class);
            startActivity(intent);
            finish();
        }catch(Exception e){}
    }

}



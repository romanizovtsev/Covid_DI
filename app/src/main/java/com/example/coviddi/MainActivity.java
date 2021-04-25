    package com.example.coviddi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coviddi.DataContract.DataDbHelper;
import com.jjoe64.graphview.GraphView;
public class MainActivity extends AppCompatActivity  {
    private String[] AllArray;
    private Presenter presenter;
    private int selected1;
    TextView NumbConf,NumbRecov,NumbDeath,DateText;
    GraphView graphView;
    ImageButton button_settings;
    Button Read_info;
    Button Start_test;
    private long backPressedTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        DataDbHelper dh=new DataDbHelper(this);

        button_settings=(ImageButton)findViewById(R.id.Settings_Button);
        Read_info=(Button)findViewById(R.id.onfoBut);
        Start_test=(Button)findViewById(R.id.TestBut);
        NumbConf=findViewById(R.id.NumbConf);
        NumbRecov=findViewById(R.id.NumbRecov);
        NumbDeath=findViewById(R.id.NumbDeath);
        DateText=findViewById(R.id.Date);

        graphView=(GraphView) findViewById(R.id.graphView);
        presenter=new Presenter(this);
        final Spinner spinner = findViewById(R.id.spinner);
        AllArray= getResources().getStringArray(R.array.Country);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AllArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        presenter.setDatesGraph();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View itemSelected, int selectedItemPosition, long selectedId) {
               selected1=selectedItemPosition;
            presenter.loadCache(selected1);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //обработка переходов
        button_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this,  Settings_Activity.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                }
            }

        });


        Read_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this,  InfoActivity.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                }
            }

        });

        Start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(MainActivity.this,  Test_activity.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                }
            }

        });
    }

    public void ShowNumbConf(String value)
    {
        NumbConf.setText(value);
    }
    public void ShowNumbRecov(String value)
    {
        NumbRecov.setText(value);
    }
    public void ShowNumbDeath(String value)
    {
        NumbDeath.setText(value);
    }
    public String[] getCountry()
    {
        return getResources()
                .getStringArray(R.array.CountryEn);
    }

    //кпопки для перехода

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();//Закрываем
            return;
        }
        else{
            backToast=Toast.makeText(getBaseContext(),"Нажмите дважды чтобы закрыть", Toast.LENGTH_SHORT);
            backToast.show();
        }
        //Засекаем время нажатия на кнопку
        backPressedTime=System.currentTimeMillis();
    }

}
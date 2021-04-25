    package com.example.coviddi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coviddi.DataContract.DataDbHelper;
import com.jjoe64.graphview.GraphView;
public class MainActivity extends AppCompatActivity  {

    private String[] AllArray;
private Presenter presenter;
private int selected1;
    TextView NumbConf,NumbRecov,NumbDeath,DateText;
    GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
       DataDbHelper dh=new DataDbHelper(this);
        NumbConf=findViewById(R.id.NumbConf);
        NumbRecov=findViewById(R.id.NumbRecov);
        NumbDeath=findViewById(R.id.NumbDeath);
        DateText=findViewById(R.id.Date);
        graphView= findViewById(R.id.graphView);
presenter=new Presenter(this);
        final Spinner spinner = findViewById(R.id.spinner);
        AllArray= getResources().getStringArray(R.array.Country);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AllArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        presenter.setDatesGraph();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
               selected1=selectedItemPosition;
            presenter.loadCache(selected1);
            }
            public void onNothingSelected(AdapterView<?> parent) {
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
        return getResources().getStringArray(R.array.CountryEn);
    }
}
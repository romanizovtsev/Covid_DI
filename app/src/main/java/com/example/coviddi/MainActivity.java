    package com.example.coviddi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coviddi.DataContract.DataDbHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity  {

    private String[] AllArray;
private Presenter presenter;
private int selected1;
    TextView NumbConf,NumbRecov,NumbDeath;
    GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
       DataDbHelper dh=new DataDbHelper(this);
        NumbConf=findViewById(R.id.NumbConf);
        NumbRecov=findViewById(R.id.NumbRecov);
        NumbDeath=findViewById(R.id.NumbDeath);
        graphView=(GraphView) findViewById(R.id.graphView);
presenter=new Presenter(this);
        final Spinner spinner = findViewById(R.id.spinner);
        AllArray= getResources().getStringArray(R.array.Country);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AllArray);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        /*selected1 = spinner.getSelectedItemPosition();
        presenter.loadInfo(selected1);*/

     /*   NetworkService.getInstance()
                .getJSONApi()
                .getPost("Germany","deaths")
                .enqueue(new Callback<post1>() {
                    @Override
                    public void onResponse(@NonNull Call<post1> call, @NonNull Response<post1> response) {
                        String content="";
                        if(response.body()!=null)

                            Log.e("Не 0","Точно");
                        post1 post = response.body();
                        if(response.isSuccessful()) {
                            if(post!=null)
                                NumbConf.append(post.getAll().getDates().get("2021-04-20") + "");

                            NumbRecov.append(post.getAll().getDates().get("2021-04-19") + "");
                            NumbDeath.append(post.getAll().getDates().get("2021-04-18") + "");
                        }
                        tv.append(response.code()+"");

                    }

                    @Override
                    public void onFailure(@NonNull Call<post1> call, @NonNull Throwable t) {

                        NumbConf.append("Errooooor");
                        NumbRecov.append("Errooooor");
                        NumbDeath.append("Errooooor");
                        t.printStackTrace();
                    }
                });*/
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
               selected1=selectedItemPosition;
                presenter.loadInfo(selected1);
                presenter.loadInfoGraph(selected1);
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
   /* public void releaseGraph(){
        Integer dayOfMonth=20;
        Random random = new Random();
        Map<Calendar, Integer> graphMap = new HashMap<Calendar, Integer>();
        for (int i=0;i<7;i++)
            graphMap.put(new GregorianCalendar(2021,3,dayOfMonth+i), random.nextInt(11000)+3000);
        Map<Calendar, Integer> sortedMap = new TreeMap<>(graphMap);

        GraphView graphView=(GraphView) findViewById(R.id.graphView);
        DataPoint[] Data= new DataPoint[sortedMap.size()];
        int i=0;
        for (Map.Entry<Calendar,Integer> pair : sortedMap.entrySet())
        {
            Calendar date = pair.getKey();
            Integer confirmed = pair.getValue();
            Data[i]=new DataPoint(date.get(Calendar.DAY_OF_MONTH), confirmed);
            i++;
        }
        LineGraphSeries series= new LineGraphSeries<>(Data);
        graphView.addSeries(series);
        graphView.getGridLabelRenderer().setNumHorizontalLabels(Data.length);
        //graphView.getViewport().setXAxisBoundsManual(true);
    }*/
    public String[] getCountry()
    {
        return getResources().getStringArray(R.array.CountryEn);
    }
}
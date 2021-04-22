    package com.example.coviddi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coviddi.DataContract.DataDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity  {

    private String[] AllArray;
private Presenter presenter;
    TextView NumbConf,NumbRecov,NumbDeath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        TextView tv=findViewById(R.id.textView6);

       DataDbHelper dh=new DataDbHelper(this);
        NumbConf=findViewById(R.id.NumbConf);
        NumbRecov=findViewById(R.id.NumbRecov);
        NumbDeath=findViewById(R.id.NumbDeath);
presenter=new Presenter(this);
presenter.loadInfo("Germany");
        final Spinner spinner = findViewById(R.id.spinner);
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

       // AllArray= getResources().getStringArray(R.array.Country);


        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AllArray);
        // Определяем разметку для использования при выборе элемента
      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
      //  spinner.setAdapter(adapter);
    }
  /*  public void loadInfo(String country)
    { Date dateNow = new Date(System.currentTimeMillis()-24*60*60*1000);
    Date DateYers=  new Date(System.currentTimeMillis()-2*24*60*60*1000);
        SimpleDateFormat formatForDateNow = new SimpleDateFormat(   "yyyy-MM-dd");

        Log.e("ДАТА",formatForDateNow.format(dateNow));
        presenter.getInfoToday(country,"confirmed",formatForDateNow.format(DateYers));
        presenter.getInfoToday(country,"confirmed",formatForDateNow.format(dateNow));
        presenter.getInfoToday(country,"recovered",formatForDateNow.format(DateYers));
        presenter.getInfoToday(country,"recovered",formatForDateNow.format(dateNow));
        presenter.getInfoToday(country,"deaths",formatForDateNow.format(dateNow));
        presenter.getInfoToday(country,"deaths",formatForDateNow.format(DateYers));

    }
    public void showInfo(Map<String,String> map)
    {    for (Map.Entry<String, String> pair : map.entrySet())
    {
        String key = pair.getKey();                      //ключ
        switch(key)
        { case "confirmed": NumbConf.setText(pair.getValue()); break;
            case "recovered": NumbRecov.setText(pair.getValue()); break;
            case "deaths": NumbDeath.setText(pair.getValue()); break;
        }
    }


    }*/
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
}
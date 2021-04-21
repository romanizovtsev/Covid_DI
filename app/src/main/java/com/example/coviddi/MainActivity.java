    package com.example.coviddi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity  {

    private String[] AllArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);
        TextView tv=findViewById(R.id.textView6);

        final   String DateParam;
        DateParam="dfff";
        TextView NumbConf=findViewById(R.id.NumbConf);
        TextView NumbRecov=findViewById(R.id.NumbRecov);
        TextView NumbDeath=findViewById(R.id.NumbDeath);

        final Spinner spinner = findViewById(R.id.spinner);
        NetworkService.getInstance()
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
                });
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-api.mmediagroup.fr/v1/")
                .build();
        Call<List<POST>>call=JsonPlaceHolderApi.getPost();
        call.enqueue(new Callback<List<POST>>() {
            @Override
            public void onResponse(Call<List<POST>> call, Response<List<POST>> response) {
                List<POST> posts=response.body();

            }

            @Override
            public void onFailure(Call<List<POST>> call, Throwable t) {

            }
        });

        JsonPlaceHolderApi service = retrofit.create(JsonPlaceHolderApi.class);
        AllArray= getResources().getStringArray(R.array.Country);
        */

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        //ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AllArray);
        // Определяем разметку для использования при выборе элемента
      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
      //  spinner.setAdapter(adapter);
    }
}
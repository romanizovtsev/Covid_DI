package com.example.coviddi;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.coviddi.DataContract.Data;
import com.example.coviddi.DataContract.DataDbHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.abs;

public class model {
    DataDbHelper dh;
    Presenter presenter;
    public model(Presenter pres)
    {
        this.presenter=pres;
        this.dh=new DataDbHelper(presenter.getContexts());
    }
    Map<String,String> map=new HashMap<>();
    int flag=0;
    public void getInfoToday(String country,String status,String Date)
    {

        NetworkService.getInstance()
                .getJSONApi()
                .getPost(country,status)
                .enqueue(new Callback<post1>() {

                    @Override
                    public void onResponse(@NonNull Call<post1> call, @NonNull Response<post1> response) {
                        post1 post = response.body();
                        //view.showInfo(status,post.getAll().getDates().get(Date)+"");
                        if(!map.containsKey(status)) {
                            map.put(status, post.getAll().getDates().get(Date) + "");
                            Log.e(status,"Первый занос");

                        }
                        else {
                            map.put(status, abs(Integer.parseInt(post.getAll().getDates().get(Date) + "") - Integer.parseInt(map.get(status))) + "");
                            Log.e("Выполнено вычитание", status);
                            flag++;
                            Log.e("dddddddddddddd",flag+"");

                        }
                        if((map.size()==3)&&(flag==3)){
                            presenter.showInfo(map);
                            flag=0;
                            Log.e("вызвана","Шоу инфо");
                        }

                    }


                    @Override
                    public void onFailure(@NonNull Call<post1> call, @NonNull Throwable t) {


                        t.printStackTrace();

                    }
                });



    }
    public void getFromSQL(String country,String status,String Date)
    {
        SQLiteDatabase db = dh.getWritableDatabase();
        // Создаем объект ContentValues, где имена столбцов ключи,
        // а информация о госте является значениями ключей
        ContentValues values = new ContentValues();
        values.put(Data.DateData.COLUMN_COUNTRY, "Germany");
        values.put(Data.DateData.COLUMN_DATE, "2021-04-21");
        values.put(Data.DateData.COLUMN_CONFIRMED, 2000);
        values.put(Data.DateData.COLUMN_RECOVERED, 2000);
        values.put(Data.DateData.COLUMN_DEATHS, 2000);

        long newRowId = db.insert(Data.DateData.TABLE_NAME, null, values);

       db = dh.getReadableDatabase();
        String query = "SELECT " + Data.DateData._ID + ", "
                + Data.DateData.COLUMN_CONFIRMED + " FROM " + Data.DateData.TABLE_NAME+"WHERE"+Data.DateData.COLUMN_DATE+"="+Date;
        Cursor cursor2 = db.rawQuery(query, null);
        while (cursor2.moveToNext()) {
            int id = cursor2.getInt(cursor2
                    .getColumnIndex(Data.DateData._ID));
            String name = cursor2.getString(cursor2
                    .getColumnIndex(Data.DateData.COLUMN_DEATHS));
            Log.i("LOG_TAG", "ROW " + id + " HAS NAME " + name);
        }
        cursor2.close();
    }
}

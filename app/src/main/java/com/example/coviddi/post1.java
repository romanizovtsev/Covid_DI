package com.example.coviddi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class post1 {

    @SerializedName("All")
    @Expose
    private All all;
    public All getAll() {
        return all;
    }
    public void setAll(All all) {
        this.all = all;
    }
    public class All {
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("dates")
        @Expose
        Map<String, String> dates;
        public Map<String, String> getDates() {

            return dates;
        }
        /*  @SerializedName("dates")
        @Expose
        private Dates dates;

        public String getCountry() {
            return country;
        }



        public Dates getDates() {
            return dates;
        }

        public void setDates(Dates dates) {
            this.dates = dates;
        }

        public class Dates {
            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
String DateParam;
            //final private String DateParam=;
           /* public Dates(int dateParam) {
                DateParam = dateParam;
            }
            @SerializedName(DateParam)
            @Expose
            private Integer nowDate;
            @SerializedName("2021-04-19")
            @Expose
            private Integer _20210419;




            public Integer getNowDate() {
                return nowDate;
            }



            public Integer get20210419() {
                return _20210419;
            }*/


        }
    }








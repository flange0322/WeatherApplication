package com.example.user.weatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String word;
    TextView textView_update;
    TextView textView_country;
    TextView textView_temperature;
    TextView textView_main;
    TextView textView_lonandlat;
    TextView textView_pressure;
    TextView textView_speed;
    Button button;
    ImageView imageView;
    boolean importing = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textView_country = findViewById(R.id.textView_country);
        textView_temperature = findViewById(R.id.textView_temperature);
        textView_main = findViewById(R.id.textView_main);
        textView_lonandlat = findViewById(R.id.textView_lonandlat);
        textView_pressure = findViewById(R.id.textView_pressure);
        textView_speed = findViewById(R.id.textView_speed);

        final OkHttpClient client = new OkHttpClient();
        final ExecutorService service = Executors.newSingleThreadExecutor();

        //預防App可能因為網路的活動而使App等待回應的時間過久，所以將網路程序拉出主要執行緒上
        service.submit(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22")
                        .build();
                try{
                    final Response response = client.newCall(request).execute();
                    word = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new GsonBuilder().registerTypeAdapter(JsonGet.class, new JsonGetDeserializer()).create();
                            JsonGet jg = gson.fromJson(word, JsonGet.class);

                            textView_country.setText(jg.getName().toString());
                            textView_temperature.setText(jg.getTemp().toString() + "\u2103");
                            textView_main.setText(jg.getMain().toString());
                            textView_lonandlat.setText(jg.getLon().toString() + "," + jg.getLat());
                            textView_pressure.setText(Integer.toString(jg.getPressure())+"Pa");
                            textView_speed.setText(jg.getSpeed().toString()+"m/s");
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

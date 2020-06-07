package com.sample;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class weatherAPI {

    public void getUpdate( float lon,float lat ) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=1a70dedd2b4b93a5efc378a159ee52c2")
                .method("GET", null)
                .addHeader("appid", "1a70dedd2b4b93a5efc378a159ee52c2")
                .build();
        Response response = client.newCall(request).execute();


        System.out.println(response.body().string());
    }
}

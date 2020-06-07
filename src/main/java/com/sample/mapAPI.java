package com.sample;

import com.jayway.jsonpath.JsonPath;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class mapAPI {

    public String getData(String city) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://api.mapbox.com/geocoding/v5/mapbox.places/"+city+".json?limit=1&access_token=pk.eyJ1IjoibHZuYXJrZSIsImEiOiJja2IxN3N2c3cwYmE2Mnptbm00Ymd1aDdhIn0.juuMaGPEWb3h9zac907E6A")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        String data = JsonPath.read(response.body().string(), "$.features.[:1].center").toString();


        System.out.println(data);

        return data;


    }
}

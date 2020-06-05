package com.sample;//package com.sample;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;



@WebServlet(urlPatterns = {"/geoLocation","/getWeatherUpdate"})

public class weatherServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String api = req.getServletPath();
        String locationUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/";
        String apiKey = "pk.eyJ1IjoibHZuYXJrZSIsImEiOiJja2IxN3N2c3cwYmE2Mnptbm00Ymd1aDdhIn0.juuMaGPEWb3h9zac907E6A ";


        switch (api){
            case "geoLocation":{

                String location = req.getParameter("location");
                String country = req.getParameter("country");
                PrintWriter writer = resp.getWriter();
                writer.println("Location : "+location+" Country: "+country);

                locationUrl = "https://api.mapbox.com/geocoding/v5/mapbox.places/Washington.json?limit=1&access_token=pk.eyJ1IjoibHZuYXJrZSIsImEiOiJja2IxN3N2c3cwYmE2Mnptbm00Ymd1aDdhIn0.juuMaGPEWb3h9zac907E6A";

                HttpURLConnection conn=null;
                BufferedReader reader=null;
                try{
                    //Declare the connection to weather api url
                    URL url = new URL(locationUrl);

                    conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "application/json");
                   // conn.setRequestProperty("apikey",apiKey);

                    if (conn.getResponseCode() != 200) {
                        throw new RuntimeException("HTTP GET Request Failed with Error code : "
                                + conn.getResponseCode());
                    }
                    else{
                        System.out.println("API worked!");
                    }

                    //Read the content from the defined connection
                    //Using IO Stream with Buffer raise highly the efficiency of IO
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
                    String output = null;
                    while ((output = reader.readLine()) != null) {
                        writer.append(output);
                        System.out.println(output);
                    }

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            break;
            case "getWeatherUpdate":{

            }
            break;

        }


    }
    }



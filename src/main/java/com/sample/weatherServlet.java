package com.sample;//package com.sample;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(urlPatterns = {"/geoLocation","/getWeatherUpdate"})

public class weatherServlet extends HttpServlet{
    static ArrayList<Float> values = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final String api = req.getServletPath();
        String location = "";
        String country = "";



        switch (api){
            case "/geoLocation":{

                System.out.println("Inside geoLocation servlet");

                location = req.getParameter("location");
                country = req.getParameter("country");
                System.out.println("Getting values");

                mapAPI mapAPI = new mapAPI();
                String data = mapAPI.getData(location);


                String str = "";
                for(int i=0;i<data.length();i++){

                    if(data.charAt(i)>='0' && data.charAt(i)<='9'|| data.charAt(i)=='.'||data.charAt(i)=='-'){
                        str = str + data.charAt(i);
                    }

                    else if(data.charAt(i)==','||data.charAt(i)==']'){
                        System.out.println(""+str);
                        float val = Float.parseFloat(str);
                        System.out.println(val);
                        values.add(val);
                        str = "";
                        if(data.charAt(i)==']')
                            break;
                    }
                }

//                PrintWriter writer = resp.getWriter();
//                writer.println("Location : "+location+"\nCountry: "+country);
//                writer.println("Longitude and Latitude"+ data);

                RequestDispatcher view = req.getRequestDispatcher("result.jsp");
                view.forward(req, resp);

            }
            break;
            case "/getWeatherUpdate":{


                System.out.println(""+values.size());
                for (int i =0;i< values.size();i++){
                    System.out.println(values.get(i));
                }
                weatherAPI weatherAPI = new weatherAPI();
                weatherAPI.getUpdate(values.get(0),values.get(1));

                values.removeAll(values);
            }
            break;

        }


    }
    }



package com.example.ricardosgobbe.manager.controlers.http;

import com.example.ricardosgobbe.manager.controlers.objects.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ricardo.sgobbe on 18/03/2016.
 */
public class LoginService {
    private static final String mURL = "http://10.0.3.2:3000/user";

    public static User getUser(){
        User user = new User();
        try {
            URL url = new URL(mURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseConde = conn.getResponseCode();

            if(responseConde == HttpURLConnection.HTTP_OK){
                InputStream inputStream = conn.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                user = mapper.readValue(inputStream, User.class);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
}

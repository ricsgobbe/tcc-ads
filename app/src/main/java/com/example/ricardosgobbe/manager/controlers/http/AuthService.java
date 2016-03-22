package com.example.ricardosgobbe.manager.controlers.http;

import android.util.Base64;

import com.example.ricardosgobbe.manager.controlers.objects.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by ricardo.sgobbe on 18/03/2016.
 */
public class AuthService {
    private static final String mURL = "http://10.11.50.167/user";

    public static List<User> getAuth(){
        List<User> user = null;
        try {
            URL url = new URL(mURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String sign = "binhotheking:12345";
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Basic" + Base64.encodeToString(sign.getBytes(), Base64.NO_WRAP).replace("\n", ""));
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(2000);

            int resultCode = conn.getResponseCode();
            if(resultCode == HttpURLConnection.HTTP_OK){
                user = LoginService.getUser();
                conn.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}

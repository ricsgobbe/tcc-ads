package com.example.ricardosgobbe.manager.controlers.http;

import com.example.ricardosgobbe.manager.controlers.objects.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ricardo.sgobbe on 22/03/2016.
 */
public class UserPostService {

    private static final String mURL = "http://10.0.3.2:3000/user";
    private static boolean flag;
    public static boolean postNewUser(User user){

        try {
            URL url = new URL(mURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();

            JSONObject obj = new JSONObject();
            obj.put("name", user.getName());
            obj.put("email", user.getEmail());
            obj.put("user", user.getUser());
            obj.put("password", user.getPassword());

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(obj.toString());
            out.close();

            int responseCode = conn.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                flag = true;
            }else{
                flag =  false;
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

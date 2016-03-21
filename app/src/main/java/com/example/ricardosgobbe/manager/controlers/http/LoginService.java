package com.example.ricardosgobbe.manager.controlers.http;

import android.annotation.TargetApi;
import android.os.Build;

import com.example.ricardosgobbe.manager.controlers.objects.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricardo.sgobbe on 18/03/2016.
 */
public class LoginService {
    private static final String mURL = "http://10.0.3.2:3000/user";



    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static List<User> getUser(){
        List<User> mListUser = new ArrayList<>();

        try {
            URL url = new URL(mURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseConde = conn.getResponseCode();
            StringBuilder builder = new StringBuilder();
            if(responseConde == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line);

                }
                JSONArray array = new JSONArray(builder.toString());
                for(int i = 0; i < array.length(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    User user = new User();
                    user.setName(obj.getString("name"));
                    user.setEmail(obj.getString("email"));
                    user.setUser(obj.getString("user"));
                    user.setPassword(obj.getString("password"));
                    mListUser.add(user);
                }
            }
            conn.disconnect();

            builder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mListUser;
    }
}

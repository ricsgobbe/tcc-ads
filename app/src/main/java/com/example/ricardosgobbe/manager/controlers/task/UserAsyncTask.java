package com.example.ricardosgobbe.manager.controlers.task;

import android.os.AsyncTask;

import com.example.ricardosgobbe.manager.controlers.http.AuthService;
import com.example.ricardosgobbe.manager.controlers.http.LoginService;
import com.example.ricardosgobbe.manager.controlers.objects.User;

/**
 * Created by ricardo.sgobbe on 18/03/2016.
 */
public class UserAsyncTask extends AsyncTask<Void, Void, User> {
    @Override
    protected User doInBackground(Void... params) {
       return LoginService.getUser();
    }
}

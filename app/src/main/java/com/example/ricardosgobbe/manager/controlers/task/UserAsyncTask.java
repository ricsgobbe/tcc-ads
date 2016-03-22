package com.example.ricardosgobbe.manager.controlers.task;

import android.os.AsyncTask;

import com.example.ricardosgobbe.manager.controlers.http.AuthService;
import com.example.ricardosgobbe.manager.controlers.http.LoginService;
import com.example.ricardosgobbe.manager.controlers.objects.User;

import java.util.List;

/**
 * Created by ricardo.sgobbe on 18/03/2016.
 */
public class UserAsyncTask extends AsyncTask<Void, Void, List<User>> {
    @Override
    protected List<User> doInBackground(Void... params) {
       return AuthService.getAuth();
    }
}

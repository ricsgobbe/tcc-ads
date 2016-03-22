package com.example.ricardosgobbe.manager.controlers.task;

import android.os.AsyncTask;

import com.example.ricardosgobbe.manager.controlers.http.UserPostService;
import com.example.ricardosgobbe.manager.controlers.objects.User;

/**
 * Created by ricardo.sgobbe on 22/03/2016.
 */
public class PostUserAsync extends AsyncTask<User, Void, Boolean> {
    @Override
    protected Boolean doInBackground(User... params) {
        return UserPostService.postNewUser(params[0]);
    }
}

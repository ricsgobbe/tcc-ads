package com.example.ricardosgobbe.manager.controlers.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.ricardosgobbe.manager.R;
import com.example.ricardosgobbe.manager.controlers.objects.User;
import com.example.ricardosgobbe.manager.controlers.task.UserAsyncTask;

/**
 * Created by ricardo.sgobbe on 28/01/2016.
 */
public class ActivityLogin extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText mUserField;
    private EditText mPasswordField;
    private ProgressBar mProgressbar;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private ScrollView mScrollview;
    private UserLoginService mTask;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mToolbar = (Toolbar) findViewById(R.id.bar_login);
        setSupportActionBar(mToolbar);
        bindElements();


    }

    private void bindElements() {
        mBtnLogin = (Button) findViewById(R.id.login_button);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        mBtnRegister = (Button) findViewById(R.id.register_button);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterForm = new Intent(ActivityLogin.this, ActivityRegisterLogin.class);
                startActivity(goToRegisterForm);
            }
        });
        mUserField = (EditText) findViewById(R.id.username_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        mScrollview = (ScrollView) findViewById(R.id.login_form);
        mProgressbar = (ProgressBar) findViewById(R.id.progressbar_id);

    }

    private void attemptLogin() {

        if (mTask != null) {
            return;
        }

        mUserField.setError(null);
        mPasswordField.setError(null);

        String user = mUserField.getText().toString();
        String password = mPasswordField.getText().toString();
        View requestFocus = null;
        boolean cancel = false;

        if (TextUtils.isEmpty(password) || !validField(password)) {
            mPasswordField.setError("Invalid field");
            cancel = true;
            requestFocus = mPasswordField;
        }

        if (TextUtils.isEmpty(user) || !validField(user)) {
            mUserField.setError("Invalid field");
            cancel = true;
            requestFocus = mUserField;
        }


        if (!cancel) {
            progressBar(true);
           /* UserLoginService login = new UserLoginService();
            login.execute((Void[]) null);*/
            UserAsyncTask task = new UserAsyncTask(){
                @Override
                protected void onPostExecute(User user) {
                    mTask = null;
                    progressBar(false);
                    if (user != null) {
                        //go to activity
                        Intent goToMainActivity = new Intent(ActivityLogin.this, MainActivity.class);
                        startActivity(goToMainActivity);
                    } else {
                        Toast.makeText(getBaseContext(), "User or password incorrect ", Toast.LENGTH_LONG).show();
                        mPasswordField.requestFocus();
                    }
                }
            };
            task.execute();
        } else
            requestFocus.requestFocus();
    }

    private boolean validField(String password) {
        if (password.length() > 4) {
            return true;
        }
        return false;
    }


    private class UserLoginService extends AsyncTask<Void, Void, Boolean> {


        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            mTask = null;
            progressBar(false);
            if (aBoolean) {
                //go to activity
                Intent goToMainActivity = new Intent(ActivityLogin.this, MainActivity.class);
                startActivity(goToMainActivity);
            } else {
                Toast.makeText(getBaseContext(), "User or password incorrect ", Toast.LENGTH_LONG).show();
                mPasswordField.requestFocus();
            }

        }
    }

    private void progressBar(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int animDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
            mScrollview.animate().alpha(show ? 0 : 1).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mScrollview.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            }).start();

            mProgressbar.animate().alpha(show ? 1 : 0).setDuration(animDuration).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            }).start();
        } else {
            mScrollview.setVisibility(show ? View.GONE : View.VISIBLE);
            mProgressbar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}

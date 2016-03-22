package com.example.ricardosgobbe.manager.controlers.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ricardosgobbe.manager.R;
import com.example.ricardosgobbe.manager.controlers.http.UserPostService;
import com.example.ricardosgobbe.manager.controlers.objects.User;
import com.example.ricardosgobbe.manager.controlers.task.PostUserAsync;

/**
 * Created by ricardo.sgobbe on 02/02/2016.
 */
public class ActivityRegisterLogin extends AppCompatActivity {


    private Toolbar mToolbar;
    private Button mRegister;
    private Button mLogin;
    private EditText mNameTxt;
    private EditText mEmailTxt;
    private EditText mUserTxt;
    private EditText mPasswordTxt;
    private View requestFocus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_login_activity);
        mToolbar = (Toolbar) findViewById(R.id.register_bar);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        }
        bindElements();
    }

    private void bindElements() {
        mNameTxt = (EditText) findViewById(R.id.name_field);
        mEmailTxt = (EditText) findViewById(R.id.register_field);
        mUserTxt = (EditText) findViewById(R.id.register_username);
        mPasswordTxt = (EditText) findViewById(R.id.register_pass);
        mRegister = (Button) findViewById(R.id.register_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields();
            }
        });
        mLogin = (Button) findViewById(R.id.register_login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityRegisterLogin.this.finish();
            }
        });
    }

    private void checkFields() {
        requestFocus = null;
        setNullOnErrors();
        errorMsg(mPasswordTxt);
        errorMsg(mUserTxt);
        errorMsg(mEmailTxt);
        errorMsg(mNameTxt);

        if(requestFocus != null){
            requestFocus.requestFocus();
        }else{
            //save user information on db
            User user = new User();
            user.setName(mNameTxt.getText().toString());
            user.setEmail(mEmailTxt.getText().toString());
            user.setUser(mUserTxt.getText().toString());
            user.setPassword(mPasswordTxt.getText().toString());
            PostUserAsync postUser = new PostUserAsync(){
                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if(aBoolean){
                        Toast.makeText(ActivityRegisterLogin.this, "Login Registred !", Toast.LENGTH_LONG).show();
                    }
                }
            };
            postUser.execute(user);
            ActivityRegisterLogin.this.finish();
        }
    }

    private void setNullOnErrors() {
        mNameTxt.setError(null);
        mEmailTxt.setError(null);
        mLogin.setError(null);
        mPasswordTxt.setError(null);
    }

    private void errorMsg(EditText text){
        boolean flag = false;
        if(TextUtils.isEmpty(text.getText().toString())){
            text.setError("This field must be filled !");
            flag = true;
        }
        else if(text.length() < 4){
            text.setError( "Too short !");
            flag = true;
        }
        if(flag)
            requestFocus = text;
    }
}

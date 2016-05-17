package com.example.administrator.pbsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

public class LoginActivity extends Activity implements View.OnClickListener{
    private ProgressBar progressBar;
    private EditText username;
    private EditText passwordtext;
    private String user;
    private String password;
    private loginHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        passwordtext = (EditText) findViewById(R.id.password);
        (findViewById(R.id.login_button)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        user = username.getText().toString();
        password = passwordtext.getText().toString();
        boolean error = false;
        if (TextUtils.isEmpty(user)){
            onUsernameEmpty();
            error = true;
        }
        if (TextUtils.isEmpty(password)){
            onPasswordEmpty();
            error = true;
        }
        if (!error){
            onNotempty(user, password);
        }
    }

    public void onUsernameEmpty() {
        username.setError(Html.fromHtml("<font color=#ff0000>Username cannot be empty</font>"));
    }

    public void onPasswordEmpty() {
        passwordtext.setError(Html.fromHtml("<font color=#ff0000>Password cannot be empty</font>"));
    }

    public void onNotempty(String user, String password) {
        progressBar.setVisibility(View.VISIBLE);
        if (onSuccess(user, password)) {
            progressBar.setVisibility(View.INVISIBLE);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            passwordtext.setError(Html.fromHtml("<font color=#ff0000>username or password is wrong</font>"));
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public boolean onSuccess(String user, String password) {
        //传送至服务器对比，此时先忽略，日后完善

        if (user.equals("xiebj") && password.equals("123")) {
            return true;
        } else {
            return false;
        }
    }
    //日后完善
    class loginHandler extends Handler {
        public loginHandler() {
        }

        public loginHandler(Looper L) {
            super(L);
        }
        @Override
        public void handleMessage(Message msg) {

        }
    }
}
package com.example.administrator.pbsystem.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.administrator.pbsystem.R;

public class LoginActivity extends Activity implements View.OnClickListener {
    private ProgressBar progressBar;
    private EditText username;
    private EditText passwordtext;
    private String user;
    private String password;
    private loginHandler handler;
    private CheckBox remem;
    private Boolean isremem;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("remember", MODE_PRIVATE);
        editor = sp.edit();
        isremem = sp.getBoolean("remem", false);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        passwordtext = (EditText) findViewById(R.id.password);
        remem = (CheckBox) findViewById(R.id.remember_pass);
        if (isremem) {
            username.setText(sp.getString("user", null));
            passwordtext.setText(sp.getString("password", null));
            remem.setChecked(true);
        }
        (findViewById(R.id.login_button)).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        //与后台交互验证密码是否正确
        boolean error = false;
        if (TextUtils.isEmpty(user)) {
            onUsernameEmpty();
            error = true;
        }
        if (TextUtils.isEmpty(password)) {
            onPasswordEmpty();
            error = true;
        }
        if (!error) {
            onNotempty(user, password);
        }
        //实现记住密码功能，用本地sharedpreference保存
        if (remem.isChecked()) {
            editor.putBoolean("remem", true);
            editor.putString("user", user);
            editor.putString("password", password);
            editor.commit();
        } else {
            editor.putBoolean("remem", false);
            editor.commit();
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

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("确认退出吗？")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
        // super.onBackPressed();
    }
}
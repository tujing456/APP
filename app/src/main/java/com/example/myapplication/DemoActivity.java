package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DemoActivity extends AppCompatActivity {
    //定义控件对象
    private EditText etUsername;
    private  EditText etPassword;
    private CheckBox cbautoLogin;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件对象
        etUsername =  findViewById(R.id.et_Username);
        etPassword = findViewById(R.id.et_Password);
        cbautoLogin = findViewById(R.id.cb_auto_login);
        btnLogin = findViewById(R.id.btn_login);
        //设置按钮的监听器
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    private void login(){
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"用户名或密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if ("android".equals(username) && "123456".equals(password)) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,InfoActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"用户或密码不正确", Toast.LENGTH_LONG).show();
        }
    }
}
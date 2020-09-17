package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvUsername;
    private EditText etRealname;
    private RadioGroup sexGroup;
    private CheckBox cbJava, cbAndroid, cbDatabase;

    private RadioButton rbMale, rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
//初始化界面控件
        //获取控件对象
        tvUsername = findViewById(R.id.tv_username);
        etRealname = findViewById(R.id.et_realname);
        sexGroup = findViewById(R.id.group_sex);
        cbJava = findViewById(R.id.cb_java);
        cbAndroid = findViewById(R.id.cb_android);
        cbDatabase = findViewById(R.id.cb_database);


        rbMale = findViewById(R.id.rbtn_male);
        rbFemale = findViewById(R.id.rbtn_female);
//获取登录界面传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("username");
            tvUsername.setText(name);
        }
//设置点击事件，键盘事件的监听器
        Button btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(this);

        //键盘事件处理

        etRealname.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                //按回车则隐藏软键盘
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && imm.isActive()) {
                        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    @Override

    public void onClick(View view) {
        if (view.getId() == R.id.btn_confirm) {
            getInfo();
        }
    }

    private void getInfo() {
        //获取控件的值
        String username = tvUsername.getText().toString().trim();
        String realname = etRealname.getText().toString().trim();
        String sex = "男";
        String favorite = "";
        //获取选择的RadioButton的id；
        int id = sexGroup.getCheckedRadioButtonId();
        if (id == R.id.rbtn_male) {
            sex = "男";
        } else {
            sex = "女";
        }

        if (cbJava.isChecked()) {
            favorite += cbJava.getText().toString() + ", ";

        }
        if (cbAndroid.isChecked()){
            favorite += cbAndroid.getText().toString() + ", ";

        }
        if (cbDatabase.isChecked()){
            favorite += cbDatabase.getText().toString() + ", ";
        }

        String info = "用户名：" + username + "/姓名: "
                +realname + "\n性别:"
                +sex + "\n喜欢的课程:"
                +favorite.trim().substring(0,favorite.trim().length() - 1);
        Toast.makeText(InfoActivity.this,info,Toast.LENGTH_LONG).show();
        //跳转至主界面
        Intent intent = new Intent(InfoActivity.this,MainActivity.class);
        startActivity(intent);
    }
}

package zjut.xiaofeng.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import zjut.xiaofeng.myapplication1.bean.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    //分别声明布局文件中用到的变量
    private EditText username;
    private EditText password;
    private RadioButton radioButton_userAgreement;
    private ImageButton button_login;
    private Button button_loginByPhone;
    private Button button_reFindPassword;
    private Button button_register;
    //声明数据存储对象
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public void initView() {
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //绑定控件，这样变量就代表了被绑定的控件
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        button_login = findViewById(R.id.btn_login);
        radioButton_userAgreement = findViewById(R.id.login_radioButton_serviceAgreement);
        button_loginByPhone = findViewById(R.id.btn_phone);
        button_reFindPassword = findViewById(R.id.btn_retrieve);
        button_register = findViewById(R.id.btn_register);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

//        监听是否点击注册按钮，点击则跳转到注册界面
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                Intent intent = new Intent();
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

        });
    }
}
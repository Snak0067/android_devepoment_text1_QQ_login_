package zjut.xiaofeng.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import zjut.xiaofeng.myapplication1.util.MD5Util;

public class MainActivity extends AppCompatActivity {
    //分别声明布局文件中用到的变量
    private EditText usernameEdit;
    private EditText passwordEdit;
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
        usernameEdit = findViewById(R.id.login_username);
        passwordEdit = findViewById(R.id.login_password);
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
        Intent intent = new Intent();


        /**
         * 监听是否点击注册按钮，点击则跳转到注册界面
         */
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View w) {
                //前一个（MainActivity.this）是目前页面，后面一个是要跳转的下一个页面
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }

        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                } else if (!isExistUserInfo(userName, password)) {
                    Toast.makeText(MainActivity.this, "用户名或密码错误，请重试", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    intent.setClass(MainActivity.this, LoginSuccessActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     *
     * @param userName
     * @return
     */
    private boolean isExistUserInfo(String userName, String password) {

        SharedPreferences sp = getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String password_stored = sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if (password_stored != null && password_stored.equals(MD5Util.MD5(password))) {
            return true;
        }
        return false;
    }
}
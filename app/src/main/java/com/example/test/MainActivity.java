package com.example.test;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    //Our username is 123456 and the passwd is 111111 to be an example
    private static final String VALID_USERNAME = "123456";
    private static final String VALID_PASSWORD = "111111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化 UI 组件
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button guestButton = findViewById(R.id.guestButton);

        // 设置输入过滤器，限制只能输入小写字母和数字
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char ch = source.charAt(i);
                    if (!Character.isLowerCase(ch) && !Character.isDigit(ch)) {
                        return ""; // 拒绝不符合要求的输入
                    }
                }
                return null;
            }
        };

        usernameEditText.setFilters(new InputFilter[]{filter});
        passwordEditText.setFilters(new InputFilter[]{filter});

        // 登录按钮点击事件
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Home_page.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 访客模式按钮点击事件
        guestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Logged in as Guest", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Home_page.class);
                startActivity(intent);
            }
        });
    }
}
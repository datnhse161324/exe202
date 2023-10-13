package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView btnReg;
    EditText username, password;
    Button btnLog, btnLogEmployee;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnReg= findViewById(R.id.register);
        btnLog= findViewById(R.id.login);
        btnLogEmployee = findViewById(R.id.btnEmployeeLogin);
        username= findViewById(R.id.LUsername);
        password= findViewById(R.id.LPassword);
        DB= new DBHelper(this);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                boolean checkUserPass = checkUserPass(user, pass);
                if(checkUserPass== true){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        btnLogEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                boolean checkUserPass = checkUserPass(user, pass);
                if(checkUserPass== true){
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), ShipperMainActivity.class);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkUserPass(String user, String pass)
    {
        if(user.equals("")|| pass.equals(""))
            Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
        boolean checkUserPass= DB.checkUserPass(user, pass);
        return checkUserPass;
    }
}
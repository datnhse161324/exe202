package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    TextView btnLo;
    Button btnRegi;
    EditText username, password, repassword, phone, address;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnLo= findViewById(R.id.login2);
        username= findViewById(R.id.RUsername);
        password= findViewById(R.id.RPassword);
        repassword= findViewById(R.id.RePassword);
        phone= findViewById(R.id.RPhone);
        address= findViewById(R.id.RAddress);
        btnRegi= findViewById(R.id.register2);
        DB= new DBHelper(this);

        btnRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user= username.getText().toString();
                String pass= password.getText().toString();
                String repass= repassword.getText().toString();
                String pho= phone.getText().toString();
                String aress= address.getText().toString();

                if(user.equals("")|| pass.equals("")|| repass.equals("")|| pho.equals("")|| aress.equals(""))
                    Toast.makeText(RegisterActivity.this, "Xin hãy nhập hết tất cả các ô!",Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkUser= DB.checkUser(user);
                        if(checkUser == false){
                            Boolean insert= DB.insert(user, pass, "customer",pho, aress);
                            if (insert == true){
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(RegisterActivity.this, "Tên tài khoản bị đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không trùng nhau",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnLo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
    }
}
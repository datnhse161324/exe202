package com.example.exe201;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewProfileActivity extends AppCompatActivity {

    ImageView btnBack;
    EditText etName, etPhone, etBirth, etGen, etAdd;
    Button btnUp, btnView;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        btnBack= findViewById(R.id.back);
        etName= findViewById(R.id.profileName);
        etPhone= findViewById(R.id.profilePhone);
        etBirth= findViewById(R.id.profileBirth);
        etGen= findViewById(R.id.profileGen);
        etAdd= findViewById(R.id.profileAddr);
        btnUp= findViewById(R.id.update);
        btnView= findViewById(R.id.view);
        DB= new DBHelper(this);

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");


        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= etName.getText().toString();
                String phone= etPhone.getText().toString();
                String birth= etBirth.getText().toString();
                String gen= etGen.getText().toString();
                String add= etAdd.getText().toString();



                Boolean checkUpdate= DB.update(username, name, phone, birth, gen, add);
                if (checkUpdate== true)
                    Toast.makeText(ViewProfileActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ViewProfileActivity.this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= getIntent();
                String username= intent.getStringExtra("user");
                Cursor res= DB.getData(username);
                if(res.getCount()== 0){
                    Toast.makeText(ViewProfileActivity.this, "Lỗi hiển thị", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Họ và tên: "+res.getString(3)+"\n");
                    buffer.append("Số điện thoại: "+res.getString(5)+"\n");
                    buffer.append("Ngày sinh: "+res.getString(6)+"\n");
                    buffer.append("Giới tính: "+res.getString(7)+"\n");
                    buffer.append("Địa chỉ: "+res.getString(8)+"\n");
                    buffer.append("Điểm: "+res.getInt(9)+"\n");
                }

                AlertDialog.Builder builder= new AlertDialog.Builder(ViewProfileActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Thông tin cá nhân");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }


}
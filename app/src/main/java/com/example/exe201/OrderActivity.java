package com.example.exe201;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;

import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    Button btnSelectLoca, btnPickTime, btnPickDate, btnCreateOrder;
    ImageView btnBack2;
    TextView txtLocation;
    DBHelper DB;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btnSelectLoca = findViewById(R.id.selectLoca);
        btnPickTime = findViewById(R.id.idBtnPickTime);
        btnPickDate = findViewById(R.id.idBtnPickDate);
        btnCreateOrder = findViewById(R.id.btnCreateOrder);
        txtLocation = findViewById(R.id.tvAddress);
        btnBack2 = findViewById(R.id.back2);
        DB= new DBHelper(this);
        builder= new AlertDialog.Builder(this);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        txtLocation.setText(data.getStringExtra("location"));
                    }
                }
        );

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");
        btnSelectLoca.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), MapActivity.class);
            intent1.putExtra("user",username);
            intent1.putExtra("address", txtLocation.getText());
            launcher.launch(intent1);

        });
        btnPickTime.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            // on below line we are getting our hour, minute.
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // on below line we are initializing our Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(OrderActivity.this, android.R.style.Theme_Holo_Light_Dialog ,
                    (view, hourOfDay, minute1) -> {
                        btnPickTime.setText(hourOfDay + ":" + minute1);
                    }, hour, minute, false);
            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.setTitle("Chọn giờ:");
            timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timePickerDialog.show();
        });
        btnPickDate.setOnClickListener(v -> {
            // on below line we are getting
            // the instance of our calendar.
            final Calendar c = Calendar.getInstance();

            // on below line we are getting
            // our day, month and year.
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // on below line we are creating a variable for date picker dialog.
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    OrderActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // on below line we are setting date to our text view.
                        btnPickDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year, month, day);
            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            datePickerDialog.show();
        });
        btnBack2.setOnClickListener(v -> {
            finish();
        });
        btnCreateOrder.setOnClickListener(v -> {
            builder.setTitle("Xác nhận đặt lịch")
                    .setMessage("Bạn có chắc muốn đặt lịch?")
                    .setCancelable(true)
                    .setPositiveButton("Có", (dialog, which) -> {
                        insertOrder();
                        dialog.cancel();
                    })
                    .setNegativeButton("Không",(dialog, which) ->{
                        dialog.cancel();
                    }).show();
        });
    }
    private void insertOrder(){
        DB.queryData("create table if not exists UserOrder (orderID Integer Primary Key Autoincrement,orderCode nvarchar(20)," +
                " userName nvarchar(20), materialAmount decimal, createDate nvarchar(20), status nvarchar(20), address nvarchar(50), orderDate nvarchar(20), orderTime nvarchar(20), orderPoint Integer,Constraint fk_UserOrder Foreign Key (userName) references User(userName))");
        String userName = getIntent().getStringExtra("user");
        String address = txtLocation.getText().toString();
        String orderTime = btnPickTime.getText().toString();
        String orderDate = btnPickDate.getText().toString();
        Cursor res= DB.getData(userName);
        if(res.getCount()== 0){
            Toast.makeText(OrderActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
        }else if(userName.equals("")||address.equals("")||orderTime.equals("Chọn")||orderDate.equals("Chọn")){
            Toast.makeText(OrderActivity.this, "Xin hãy nhập hết tất cả các ô!",Toast.LENGTH_SHORT).show();
        }else{
            Boolean insert= DB.insertOrder(userName, address, orderTime, orderDate);
            if(insert){
                Toast.makeText(OrderActivity.this, "Đặt lịch thành công",Toast.LENGTH_SHORT).show();
                Intent out = new Intent(getApplicationContext(), ViewMyOrderActivity.class);
                out.putExtra("user", userName);
                startActivity(out);
                finish();
            }else{
                Toast.makeText(OrderActivity.this, "Đặt lịch thất bại",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
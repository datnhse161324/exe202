package com.example.exe201;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> launcher;
    Button btnChoose, btnPickTime;
    TextView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        btnChoose= findViewById(R.id.selectLoca);
        btnPickTime = findViewById(R.id.idBtnPickTime);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        location.setText(data.getStringExtra("location"));
                    }
                }
        );

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");
        location = findViewById(R.id.tvAddress);
        btnChoose.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), MapActivity.class);
            intent1.putExtra("user",username);
            intent1.putExtra("address", location.getText());
            launcher.launch(intent1);

        });
        btnPickTime.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            // on below line we are getting our hour, minute.
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // on below line we are initializing our Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(OrderActivity.this,
                    (view, hourOfDay, minute1) -> {
                        // on below line we are setting selected time
                        // in our text view.
                        btnPickTime.setText(hourOfDay + ":" + minute1);
                    }, hour, minute, false);
            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.show();
        });
    }
}
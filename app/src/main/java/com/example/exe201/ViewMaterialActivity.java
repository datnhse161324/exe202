package com.example.exe201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewMaterialActivity extends AppCompatActivity {

    ImageView btnBack;
    ListView lvMaterial;
    DBHelper DB;
    ArrayList<Material> arrayMaterial;
    MaterialAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_material);
        btnBack= findViewById(R.id.back3);
        lvMaterial= findViewById(R.id.lvMaterial);
        DB= new DBHelper(this);
        arrayMaterial= new ArrayList<>();
        adapter=new MaterialAdapter(this, R.layout.material_row,arrayMaterial);
        lvMaterial.setAdapter(adapter);

        Intent intent= getIntent();
        String username= intent.getStringExtra("user");

        getView();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user",username);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getView() {
        DB.queryData("Create Table if not exists Material (materialID Integer Primary Key Autoincrement," +
                "materialName nvarchar(20), unitPrice Integer)");
//        DB.queryDataMaterial();
        Cursor dataMaterial= DB.getVMaterial();
        arrayMaterial.clear();
        while (dataMaterial.moveToNext()){
            String ten= dataMaterial.getString(1);
            int gia= dataMaterial.getInt(2);
            int id= dataMaterial.getInt(0);
            arrayMaterial.add(new Material(id, ten, gia));
        }
        adapter.notifyDataSetChanged();
    }
}
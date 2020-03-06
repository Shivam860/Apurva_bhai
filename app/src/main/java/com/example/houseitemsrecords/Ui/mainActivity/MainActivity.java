package com.example.houseitemsrecords.Ui.mainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.houseitemsrecords.R;
import com.example.houseitemsrecords.Utils.Utils;
import com.example.houseitemsrecords.clothes;
import com.example.houseitemsrecords.furniture;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button selectFurnitureBtn;
    private Button selectClothsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callBaseMethod();
        Utils.customToast(this,"Connection Established..");
    }

    private void callBaseMethod(){
        setReferences();
        setListeners();
    }

    private void setReferences(){
        selectFurnitureBtn = findViewById(R.id.button);
        selectClothsBtn = findViewById(R.id.button2);
    }

    private void setListeners(){
        selectClothsBtn.setOnClickListener(this);
        selectFurnitureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                navigateActivity(furniture.class);
                break;

            case R.id.button2:
                navigateActivity(clothes.class);
                break;
        }
    }

    private <T> void navigateActivity(Class<T> navigationClass){
        startActivity(new Intent(this,navigationClass));
    }
}

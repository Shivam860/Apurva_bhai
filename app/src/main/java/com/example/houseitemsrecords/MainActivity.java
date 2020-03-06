package com.example.houseitemsrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openfurniture();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openclothes();
            }
        });

        Toast.makeText(MainActivity.this,"Firebase connected " ,Toast.LENGTH_LONG).show();
    }

    private void Openfurniture() {
        Intent intent1 = new Intent(this,furniture.class);
        startActivity(intent1);
    }

    private void Openclothes() {
        Intent intent = new Intent(this,clothes.class );
        startActivity(intent);
    }
}

package com.example.houseitemsrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clothes extends AppCompatActivity {

    private Button button ,bpoonam,bapurva,bbaby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        button = findViewById(R.id.addbut);
        bpoonam = findViewById(R.id.button2);
        bapurva = findViewById(R.id.button);
        bbaby = findViewById(R.id.babycloth);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddclothes();
            }
        });

        bpoonam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openpoonam();
            }
        });

    }


    private void openpoonam() {
        Intent intent = new Intent(clothes.this, She.class);
        startActivity(intent);
    }


    private void openaddclothes() {
        Intent intent = new Intent(clothes.this,Add_clothes.class);
        startActivity(intent);
    }
}

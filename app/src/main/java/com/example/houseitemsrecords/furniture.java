package com.example.houseitemsrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class furniture extends AppCompatActivity {

    private Button b1,b2,b3,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        b1 = findViewById(R.id.kitchenbut);
        b2 = findViewById(R.id.bedbutton);
        b3 = findViewById(R.id.drowbutt);
        b4 = findViewById(R.id.dinibutt);
        b5 = findViewById(R.id.addfurniture);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openkitchenlist();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbedroomlist();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendrowinglist();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendininglist();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFurniture();
            }
        });
    }

    public void addFurniture(){

    }

    private void opendininglist() {

        Intent intent = new Intent(this,diningroom.class);
        startActivity(intent);
    }

    private void opendrowinglist() {

        Intent intent = new Intent(this,drowingroom.class);
        startActivity(intent);
    }

    private void openbedroomlist() {

        Intent intent = new Intent(this,bedroom.class);
        startActivity(intent);
    }

    private void openkitchenlist() {
        Intent intent = new Intent(this,Kitchen.class);
        startActivity(intent);
    }
}

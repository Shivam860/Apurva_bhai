package com.example.houseitemsrecords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class Add_Furniture extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button button_upload_Furniture;
    private Button  button_add_Furniture;
    private ImageView image_view_Furniture;
    private EditText description_Furniture;
    private EditText name_Furniture;
    private EditText remark_Furniture;
    private EditText  category_Furniture;

    private ProgressBar furniture_progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__furniture);

        baseMethodCall();
    }

    private void baseMethodCall() {
        setReferences();
        setListeners();
    }

    private void setReferences() {
        button_upload_Furniture = findViewById(R.id.btn_upload_Furniture_image);
        button_add_Furniture = findViewById(R.id.btn_add_furniture);
        image_view_Furniture = findViewById(R.id.furniture_image_view);
        description_Furniture = findViewById(R.id.ET_description);
        name_Furniture = findViewById(R.id.ET_name);
        remark_Furniture = findViewById(R.id.ET_remark);
        category_Furniture = findViewById(R.id.ET_Category);
    }

    private void setListeners() {
        button_upload_Furniture.setOnClickListener(this);
        button_add_Furniture.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_upload_Furniture_image:
                OpenFileChooser();
                break;

            case R.id.btn_add_furniture:
                upload_file();
                break;
        }

    }

    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void upload_file() {

    }
}

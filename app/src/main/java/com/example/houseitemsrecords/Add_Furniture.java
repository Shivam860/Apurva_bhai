package com.example.houseitemsrecords;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.houseitemsrecords.Utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class Add_Furniture extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST_Furniture = 1;

    private Button button_upload_Furniture;
    private Button  button_add_Furniture;
    private ImageView image_view_Furniture;
    private EditText description_Furniture;
    private EditText name_Furniture;
    private EditText remark_Furniture;
    private EditText  category_Furniture;

    private ProgressBar furniture_progressbar;

    private Uri mImageFurnitureImage;
    private StorageTask mUploadTask;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__furniture);

        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Upload_Furniture");

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
        startActivityForResult(intent, PICK_IMAGE_REQUEST_Furniture);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST_Furniture && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageFurnitureImage = data.getData();

            Picasso.with(this).load(mImageFurnitureImage).into(image_view_Furniture);
        }
    }

    private String getFileExtension(Uri urifurniture){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(urifurniture));
    }

    private void upload_file() {
        if(mImageFurnitureImage != null){
            final StorageReference file = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageFurnitureImage));

            mUploadTask = file.putFile(mImageFurnitureImage)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    furniture_progressbar.setProgress(0);
                                }
                            },5000);
                            Utils.customToast(Add_Furniture.this,"Upload Successfully.....");
                            Upload_Furniture upload_fur = new Upload_Furniture(description_Furniture.getText().toString().trim(),
                                    name_Furniture.getText().toString().trim(),
                                    category_Furniture.getText().toString().trim(),
                                    remark_Furniture.getText().toString().trim(),
                                    file.toString());
                            String upload_Furniture_id = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(upload_Furniture_id).setValue(upload_fur);

                    }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Utils.customToast(Add_Furniture.this, e.getMessage());
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            furniture_progressbar.setProgress((int) progress);
                        }
                    });
        }
        else{
            Utils.customToast(Add_Furniture.this, "No File Selected.......");
        }
    }
}

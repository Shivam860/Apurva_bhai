package com.example.houseitemsrecords;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Toast;

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

public class Add_clothes extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mButtonChooseImageCloth , mButtonUploadCloth;
    private EditText mEditTextName,mEditTextDescription,mEditTextRemark,mEditTextCategory;
    private ImageView mImageView;
    private ProgressBar mProgressBarCloth;

    private Uri mImageClothUri;


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);

        mButtonChooseImageCloth = findViewById(R.id.upload_cloth_image);
        mButtonUploadCloth = findViewById(R.id.add_cloth);
        mEditTextName = findViewById(R.id.name);
        mEditTextDescription =  findViewById(R.id.description);
        mEditTextRemark = findViewById(R.id.remark);
        mEditTextCategory = findViewById(R.id.category_clothe);
        mImageView =  findViewById(R.id.cloth_image_view);
        mProgressBarCloth = findViewById(R.id.progress_bar_cloth);

        mStorageRef = FirebaseStorage.getInstance().getReference("Upload_Clothes");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Upload_Clothes");

        mButtonChooseImageCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mButtonUploadCloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(mUploadTask != null && mUploadTask.isInProgress()){
                   Toast.makeText(Add_clothes.this, " Upload is In Progress", Toast.LENGTH_SHORT).show();
               }
               else {
                   uploadFile();
               }
            }
        });

    }


    //Working of upload button(by clicking it open the fileManager to choose the image )
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    //Load The Image To the ImageViewer
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageClothUri = data.getData();

            Picasso.with(this).load(mImageClothUri).into(mImageView);
        }
    }

    // To Get The File Extentsion From Storage
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if(mImageClothUri != null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageClothUri));

            mUploadTask = fileReference.putFile(mImageClothUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBarCloth.setProgress(0);
                                }
                            },5000);

                            Toast.makeText(Add_clothes.this, "upload Successful" , Toast.LENGTH_LONG).show();

                            Upload_Clothes upload = new Upload_Clothes(mEditTextName.getText().toString().trim(),
                                    mEditTextDescription.getText().toString().trim(),
                                    mEditTextRemark.getText().toString().trim(),
                                    mEditTextCategory.getText().toString().trim(),
                                    fileReference.toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_clothes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress =(100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBarCloth.setProgress((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(this,"No File Selected", Toast.LENGTH_SHORT).show();
        }
    }



}

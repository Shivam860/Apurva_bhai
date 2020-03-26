package com.example.houseitemsrecords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.houseitemsrecords.Utils.Constants;
import com.example.houseitemsrecords.Utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class She extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Cloth_Image_Adapteer mAdapter;

    private DatabaseReference mDatabaseRef;
    private StorageReference storageReference;
    private List<Upload_Clothes> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poonam);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        mUploads = new ArrayList<>();




        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Upload_Clothes");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren()){
                    Upload_Clothes upload = postSnapShot.getValue(Upload_Clothes.class);
                    mUploads.add(upload);
                }

                mAdapter = new Cloth_Image_Adapteer(She.this,mUploads);

                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(She.this , databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}


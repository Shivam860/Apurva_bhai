package com.example.houseitemsrecords.Utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.houseitemsrecords.R;
import com.example.houseitemsrecords.She;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Utils {

    public static void customToast(Context mCtx,String msg){
        Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show();
    }

    public static FirebaseStorage getFireBaseInstance(){
        return FirebaseStorage.getInstance();
    }



    public static void getImageFromWeb(final Context mCtx,final ImageView image,String url){
        FirebaseStorage storage = getFireBaseInstance();
        StorageReference gsReference = storage.getReferenceFromUrl(url);
        gsReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                System.out.println("success L "+uri);
                if(uri != null){
                    Picasso.with(mCtx).load(uri).into(image);
                }
            }
        });


    }
}

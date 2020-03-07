package com.example.houseitemsrecords.Utils;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;

public class Utils {

    public static void customToast(Context mCtx,String msg){
        Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show();
    }

    public static FirebaseStorage getFireBaseInstance(){
        return FirebaseStorage.getInstance();
    }
}

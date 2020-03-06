package com.example.houseitemsrecords.Utils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static void customToast(Context mCtx,String msg){
        Toast.makeText(mCtx, msg, Toast.LENGTH_SHORT).show();
    }
}

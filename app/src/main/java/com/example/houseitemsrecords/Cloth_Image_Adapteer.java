package com.example.houseitemsrecords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.houseitemsrecords.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Cloth_Image_Adapteer extends RecyclerView.Adapter<Cloth_Image_Adapteer.ImageViewHolder> {

    private Context mContext;
    private List<Upload_Clothes> mUpload;

    public Cloth_Image_Adapteer(Context context ,List<Upload_Clothes> uploads){
        mContext = context;
        mUpload = uploads;
    }

    //public void setUploads(List
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.clothes_image_item, parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload_Clothes uploadCurrent = mUpload.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewDescription.setText(uploadCurrent.getDescription());
        holder.textCiewRemark.setText(uploadCurrent.getRemark());

//       Picasso.with(mContext)
//                .load(uploadCurrent.getImageUrl())
//                .fit()
//                .centerCrop()
//                .into(holder.imageView);
       Utils.getImageFromWeb(mContext,holder.imageView,uploadCurrent.getImageUrl());

    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewName,textViewDescription,textCiewRemark;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDescription =  itemView.findViewById(R.id.text_view_description);
            textCiewRemark = itemView.findViewById(R.id.text_view_remark);
            imageView = itemView.findViewById(R.id.image_view_upload);
        }
    }
}

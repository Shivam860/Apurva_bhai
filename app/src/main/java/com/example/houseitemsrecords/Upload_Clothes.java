package com.example.houseitemsrecords;

public class Upload_Clothes {
    private String mName,mDescription,mRemark,mCategory;
    private String mImageUrl;

    public Upload_Clothes(){
        //Empty constructor needed.
    }



    public Upload_Clothes(String name, String description, String remark ,String category , String imageUrl){
        if(name.trim().equals("")){
            name="no name";
        }

        mName = name;
        mDescription = description;
        mRemark = remark;
        mCategory = category;
        mImageUrl = imageUrl;
    }

    public String getName(){
        return mName;
    }
    public void setName(String name){
        mName = name;
    }

    public String getDescription(){
        return mDescription;
    }
    public void setDescription(String description){
        mDescription = description;
    }

    public String getRemark(){
        return mRemark;
    }
    public void setRemark(String remark){
        mRemark = remark;
    }

    public String getCategory(){
        return mCategory;
    }
    public void setCategory(String category){
        mCategory = category;
    }

    public String getImageUrl(){
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }


}

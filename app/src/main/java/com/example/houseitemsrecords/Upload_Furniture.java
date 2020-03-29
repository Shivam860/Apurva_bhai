package com.example.houseitemsrecords;

class Upload_Furniture {

    private String name;
    private String description;
    private String category;
    private String remark;
    private String imageUri;

    public Upload_Furniture(){
    }

    public Upload_Furniture(String name, String description, String category, String remark, String imageUri) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.remark = remark;
        this.imageUri = imageUri;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}

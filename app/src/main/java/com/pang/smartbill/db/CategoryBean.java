package com.pang.smartbill.db;

/*
 * A class that represents a specific type of group category
 * */

public class CategoryBean {

    int id;
    String categoryname;
    int imageId;    //Unselected image id
    int simageId;    //id of the selected image


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getSimageId() {
        return simageId;
    }

    public void setSimageId(int simageId) {
        this.simageId = simageId;
    }


    public CategoryBean() {
    }

    public CategoryBean(int id, String categoryname, int imageId, int simageId) {
        this.id = id;
        this.categoryname = categoryname;
        this.imageId = imageId;
        this.simageId = simageId;

    }

}

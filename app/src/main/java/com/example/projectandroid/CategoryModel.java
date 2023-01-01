package com.example.projectandroid;

public class CategoryModel {
    private int id;
    private String name ;
    private String image ;

    public CategoryModel(int id,String name, String image){
        this.id = id;
        this.name =name;
        this.image = image;
    }
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

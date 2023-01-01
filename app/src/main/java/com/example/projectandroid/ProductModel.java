package com.example.projectandroid;

public class ProductModel {
    private int id;
    private String title;
    private float price;
    private String description;
    //    private int discount_prcnt ;
//    private int prod_rating;
//    private int prod_stock;
//    private String prod_brand;
    private CategoryModel category;
    //    private int prod_thumb;
    private String[] images;

    public ProductModel(int id, String title, String description, float price
//                        ,int discount_prcnt,int prod_rating,int prod_stock,String prod_brand,,int prod_thumb
            , CategoryModel category, String[] images) {
        this.id = id;
        this.title = title;
//        this.prod_thumb = prod_thumb;
        this.price = price;
//        this.prod_brand = prod_brand;
        this.description = description;
//        this.discount_prcnt= discount_prcnt ;
//        this.prod_rating = prod_rating;
//        this.prod_stock=prod_stock;
        this.category = category;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImages() {
        return images;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }
}
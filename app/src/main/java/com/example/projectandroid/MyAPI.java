package com.example.projectandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {
    String BASE_URL ="https://api.escuelajs.co/api/v1/";

    @GET("products")
    Call<List<ProductModel>> getProducts();

    @GET("categories")
    Call<List<CategoryModel>> getCategories();
    @GET("users")
    Call<List<UsersModel>> getUsers();


}

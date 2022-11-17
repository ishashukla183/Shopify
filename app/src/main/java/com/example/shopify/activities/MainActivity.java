package com.example.shopify.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopify.R;
import com.example.shopify.adapters.CategoryAdapter;
import com.example.shopify.adapters.ProductAdapter;
import com.example.shopify.databinding.ActivityMainBinding;
import com.example.shopify.models.Category;
import com.example.shopify.models.Product;
import com.example.shopify.utils.Constants;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
CategoryAdapter categoryAdapter;
ArrayList<Category> categories;
ProductAdapter productAdapter;
ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initCategories();
        initProducts();
        initSlider();

    }

    private void initSlider() {
        getOffers();
        //binding.carousel.addData(new CarouselItem("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg","caption"));
        //binding.carousel.addData(new CarouselItem("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg","caption"));
    }

    void initCategories(){
        categories = new ArrayList<>();
        /*categories.add(new Category("Sports and Outdoor","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxEKu0wDXe3xYom1L9V7rLIJIsngRigIPzw&usqp=CAU","#7997af","Some description",1));
        categories.add(new Category("Sports and Outdoor","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxEKu0wDXe3xYom1L9V7rLIJIsngRigIPzw&usqp=CAU","#7997af","Some description",1));
        categories.add(new Category("Sports and Outdoor","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxEKu0wDXe3xYom1L9V7rLIJIsngRigIPzw&usqp=CAU","#7997af","Some description",1)); */

        categoryAdapter = new CategoryAdapter(this, categories);
        getCategories();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);

    }
    void getCategories(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               //Log.e("err",response);
                try {
                    JSONObject mainObj = new JSONObject(response);
                    if(mainObj.getString("status").equals("success")){
                        JSONArray categoryArray=mainObj.getJSONArray("categories");
                        for(int i=0;i<categoryArray.length();i++){
                            JSONObject obj=categoryArray.getJSONObject(i);
                            Category category = new Category(
                                    obj.getString("name"),
                                    Constants.CATEGORIES_IMAGE_URL+obj.getString("icon"),
                                    obj.getString("color"),
                                    obj.getString("brief"),
                                    obj.getInt("id"));
                            categories.add((category));
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }
                    else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override

            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    void getProducts(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url=Constants.GET_PRODUCTS_URL+"?count=8";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("status").equals("success")){
                        JSONArray productArray = obj.getJSONArray("products");
                        for(int i=0;i<productArray.length();i++){
                            JSONObject prodObj=productArray.getJSONObject(i);
                            Product product = new Product(
                                    prodObj.getString("name"),
                                    Constants.PRODUCTS_IMAGE_URL+prodObj.getString("image"),
                                    prodObj.getString("status"),
                                    prodObj.getDouble("price"),
                                    prodObj.getDouble("price_discount"),
                                    prodObj.getInt("stock"),
                                    prodObj.getInt("id")
                            );
                            products.add(product);
                        }
                        productAdapter.notifyDataSetChanged();
                    }
                    else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
    void getOffers(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET,Constants.GET_OFFERS_URL,response -> {
            try {
                JSONObject obj = new JSONObject(response);
                if(obj.getString("status").equals("success")){
                    JSONArray offersArray = obj.getJSONArray("news_infos");
                    for(int i=0;i<offersArray.length();i++){
                        JSONObject offer = offersArray.getJSONObject(i);
                        binding.carousel.addData(
                                new CarouselItem(
                                        Constants.NEWS_IMAGE_URL+offer.getString("image"),
                                         offer.getString("title")
                                )
                        );
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {});
        queue.add(request);
    }
    void initProducts(){
        products = new ArrayList<>();
        /*products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1)); */

        productAdapter = new ProductAdapter(this, products);
        getProducts();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }
}
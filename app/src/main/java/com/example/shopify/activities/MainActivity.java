package com.example.shopify.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.example.shopify.R;
import com.example.shopify.adapters.CategoryAdapter;
import com.example.shopify.adapters.ProductAdapter;
import com.example.shopify.databinding.ActivityMainBinding;
import com.example.shopify.models.Category;
import com.example.shopify.models.Product;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

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
        binding.carousel.addData(new CarouselItem("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg","caption"));
        binding.carousel.addData(new CarouselItem("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg","caption"));
    }

    void initCategories(){
        categories = new ArrayList<>();
        categories.add(new Category("Sports and Outdoor","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxEKu0wDXe3xYom1L9V7rLIJIsngRigIPzw&usqp=CAU","#7997af","Some description",1));
        categories.add(new Category("Sports and Outdoor","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxEKu0wDXe3xYom1L9V7rLIJIsngRigIPzw&usqp=CAU","#7997af","Some description",1));
        categories.add(new Category("Sports and Outdoor","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZxEKu0wDXe3xYom1L9V7rLIJIsngRigIPzw&usqp=CAU","#7997af","Some description",1));

        categoryAdapter = new CategoryAdapter(this, categories);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    void initProducts(){
        products = new ArrayList<>();
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        /*products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1));
        products.add(new Product("Adidas Men Shorts", "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcSd-I3l2TR1lSRokm0EZL_raW25o8ZfBsUAdBMUAxGO3YCNq9HGqzeZJ5deXi5OSxgHM0NxErKWSnBy-qOZie-w1yJvm5sxSHqUTP4LSHwmg4hUHBLm3-9oRg&usqp=CAE", "", 1.0, 1.0, 1, 1)); */

        productAdapter = new ProductAdapter(this, products);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }
}
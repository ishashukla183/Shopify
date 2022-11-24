package com.example.shopify.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shopify.R;
import com.example.shopify.adapters.ProductAdapter;
import com.example.shopify.databinding.ActivityCategoryBinding;
import com.example.shopify.models.Product;
import com.example.shopify.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;
    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        products = new ArrayList<>();

        productAdapter = new ProductAdapter(this, products);
        int catId=getIntent().getIntExtra("catId", 0);
        String categoryName=getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(categoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getProducts(catId);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    void getProducts(int catId){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url= Constants.GET_PRODUCTS_URL+"?category_id="+catId;
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
}
package com.example.shopify.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.shopify.R;
import com.example.shopify.databinding.ActivityProductDetailBinding;
import com.example.shopify.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String name = getIntent().getStringExtra("name");
        String img = getIntent().getStringExtra("image");
        int id = getIntent().getIntExtra("id",0);
        double price=getIntent().getDoubleExtra("price",0);
        Glide.with(this).load(img).into(binding.productImage);
        getProductDetails(id);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cart)
        {
            startActivity(new Intent(this,CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    void getProductDetails(int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.GET_PRODUCT_DETAILS_URL+id;
        StringRequest request = new StringRequest(Request.Method.GET,url,response -> {
            JSONObject obj = null;
            try {
                obj = new JSONObject(response);
                if(obj.getString("status").equals("success")){
                    JSONObject prodObj = obj.getJSONObject("product");
                    String description = prodObj.getString("description");
                    binding.productDescription.setText(
                            Html.fromHtml(description)
                    );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            },error -> {

        });
        queue.add(request);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();  //finish current activity (product details)
        return super.onSupportNavigateUp();
    }
}
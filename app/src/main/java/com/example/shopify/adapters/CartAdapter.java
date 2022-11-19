package com.example.shopify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopify.R;
import com.example.shopify.databinding.ItemCartBinding;
import com.example.shopify.databinding.QuantityDialogBinding;
import com.example.shopify.models.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<Product> products;

    public CartAdapter(Context context,ArrayList<Product> products)
    {
        this.context=context;
        this.products=products;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product=products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);

        holder.binding.name.setText(product.getName());
        holder.binding.price.setText("PKR "+product.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuantityDialogBinding quantityDialogBinding=QuantityDialogBinding.inflate(LayoutInflater.from(context));

                AlertDialog dialog=new AlertDialog.Builder(context)
                        .setView(quantityDialogBinding.getRoot())
                        .create();

                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{

        ItemCartBinding binding;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemCartBinding.bind(itemView);
        }
    }
}

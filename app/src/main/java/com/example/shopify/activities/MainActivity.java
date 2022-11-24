package com.example.shopify.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

/*ActivityMainBinding binding;
CategoryAdapter categoryAdapter;
ArrayList<Category> categories;
ProductAdapter productAdapter;
ArrayList<Product> products; */
EditText email,pass,phone,addr;
    Button SignUp,LogIn;
    ProgressDialog progress;
    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        LogIn=findViewById(R.id.LogIn);
        SignUp=findViewById(R.id.LSignUp);
        email=findViewById(R.id.LEmail);
        pass=findViewById(R.id.LPassword);

        progress=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authentication();
            }
        });
    }


    private void Authentication() {
        String Email = email.getText().toString();
        String password = pass.getText().toString();
        if(password.isEmpty() || password.length()<6){
            pass.setError("Password should be grater than 6 characters");
        }
        else{
            progress.setMessage("Please Wait...");
            progress.setTitle("Registration");
            progress.setCanceledOnTouchOutside(false);
            progress.show();

            mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progress.dismiss();

                        NextActivity();
                        Toast.makeText(MainActivity.this,"LogIn Successful !!",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        progress.dismiss();
                        Toast.makeText(MainActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    }



    private void NextActivity() {
        Intent intent = new Intent(MainActivity.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


}
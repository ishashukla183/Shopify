package com.example.shopify.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopify.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    //TextView alreadyHaveAcc;
    EditText email,pass,Name,phone;
    Button btnReg,btnLogin;
    ProgressDialog progress;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.Email);
        pass=findViewById(R.id.Password);
        btnReg=findViewById(R.id.SignUp);
        phone=findViewById(R.id.Phone);
        //addr=findViewById(R.id.Address);
        //btnLogin=findViewById(R.id.LogIn);
        Name=findViewById(R.id.user);
        progress=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(SignUp.this,LogIn.class));
            }
        }); */

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    PerforAuth();
            }
        });
    }

    private void PerforAuth(){
        String Email = email.getText().toString();
        String password = pass.getText().toString();
        String name=Name.getText().toString();
        String Phone=phone.getText().toString();
        //String Address=addr.getText().toString();
        if(password.isEmpty() || password.length()<6){
            pass.setError("Password should be grater than 6 characters");
        }
        else{
            progress.setMessage("Please Wait...");
            progress.setTitle("Registration");
            progress.setCanceledOnTouchOutside(false);
            progress.show();

            mAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progress.dismiss();
                                AddDetailsToFirebase(Email,password,Phone,name);
                                //sendUserToNextActivity();
                                //Toast.makeText(SignUp.this,"Registeration Successful !!",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                progress.dismiss();
                                Toast.makeText(SignUp.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }
    }

    private void AddDetailsToFirebase(String email,String pass,String phone,String name) {
        final DatabaseReference Root;
        Root = FirebaseDatabase.getInstance().getReference();
        Root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phone).exists())){
                     HashMap<String,Object> userdata = new HashMap<>();
                     userdata.put("name",name);
                     userdata.put("password",pass);
                     userdata.put("phone",phone);
                     //userdata.put("Address",addr);
                     userdata.put("email",email);

                     Root.child("Users").child(phone).updateChildren(userdata)
                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if(task.isSuccessful()){
                                         Toast.makeText(SignUp.this,"Your account has been created !!",Toast.LENGTH_SHORT);
                                         sendUserToNextActivity();
                                     }
                                     else{
                                         Toast.makeText(SignUp.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                                     }
                                 }
                             });

                }
                /*else{
                    Toast.makeText(SignUp.this,"This "+email+" already exists",Toast.LENGTH_SHORT);
                    progress.dismiss();

                } */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignUp.this,Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
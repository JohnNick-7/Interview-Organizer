package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class companyLogin extends AppCompatActivity {

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText email, pass;
    TextView t1,t2;

    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        t1 = findViewById(R.id.clogin);
        t2=findViewById(R.id.cloginPage);
        email = findViewById(R.id.username1);
        pass = findViewById(R.id.pass1);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfAuth();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),companySignup.class);
                startActivity(i);
                finish();
            }
        });

    }





    public void perfAuth() {
        String em = email.getText().toString();
        String pa = pass.getText().toString();

        if (!em.matches(emailPattern)) {
            email.setError("Enter proper EMail id");
        } else if (pa.isEmpty() || pa.length() < 6) {
            pass.setError("Enter proper password");
        } else {
            progressDialog.setMessage("Please wait while login......");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            auth.signInWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUser();
                        Toast.makeText(companyLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(companyLogin.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void sendUser() {
        Intent i=new Intent(getApplicationContext(), companyHome.class);
        startActivity(i);
        finish();
    }


}
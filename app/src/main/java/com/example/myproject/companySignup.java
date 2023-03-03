package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class companySignup extends AppCompatActivity {
    TextView t1,t2;
    EditText email,pass,cpass;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_signup);
        t1=findViewById(R.id.cSignupPage);
        t2=findViewById(R.id.csignup);
        email=findViewById(R.id.username);
        pass=findViewById(R.id.pass2);
        cpass=findViewById(R.id.cpass2);
        progressDialog=new ProgressDialog(this);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),company_login.class);
                startActivity(i);
                finish();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String em=email.getText().toString();
        String pa=pass.getText().toString();
        String cpa=cpass.getText().toString();
        if(!em.matches(emailpattern))
        {
            email.setError("Enter proper EMail id");
        }
        else if(pa.isEmpty() ||pa.length()<6)
        {
            pass.setError("Enter correct password");
        }
        else if(!pa.equals(cpa))
        {
            cpass.setError("Password not matches");
        }
        else{
            progressDialog.setMessage("Registering......");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            auth.createUserWithEmailAndPassword(em,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(companySignup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(companySignup.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}

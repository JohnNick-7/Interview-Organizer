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
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    EditText email, pass;
    TextView t1;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseUser user;
    static int count=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.login);
        email = findViewById(R.id.username_input);
        pass = findViewById(R.id.pass);

        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }


    public void signUpPage(View view) {
        Intent i = new Intent(getApplicationContext(), signUpActivity.class);
        startActivity(i);
        finish();
    }


    public void signIn(View view) {
        String em = email.getText().toString();
        String pa = pass.getText().toString();

        if (!em.matches(emailpattern)) {
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
                        Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void sendUser() {
      if(count==0) {
          count++;
          Intent i = new Intent(getApplicationContext(), UserRegister.class);
          startActivity(i);
          finish();
      }
      else
      {
          Intent i = new Intent(getApplicationContext(), homeUser.class);
          startActivity(i);
          finish();
      }
      }
}
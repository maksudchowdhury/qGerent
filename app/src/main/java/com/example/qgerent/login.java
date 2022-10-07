package com.example.qgerent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText customerEmailEdtID,customerPassEdtID;
    TextView customerLoginBtnID,customerSignupTextID;
    ProgressBar loginProgressBar;


    FirebaseDatabase rootNode;
    DatabaseReference reference;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        customerEmailEdtID=findViewById(R.id.customerEmailEdt);
        customerPassEdtID=findViewById(R.id.customerPassEdt);
        customerLoginBtnID=findViewById(R.id.customerLoginBtn);
        customerSignupTextID=findViewById(R.id.customerSignupText);
        loginProgressBar=findViewById(R.id.loginProgressBar);

        customerLoginBtnID.setOnClickListener(this);
        customerSignupTextID.setOnClickListener(this);


    }
    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.customerLoginBtn){
            userLogin();


        }
        if(v.getId()==R.id.customerSignupText){
            Intent i = new Intent(login.this,register.class);
            startActivity(i);

        }
    }


    private void userLogin() {
        loginProgressBar.setVisibility(View.VISIBLE);
        System.out.println("customer Login button clicked");
        String customerEmail = customerEmailEdtID.getText().toString().trim();
        String customerPassword = customerPassEdtID.getText().toString().trim();
        System.out.println(customerEmail+" "+customerPassword);
        mAuth.signInWithEmailAndPassword(customerEmail,customerPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent i = new Intent(getApplicationContext(),customerPanel.class);
                    i.putExtra("userEmail",customerEmail);
                    loginProgressBar.setVisibility(View.INVISIBLE);
                    startActivity(i);

                }
                else{
                    Toast.makeText(login.this, "Could not login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
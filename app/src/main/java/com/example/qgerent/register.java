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

public class register extends AppCompatActivity implements View.OnClickListener {
    EditText customerNameEdtID,customerEmailEdtID,customerGenderEdtID,customerPhoneEdtID,customerPassEdtID,customerCPassEdtID;
    TextView customerSignupBtnID,customerSigninRedirectID;
    ProgressBar customerRegisterLoading;
    String customerRole="customer";
    String adminRole="hdresser";


    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        customerNameEdtID=findViewById(R.id.customerNameEdt);
        customerEmailEdtID=findViewById(R.id.customerEmailEdt);
        customerGenderEdtID=findViewById(R.id.customerGenderEdt);
        customerPhoneEdtID=findViewById(R.id.customerPhoneEdt);
        customerPassEdtID=findViewById(R.id.customerPassEdt);
        customerCPassEdtID =findViewById(R.id.customerCPassEdt);
        customerSignupBtnID =findViewById(R.id.customerSignupBtn);
        customerSigninRedirectID =findViewById(R.id.customerSigninRedirect );
        customerRegisterLoading = findViewById(R.id.customerRegisterLoadingCircle);

        customerSignupBtnID.setOnClickListener(this);
        customerSigninRedirectID.setOnClickListener(this);


    }
    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.customerSignupBtn){
            System.out.println("Customer Wants to Sign up");
            registerUser();

        }
        else if(v.getId()==R.id.customerSigninRedirect){
            System.out.println("Customer Wants to go to sign in page");
            Intent j = new Intent(getApplicationContext(),login.class);
            startActivity(j);
        }
        else{

        }
    }

    private void registerUser() {
        String name = customerNameEdtID.getText().toString().trim();
        String email =customerEmailEdtID.getText().toString().trim();
        String gender = customerGenderEdtID.getText().toString().trim();
        String phone = customerPhoneEdtID.getText().toString().trim();
        String password = customerPassEdtID.getText().toString().trim();
        String cpassword = customerCPassEdtID.getText().toString().trim();
        String role = customerRole;
        int isPassSame = password.compareTo(cpassword);
        if(name.isEmpty())
        {
            customerNameEdtID.setError("Enter a name");
            customerNameEdtID.requestFocus();
            return;
        }
        if(gender.isEmpty())
        {
            customerGenderEdtID.setError("Enter your gender");
            customerGenderEdtID.requestFocus();
            return;
        }
        if(phone.isEmpty())
        {
            customerPhoneEdtID.setError("Enter a phone number");
            customerPhoneEdtID.requestFocus();
            return;
        }


        if(email.isEmpty())
        {
            customerEmailEdtID.setError("Enter an email address");
            customerEmailEdtID.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            customerEmailEdtID.setError("Enter a valid email address");
            customerEmailEdtID.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            customerPassEdtID.setError("Enter a password");
            customerPassEdtID.requestFocus();
            return;
        }
        if(password.length()<8)
        {
            customerPassEdtID.setError("Enter at least 8 characters");
            customerPassEdtID.requestFocus();
            return;
        }
        if(cpassword.isEmpty())
        {
            customerCPassEdtID.setError("Confirm the password");
            customerCPassEdtID.requestFocus();
            return;
        }

        if(isPassSame==-1)
        {
            customerPassEdtID.setError("Passwords Do not match");
            customerPassEdtID.requestFocus();
            customerCPassEdtID.requestFocus();
            return;
        }
        customerRegisterLoading.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            users newUser = new users(name,email,gender,phone,"customer");

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                                        customerRegisterLoading.setVisibility(View.INVISIBLE);
                                        Intent i = new Intent(register.this,login.class);
                                        startActivity(i);
                                    }

                                    else{
                                        Toast.makeText(register.this, "Registration could not be done. Try again", Toast.LENGTH_LONG).show();
                                        customerRegisterLoading.setVisibility(View.INVISIBLE);
                                    }
                                }
                            });
                        }
                        else{
                            Toast.makeText(register.this, "Registration could not be done. Try again", Toast.LENGTH_LONG).show();
                            customerRegisterLoading.setVisibility(View.INVISIBLE);
                        }
                    }
                });



        customerRegisterLoading.setVisibility(View.INVISIBLE);
        return;

    }
}
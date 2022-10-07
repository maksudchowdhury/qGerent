package com.example.qgerent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminLogin extends AppCompatActivity implements View.OnClickListener {


    EditText hdresserEmailEdtID,hdresserPassEdtID;
    TextView hdresserLoginBtnID;
    final String userRole="admin";

    private FirebaseAuth mAuth;
    DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        hdresserEmailEdtID=findViewById(R.id.hdresserEmailEdt);
        hdresserPassEdtID=findViewById(R.id.hdresserPassEdt);
        hdresserLoginBtnID=findViewById(R.id.hdresserLoginBtn);






        hdresserLoginBtnID.setOnClickListener(this);

    }

    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.hdresserLoginBtn){
            System.out.println("admin log in button pressed");
            adminLogin();

        }
    }
    private void adminLogin() {


        System.out.println("admin Login process started");
        String adminEmail = hdresserEmailEdtID.getText().toString().trim();
        String adminPassword = hdresserPassEdtID.getText().toString().trim();
        System.out.println(adminEmail+" "+adminPassword);

        if(userRole.equals("admin")){
            mAuth.signInWithEmailAndPassword(adminEmail,adminPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){


                        String userUID= FirebaseAuth.getInstance().getCurrentUser().getUid();

                        usersDB.child(userUID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                String userType = snapshot.child("role").getValue(String.class);
                                if(userType.equals("admin")){
                                    Intent i = new Intent(getApplicationContext(),adminPanel.class);
                                    i.putExtra("adminEmail",adminEmail);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "This is not an admin account", Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {

                            }
                        });




//                        Intent i = new Intent(getApplicationContext(),adminPanel.class);
//                        i.putExtra("adminEmail",adminEmail);
//                        startActivity(i);

                    }
                    else{
                        Toast.makeText(adminLogin.this, "Could not login", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(), "The user is not an admin", Toast.LENGTH_LONG).show();
        }



    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adminLogin.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adminLogin.stopListening();
//    }

}
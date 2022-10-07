package com.example.qgerent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class customerPanel extends AppCompatActivity implements View.OnClickListener {
    private EditText userEmailID,serviceNameID,addReqID;
    RecyclerView qlistID;
    customerPanelAdapter customerPanelAdapter;
    DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference qReqDB = FirebaseDatabase.getInstance().getReference("qReq");
    String userUID= FirebaseAuth.getInstance().getCurrentUser().getUid();
    String userName,userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_panel);
        getSupportActionBar().hide();
        Intent i =getIntent();

        serviceNameID=findViewById(R.id.serviceNameEdt);
        qlistID=findViewById(R.id.qlist);
        qlistID.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<requestQ>options=new FirebaseRecyclerOptions.Builder<requestQ>()
                .setQuery(qReqDB,requestQ.class).build();

        customerPanelAdapter= new customerPanelAdapter(options);
        qlistID.setAdapter(customerPanelAdapter);

        userEmailID=findViewById(R.id.userEmailShow);
        userEmailID.setEnabled(false);
        serviceNameID=findViewById(R.id.serviceNameEdt);
        addReqID=findViewById(R.id.addReq);






        usersDB.child(userUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userEmail = snapshot.child("email").getValue(String.class);
                userName = snapshot.child("name").getValue(String.class);
                userEmailID.setText(userEmail);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });






        addReqID.setOnClickListener(this);
    }
    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }
    @Override
    public void onClick(View v) {
        if(v==addReqID){
            String service =serviceNameID.getText().toString();
            if(service.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please Enter a service name", Toast.LENGTH_LONG).show();
            }
            else{
                requestQ newReq = new requestQ(userName,userEmail,service);
                qReqDB.child(userUID).setValue(newReq);
                Toast.makeText(getApplicationContext(), "New Request Has been Added", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        customerPanelAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        customerPanelAdapter.stopListening();
    }
}
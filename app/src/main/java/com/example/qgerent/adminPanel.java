package com.example.qgerent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminPanel extends AppCompatActivity implements View.OnClickListener {
    private EditText adminEmailID;
    RecyclerView reqPanelID;
    adminPanelAdapter adminPanelAdapter;
    DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference("users");
    DatabaseReference qReqDB = FirebaseDatabase.getInstance().getReference("qReq");
    String userUID= FirebaseAuth.getInstance().getCurrentUser().getUid();
    String adminName,adminEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        getSupportActionBar().hide();
        reqPanelID=findViewById(R.id.reqPanel);
        reqPanelID.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<requestQ> options=new FirebaseRecyclerOptions.Builder<requestQ>()
                .setQuery(qReqDB,requestQ.class).build();

        adminPanelAdapter= new adminPanelAdapter(options);
        reqPanelID.setAdapter(adminPanelAdapter);

        adminEmailID=findViewById(R.id.adminEmailShow);
        adminEmailID.setEnabled(false);



        usersDB.child(userUID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                adminEmail = snapshot.child("email").getValue(String.class);
                adminName = snapshot.child("name").getValue(String.class);
                adminEmailID.setText(adminEmail);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    @Override
    protected void onPause() {

        super.onPause();
//        finish();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        adminPanelAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adminPanelAdapter.stopListening();
    }
}
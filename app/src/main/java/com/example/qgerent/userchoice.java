package com.example.qgerent;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class userchoice extends AppCompatActivity implements View.OnClickListener {
    private TextView customerBtnID, hdresserBtnID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchoice);
        getSupportActionBar().hide();

        customerBtnID= findViewById(R.id.customerBtn);
        hdresserBtnID= findViewById(R.id.hdresserBtn);

        customerBtnID.setOnClickListener(this);
        hdresserBtnID.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.customerBtn){
            System.out.println("customer button clicked");
            Intent i = new Intent(getApplicationContext(),login.class);
            startActivity(i);

        }
        else if(v.getId()==R.id.hdresserBtn){
            System.out.println("hdresser button clicked");
            Intent j = new Intent(getApplicationContext(),adminLogin.class);
            startActivity(j);
        }
        else{

        }
    }
}
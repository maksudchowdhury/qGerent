package com.example.qgerent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(this,userchoice.class);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                System.out.println("Splash finished");
                startActivity(i);
            }
        }, 2000);





//        final EditText edit_name=findViewById(R.id.edit_name);
//        final EditText edit_position=findViewById(R.id.edit_position);
//        Button btn = findViewById(R.id.btn_submit);
//        DAOEmployee dao = new DAOEmployee();
//        btn.setOnClickListener(v -> {
//            Employee emp = new Employee(edit_name.getText().toString(),edit_position.getText().toString());
//            dao.add(emp).addOnSuccessListener(suc->{
//                Toast.makeText(this,"Record Inserted",Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(er->{
//                Toast.makeText(this, ""+er.getMessage() , Toast.LENGTH_SHORT).show();
//            });
//        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
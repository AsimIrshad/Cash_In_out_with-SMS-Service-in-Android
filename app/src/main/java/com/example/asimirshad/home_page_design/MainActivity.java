package com.example.asimirshad.home_page_design;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
    }

   public void receiving_amount(View view){

       Intent i=new Intent(MainActivity.this,Receiving_screen.class);
       this.startActivity(i);

    }
    public void giving_amount(View  view){
        Intent i=new Intent(MainActivity.this,Giving_screen.class);
        this.startActivity(i);
    }
    public void report(View view){
        Intent i=new Intent(MainActivity.this,report.class);
        this.startActivity(i);
    }
    public void setting(View view){
        Intent i=new Intent(MainActivity.this,Setting.class);
        this.startActivity(i);
    }
}

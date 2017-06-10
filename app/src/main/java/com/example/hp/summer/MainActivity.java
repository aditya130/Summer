package com.example.hp.summer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onlogin(View v)
    {
        Toast.makeText(this,"Hero",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,LaterActivity.class);
        startActivity(i);
    }
}

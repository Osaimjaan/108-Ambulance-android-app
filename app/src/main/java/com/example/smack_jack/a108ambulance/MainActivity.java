package com.example.smack_jack.a108ambulance;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button b1,b2,b3;
    public static boolean isQuit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.signup);
        b3=(Button)findViewById(R.id.forgotpassword);
        b3.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.forgotpassword)
        {
            Intent i=new Intent(this,ForgetPassword.class);
            startActivity(i);
        }
        if(view.getId()==R.id.signup)
        {
            Intent i=new Intent(this,Signup.class);
            startActivity(i);
        }
        if(view.getId()==R.id.login)
        {
            Intent i=new Intent(this,Welcome.class);
            startActivity(i);
            finish();
            return;
        }

    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press BACK button again to exit the application", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

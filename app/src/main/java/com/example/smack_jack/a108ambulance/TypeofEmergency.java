package com.example.smack_jack.a108ambulance;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class TypeofEmergency extends AppCompatActivity implements View.OnClickListener {
    ImageView i1, i2, i3, i4, i5, i6;
    Button b1,b2;
    TextView t1;
    Welcome w1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeof_emergency);

        i1 = (ImageView) findViewById(R.id.head);
        i2 = (ImageView) findViewById(R.id.pregnency);
        i3 = (ImageView) findViewById(R.id.aacident);
        i4 = (ImageView) findViewById(R.id.heart);
        i5 = (ImageView) findViewById(R.id.fire);
        i6 = (ImageView) findViewById(R.id.police);
        b1 = (Button) findViewById(R.id.other);
        b2=  (Button) findViewById(R.id.buttonexittype) ;


        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);
        i6.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        t1=(TextView)findViewById(R.id.fortimecount);
        new CountDownTimer(7000, 1000) {

            public void onTick(long millisUntilFinished) {
                t1.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(),Description.class);
                startActivity(intent);
                System.exit(1);
            }
        }.start();

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this,Welcome.class);
            startActivity(intent);
            System.exit(1);
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.head)
            {

                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);

            }
            if (v.getId() == R.id.pregnency) {
                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);
            }
            if (v.getId() == R.id.aacident) {
                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);
            }
            if (v.getId() == R.id.heart) {
                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);
            }
            if (v.getId() == R.id.fire) {
                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);
            }
            if (v.getId() == R.id.police) {
                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);
            }
            if (v.getId() == R.id.other) {
                Intent i = new Intent(this, Description.class);
                startActivity(i);
                System.exit(1);
            }
            if (v.getId() == R.id. buttonexittype) {
                Intent i = new Intent(this, Welcome.class);
                startActivity(i);
                System.exit(1);
            }
        }


    }

package com.example.smack_jack.a108ambulance;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Emergencyfor extends AppCompatActivity implements View.OnClickListener {
    ImageView i1,i2,i3;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencyfor);
        i1 = (ImageView) findViewById(R.id.forself);
        i2 = (ImageView) findViewById(R.id.forfamilymember);
        i3 = (ImageView) findViewById(R.id.forstranger);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        t1=(TextView)findViewById(R.id.fortimecount);

        Button b1=(Button)findViewById(R.id.buttonexitemergencyfor);
        b1.setOnClickListener(this);

        new CountDownTimer(7000, 1000) {

            public void onTick(long millisUntilFinished) {
                t1.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(),TypeofEmergency.class);
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
        if (v.getId() == R.id.forself) {
            Intent i = new Intent(this, TypeofEmergency.class);
            startActivity(i);
            System.exit(1);
        }
        if (v.getId() == R.id.forfamilymember) {
            Intent i = new Intent(this, TypeofEmergency.class);
            startActivity(i);
            System.exit(1);
        }
        if (v.getId() == R.id.forstranger) {
            Intent i = new Intent(this, TypeofEmergency.class);
            startActivity(i);
            System.exit(1);
        }
        if (v.getId() == R.id.buttonexitemergencyfor) {
            Intent i = new Intent(this, Welcome.class);
            startActivity(i);
            System.exit(1);
        }
    }
}
package com.example.smack_jack.a108ambulance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity{
    Button signup;
    EditText name,email,pass;
    private  AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
    private FirebaseAuth mAuth;
    RadioButton radioButton;
    int value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.namesignup);
        email=(EditText)findViewById(R.id.userEmailsignup);
        pass=(EditText)findViewById(R.id.userPasswordsignup);
        mAuth = FirebaseAuth.getInstance();



        signup=(Button)findViewById(R.id.signupsignup);
        radioButton=findViewById(R.id.radioButton);


        mAwesomeValidation.addValidation(this, R.id.namesignup, "[a-zA-Z\\s]+", R.string.err_firstname);
        mAwesomeValidation.addValidation(this, R.id.userEmailsignup, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        String regexPassword = "^(?=\\S+$).{8,}$";
        mAwesomeValidation.addValidation(this, R.id.userPasswordsignup, regexPassword, R.string.err_pass);
        mAwesomeValidation.addValidation(this, R.id.userCPasswordsignup, R.id.userPasswordsignup, R.string.err_repass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
                if (value == 1) {
                    onRegister();
                }
            }

        });
    }
    public void onRegister()
    {
        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
            R.style.Theme_AppCompat_Light_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();
        final String cemail = email.getText().toString();
        final String password = pass.getText().toString();
        mAuth.createUserWithEmailAndPassword(cemail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            Toast.makeText(Signup.this, "Registered Successfully.",
                                    Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Signup.this,MainActivity.class);
                            startActivity(i);

                        } else {
                            progressDialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure");
                            Toast.makeText(getApplicationContext(),"E-mail id Already Exist",Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
    private void submitForm() {
      mAwesomeValidation.validate();

        if(radioButton.isChecked())
        {
            value=1;
        }

        else if (mAwesomeValidation.validate()) {

            Toast.makeText(getApplicationContext(),"Please Accept Tearms and Conditions",Toast.LENGTH_SHORT).show();
        }
    }

}

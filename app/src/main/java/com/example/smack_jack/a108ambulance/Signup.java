package com.example.smack_jack.a108ambulance;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.common.collect.Range;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Signup extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2;
    TextView t1;
    EditText name,email,mob,age,pass;
    Spinner bloodgroup,sex,state,city;
    private Button btn;
    private ImageView imageview;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    private  AwesomeValidation mAwesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
    ToggleButton toggle;
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.firstnamesignup);
        email=(EditText)findViewById(R.id.emailSignup);
        mob=(EditText)findViewById(R.id.mobilenumsignup);
        age=(EditText)findViewById(R.id.agesignup);
        pass=(EditText)findViewById(R.id.passwordSignup);

        bloodgroup = (Spinner) findViewById(R.id.bloodgroupsignup);
        sex = (Spinner) findViewById(R.id.sexsignup);
        state = (Spinner) findViewById(R.id.stateSignup);
        city  = (Spinner) findViewById(R.id.citySignup);
        t1=(TextView)findViewById(R.id.tv);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.bloodgroups, android.R.layout.simple_spinner_dropdown_item);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        bloodgroup.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.sex, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.state, android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(adapter3);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.city, android.R.layout.simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(adapter4);

        b1=(Button)findViewById(R.id.loginSignup);
        b2=(Button)findViewById(R.id.signupSignup);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        btn = (Button) findViewById(R.id.uploadimagesignup);
        imageview = (ImageView) findViewById(R.id.picviewsignup);

        mAwesomeValidation.addValidation(this, R.id.firstnamesignup, "[a-zA-Z\\s]+", R.string.err_firstname);
        mAwesomeValidation.addValidation(this, R.id.emailSignup, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        mAwesomeValidation.addValidation(this, R.id.mobilenumsignup, "^[2-9]{2}[0-9]{8}$", R.string.err_mob);
        mAwesomeValidation.addValidation(this, R.id.agesignup,Range.closed(9, 80), R.string.err_age);
        String regexPassword = "^(?=\\S+$).{8,}$";
        mAwesomeValidation.addValidation(this, R.id.passwordSignup, regexPassword, R.string.err_pass);
        mAwesomeValidation.addValidation(this, R.id.ReenterpasswordSignup, R.id.passwordSignup, R.string.err_repass);

        toggle = (ToggleButton) findViewById(R.id.toggleButtonSignup);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    pass.setTransformationMethod(null);
        } else {
                    // The toggle is disabled
                    pass.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pass.setSelection(pass.getText().length());
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPictureDialog();
            }
        });

    }
    private void submitForm() {
      mAwesomeValidation.validate();

        if(imageview.getDrawable() == null)
        {
            Toast.makeText(this, "Please Select A photo", Toast.LENGTH_SHORT).show();
        }
        else if(bloodgroup.getSelectedItem().toString().trim().equals("Blood Group"))
        {
            Toast.makeText(this, "Please Select BloodGroup", Toast.LENGTH_SHORT).show();
        }
        else if(sex.getSelectedItem().toString().trim().equals("Sex"))
        {
            Toast.makeText(this, "Please Select Sex", Toast.LENGTH_SHORT).show();
        }
        else if(state.getSelectedItem().toString().trim().equals("Select State"))
        {
            Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
        }
        else if(city.getSelectedItem().toString().trim().equals("Select City"))
        {
            Toast.makeText(this, "Please Select City", Toast.LENGTH_SHORT).show();
        }
        //first validate the form then move ahead
        //if this becomes true that means validation is successfull
        else if (mAwesomeValidation.validate()) {
           Toast.makeText(this, "Registration Successfull Login Now", Toast.LENGTH_LONG).show();

            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };

        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(Signup.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(Signup.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            saveImage(thumbnail);
            Toast.makeText(Signup.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.signupSignup)
        {
         submitForm();
        }
        if(view.getId()==R.id.loginSignup)
        {
           Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }

    }
}

package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {
    private Button editBtn;

    private EditText regName, regaddress, regGmail, regPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        editBtn = findViewById(R.id.btnRegister);


        regName = findViewById(R.id.EditName);
        regaddress = findViewById(R.id.Editaddress);
        regGmail = findViewById(R.id.EditGmail);
        regPassword = findViewById(R.id.EditPassword);


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = regName.getText().toString();
                final String address = regaddress.getText().toString();
                final String email = regGmail.getText().toString();
                final String password = regPassword.getText().toString();
                if (TextUtils.isEmpty(address) || TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
                    Toast.makeText(EditProfile.this,"All fields are required",Toast.LENGTH_SHORT).show();

                }


            }
        });


    }
}
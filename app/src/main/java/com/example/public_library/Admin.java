package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {
    private CardView cvAdd,cvuser,cvbooks,cvLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        cvAdd = findViewById(R.id.Adminadd);
        cvuser = findViewById(R.id.Adminuserview);
        cvbooks = findViewById(R.id.Adminviewbook);
        cvLogout = findViewById(R.id.Adminlogout);

        cvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, Add_Books.class));

            }
        });
        cvuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, UserList.class));

            }
        });
        cvbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, BookDetails.class));

            }
        });
        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this, MainActivity.class));

            }
        });


    }
}
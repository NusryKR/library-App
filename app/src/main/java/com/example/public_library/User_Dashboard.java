package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class User_Dashboard extends AppCompatActivity {
    private CardView cv_addBorrow,cv_viewBorrow,cvEditProfile,cvLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__dashboard);

        cv_addBorrow = findViewById(R.id.user_borrowBook);
        cv_viewBorrow = findViewById(R.id.user_viewBorrowedBooks);
        cvEditProfile = findViewById(R.id.edit_Profile);
        cvLogout = findViewById(R.id.Userlogout);
        TextView txtep = findViewById(R.id.txtadmin);

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");

        // display the string into textView
        txtep.setText(str);

        cv_addBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Dashboard.this, view_borrowBook.class));

            }
        });
        cv_viewBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Dashboard.this, Borrow_Book_Details.class));

            }
        });
        cvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Dashboard.this, user_borrowed_books.class));

            }
        });
        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Dashboard.this, MainActivity.class));

            }
        });


    }
}
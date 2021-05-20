package com.example.public_library;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Add_Books extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;

    EditText BookName, BookId, BookAuthor,BookType;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__books);

        addBtn = findViewById(R.id.btnAdd);


        BookId = findViewById(R.id.etbookid);
        BookName = findViewById(R.id.etbookname);
        BookAuthor = findViewById(R.id.etauthor);
        BookType = findViewById(R.id.ettype);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String bookid = BookId.getText().toString();
                final String bookname = BookName.getText().toString();
                final String bookauthor = BookAuthor.getText().toString();
                final String booktype = BookType.getText().toString();
                if (TextUtils.isEmpty(bookid) || TextUtils.isEmpty(bookname)
                        || TextUtils.isEmpty(bookauthor) || TextUtils.isEmpty(booktype) ) {
                    Toast.makeText(Add_Books.this,"All fields are required",Toast.LENGTH_SHORT).show();

                }
                else {
                    addNewBook(bookid,bookname,bookauthor,booktype);
                }
            }
        });


    }
        public void addNewBook(final String bookname, final String bookid, final String bookauthor, final String booktype){

            final ProgressDialog progressDialog = new ProgressDialog(Add_Books.this);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.setTitle("New Book Adding...");
            progressDialog.show();
            String url = "http://192.168.1.7:8081/PL/Add_book.php";
            //http://192.168.8.100:8081/try/register.php
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("Successfully Added")){
                        progressDialog.dismiss();
                        Toast.makeText(Add_Books.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Admin.class));
                        //finish();


                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(Add_Books.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Add_Books.this, error.toString(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                protected Map<String, String>getParams() throws AuthFailureError{
                    HashMap<String, String> param = new HashMap<>();
                    param.put("bookid",bookid);
                    param.put("bookname",bookname);
                    param.put("bookauthor",bookauthor);
                    param.put("booktype",booktype);

                    return param;
                }
            };

            request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            MySingleton.getInstance(Add_Books.this).addToRequestQueue(request);
        }
    }



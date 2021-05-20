package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Borrow_Book_Details extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 1;

    EditText BookName, BookId, Date;
    private Button borrowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow__book__details);
        borrowBtn = findViewById(R.id.btnBorrow);


        BookId = findViewById(R.id.etborrwbookid);
        BookName = findViewById(R.id.etborrowbookname);
        Date = findViewById(R.id.etreturndate);



        borrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String bookid = BookId.getText().toString();
                final String bookname = BookName.getText().toString();
                final String date = Date.getText().toString();

                if (TextUtils.isEmpty(bookid) || TextUtils.isEmpty(bookname)
                        || TextUtils.isEmpty(date)  ) {
                    Toast.makeText(Borrow_Book_Details.this,"All fields are required",Toast.LENGTH_SHORT).show();

                }
                else {
                    addNewBook(bookid,bookname,date);
                }
            }
        });


    }
    public void addNewBook(final String bookname, final String bookid, final String date){

        final ProgressDialog progressDialog = new ProgressDialog(Borrow_Book_Details.this);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Borrowing Book...");
        progressDialog.show();
        String url = "http://192.168.1.7:8081/PL/borrowbooks.php";
        //http://192.168.8.100:8081/try/register.php
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully Borrowed")){
                    progressDialog.dismiss();
                    Toast.makeText(Borrow_Book_Details.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),User_Dashboard.class));
                    //finish();


                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Borrow_Book_Details.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Borrow_Book_Details.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("bookid",bookid);
                param.put("bookname",bookname);
                param.put("date",date);


                return param;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(Borrow_Book_Details.this).addToRequestQueue(request);
    }
}
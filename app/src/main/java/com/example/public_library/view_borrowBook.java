package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class view_borrowBook extends AppCompatActivity {

    ProgressBar progressBarSubject;
    EditText editText ;
    private static final String URL_PRODUCTS = "http://192.168.1.7:8081/PL/book_Api.php";
    public static view_borrowBook ma;


    //a list to store all the products
    List<Books> booksList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_borrow_book);


        //getting the recyclerview from xml
        recyclerView=findViewById(R.id.recylcerViewborrowBook);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        booksList = new ArrayList<>();
        ma = this;

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }


    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject book = array.getJSONObject(i);

                                //adding the product to product list
                                booksList.add(new Books(
                                        book.getString("Bookid"),
                                        book.getString("Bookname"),
                                        book.getString("Bookauthor"),
                                        book.getString("Booktype")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            BooksAdapter adapter = new BooksAdapter( view_borrowBook.this, booksList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
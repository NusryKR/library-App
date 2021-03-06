package com.example.public_library;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class UserList extends AppCompatActivity {
    ProgressBar progressBarSubject;
    EditText editText ;
    private static final String URL_PRODUCTS = "http://192.168.1.7:8081/PL/user_Api.php";
    public static UserList ma;

    //a list to store all the products
    List<Users> usersList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        //getting the recyclerview from xml
        recyclerView=findViewById(R.id.recylcerViewuserlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        usersList = new ArrayList<>();
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
                                JSONObject employee = array.getJSONObject(i);

                                //adding the product to product list
                                usersList.add(new Users(
                                        employee.getString("Username"),
                                        employee.getString("Address"),
                                        employee.getString("Email")
                                       // employee.getString("Password")

                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            UserAdapter adapter = new UserAdapter(UserList.this, usersList);
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
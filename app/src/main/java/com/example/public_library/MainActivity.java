package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class MainActivity extends AppCompatActivity {
    private TextView tvRegister,txtForget;
    private EditText etLoginPassword, etName;
    private Button loginButton;


    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;



    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRegister = findViewById(R.id.tvRegister);
        txtForget = findViewById(R.id.txtForget);
        etName = findViewById(R.id.etname);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        loginButton = findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
                finish();
            }
        });


        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.pref_login_status),"");
        if (loginStatus.equals("Logged in")){
            startActivity(new Intent(MainActivity.this,User_Dashboard.class));
            finish();

        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = etName.getText().toString();
                String password = etLoginPassword.getText().toString();
                if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"All Fields are required",Toast.LENGTH_SHORT).show();
                }
                else {
                    login(Name,password);

                }
            }
        });

    }
    private void login(final String email, final String password){
        final ProgressDialog progressDialog = new  ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Finding your account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String url = "http://192.168.1.7:8081/PL/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Login Success")) {
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    startActivity(new Intent(MainActivity.this, User_Dashboard.class));
                    progressDialog.dismiss();
                    finish();

                    String str = etName.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), User_Dashboard.class);
                    intent.putExtra("message_key", str);
                    startActivity(intent);

                }else if (email.equals("admin@gmail.com") || password.equals("admin")){
                    startActivity(new Intent(MainActivity.this, Admin.class));
                    Toast.makeText(MainActivity.this,"Admin Login",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(MainActivity.this, "Password or email incorrect",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

        }){
            protected Map<String, String>getParams() throws AuthFailureError{
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("password",password);
                return param;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);


    }

}
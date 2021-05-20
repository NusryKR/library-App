package com.example.public_library;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register extends AppCompatActivity {
    private Button registerBtn;
    TextView gotoLoginBtn;
    private EditText regName, regaddress, regGmail, regPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn = findViewById(R.id.btnRegister);
        gotoLoginBtn = findViewById(R.id.btnLoginReg);

        regName = findViewById(R.id.etEditName);
        regaddress = findViewById(R.id.etaddress);
        regGmail = findViewById(R.id.etEditGmail);
        regPassword = findViewById(R.id.etEditPassword);

        gotoLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = regName.getText().toString();
                final String address = regaddress.getText().toString();
                final String email = regGmail.getText().toString();
                final String password = regPassword.getText().toString();
                if (TextUtils.isEmpty(address) || TextUtils.isEmpty(name)
                        || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) ) {
                    Toast.makeText(Register.this,"All fields are required",Toast.LENGTH_SHORT).show();

                }
                else {
                    registerNewUser(name,address,email,password);
                }
            }
        });
    }

    public void registerNewUser( final String name, final String address, final String email, final String password){

        final ProgressDialog progressDialog = new ProgressDialog(Register.this);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("New Account Registration");
        progressDialog.show();
        String url = "http://192.168.1.7:8081/PL/register1.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Successfully registered")){
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            protected Map<String, String>getParams() throws AuthFailureError{
                HashMap<String, String> param = new HashMap<>();
                param.put("name",name);
                param.put("address",address);
                param.put("email",email);
                param.put("password",password);

                return param;
            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(30000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(Register.this).addToRequestQueue(request);
    }

}
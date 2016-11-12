package com.adithya.loginsignup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.adithya.loginsignup.Signup.isValidEmail;
import static com.adithya.loginsignup.Signup.isValidPassword;

public class MainActivity extends AppCompatActivity {


    DatabaseMod dm = new DatabaseMod(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bsignin) {
            EditText a = (EditText) findViewById(R.id.TFemail);
            String stremail = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String pass = b.getText().toString();


            if (stremail.isEmpty()) {
                Toast.makeText(MainActivity.this, "Email Cannot be blank", Toast.LENGTH_SHORT).show();
            }
            if (!isValidEmail(stremail) && !stremail.isEmpty()) {
                Toast.makeText(MainActivity.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            }
            if (pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Password Cannot be blank", Toast.LENGTH_SHORT).show();
            }
            if (!isValidPassword(pass) && !pass.isEmpty()) {
                Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }

            String p[] = dm.searchPass(stremail);
            if ((isValidEmail(stremail)) && (isValidPassword(pass)) && (pass.equals(p[0]))) {
                    Intent i = new Intent(MainActivity.this, Display.class);
                    i.putExtra("email", stremail);
                    i.putExtra("name", p[1]);
                    i.putExtra("cono", p[2]);
                    startActivity(i);
            }
             else
             {
                 if( (!pass.isEmpty()) && (!stremail.isEmpty()))
                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
             }

        }
            if (v.getId() == R.id.Bsup) {
                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }

    }
}
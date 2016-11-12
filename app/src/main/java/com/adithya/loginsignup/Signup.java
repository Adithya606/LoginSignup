package com.adithya.loginsignup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RAdithya on 11/6/2016.
 */

public class Signup extends Activity {

    DatabaseMod dm = new DatabaseMod(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public static boolean isValidPassword(CharSequence passval) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!*])(?=\\S+$).{6,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(passval);
        return matcher.matches();
    }
    public static boolean isValidName(CharSequence nameval) {

        Pattern pattern;
        Matcher matcher;
        final String NAME_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$";
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(nameval);
        return matcher.matches();
    }

    public void OnSignClick (View v)
    {
        if(v.getId() == R.id.Bsignup)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText mail = (EditText)findViewById(R.id.TFmail);
            EditText pass = (EditText)findViewById(R.id.TFpass);
            EditText cono = (EditText)findViewById(R.id.TFcono);

            String namestr = name.getText().toString();
            String mailstr = mail.getText().toString();
            String passstr = pass.getText().toString();
            String conostr = cono.getText().toString();

            if(namestr.isEmpty() )
            {
                Toast.makeText(Signup.this,"Name Cannot be blank",Toast.LENGTH_SHORT).show();
            }

            if(mailstr.isEmpty() )
            {
                Toast.makeText(Signup.this,"Email Cannot be blank",Toast.LENGTH_SHORT).show();
            }
            if(!isValidEmail(mailstr) && !mailstr.isEmpty())
            {
                Toast.makeText(Signup.this,"Invalid Email Address",Toast.LENGTH_SHORT).show();
            }
            if(!isValidName(namestr) && !namestr.isEmpty())
            {
                Toast.makeText(Signup.this,"Invalid Name Entry",Toast.LENGTH_SHORT).show();
            }
            if(passstr.isEmpty() )
            {
                Toast.makeText(Signup.this,"Password Cannot be blank",Toast.LENGTH_SHORT).show();
            }
            if(!isValidPassword(passstr) && !passstr.isEmpty())
            {
                Toast.makeText(Signup.this,"Invalid Password",Toast.LENGTH_SHORT).show();
            }

            if(conostr.isEmpty() )
            {
                Toast.makeText(Signup.this,"Contact Number cannot be blank",Toast.LENGTH_SHORT).show();
            }
            if(conostr.length() !=  10 && !conostr.isEmpty())
            {
                Toast.makeText(Signup.this,"Contact Number invalid",Toast.LENGTH_SHORT).show();
            }




         if((isValidEmail(mailstr)) && (isValidName(namestr)) && (isValidPassword(passstr)) && (conostr.length() == 10)) {
              // Database Insertion
              Contact c = new Contact();
              c.setName(namestr);
              c.setEmail(mailstr);
              c.setPword(passstr);
              c.setContno(conostr);

              dm.insertContact(c);
              Intent i = new Intent(Signup.this,MainActivity.class);
              startActivity(i);
              Toast.makeText(Signup.this,"Account Created Enter Credentials to log in ",Toast.LENGTH_SHORT).show();
          }

        }
    }
}

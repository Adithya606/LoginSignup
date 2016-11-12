package com.adithya.loginsignup;

import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TabHost;


/**
 * Created by RAdithya on 11/6/2016.
 */

public class Display extends Activity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        String email1 = getIntent().getStringExtra("email");
        EditText tv = (EditText) findViewById(R.id.TVemail);
        tv.setText(email1);

        String name1 = getIntent().getStringExtra("name");
        EditText tv1 = (EditText) findViewById(R.id.TVname);
        tv1.setText(name1);

        String cono1 = getIntent().getStringExtra("cono");
        EditText tv2 = (EditText) findViewById(R.id.TVcono);
        tv2.setText(cono1);



    }
}

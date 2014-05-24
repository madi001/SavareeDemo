package com.demo.savareedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class SigninActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Parse.initialize(this, "hLyBrjGbpj6gETDBQaVw7ycVkUtZ1aeSUGayFrEt", "cl3BBnQj3HrlO9bsKij5LW52YnIPO385mjqvHcEE");

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
          // do stuff with the user
            Intent intent = new Intent(SigninActivity.this,ChooserActivity.class);
            // Hooray! The user is logged in.
            startActivity(intent);

        } else {
          // show the signup or login screen
        }


        TextView signup = (TextView)findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Button signIn = (Button)findViewById(R.id.signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);

                ParseUser.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Intent intent = new Intent(SigninActivity.this, ChooserActivity.class);
                            // Hooray! The user is logged in.
                            startActivity(intent);

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

}

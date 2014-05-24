package com.demo.savareedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        LinearLayout driver = (LinearLayout)findViewById(R.id.driver);
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooserActivity.this,ActionbarActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout passenger = (LinearLayout)findViewById(R.id.passenger);
        passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooserActivity.this,PassangerHomeActivity.class);
                startActivity(intent);
            }
        });


    }






}

package com.example.perforamancetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.performancetest.R;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_main);
        ViewGroup rootView = (ViewGroup) findViewById(R.id.main_rootview);
        addButton("Fibonacci Test", rootView);
    }

    public void addButton( String description, ViewGroup parent) {
        Button button = new Button(this);
        button.setOnClickListener(v -> {
            Intent problemIntent = new Intent(LauncherActivity.this, FibonacciActivity.class);
            startActivity(problemIntent);
        });

        button.setText(description);
        parent.addView(button);
    }
}

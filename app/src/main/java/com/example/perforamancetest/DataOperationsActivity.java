package com.example.perforamancetest;

import static com.example.perforamancetest.TestApplication.FIRST_NAME;
import static com.example.perforamancetest.TestApplication.LAST_NAME;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.perforamancetest.data.Person;
import com.example.perforamancetest.data.PersonDao;
import com.example.perforamancetest.data.PersonDataBase;
import com.example.performancetest.R;


/**
 * Demonstration Activity to present aview showing overlapping stack of cards. This will demonstrate difference in UI rendering performance
 * for both View with extra drawing and another with clipping to avoid drawing areas of the screen which are not visible
 */
public class DataOperationsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_operations);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl("file:///android_asset/dancing_cartoon.gif");

        PersonDataBase personDataBase =((TestApplication)getApplication()).getPersonDataBase();
        PersonDao personDao = personDataBase.personDao();
        TextView textView = findViewById(R.id.tv_name);

        findViewById(R.id.btn_get_data).setOnClickListener(v -> {
            textView.setText("In Progress");
            for( int index =0; index<1000; index++){
                Person person = personDao.findById(index);
                textView.setText(person.firstName + " " + person.lastName);
            }
            textView.setText("Done");

        });
    }
}
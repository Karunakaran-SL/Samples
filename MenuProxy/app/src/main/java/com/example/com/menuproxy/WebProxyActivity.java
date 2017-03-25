package com.example.com.menuproxy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WebProxyActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_proxy);
        textView = (TextView)findViewById(R.id.editText);
    }

    public void start(View view){
        Intent intent = new Intent(this,ProxyService.class);
        startService(intent);
        textView.setText("Service Started");

    }


    public void stop(View view){
        Intent intent = new Intent(this,ProxyService.class);
        stopService(intent);
        textView.setText("Service Stopped");
    }
}

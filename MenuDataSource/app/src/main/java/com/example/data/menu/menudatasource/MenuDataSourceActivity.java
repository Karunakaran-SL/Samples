package com.example.data.menu.menudatasource;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import fi.iki.elonen.NanoHTTPD;

public class MenuDataSourceActivity extends AppCompatActivity {
    Messenger messenger ;
    boolean isBind = false;
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_data_source);
        Intent intent = new Intent(this, MenuDataSourceService.class);
        bindService(intent,serviceConnection,Context.BIND_AUTO_CREATE);
        textView = (TextView)findViewById(R.id.editText);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
            isBind = false;
        }
    };

    class ResponseHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            textView.setText(msg.getData().get("menu").toString());
        }
    }

    public void fetchData(View view){
        Message message = Message.obtain(null,1);
        message.replyTo = new Messenger(new ResponseHandler());
        try {
            messenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        isBind = false;
        messenger = null;
        super.onStop();
    }
}

package com.example.com.menuproxy;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Karunakaran on 3/25/2017.
 */

public class ProxyService extends Service{
    MyHttpServer myHttpServer ;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    Messenger messenger;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Intent i = new Intent();
            i.setComponent(new ComponentName("com.example.data.menu.menudatasource", "com.example.data.menu.menudatasource.MenuDataSourceService"));
            //getApplicationContext().startService(i);
            bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

            myHttpServer = new MyHttpServer();
            myHttpServer.setMessenger(messenger);
            myHttpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        myHttpServer.stop();
        super.onDestroy();
    }
}

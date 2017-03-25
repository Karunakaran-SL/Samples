package com.example.data.menu.menudatasource;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Karunakaran on 3/25/2017.
 */

public class MenuDataSourceService extends Service{
    private final int RESPONSE_VALUE = 1;
    private String menu = null;
    Messenger messenger = new Messenger(new IncomingHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    class IncomingHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Message response = Message.obtain(null,RESPONSE_VALUE) ;
            Bundle bundle = new Bundle();
            //menu = "hi";
            if(menu == null){
                menu = loadJSONFromAsset();
            }
            bundle.putString("menu",menu);
            response.setData(bundle);
            try {
                msg.replyTo.send(response);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        public String loadJSONFromAsset() {
            String json = "[\n" +
                    "  {\n" +
                    "    \"id\": \"58ab140932dfbcc4253b5236\",\n" +
                    "    \"name\": \"consectetur\",\n" +
                    "    \"price\": 1200,\n" +
                    "    \"type\": \"main course\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab140904117a99a73565e4\",\n" +
                    "    \"name\": \"adipisicing\",\n" +
                    "    \"price\": 1400,\n" +
                    "    \"type\": \"drink\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab140950d5905bd0d4752a\",\n" +
                    "    \"name\": \"commodo\",\n" +
                    "    \"price\": 500,\n" +
                    "    \"type\": \"main course\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab14097e1bf08ae9af7829\",\n" +
                    "    \"name\": \"labore\",\n" +
                    "    \"price\": 1800,\n" +
                    "    \"type\": \"drink\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab140961c812ff8022b757\",\n" +
                    "    \"name\": \"occaecat\",\n" +
                    "    \"price\": 1400,\n" +
                    "    \"type\": \"appetizer\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab1409b0148f92565506d0\",\n" +
                    "    \"name\": \"incididunt\",\n" +
                    "    \"price\": 1300,\n" +
                    "    \"type\": \"drink\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab1409a82cddf441e296c7\",\n" +
                    "    \"name\": \"ipsum\",\n" +
                    "    \"price\": 1500,\n" +
                    "    \"type\": \"main course\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab140931b3af85a6a11b10\",\n" +
                    "    \"name\": \"consectetur\",\n" +
                    "    \"price\": 400,\n" +
                    "    \"type\": \"drink\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab1409248dc6f777c816ce\",\n" +
                    "    \"name\": \"ut\",\n" +
                    "    \"price\": 2500,\n" +
                    "    \"type\": \"drink\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab14097fff45868acc9a94\",\n" +
                    "    \"name\": \"proident\",\n" +
                    "    \"price\": 1300,\n" +
                    "    \"type\": \"drink\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"id\": \"58ab14098a4ea9b9491121fa\",\n" +
                    "    \"name\": \"in\",\n" +
                    "    \"price\": 3700,\n" +
                    "    \"type\": \"appetizer\"\n" +
                    "  }\n" +
                    "]";
            /*try {
                InputStream is = getAssets().open("file_name.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }*/
            return json;
        }
    }


}

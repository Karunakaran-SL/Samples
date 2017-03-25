package com.example.com.menuproxy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * Created by Karunakaran on 3/25/2017.
 */

public class MyHttpServer extends NanoHTTPD{
    private static final int PORT = 8769;
    Messenger messenger;
    String menu;

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public MyHttpServer() throws IOException {
        super(PORT);
    }

    class ResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            menu = msg.getData().get("menu").toString();
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        Method method = session.getMethod();
        String uri = session.getUri();
        System.out.println(method + " '" + uri + "' ");

        try {
            Message message = Message.obtain(null,1);
            message.replyTo = new Messenger(new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    menu = msg.getData().get("menu").toString();
                }
            });
            messenger.send(message);
        } catch (Exception e) {
            menu = "[\n" +
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
        }
        return newFixedLengthResponse(menu);
    }
}

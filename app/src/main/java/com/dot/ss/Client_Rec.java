package com.dot.ss;

import android.app.Activity;
import android.graphics.BitmapFactory;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import android.widget.ImageView;
import static com.dot.ss.Screen.fps;

public class Client_Rec implements Runnable {
    private Activity activity;
    ImageView imageView;
    static Socket socket;
    static DataOutputStream dataOutputStream=null;
    private ByteArrayInputStream bais;

    Client_Rec(ImageView imageView,Activity activity) {
        this.imageView = imageView;
        this.activity= activity;
    }

    @Override
    public void run() {
        try {
            //"192.168.43.208" "192.168.137.1"
            socket = new Socket("192.168.137.1", 3333);
            dataOutputStream=new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream  = new DataInputStream(socket.getInputStream());
            int len;
            byte[] bytes;

            while(true){
                len = dataInputStream.readInt();
                bytes = new byte[len];
                dataInputStream.readFully(bytes, 0, len);
                bais = new ByteArrayInputStream(bytes);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(BitmapFactory.decodeStream(bais));
                        fps++;
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
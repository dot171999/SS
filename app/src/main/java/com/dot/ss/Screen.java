package com.dot.ss;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import static com.dot.ss.Client_Rec.socket;

public class Screen extends AppCompatActivity {
    ImageView imageView,imageView2;
    TextView textView;
    static int fps=0;
    float scaleFactor =0.2f;
    float minScale=1f;
    float maxScale=2f;
    float currScale=1f;
    float downX,downY,moveX,moveY,x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                textView.setText("Fps = "+fps);
                fps=0;
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        ss_mouse();

        textView=findViewById(R.id.textView2);

        Thread thread=new Thread(new Client_Rec(imageView,Screen.this),"Client_Receiver");
        thread.start();
    }

    public void zoom_ss(View view){
        if(currScale>=minScale) {
            currScale = currScale - scaleFactor;
            new Send2_Server().execute(new PacketModel("ZOOM",currScale,0));
        }
    }

    public void un_zoom_ss(View view){
        if(currScale<=maxScale) {
            currScale = currScale + scaleFactor;
            new Send2_Server().execute(new PacketModel("ZOOM",currScale,0));
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void ss_mouse(){
        imageView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    downX=event.getX();
                    downY=event.getY();
                }
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    moveX=event.getX()-downX;
                    moveY=event.getY()-downY;
                    downX=event.getX();
                    downY=event.getY();
                }

                new Send2_Server().execute(new PacketModel("CURSOR",(int)moveX,(int)moveY));
                return true;
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




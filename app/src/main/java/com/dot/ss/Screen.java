package com.dot.ss;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import static com.dot.ss.Client_Rec.socket;

public class Screen extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    static int fps=0;

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

        ss_Resize();

        textView=findViewById(R.id.textView2);

        Thread thread=new Thread(new Client_Rec(imageView,Screen.this),"Client_Receiver");
        thread.start();

    }

    @SuppressLint("ClickableViewAccessibility")
    void ss_Resize(){

        imageView.setOnTouchListener(new View.OnTouchListener() {
            float xu,yu,xd,yd;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(MotionEvent.ACTION_DOWN==event.getAction()){
                    xd=event.getX();
                    yd=event.getY();
                }
                else if(MotionEvent.ACTION_UP==event.getAction()){
                    xu=event.getX();
                    yu=event.getY();
                    xd=xd-xu;
                    yd=yd-yu;
                    Log.d("cord","x= "+xd+"    y= "+yd);
                    new Send2_Server().execute(xd,yd);
                }
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




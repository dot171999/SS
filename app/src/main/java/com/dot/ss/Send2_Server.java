package com.dot.ss;

import android.os.AsyncTask;

import java.io.IOException;

import static com.dot.ss.Client_Rec.dataOutputStream;

public class Send2_Server extends AsyncTask<Float, Void, Void> {

    @Override
    protected Void doInBackground(Float... params) {
        float x=params[0];
        float y=params[1];

        try {
            dataOutputStream.writeFloat(x);
            dataOutputStream.writeFloat(y);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

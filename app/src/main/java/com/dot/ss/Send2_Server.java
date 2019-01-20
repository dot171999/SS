package com.dot.ss;

import android.os.AsyncTask;

import java.io.IOException;

import static com.dot.ss.Client_Rec.dataOutputStream;

public class Send2_Server extends AsyncTask<PacketModel, Void, Void> {

    @Override
    protected Void doInBackground(PacketModel... params) {
        PacketModel x=params[0];
        try {
            dataOutputStream.writeObject(x);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

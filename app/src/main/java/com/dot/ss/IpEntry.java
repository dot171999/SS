package com.dot.ss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class IpEntry extends AppCompatActivity {
    EditText editText;
    static String IP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ip_entry);
        editText=findViewById(R.id.textView);
    }

    public void send_IP(View view){
        IP=editText.getText().toString();
        Intent intent=new Intent(IpEntry.this,Screen.class);
        startActivity(intent);
    }
}

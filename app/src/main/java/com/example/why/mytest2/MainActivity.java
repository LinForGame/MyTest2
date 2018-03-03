package com.example.why.mytest2;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final static String tag = "MainActivity";
    private MyService2.MyBinder myBinder;
    private ServiceConnection myCon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(tag,"onServiceConnected(ComponentName name:"
                    +name+", IBinder service:"+service+")");
            myBinder = (MyService2.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(tag,"onServiceDisconnected(ComponentName name:"+name+")");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnStartByBinder = findViewById(R.id.btnStartByBinder);
        Button btnUnBinder = findViewById(R.id.btnUnBinder);
        Button btnStartThread = findViewById(R.id.btnStartThread);
        Button btnStopThread = findViewById(R.id.btnStopThread);
        Button btnGetData = findViewById(R.id.getData);
        final Intent intent = new Intent(this,MyService2.class);
        intent.setAction("nihao");
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });


        btnStartByBinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, myCon, Service.BIND_AUTO_CREATE);
            }
        });
        btnUnBinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myCon);
            }
        });

        btnStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBinder.getService().startMyThread();
            }
        });
        btnStopThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBinder.getService().stopMyThread();
            }
        });
        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tag,"getData:"+myBinder.getService().getData());
            }
        });
    }
}

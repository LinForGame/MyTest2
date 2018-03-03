package com.example.why.mytest2;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import static java.lang.Thread.sleep;

/**
 * Created by WHY on 2018/3/1.
 */


public class MyService2 extends Service {
    private final static String tag = "MyService2";
    private int privateInt=0;
    private boolean qiut = false;

    private MyBinder myBinder = new MyBinder();
    public class MyBinder extends Binder{
        public MyService2 getService(){
            return MyService2.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(tag,"onBind(Intent intent)"+"intent:"+intent);
        return myBinder;
    }

    @Override
    public void onCreate() {
            privateInt=privateInt+3;
            Log.d(tag,"onCreate():"+privateInt);
     

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(tag,"onStartCommand(Intent intent:"+intent+", int flags:"+flags+", int startId:"+startId+")");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(tag,"onDestroy()");
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(tag,"onUnbind(Intent intent:"+intent+")");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(tag,"onRebind(Intent intent:"+intent+")");
        super.onRebind(intent);
    }
    public void startMyThread(){
        Log.d(tag,"startMyThread,do nothing!");
    }
    public void stopMyThread(){
        Log.d(tag,"stopMyThread(),do nothing!");
    }
    public int getData(){
        return privateInt;
    }
}

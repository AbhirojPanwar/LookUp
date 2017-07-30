package io.github.abhiroj.lookup.Service;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class ClipBoardWatcher extends Service {

    public static String LOG_TAG=ClipBoardWatcher.class.getSimpleName();

    ClipboardManager.OnPrimaryClipChangedListener primaryClipChangedListener=new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {
           if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
           {
               if(Settings.canDrawOverlays(ClipBoardWatcher.this))
               {


               }
           }
           else
           {

           }
        }
    };

    public ClipBoardWatcher() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onCreate() {
        ((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).addPrimaryClipChangedListener(primaryClipChangedListener);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(LOG_TAG,"Service is On!! Assign Clip changed listener");
    }
}

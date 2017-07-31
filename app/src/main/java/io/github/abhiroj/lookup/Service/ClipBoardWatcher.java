package io.github.abhiroj.lookup.Service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import static io.github.abhiroj.lookup.Utils.Utility.isWord;

public class ClipBoardWatcher extends Service {

    public static String LOG_TAG=ClipBoardWatcher.class.getSimpleName();

    ClipboardManager.OnPrimaryClipChangedListener primaryClipChangedListener=new ClipboardManager.OnPrimaryClipChangedListener() {
        @Override
        public void onPrimaryClipChanged() {
           Log.d(LOG_TAG,"Primary Clip Response Changed");
            processClip();
        }
    };

    private void processClip() {
        ClipboardManager clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        if(clipboardManager.hasPrimaryClip()) {
            ClipData clipData = clipboardManager.getPrimaryClip();
        if(clipData.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
        {
            String text=clipData.getItemAt(0).getText().toString();
            if(isWord(text))
            {
               Log.d(LOG_TAG,text);
            }
        }
        }

    }

    public ClipBoardWatcher() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(LOG_TAG,"Getting on now!");
        ((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).addPrimaryClipChangedListener(primaryClipChangedListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"Getting off now!");
        ((ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).removePrimaryClipChangedListener(primaryClipChangedListener);
    }
}

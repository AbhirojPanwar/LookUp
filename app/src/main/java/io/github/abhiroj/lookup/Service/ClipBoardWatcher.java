package io.github.abhiroj.lookup.Service;

import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.util.AndroidException;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.github.abhiroj.lookup.R;

import static io.github.abhiroj.lookup.Utils.Utility.isWord;

public class ClipBoardWatcher extends Service {

    private WindowManager windowManager;
    private View view;
    private TextView meaning;

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
            final String text=clipData.getItemAt(0).getText().toString().toLowerCase();
            if(isWord(text))
            {
               Log.d(LOG_TAG,text);
                Log.d(LOG_TAG,"Ready for the overlay!");


                view= LayoutInflater.from(this).inflate(R.layout.floatview,null);
                //Add the view to the window.
                final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        0,
                        100,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT);

                params.gravity= Gravity.TOP | Gravity.LEFT;

                windowManager=(WindowManager) getSystemService(WINDOW_SERVICE);
                windowManager.addView(view,params);

                final ImageView close=(ImageView) view.findViewById(R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        windowManager.removeView(view);
                    }
                });
                view.setOnTouchListener(new View.OnTouchListener() {

                    int lastAction;
                    int initX;
                    int initY;
                    float initTouchX;
                    float initTouchY;


                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch(event.getAction())
                        {
                            case MotionEvent.ACTION_DOWN:

                                initX=params.x;
                                initY=params.y;

                                initTouchX= event.getRawX();
                                initTouchY=event.getRawY();

                                lastAction=MotionEvent.ACTION_DOWN;
                            return true;
                            case MotionEvent.ACTION_UP:
                                if(lastAction==MotionEvent.ACTION_DOWN)
                                {
                                    // TODO: Show the meaning of the word
                                    Log.d(LOG_TAG,"Here we need to show the meaning!");
                                    LinearLayout linearLayout=(LinearLayout) view.getRootView();
                                    if(meaning==null) {
                                        linearLayout.addView(appendTextView("meaning of the text chosen by user is "+ text));
                                        params.x = 0;
                                        params.y = 500;
                                    }
                                    else{
                                        destroyTextView();
                                    }
                                    windowManager.updateViewLayout(view,params);
                                }
                                return true;
                            case MotionEvent.ACTION_MOVE:
                                params.x = initX + (int) (event.getRawX() - initTouchX);
                                params.y = initY + (int) (event.getRawY() - initTouchY);
                                //Log.d(LOG_TAG,"Update X "+params.x+" Update Y "+params.y);

                                windowManager.updateViewLayout(view,params);
                                lastAction=MotionEvent.ACTION_MOVE;
                                return true;
                        }
                        return false;
                    }
                });

            }
            else{
                Log.d(LOG_TAG,"Not a valid word");
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

    // create a new meaning object
    public View appendTextView(String meaning) {
        this.meaning=new TextView(view.getContext());
        this.meaning.setText(meaning);
        this.meaning.setTextColor(Color.BLACK);
        this.meaning.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20,0,0,0);
        this.meaning.setPadding(40,40,40,40);
        this.meaning.setLayoutParams(layoutParams);
        return this.meaning;
    }

    // to destroy th
    // e meaning
    public void destroyTextView() {
        LinearLayout linearLayout=(LinearLayout) view.getRootView();
        linearLayout.removeView(meaning);
        meaning=null;
    }
}

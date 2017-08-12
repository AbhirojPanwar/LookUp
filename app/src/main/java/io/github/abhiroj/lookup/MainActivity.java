package io.github.abhiroj.lookup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import io.github.abhiroj.lookup.Service.ClipBoardWatcher;
import io.github.abhiroj.lookup.data.Constants;

public class MainActivity extends AppCompatActivity{

    private static final String LOG_TAG=MainActivity.class.getSimpleName();
    private SharedPreferences preferences;
    private Switch startService;

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG,((preferences.getBoolean(Constants.KEY,false))?"Service is running according to prefs":"Service is not running acfording to prefs")+">>> on Resume");
        if(startService!=null)
        {
            startService.setChecked(preferences.getBoolean(Constants.KEY,false));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService = (Switch) findViewById(R.id.switch_service);
        preferences = getSharedPreferences(Constants.FILE, Context.MODE_PRIVATE);

        if(getIntent().getAction().equals(Intent.ACTION_MAIN)) {
            Log.d(LOG_TAG,"First Run");
            preferences.edit().putBoolean(Constants.KEY,true).apply();
            startService.setChecked(true);
            changeServiceStatus(true);
            Toast.makeText(getApplicationContext(),R.string.service_on,Toast.LENGTH_SHORT).show();
        }
        else{
          startService.setChecked(preferences.getBoolean(Constants.KEY,false));
        }
        startService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferences.edit().putBoolean(Constants.KEY,isChecked).apply();
                changeServiceStatus(isChecked);
            }
        });
    }

    private void changeServiceStatus(boolean b) {
    Intent intent=new Intent(MainActivity.this,ClipBoardWatcher.class);
        if(b)
        {
            startService(intent);
        }
        else
        {
            stopService(intent);
        }
    }

}

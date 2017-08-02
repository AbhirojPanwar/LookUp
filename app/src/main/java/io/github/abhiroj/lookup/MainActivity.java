package io.github.abhiroj.lookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import io.github.abhiroj.lookup.Service.ClipBoardWatcher;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch startService=(Switch) findViewById(R.id.switch_service);
        if(startService!=null)
        {
            startService.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    launchorkillService(isChecked);
                }
            });
        }
    }

    private void launchorkillService(boolean state) {
        Intent intent=new Intent(MainActivity.this, ClipBoardWatcher.class);
        if(state)
        startService(intent);
        else{
            stopService(intent);
        }
    }


}

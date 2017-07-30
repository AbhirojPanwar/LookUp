package io.github.abhiroj.lookup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch startService=(Switch) findViewById(R.id.switch_service);
        if(startService!=null)
        {
            startService.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        // TODO: Start Service
    }
}

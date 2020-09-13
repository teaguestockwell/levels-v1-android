package com.TsAppDevelopment.c17mac;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.MalformedInputException;
import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {

    private int currentActivity = 0, request;
    private Vibrator v;
    private Intent zero, one, two, three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        } //remove top nav bar

        WindowManager.LayoutParams windowParams = getWindow().getAttributes();
        windowParams.screenBrightness = 1.0f;
        getWindow().setAttributes(windowParams);




        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        zero = new Intent(MainActivity2.this, Time.class);
        one = new Intent(MainActivity2.this, Units.class);
        two = new Intent(MainActivity2.this, MainActivity.class);
        three = new Intent(MainActivity2.this, FAQ.class);

        setContentView(R.layout.disclaimer);


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {


                request = Integer.parseInt(data.getStringExtra("result"));

                switch (request) {
                    case 0:
                        currentActivity = 0;
                        startActivityForResult(zero, 1);
                        break;
                    case 1:
                        currentActivity = 1;
                        startActivityForResult(one, 1);
                        break;
                    case 2:
                        currentActivity = 2;
                        startActivityForResult(two, 1);
                        break;
                    case 3:
                        currentActivity = 3;
                        startActivityForResult(three, 1);
                        break;
                }
            }
        }


    }


    public void accept(View view) {
        //setContentView(R.layout.black);
        vibrate();
        setContentView(R.layout.black);
        startActivityForResult(zero, 1);

    }


    public void contact(View view) {
        vibrate();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "C17 %MAC App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    private void vibrate() {
        v.vibrate(25);
    }


}

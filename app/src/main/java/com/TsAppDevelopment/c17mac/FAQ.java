package com.TsAppDevelopment.c17mac;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FAQ extends AppCompatActivity {
    private TextView mac;
    private ImageButton caculator, showW, moreop;
    private Vibrator v;
    private View faqscroll, opView;
    private ViewGroup moreoprellay;
    private LayoutInflater inflater;
    private boolean evenodd2, showWork;
    private ImageButton IB0, IB1, IB2, IB3;

    @Override
    public void onBackPressed(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        } //delete ugly defualt bar

        WindowManager.LayoutParams windowParams = getWindow().getAttributes();
        windowParams.screenBrightness = 1.0f;
        getWindow().setAttributes(windowParams);

        initilize();


        faqscroll.setOnTouchListener(new OnSwipeTouchListener(FAQ.this) {
            public void touch() {
                deflatemoreop();
            }

            public void onSwipeRight() {
                passActivity2();
            }


        });

        moreop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOpclick();
            }
        });
        IB0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity0();
            }
        });
        IB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity1();
            }
        });
        IB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity2();
            }
        });
        IB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity3();
            }
        });


    }


    private void endfaq() {
        vibrate();
        finish();
    } //CustomIntent.customType(this, "right-to-left");//animation repo https://www.youtube.com/watch?v=C8MrscyUXz8

    private void initilize() {
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        faqscroll = findViewById(R.id.faqscroll);
        moreop = findViewById(R.id.moreOptionsImageButton);

        moreoprellay = findViewById(R.id.mmoptionsrellayfaq);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        opView = inflater.inflate(R.layout.moreop, moreoprellay, false);

        IB0 = findViewById(R.id.IB0);
        IB1 = findViewById(R.id.IB1);
        IB2 = findViewById(R.id.IB2);
        IB3 = findViewById(R.id.IB3);

    }

    private void vibrate() {
        v.vibrate(25);
    }

    private void inflatemoreop() {
        moreoprellay.addView(opView);
        evenodd2 = true;
    }

    private void deflatemoreop() {
        moreoprellay.removeView(opView);
        evenodd2 = false;
    }

    private void moreOpclick() {
        vibrate();
        if (!evenodd2) {
            inflatemoreop();
        } else {
            deflatemoreop();
        }
    }

    public void review(View view) {
        vibrate();
        deflatemoreop();
        Uri webpage = Uri.parse(getString(R.string.playadd));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void contact(View view) {
        vibrate();
        deflatemoreop();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "C17 %MAC App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void share(View view) {
        vibrate();
        deflatemoreop();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getString(R.string.playadd);
        String shareSub = "C17 %MAC App";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    private void passActivity3() {
//        vibrate();
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("result", "3");
//        setResult(RESULT_OK, returnIntent);
//        finish();
    }

    private void passActivity2() {
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "2");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void passActivity1() {
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "1");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void passActivity0() {
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "0");
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}

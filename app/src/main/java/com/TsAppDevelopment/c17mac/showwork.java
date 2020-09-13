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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class showwork extends AppCompatActivity {

    TextView permacTextView, t1mtv, t2mtv, t3mtv, t4mtv, t1lbtv, t2lbtv, t3lbtv, t4lbtv, miscmtv, misclbtv, bLmtv, blbtv, totmtv, totlbtv, balarmtv, totm2tv, totlb2tv, balarm2tv, permacdectv, permacequalright, t2nametv, t3nametv, miscnametv;
    private boolean evenodd = false;
    private boolean iser, evenodd2;
    private int miscCargoListInflatorCounter;
    private LayoutInflater inflater;
    private String[] stringAr;
    private ArrayList<String> arrListStringMiscCargo = new ArrayList<>();
    private Vibrator v;
    private LinearLayout mainLayout; //refrence to the linear layout in the cardview of the misc card
    private ImageButton calButton, showWButton, faqButton, moreOButton, miscButton;
    private List<View> miscViews = new ArrayList<>();
    private ViewGroup line, moreoprellay, opView;
    private View swScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showwork);
        try { this.getSupportActionBar().hide(); } catch (NullPointerException e) { } //remove top nav bar

        WindowManager.LayoutParams windowParams = getWindow().getAttributes();
        windowParams.screenBrightness = 1.0f;
        getWindow().setAttributes(windowParams);

        initializeViews();
        inflateMisc();


        swScroll.setOnTouchListener(new OnSwipeTouchListener(showwork.this) {
            public void touch(){
                deflatemoreop();
            }



        });
        moreOButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOpclick();
            }
        });
        miscButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                miscButtonClicked();
            }
        });
    }


    private void initializeViews() {

        //extracts bundle
        Bundle bundle = getIntent().getExtras();
        iser = bundle.getBoolean("iser");
        stringAr = bundle.getStringArray("stringAr");
        arrListStringMiscCargo = bundle.getStringArrayList("miscList");

        //assign ImageButtons
        moreOButton = findViewById(R.id.moreOptionsImageButton);
        miscButton = findViewById(R.id.miscDropButton);

        //assign TVs
        t1mtv = findViewById(R.id.t1momTextView);
        t2mtv = findViewById(R.id.t2momTextView);
        t3mtv = findViewById(R.id.t3momTextView);
        t4mtv = findViewById(R.id.t4momTextView);
        t1lbtv = findViewById(R.id.t1lbTextView);
        t2lbtv = findViewById(R.id.t2lbTextView);
        t3lbtv = findViewById(R.id.t3lbTextView);
        t4lbtv = findViewById(R.id.t4lbTextView);
        miscmtv = findViewById(R.id.misctotmomTextView);
        misclbtv = findViewById(R.id.miscTotlbTextView);
        blbtv = findViewById(R.id.basiclbTextView);
        bLmtv = findViewById(R.id.basiclongmomTextVeiw);
        totmtv = findViewById(R.id.totalmomTextView);
        totlbtv = findViewById(R.id.totallbTextView);
        balarmtv = findViewById(R.id.balarmTextView);
        totm2tv = findViewById(R.id.totalmomTextView2);
        totlb2tv = findViewById(R.id.totallbTextView2);
        balarm2tv = findViewById(R.id.balarmTextView2);
        permacdectv = findViewById(R.id.decimalmacTextView);
        permacequalright = findViewById(R.id.percentmacTExtView2);
        t2nametv = findViewById(R.id.t2nameTextView);
        t3nametv = findViewById(R.id.t3nameTextView);
        miscnametv = findViewById(R.id.miscnametv);
        swScroll = findViewById(R.id.swScroll);
        permacTextView = findViewById(R.id.permacTVsw);
        permacTextView.setText(stringAr[0]);

        //set Tv text
        t1lbtv.setText(stringAr[1]);
        t2lbtv.setText(stringAr[2]);
        t3lbtv.setText(stringAr[3]);
        t4lbtv.setText(stringAr[4]);
        t1mtv.setText(stringAr[5]);
        t2mtv.setText(stringAr[6]);
        t3mtv.setText(stringAr[7]);
        t4mtv.setText(stringAr[8]);
        bLmtv.setText(stringAr[9]);
        blbtv.setText(stringAr[10]);
        totmtv.setText(stringAr[11]);
        totm2tv.setText(stringAr[12]);
        totlbtv.setText(stringAr[13]);
        totlb2tv.setText(stringAr[14]);
        balarmtv.setText(stringAr[15]);
        balarm2tv.setText(stringAr[16]);
        permacdectv.setText(stringAr[17]);
        permacequalright.setText(stringAr[18]);
        miscmtv.setText(stringAr[19]);
        misclbtv.setText(stringAr[20]);

        if (arrListStringMiscCargo.size() == 0) {
            miscnametv.setText("Misc");
            miscButton.setEnabled(false);
        }

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        line = (ViewGroup) inflater.inflate(R.layout.linecopy, null, false);
        moreoprellay = findViewById(R.id.mmoptionsrellaysw);
        opView = (ViewGroup) inflater.inflate(R.layout.moreop, moreoprellay, false);

        //linear lay of cardview
        mainLayout = findViewById(R.id.addmiscMainLinearlay);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        //set 2&3 ER nonER
        if (iser) {
            t2nametv.setText("Tank 2 ER");
            t3nametv.setText("Tank 3 ER");
        } else {
            t2nametv.setText("Tank 2");
            t3nametv.setText("Tank 3");
        }

        // stringAr
        // percentMACFormated,t1lb,t2lb,t3lb,t4lb,t1m,t2m,t3m,t4m,basicm,basiclb 0-10
        // ,totm,totm,totlb,totlb,balarm,balarm,permacdec,percentmacformated,totalmiscmformated,totalmisclbformated 10-20


    }


    private void inflateMisc() {

        //inflate cargo tv viewGroup for every 3 strings in  arrListStringMiscCargo
        for (int i = 0; i < (arrListStringMiscCargo.size() / 3); i++) {
            inflateonecargo();
        }
    }

    private void inflateonecargo() {

        //inflate copy and assin to View pass copy of parent parameters
        ViewGroup myLayout = (ViewGroup) inflater.inflate(R.layout.misccopy, mainLayout, false);
        ViewGroup line = (ViewGroup) inflater.inflate(R.layout.linecopy, null, false);

        //get the tvs
        ViewGroup linlay = (ViewGroup) myLayout.getChildAt(0);
        TextView tvname = (TextView) linlay.getChildAt(0);
        TextView tvmoment = (TextView) linlay.getChildAt(2);
        TextView tvweight = (TextView) linlay.getChildAt(4);

        //assign text to tvs
        tvname.setText(arrListStringMiscCargo.get(miscCargoListInflatorCounter));
        miscCargoListInflatorCounter++;
        tvmoment.setText(arrListStringMiscCargo.get(miscCargoListInflatorCounter));
        miscCargoListInflatorCounter++;
        tvweight.setText(arrListStringMiscCargo.get(miscCargoListInflatorCounter));
        miscCargoListInflatorCounter++;

        //add view to view list
        miscViews.add(myLayout);
        System.out.println("one cargo inflated");

        //do not add lineCopy if there is more cargo
        if (miscCargoListInflatorCounter != arrListStringMiscCargo.size()) {
            miscViews.add(line);
            System.out.println("one line inflated");
        }
    }

    private void caculateClicked() {
        vibrate();
        finish();  //CustomIntent.customType(this, "left-to-right");//animation repo https://www.youtube.com/watch?v=C8MrscyUXz8

    }

    private void faqClicked() {
        vibrate();
        Intent faqIntent2 = new Intent(showwork.this, FAQ.class);
        startActivity(faqIntent2);
        // CustomIntent.customType(this, "left-to-right");//animation repo https://www.youtube.com/watch?v=C8MrscyUXz8

    }

    private void miscButtonClicked() {
        vibrate();
        //even odd toggles to run the add or remove every other button click

        //add misc list views
        if (!evenodd) {
            for (int i = 0; i < miscViews.size(); i++) {
                mainLayout.addView(miscViews.get(i));
            }
            evenodd = true;
            miscnametv.setText("Misc ▲");
            miscmtv.setText("--------------");
            misclbtv.setText("--------------");
            mainLayout.addView(line);
        }

        //remove mist list views
        else {
            for (int i = 0; i < miscViews.size(); i++) {
                mainLayout.removeView(miscViews.get(i));
            }
            evenodd = false;
            miscnametv.setText("Misc ▼");
            mainLayout.removeView(line);
            miscmtv.setText(stringAr[19]);
            misclbtv.setText(stringAr[20]);
        }
    }

    private void vibrate() {
        if (v != null) {
            v.vibrate(25);
        }
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

    public void rellayblackclick(View view) {
        vibrate();
        deflatemoreop();
    }

}


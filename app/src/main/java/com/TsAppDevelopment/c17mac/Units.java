package com.TsAppDevelopment.c17mac;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.Toast.makeText;

public class Units extends AppCompatActivity {
    private ViewGroup mmoptionsrellay;
    private View moreOp, opView, mainScroll;
    private LayoutInflater inflater;

    private Vibrator v;
    private DecimalFormat format10;
    private boolean evenodd2 = false;

    private ImageButton IB0, IB1, IB2, IB3, IBsub0, IBsub1, IBsub2;

    //List of Relative Layouts for each card
    private List<RelativeLayout> listLay0 = new ArrayList<>();
    private List<RelativeLayout> listLay1 = new ArrayList<>();
    private List<RelativeLayout> listLay2 = new ArrayList<>();


    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.units);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        } //delete ugly default bar

        WindowManager.LayoutParams windowParams = getWindow().getAttributes();
        windowParams.screenBrightness = 1.0f;
        getWindow().setAttributes(windowParams);

        init();

        moreOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOpclick();
            }
        });
        mainScroll.setOnTouchListener(new OnSwipeTouchListener(Units.this) {
            public void touch() {
                defaltemoreop();
            }

            public void onSwipeLeft() {
                passActivity2();
            }

            public void onSwipeRight() {
                passActivity0();
            }
        });

        IB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity3();
            }
        });
        IB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity2();
            }
        });
        IB0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity0();
            }
        });

        IBsub0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcCard(listLay0, 0);
            }
        });
        IBsub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcCard(listLay1, 1);
            }
        });
        IBsub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcCard(listLay2, 2);
            }
        });
    }

    /**
     * checks for only one non null editText and handles setting red spinners and making toast
     *
     * @param layList the list that contains the cards relative layouts
     * @return if invalid return -1 at [0], else ret[0] = valid entry, ret[1] = card ind
     */
    double[] validateOneEntryPerCard(List<RelativeLayout> layList) {
        int size = layList.size(), counter = 0;
        double[] ret = new double[2];
        ret[0] = -1;

        for (int i = 0; i < size; i++) {// counts non null Edit Texts and puts value into return
            String strAti = ((EditText) layList.get(i).getChildAt(0)).getText().toString();
            if (!strAti.equals("")) {
                counter++;
                ret[0] = Double.parseDouble(strAti);
                ret[1] = i;
            }
        }

        for (int i = 0; i < size; i++) {//reset layout to white
            layList.get(i).setBackgroundResource(R.drawable.spinnerdraw);
        }

        if (counter > 1) { // if there is more than one non null edit text all non null layout to red
            ret[0] = -1;
            for (int i = 0; i < size; i++) {
                String strAti = ((EditText) layList.get(i).getChildAt(0)).getText().toString();
                if (!strAti.equals("")) {
                    layList.get(i).setBackgroundResource(R.drawable.redspinner);
                }
            }

            Toast t = makeText(this, "Please enter only one unit to convert at a time.", Toast.LENGTH_LONG);
            t.show();
        }
        return ret;
    }

    void calcCard(List<RelativeLayout> list, int cardind) {
        vibrate();
        double[] ret = validateOneEntryPerCard(list);
        if (ret[0] != -1) {
            double[] conv = new double[0];
            switch (cardind) {
                case 0:
                    switch ((int) ret[1]) {
                        case 0:
                            conv = new double[]{1, 5280, 1.60934, 1609.34, .868976};
                            break;
                        case 1:
                            conv = new double[]{.000189394, 1, .0003048, .3048, .000164579};
                            break;
                        case 2:
                            conv = new double[]{0.621371, 3280.84, 1, 1000, 0.539957};
                            break;
                        case 3:
                            conv = new double[]{0.000621371, 3.28084, .001, 1, 0.000539957};
                            break;
                        case 4:
                            conv = new double[]{1.15078, 6076.12, 1.852, 1852, 1};
                            break;
                        default:
                            conv = new double[]{0, 0, 0, 0, 0};
                    }
                    break;
                case 1:
                    switch ((int) ret[1]) {
                        case 0:
                            conv = new double[]{1, 0.453592};
                            break;
                        case 1:
                            conv = new double[]{2.20462, 1};
                            break;
                        default:
                            conv = new double[]{0, 0};
                    }
                    break;
                case 2:
                    switch ((int) ret[1]) {
                        case 0:
                            conv = new double[]{1, 3.78541};
                            break;
                        case 1:
                            conv = new double[]{0.264172, 1};
                            break;
                        default:
                            conv = new double[]{0, 0};
                    }
                    break;
            }
            setEditHints(list, conv, ret[0]);
        }
    }

    double[] getCardEditTextsAsdoubleArr(List<RelativeLayout> relLayList) {
        int size = relLayList.size();
        double[] ret = new double[size];

        for (int i = 0; i < size; i++) {
            ret[i] = (Double.parseDouble(((EditText) relLayList.get(i).getChildAt(0)).getText().toString()));
        }
        return ret;
    }

    void init() {
        //misc
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        format10 = new DecimalFormat("#####.#####");
        //Image Buttons
        moreOp = findViewById(R.id.moreOptionsImageButton);

        IB0 = findViewById(R.id.IB0);
        IB1 = findViewById(R.id.IB1);
        IB2 = findViewById(R.id.IB2);
        IB3 = findViewById(R.id.IB3);

        IBsub0 = findViewById(R.id.IBsub0);
        IBsub1 = findViewById(R.id.IBsub1);
        IBsub2 = findViewById(R.id.IBsub2);

        //inflater
        mmoptionsrellay = findViewById(R.id.mmoptionsrellay);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        opView = inflater.inflate(R.layout.moreop, mmoptionsrellay, false);
        mainScroll = findViewById(R.id.mainscroll);

        //listLay0
        listLay0.add((RelativeLayout) findViewById(R.id.card0lay0));
        listLay0.add((RelativeLayout) findViewById(R.id.card0lay1));
        listLay0.add((RelativeLayout) findViewById(R.id.card0lay2));
        listLay0.add((RelativeLayout) findViewById(R.id.card0lay3));
        listLay0.add((RelativeLayout) findViewById(R.id.card0lay4));

        //listLay1
        listLay1.add((RelativeLayout) findViewById(R.id.card1lay0));
        listLay1.add((RelativeLayout) findViewById(R.id.card1lay1));

        //listLay2
        listLay2.add((RelativeLayout) findViewById(R.id.card2lay0));
        listLay2.add((RelativeLayout) findViewById(R.id.card2lay1));


    }

    void setEditHints(List<RelativeLayout> lay, double[] conversionArr, double in) {
        int size = lay.size();
        double[] out = new double[size];

        for (int i = 0; i < size; i++) {
            out[i] = conversionArr[i] * in;
        }
        System.out.println();
        System.out.println("setETHint to this arr: ");
        System.out.println(Arrays.toString(out));
        System.out.println();

        for (int i = 0; i < size; i++) {
            ((EditText) lay.get(i).getChildAt(0)).setText(null);
            ((EditText) lay.get(i).getChildAt(0)).setHint(format10.format(out[i]));
        }
        View current = getCurrentFocus();
        if (current != null) current.clearFocus();

    }

    private void passActivity3() {
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "3");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void passActivity2() {
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "2");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void passActivity1() {
//        vibrate();
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("result", "1");
//        setResult(RESULT_OK, returnIntent);
//        finish();
    }

    private void passActivity0() {
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "0");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            View current = getCurrentFocus();
            current.clearFocus();
        }
        return super.dispatchTouchEvent(ev);
    }// hide keyboard & edit text cursor when clicking away

    private void inflatemoreop() {
        mmoptionsrellay.addView(opView);
        evenodd2 = true;
    }

    private void defaltemoreop() {
        mmoptionsrellay.removeView(opView);
        evenodd2 = false;
    }

    private void moreOpclick() {
        vibrate();
        if (!evenodd2) {
            inflatemoreop();
        } else {
            defaltemoreop();
        }
    }

    public void review(View view) {
        vibrate();
        defaltemoreop();
        Uri webpage = Uri.parse(getString(R.string.playadd));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void contact(View view) {
        vibrate();
        defaltemoreop();
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", getString(R.string.email), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "C17 %MAC App");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }

    public void share(View view) {
        vibrate();
        defaltemoreop();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getString(R.string.playadd);
        String shareSub = "C17 %MAC App";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    void vibrate() {
        v.vibrate(25);
    }

}

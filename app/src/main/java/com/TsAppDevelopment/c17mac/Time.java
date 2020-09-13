package com.TsAppDevelopment.c17mac;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;
import static java.lang.Integer.parseInt;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Time extends AppCompatActivity {
    private TextView zJulianTV, zTimeTV, zYMDTV, lJulianTV, lTimeTV, lYMDTV, preExpireAtDateTimeTV, julinaLookUpJulianTV, yyyymmddLookUpymdTV;
    private EditText preHoursSET, preYearET, preMonthET, preDayET, preTimeET, julianLookUpYearET, julianLookUpMonthET, julianLookUpDayET, ymdLookUpYYYYET, ymdLookUpJulianDDDET;
    private RelativeLayout preHoursLay, preYearsLay, preMonthLay, preDayLay, preTimeLay, julianLookupYearLay, julianLookupMonthLay, julianLookupDayLay, ymdLookupYearLay, ymdLookupJJJLay;
    private ImageButton expiresAtIB, JulianDayIB, yyyymmddIB, moreOp, IB0, IB1, IB2, IB3;
    private LocalDateTime localTimeNow, zuluTimeNow, julianLookupTime;
    private LocalDate ymd;
    private DateTimeFormatter getDDD = DateTimeFormatter.ofPattern("DDD"), getYYYYMMDD = DateTimeFormatter.ofPattern("yyyy MM dd"), getYYYY = DateTimeFormatter.ofPattern("yyyy");

    private Vibrator v;
    private ViewGroup mmoptionsrellay;
    private View opView, mainScroll;
    private LayoutInflater inflater;
    private boolean evenodd2 = false;

    //List of Edit Texts for each card
    private List<EditText> preListET = new ArrayList<>();
    private List<EditText> julainListET = new ArrayList<>();
    private List<EditText> ymdListET = new ArrayList<>();

    //List of Relative Layouts for each card
    private List<RelativeLayout> preListLay = new ArrayList<>();
    private List<RelativeLayout> julianListLay = new ArrayList<>();
    private List<RelativeLayout> ymdListLay = new ArrayList<>();

    @Override
    public void onBackPressed(){}

    private void initViews() {


        mmoptionsrellay = findViewById(R.id.mmoptionsrellay);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //Text Views
        zJulianTV = findViewById(R.id.zuluJuilanTV);
        zTimeTV = findViewById(R.id.zuluTimeTV);
        zYMDTV = findViewById(R.id.zuluymdTV);
        lJulianTV = findViewById(R.id.localJulianTextView);
        lTimeTV = findViewById(R.id.localTimeTV);
        lYMDTV = findViewById(R.id.localymdTV);
        preExpireAtDateTimeTV = findViewById(R.id.preExpiresAtTV);
        julinaLookUpJulianTV = findViewById(R.id.julianLookupTV);
        yyyymmddLookUpymdTV = findViewById(R.id.ymdLookUpTV);

        //Edit Texts
        preHoursSET = findViewById(R.id.prehrsUntilDueET);
        preHoursSET.setText("48");
        preYearET = findViewById(R.id.preYYYYET);
        preMonthET = findViewById(R.id.preMMET);
        preDayET = findViewById(R.id.preDDET);
        preTimeET = findViewById(R.id.preHHMMET);
        julianLookUpYearET = findViewById(R.id.julianLookupYYYYET);
        julianLookUpMonthET = findViewById(R.id.julianLookupMMET);
        julianLookUpDayET = findViewById(R.id.julianLookupDDET);
        ymdLookUpYYYYET = findViewById(R.id.ymdLookupYYYYET);
        ymdLookUpJulianDDDET = findViewById(R.id.yyyymmddLookupJJJET);

        //Edit Text Layouts
        preHoursLay = findViewById(R.id.preHoursLay);
        preYearsLay = findViewById(R.id.preYYYYLay);
        preMonthLay = findViewById(R.id.preMMLay);
        preDayLay = findViewById(R.id.preDDLay);
        preTimeLay = findViewById(R.id.preHHMMLay);
        julianLookupYearLay = findViewById(R.id.julianLookupYYYYLay);
        julianLookupMonthLay = findViewById(R.id.julianLookupMMLay);
        julianLookupDayLay = findViewById(R.id.julianLookupDDLay);
        ymdLookupYearLay = findViewById(R.id.yyyymmddLookupYYYYLay);
        ymdLookupJJJLay = findViewById(R.id.yyyymmddLookupJJJLay);

        //Image Buttons
        expiresAtIB = findViewById(R.id.preExpireImageBut);
        JulianDayIB = findViewById(R.id.julianLookupImageBut);
        yyyymmddIB = findViewById(R.id.ymdLookupImageBut);
        moreOp = findViewById(R.id.moreOptionsImageButton);

        //Edit Text List
        //preflight card
        preListET.add(preHoursSET);
        preListET.add(preYearET);
        preListET.add(preMonthET);
        preListET.add(preDayET);
        preListET.add(preTimeET);

        //Julian card
        julainListET.add(julianLookUpYearET);
        julainListET.add(julianLookUpMonthET);
        julainListET.add(julianLookUpDayET);

        //ymd card
        ymdListET.add(ymdLookUpYYYYET);
        ymdListET.add(ymdLookUpJulianDDDET);

        //Relative layout List
        //preflight card
        preListLay.add(preHoursLay);
        preListLay.add(preYearsLay);
        preListLay.add(preMonthLay);
        preListLay.add(preDayLay);
        preListLay.add(preTimeLay);

        //Julian Card
        julianListLay.add(julianLookupYearLay);
        julianListLay.add(julianLookupMonthLay);
        julianListLay.add(julianLookupDayLay);

        //ymd card
        ymdListLay.add(ymdLookupYearLay);
        ymdListLay.add(ymdLookupJJJLay);


        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        opView = inflater.inflate(R.layout.moreop, mmoptionsrellay, false);
        mainScroll = findViewById(R.id.mainscroll);

        //IB
        IB0 = findViewById(R.id.IB0);
        IB1 = findViewById(R.id.IB1);
        IB2 = findViewById(R.id.IB2);
        IB3 = findViewById(R.id.IB3);

        updateTime();

        julianLookUpYearET.setText(localTimeNow.format(getYYYY));
        ymdLookUpYYYYET.setText(localTimeNow.format(getYYYY));
        preYearET.setText(localTimeNow.format(getYYYY));


    }

    private void updateTime() {
        localTimeNow = LocalDateTime.now();
        zuluTimeNow = LocalDateTime.now(ZoneOffset.UTC);
        DateTimeFormatter getHHMM = DateTimeFormatter.ofPattern("HH:mm:ss");


        lTimeTV.setText(localTimeNow.format(getHHMM));
        zTimeTV.setText(zuluTimeNow.format(getHHMM));

        lYMDTV.setText(localTimeNow.format(getYYYYMMDD));
        zYMDTV.setText(zuluTimeNow.format(getYYYYMMDD));

        lJulianTV.setText(localTimeNow.format(getDDD));
        zJulianTV.setText(zuluTimeNow.format(getDDD));
    }

    /**
     * Checks cards edit Text values and if invalid changes the color of their respective layouts to red.
     *
     * @param card       The cards index from the top of the scroll view starting at 0
     * @param relLayList list of Relative Layouts that contain Edits Texts child views at index 0. You may use relLayList for prelfight, julain and ymd cards
     * @return return true if card is valid. False return indicated card.
     */
    private boolean cardHasValidEditTexts(List<RelativeLayout> relLayList, int card) throws IllegalStateException {
        boolean ret = true;

        // switch statement to determine card passed based on relLayList size
        switch (card) {
            case 1: //preflight calc
                for (int i = 0; i < 5; i++) {
                    RelativeLayout relLayAti = relLayList.get(i);
                    String editTextStringAtI = String.valueOf(((EditText) relLayAti.getChildAt(0)).getText());
                    int intAti;

                    //check for empty
                    if (editTextStringAtI.matches("")) {
                        relLayAti.setBackgroundResource(R.drawable.redspinner);
                        ret = false;
                        continue;
                    }

                    //check for ints
                    try {
                        intAti = parseInt(editTextStringAtI);
                    } catch (Exception e) {
                        relLayAti.setBackgroundResource(R.drawable.redspinner);
                        ret = false;
                        continue;
                    }


                    switch (i) {
                        case 0: //HH
                            if (intAti <= 0) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 1: //YYYY
                            if (intAti <= 2000 || intAti >= 3000) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 2: //MM
                            if (intAti <= 0 || intAti > 12) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 3: //DD
                            if (intAti <= 0 || intAti > 31) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 4: //HHMM
                            if (intAti < 0 || intAti > 2400) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                    }
                }
                break;
            case 2: //Julian lookup
                for (int i = 0; i <= 2; i++) {
                    RelativeLayout relLayAti = relLayList.get(i);
                    String editTextStringAtI = String.valueOf(((EditText) relLayAti.getChildAt(0)).getText());
                    int intAti;

                    //check for empty
                    if (editTextStringAtI.matches("")) {
                        relLayAti.setBackgroundResource(R.drawable.redspinner);
                        ret = false;
                        continue;
                    }

                    //check for ints
                    try {
                        intAti = parseInt(editTextStringAtI);
                    } catch (Exception e) {
                        relLayAti.setBackgroundResource(R.drawable.redspinner);
                        ret = false;
                        continue;
                    }


                    switch (i) {
                        case 0: //YYYY
                            if (intAti <= 2000 || intAti >= 3000) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 1: //MM
                            if (intAti <= 0 || intAti > 12) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 2: //DD
                            if (intAti <= 0 || intAti > 31) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                    }
                }
                break;
            case 3: //YYYYMMDD lookup
                for (int i = 0; i <= 1; i++) {
                    RelativeLayout relLayAti = relLayList.get(i);
                    String editTextStringAtI = String.valueOf(((EditText) relLayAti.getChildAt(0)).getText());
                    int intAti;

                    //check for empty
                    if (editTextStringAtI.matches("")) {
                        relLayAti.setBackgroundResource(R.drawable.redspinner);
                        ret = false;
                        continue;
                    }

                    //check for ints
                    try {
                        intAti = parseInt(editTextStringAtI);
                    } catch (Exception e) {
                        relLayAti.setBackgroundResource(R.drawable.redspinner);
                        ret = false;
                        continue;
                    }


                    switch (i) {
                        case 0: //YYYY
                            if (intAti <= 2000 || intAti >= 3000) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                        case 1: //DDD
                            if (intAti <= 0 || intAti > 366) {
                                relLayAti.setBackgroundResource(R.drawable.redspinner);
                                ret = false;
                            } else {
                                relLayAti.setBackgroundResource(R.drawable.spinnerdraw);
                            }
                            break;
                    }
                }
                break;
            default:
                throw new IllegalStateException("param card was not accounted for:" + card);
        }
        return ret;
    }

    /**
     * pulls ArrayList<Double> from a cards relativeLayoutList EditTexts
     *
     * @param relLayList realtive layout list of card
     * @return ArrayList<Double> of editText values
     */
    private double[] getCardEditTextsAsdoubleArr(List<RelativeLayout> relLayList) {
        int size = relLayList.size();
        double[] ret = new double[size];

        for (int i = 0; i < size; i++) {
            ret[i] = (Double.parseDouble(((EditText) relLayList.get(i).getChildAt(0)).getText().toString()));
        }
        return ret;
    }

    private int[] doubleArrToIntArr(double[] arr) {
        int[] ret = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ret[i] = (int) arr[i];
        }
        return ret;
    }

    private int[] hhmmTointArr(int hhmm) {
        int[] ret = new int[2];
        StringBuilder hh = new StringBuilder();
        StringBuilder mm = new StringBuilder();
        String strHHMM = String.format("%04d", hhmm);

        hh.append(strHHMM.charAt(0));
        hh.append(strHHMM.charAt(1));
        ret[0] = Integer.parseInt(hh.toString());

        mm.append(strHHMM.charAt(2));
        mm.append(strHHMM.charAt(3));
        ret[1] = Integer.parseInt(mm.toString());

        return ret;
    }

    private void vibrate() {
        v.vibrate(25);
    }


    private void calcCard1() {
        vibrate();
        try {
            if (cardHasValidEditTexts(preListLay, 1)) {
                int[] arr = doubleArrToIntArr(getCardEditTextsAsdoubleArr(preListLay));
                int[] hhmmArr = hhmmTointArr(arr[4]);


                LocalDateTime insp = LocalDateTime.of(arr[1], arr[2], arr[3], hhmmArr[0], hhmmArr[1]);
                LocalDateTime exp = insp.plusHours(arr[0]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
                preExpireAtDateTimeTV.setText(exp.format(formatter));

            }
        } catch (Exception e) {
            Toast t = makeText(this, "Invalid date for a leap year, or more than 60 MM.", LENGTH_LONG);
            t.show();
        }

    }

    private void calcCard2() {
        vibrate();
        try {
            if (cardHasValidEditTexts(julianListLay, 2)) {
                int[] arr = doubleArrToIntArr(getCardEditTextsAsdoubleArr(julianListLay));
                julianLookupTime = LocalDateTime.of(arr[0], arr[1], arr[2], 12, 30);
                julinaLookUpJulianTV.setText(julianLookupTime.format(getDDD));
            }
        } catch (Exception e) {
            Toast t = makeText(this, "Invalid date for a leap year.", LENGTH_LONG);
            t.show();
            julianListLay.get(2).setBackgroundResource(R.drawable.redspinner);
        }
    }

    private void calcCard3() {
        vibrate();
        try {
            if (cardHasValidEditTexts(ymdListLay, 3)) {
                int[] arr = doubleArrToIntArr(getCardEditTextsAsdoubleArr(ymdListLay));
                ymd = LocalDate.ofYearDay(arr[0], arr[1]);
                yyyymmddLookUpymdTV.setText(ymd.format(getYYYYMMDD));
            }
        } catch (Exception e) {
            Toast t = makeText(this, "Invalid date for a leap year.", LENGTH_LONG);
            t.show();
            ymdListLay.get(1).setBackgroundResource(R.drawable.redspinner);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        } //delete ugly defualt bar

        WindowManager.LayoutParams windowParams = getWindow().getAttributes();
        windowParams.screenBrightness = 1.0f;
        getWindow().setAttributes(windowParams);

        initViews();
        updateTime();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //do something
                updateTime();
                handler.postDelayed(this, 1000);
            }
        }, 1000);

        expiresAtIB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                calcCard1();
            }
        });
        JulianDayIB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                calcCard2();
            }
        });
        yyyymmddIB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                calcCard3();
            }
        });
        moreOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOpclick();
            }
        });
        mainScroll.setOnTouchListener(new OnSwipeTouchListener(Time.this) {
            public void touch() {
                defaltemoreop();
            }

            public void onSwipeLeft() {
                passActivity1();
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
        IB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity1();
            }
        });
        IB0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passActivity0();
            }
        });


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
        vibrate();
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "1");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void passActivity0() {
//        vibrate();
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("result", "0");
//        setResult(RESULT_OK, returnIntent);
//        finish();
    }
}

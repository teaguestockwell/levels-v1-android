package com.TsAppDevelopment.c17mac;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private int t1lb, t2lb, t3lb, t4lb, t1m, t2m, t3m, t4m;
    private double basicm, basiclb, totalm, totallb, balarm, percentMAC;//configlb,configmom;
    private final double lemac = 793.6, mac = 309.5;
    private boolean iser = false, showW = false;
    private String percentMACFormated;
    public boolean evenodd2 = false;
    public View discalimerinflatedview;

    //spinner Arrays
    private static final Integer[] tank23erlb = new Integer[]{250, 500, 750, 1000, 1250, 1500, 1750, 2000, 2250, 2500, 2750, 3000, 3250, 3500, 3750, 4000, 4250, 4500, 4750, 5000, 5250, 5500, 5750, 6000, 6250, 6500, 6750, 7000, 7250, 7500, 7750, 8000, 8250, 8500, 8750, 9000, 9250, 9500, 9750, 10000, 10250, 10500, 10750, 11000, 11250, 11500, 11750, 12000, 12250, 12500, 12750, 13000, 13250, 13500, 13750, 14000, 14250, 14500, 14750, 15000, 15250, 15500, 15750, 16000, 16250, 16500, 16750, 17000, 17250, 17500, 17750, 18000, 18250, 18500, 18750, 19000, 19250, 19500, 19750, 20000, 20250, 20500, 20750, 21000, 21250, 21500, 21750, 22000, 22250, 22500, 22750, 23000, 23250, 23500, 23750, 24000, 24250, 24500, 24750, 25000, 25250, 25500, 25750, 26000, 26250, 26500, 26750, 27000, 27250, 27500, 27750, 28000, 28250, 28500, 28750, 29000, 29250, 29500, 29750, 30000, 30250, 30500, 30750, 31000, 31250, 31500, 31750, 32000, 32250, 32500, 32750, 33000, 33250, 33500, 33750, 34000, 34250, 34500, 34750, 35000, 35250, 35500, 35750, 36000, 36250, 36500, 36750, 37000, 37250, 37500, 37750, 38240, 38740, 39240, 39740, 40240, 40740, 41240, 41740, 42240, 42740, 43240, 43740, 44240, 44740, 45240, 45740, 46240, 46740, 47240, 47740, 48240, 48740, 49240, 49740, 50240, 50740, 51240, 51740, 52240, 52740, 53240, 53740, 54240, 54740, 55240, 55740, 56240, 56740, 57240, 57740, 58240, 58740, 59240, 59740, 60240, 60740, 61240, 61740, 62240, 62740, 63240, 63640, 64240, 64740, 65240, 65740, 66240, 66740, 67240, 67740, 68240, 68740, 69240, 69740, 70240, 70740, 71240, 71740, 72240, 72740, 73240, 73740, 74240, 74640, 75240, 75740, 76240, 76740, 77240, 77740, 78240, 78740, 79240, 79740, 80240, 80740, 81240, 81740, 82240, 82740, 83240, 83740, 84240, 84540};
    private static final Integer[] tank23nonerlb = new Integer[]{250, 500, 750, 1000, 1250, 1500, 1750, 2000, 2250, 2500, 2750, 3000, 3250, 3500, 3750, 4000, 4250, 4500, 4750, 5000, 5250, 5500, 5750, 6000, 6250, 6500, 6750, 7000, 7250, 7500, 7750, 8000, 8250, 8500, 8750, 9000, 9250, 9500, 9750, 10000, 10250, 10500, 10750, 11000, 11250, 11500, 11750, 12000, 12250, 12500, 12750, 13000, 13250, 13500, 13750, 14000, 14250, 14500, 14750, 15000, 15250, 15500, 15750, 16000, 16250, 16500, 16750, 17000, 17250, 17500, 17750, 18000, 18250, 18500, 18750, 19000, 19250, 19500, 19750, 20000, 20250, 20500, 20750, 21000, 21250, 21500, 21750, 22000, 22250, 22500, 22750, 23000, 23250, 23500, 23750, 24000, 24250, 24500, 24750, 25000, 25250, 25500, 25750, 26000, 26250, 26500, 26750, 27000, 27250, 27500, 27750, 28000, 28250, 28500, 28750, 29000, 29250, 29500, 29750, 30000, 30250, 30500, 30750, 31000, 31250, 31500, 31750, 32000, 32250, 32500, 32750, 33000, 33250, 33500, 33750, 34000, 34250, 34500, 34750, 35000, 35250, 35500, 35750, 36000, 36250, 36500, 36750, 37000, 37250, 37500, 37750, 38240, 38740, 39240, 39740, 40240, 40740, 41240, 41740, 42240, 42740, 43240, 43740, 44240, 44740, 45240, 45740, 46240, 46740, 47240, 47740, 48240, 48740, 49240, 49740, 50240, 50740, 51240, 51740, 52240, 52640};
    private static final Integer[] tank14lb = new Integer[]{250, 500, 750, 1000, 1250, 1500, 1750, 2000, 2250, 2500, 2750, 3000, 3250, 3500, 3750, 4000, 4250, 4500, 4750, 5000, 5250, 5500, 5750, 6000, 6250, 6500, 6750, 7000, 7250, 7500, 7750, 8000, 8250, 8500, 8750, 9000, 9250, 9500, 9750, 10000, 10250, 10500, 10750, 11000, 11250, 11500, 11750, 12000, 12250, 12500, 12750, 13000, 13250, 13500, 13750, 14000, 14250, 14500, 14750, 15000, 15250, 15500, 15750, 16000, 16250, 16500, 16750, 17000, 17250, 17500, 17750, 18000, 18250, 18500, 18750, 19000, 19250, 19500, 19750, 20000, 20250, 20500, 20750, 21000, 21250, 21500, 21750, 22000, 22250, 22500, 22750, 23000, 23250, 23500, 23750, 24000, 24250, 24500, 24750, 25000, 25250, 25500, 25750, 26000, 26250, 26500, 26750, 27000, 27250, 27500, 27750, 28000, 28250, 28500, 28750, 29000, 29250, 29500, 29750, 30000, 30250, 30500, 30750, 31000, 31250, 31500, 31750, 32000, 32250, 32500, 32750, 33000, 33250, 33500, 33750, 34000, 34250, 34500, 34750, 35000, 35250, 35500, 35750, 36000, 36250, 36500, 36750, 37000, 37250, 37500, 37750, 37760};
    private static final String[] seNamesArr = new String[]{"", "Igloo 5 Gallon)", "2 gal liq con", "Hot Cup", "Human Waste kit", "Blanket Large", "Pillow Large w/Case", "Blanket Small", "Pillow Small w/Case", "Expendables", "Pax Demo Kit", "Pax info cards (102)", "ATGL Serviced", "LPU-6p Cot", "A/C Life Pres", "Protec clothing kit", "BA-22 Parachute", "LPU-10P", "EPOS", "PBE", "Survival Vest", "Body Armor Lvl IIIA", "60 Hz BU Converter", "Add. Aeromed Sta. ", "DV 5 Seats/Pallet", "DV 10 Seats/pallet", "15 Seats/pallet", "Flare Haz Placards", "SLIP unoccupied", "SLICC Berthing Capsule", "SLICC Conf.  Capsl unoccupied"};

    // private static final String[]  configStringArray = new String[] {"Custom","AE-1","AE-2","AE-3","AEC-1","C-1","C-2","C-3","P-1","SP-X","CP-X","ADP-1","ADP-2","ADP-3","ADC-1","ADC-2","CDS-1","DV-1","SD-1","SLC-1"};


    private static final int[] tank23ermom = new int[]{21, 43, 64, 85, 107, 128, 149, 170, 191, 213, 234, 255, 276, 297, 318, 339, 359, 380, 401, 421, 441, 461, 481, 501, 521, 541, 561, 581, 600, 620, 640, 660, 679, 699, 719, 739, 758, 778, 797, 817, 837, 857, 876, 896, 916, 935, 955, 975, 994, 1014, 1033, 1053, 1073, 1093, 1112, 1132, 1152, 1171, 1191, 1210, 1230, 1250, 1270, 1289, 1309, 1329, 1348, 1368, 1387, 1407, 1427, 1446, 1466, 1486, 1506, 1525, 1545, 1564, 1584, 1604, 1623, 1643, 1663, 1682, 1702, 1721, 1741, 1760, 1780, 1799, 1819, 1839, 1858, 1878, 1897, 1917, 1936, 1956, 1976, 1995, 2014, 2034, 2053, 2072, 2091, 2111, 2130, 2149, 2168, 2187, 2206, 2225, 2244, 2262, 2285, 2308, 2330, 2353, 2376, 2399, 2422, 2444, 2466, 2489, 2511, 2534, 2557, 2579, 2602, 2625, 2647, 2669, 2692, 2714, 2736, 2758, 2781, 2803, 2826, 2848, 2870, 2893, 2915, 2937, 2959, 2982, 3004, 3026, 3049, 3071, 3094, 3137, 3182, 3227, 3271, 3316, 3361, 3405, 3450, 3494, 3539, 3583, 3628, 3672, 3717, 3761, 3805, 3850, 3894, 3939, 3983, 4027, 4072, 4116, 4159, 4203, 4246, 4289, 4332, 4375, 4419, 4462, 4505, 4548, 4589, 4631, 4672, 4713, 4755, 4796, 4837, 4879, 4920, 4961, 5003, 5044, 5085, 5127, 5168, 5209, 5250, 5290, 5330, 5370, 5411, 5451, 5491, 5531, 5571, 5611, 5648, 5684, 5720, 5756, 5792, 5828, 5864, 5900, 5935, 5971, 6007, 6043, 6079, 6115, 6151, 6187, 6222, 6258, 6294, 6329, 6365, 6401, 6437, 6473, 6509, 6545, 6581, 6616, 6652, 6687, 6723, 6758, 6794, 6829, 6850};
    private static final int[] tank23nonermom = new int[]{21, 43, 64, 85, 107, 128, 149, 170, 191, 213, 234, 255, 276, 297, 318, 338, 359, 379, 400, 420, 440, 460, 480, 500, 520, 540, 560, 580, 600, 619, 639, 659, 679, 698, 718, 738, 758, 777, 797, 817, 836, 856, 876, 895, 915, 935, 954, 974, 994, 1013, 1033, 1053, 1072, 1092, 1112, 1131, 1151, 1171, 1190, 1210, 1230, 1249, 1269, 1289, 1308, 1328, 1348, 1367, 1387, 1407, 1426, 1446, 1466, 1485, 1505, 1525, 1544, 1564, 1584, 1603, 1623, 1643, 1662, 1682, 1701, 1721, 1740, 1760, 1780, 1799, 1819, 1838, 1858, 1877, 1897, 1916, 1936, 1955, 1975, 1994, 2013, 2033, 2052, 2072, 2095, 2118, 2141, 2164, 2187, 2209, 2232, 2255, 2277, 2300, 2322, 2345, 2367, 2390, 2412, 2435, 2457, 2480, 2502, 2524, 2547, 2569, 2592, 2614, 2636, 2659, 2681, 2703, 2725, 2748, 2770, 2792, 2815, 2837, 2859, 2882, 2904, 2926, 2948, 2971, 2993, 3015, 3038, 3060, 3082, 3104, 3127, 3170, 3215, 3260, 3304, 3349, 3393, 3438, 3483, 3527, 3571, 3615, 3660, 3704, 3748, 3792, 3836, 3880, 3924, 3969, 4013, 4057, 4101, 4144, 4186, 4227, 4268, 4309, 4350, 4391, 4424};
    private static final int[] tank14mom = new int[]{28, 56, 84, 112, 140, 168, 196, 222, 248, 274, 300, 326, 353, 379, 405, 430, 456, 482, 508, 534, 559, 585, 610, 636, 661, 687, 712, 738, 763, 789, 814, 839, 864, 889, 915, 940, 965, 990, 1016, 1041, 1066, 1091, 1116, 1141, 1166, 1190, 1215, 1240, 1265, 1290, 1315, 1340, 1365, 1390, 1414, 1439, 1464, 1489, 1514, 1538, 1563, 1588, 1612, 1637, 1662, 1686, 1711, 1736, 1760, 1785, 1810, 1834, 1859, 1883, 1908, 1932, 1957, 1982, 2006, 2031, 2055, 2080, 2104, 2129, 2153, 2178, 2202, 2227, 2251, 2276, 2300, 2325, 2349, 2373, 2398, 2422, 2447, 2471, 2495, 2520, 2544, 2569, 2593, 2617, 2641, 2665, 2689, 2713, 2737, 2761, 2785, 2809, 2833, 2857, 2881, 2905, 2929, 2953, 2977, 3001, 3024, 3048, 3072, 3096, 3119, 3143, 3167, 3191, 3214, 3238, 3262, 3285, 3309, 3332, 3356, 3379, 3403, 3427, 3450, 3474, 3497, 3520, 3543, 3566, 3590, 3613, 3636, 3659, 3682, 3705, 3729, 3730};
    private static final String[] seNumsArr = new String[]{"", "", "40", "358", "25", "260", "3", "260", "5", "280", "3.5", "280", "2", "280", "1", "", "0.5", "", "10", "260", "3", "380", "3", "280", "3620", "401", "4", "280", "1.5", "", "36", "280", "28", "280", "4", "280", "2", "", "5", "280", "11.5", "280", "8.5", "280", "43", "252", "66", "", "591", "", "767", "", "943", "", "20", "400", "1350", "", "3790", "580", "4660", "685"};
    private static final String[] mdsArr = new String[] {"C-17A"};

    // private static final int[] configlbArray = new int[] {0,1121,4741,1121,4741,1121,1121,1121,4741,1121,1121,1121,1121,4741,1121,1121,1121,4741,4741,4741};
    // private static final double[] configmomArray = new double[] {0.0,54.9,200.1,54.9,200.1,54.9,54.9,54.9,185.9,54.9,54.9,54.9,54.9,185.9,54.9,54.9,54.9,185.9,185.9,185.9};


    private ArrayAdapter<Integer> tank14lbAdapter;
    private ArrayAdapter<Integer> tank23nonerlbAdapter;
    private ArrayAdapter<Integer> tank23erlbAdapter;
    private ArrayAdapter<String> seStringArrAdapter;
    private ArrayAdapter<String> mdsarrAdapter;
    //  private ArrayAdapter<String>  configStringAdapter;

    private DecimalFormat format1, format, format2, format3;
    private String totmforS, balarmforS, decimalperforS;


    private Vibrator v; //import in androidManifest.xml   <uses-permission android:name="android.permission.VIBRATE"/>  import in java  import android.os.Vibrator;

    private ImageButton work, moreCargo, IB0, IB1, IB2, IB3, moreOp, lessCargo, submitBut;

    private Spinner tank1Spin, tank2Spin, tank3Spin, tank4Spin, seSpin,mdsSpin;//configSpin;

    private TextView tank2TextView, tank3TextView;

    private EditText basicmEditText, basicweightEditText;

    private RelativeLayout basicMomDraw, basicWDraw; //edit text drawable layouts to assign background resources


    private Switch er, flares, armor;
    private ViewGroup mmoptionsrellay, discalimerparent, botbarlay, botbarlay2;
    //misc cargo layout inflator objects
    private LayoutInflater inflater;
    private List<EditText> miscEditTexts = new ArrayList<>();
    private List<RelativeLayout> miscDraw = new ArrayList<>();
    private List<View> miscViews = new ArrayList<>();
    private LinearLayout mainLayout; //refrence to the linear layout in the cardview of the misc card
    private List<String> listMiscName = new ArrayList<>();
    private List<Double> listMisclb = new ArrayList<>();
    private List<Double> listMiscm = new ArrayList<>();
    private List<Integer> listMiscfs = new ArrayList<>();
    private double totalmisclb, totalmiscm;
    private View mainScroll, opView;

    List<String> stringListMiscCargo = new ArrayList<>();

    boolean isArmor, isFlares; //for equipment that is selected with switches

    @Override
    public void onBackPressed(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        } //delete ugly defualt bar

        WindowManager.LayoutParams windowParams = getWindow().getAttributes();
        windowParams.screenBrightness = 1.0f;
        getWindow().setAttributes(windowParams);

        assignobjects();
        // setDiscalimerinflatedview();
        initializeSpinner();

        mainScroll.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void touch() {
                defaltemoreop();
            }

            public void onSwipeRight() {
                passActivity1();
            }

            public void onSwipeLeft() {
                passActivity3();
            }


        });


        flares.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flaresClicked(isChecked);
            }
        });
        armor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                armorToggled(isChecked);
            }
        });
        er.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                erToggled(isChecked);
            }
        });
        submitBut.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                caculateMAC();
            }
        });
        moreOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreOpclick();
            }
        });
        lessCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMisc();
            }
        });
        moreCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrate();
                createMisc();
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

    private void assignobjects() {
        //assign obgects
        botbarlay = findViewById(R.id.botBarlay);
        botbarlay2 = findViewById(R.id.botbattext);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        tank14lbAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tank14lb);
        tank23nonerlbAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tank23nonerlb);
        tank23erlbAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tank23erlb);
        seStringArrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seNamesArr);
        mdsarrAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mdsArr);
        //  configStringAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, configStringArray);
        tank1Spin = findViewById(R.id.tank1Spinner);
        tank2Spin = findViewById(R.id.tank2Spinner);
        tank3Spin = findViewById(R.id.tank3Spinner);
        tank4Spin = findViewById(R.id.tank4Spinner);
        //configSpin = findViewById(R.id.aaSpinner);
        tank2TextView = findViewById(R.id.tank2TextView);
        tank3TextView = findViewById(R.id.tank3TextView);
        basicmEditText = findViewById(R.id.basicmomeEditText);
        basicweightEditText = findViewById(R.id.basicweightEditText);
        basicMomDraw = findViewById(R.id.basicMomEditTextlay);
        basicWDraw = findViewById(R.id.basicWeightEditTextlay);
        submitBut = findViewById(R.id.submitImageBut);
        moreCargo = findViewById(R.id.addButton);
        er = findViewById(R.id.erSwitch);
        flares = findViewById(R.id.flaresSwitch);
        armor = findViewById(R.id.armorSwitch);
        moreOp = findViewById(R.id.moreOptionsImageButton);
        lessCargo = findViewById(R.id.deleteButton);
        mainScroll = findViewById(R.id.mainscroll);
        seSpin = findViewById(R.id.seSpin);
        mdsSpin = findViewById(R.id.MDSspinner);
        //IB
        IB0 = findViewById(R.id.IB0);
        IB1 = findViewById(R.id.IB1);
        IB2 = findViewById(R.id.IB2);
        IB3 = findViewById(R.id.IB3);

        mmoptionsrellay = findViewById(R.id.mmoptionsrellay);


        mainLayout = findViewById(R.id.miscLayout);
        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        opView = inflater.inflate(R.layout.moreop, mmoptionsrellay, false);

        discalimerparent = findViewById(R.id.disclaimerrellayroot);
        discalimerinflatedview = inflater.inflate(R.layout.disclaimer, discalimerparent, false);

        format = new DecimalFormat("#####.#");
        format2 = new DecimalFormat("###.#");
        format3 = new DecimalFormat("#.#####");
        format1 = new DecimalFormat("##.###");
    }

    private void initializeSpinner() {
        // set the lb adapters' default drop down vertical spacing
        tank14lbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tank23nonerlbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tank23erlbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seStringArrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //configStringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set the initial adapters
        tank1Spin.setAdapter(tank14lbAdapter);
        tank2Spin.setAdapter(tank23nonerlbAdapter);
        tank3Spin.setAdapter(tank23nonerlbAdapter);
        tank4Spin.setAdapter(tank14lbAdapter);
        seSpin.setAdapter(seStringArrAdapter);
        mdsSpin.setAdapter(mdsarrAdapter);
        //configSpin.setAdapter(configStringAdapter);


        // set 130k as ramp load default and C2 config
        tank1Spin.setSelection(129);
        tank2Spin.setSelection(129);
        tank3Spin.setSelection(129);
        tank4Spin.setSelection(129);
        seSpin.setSelection(0);
        mdsSpin.setSelection(0);
        //configSpin.setSelection(0);
    }

    private boolean addMiscvalues() {

        //clears all previous values
        boolean ret = true;
        int count = 0;
        totalmiscm = 0;
        totalmisclb = 0;
        listMiscName.clear();
        listMisclb.clear();
        listMiscfs.clear();
        listMiscm.clear();

        //adds equipment to lists if switch is checked
        addEquipmentWithSwitchesToMiscList();

        //inflated edit texts are put into miscEditText in this order name, weight, FS
        //checks each edit text from inflated cargo.
        for (int x = 0; x < miscEditTexts.size(); x++) {
            if (count == 0) { //first edit is string for name
                if (!(miscEditTexts.get(x).getText().toString().matches(""))) {
                    miscDraw.get(x).setBackgroundResource(R.drawable.spinnerdraw);
                    listMiscName.add((miscEditTexts.get(x)).getText().toString());
                    count++;
                } else {
                    miscDraw.get(x).setBackgroundResource(R.drawable.redspinner);
                    count++;
                    ret = false;
                }
            } else if (count == 1) { //second edit text for lb
                if (!(miscEditTexts.get(x).getText().toString().matches(""))) {
                    miscDraw.get(x).setBackgroundResource(R.drawable.spinnerdraw);
                    double misclbtemp = (Double.parseDouble(miscEditTexts.get(x).getText().toString()));
                    listMisclb.add(misclbtemp);
                    count++;
                } else {
                    miscDraw.get(x).setBackgroundResource(R.drawable.redspinner);
                    count++;
                    ret = false;
                }
            } else {
                if (!(miscEditTexts.get(x).getText().toString().matches(""))) {//third edit text for fs
                    if (Integer.parseInt(miscEditTexts.get(x).getText().toString()) > 80 && Integer.parseInt(miscEditTexts.get(x).getText().toString()) < 2168) {
                        miscDraw.get(x).setBackgroundResource(R.drawable.spinnerdraw);
                        listMiscfs.add(Integer.parseInt(miscEditTexts.get(x).getText().toString()));
                        count = 0;
                    } else {
                        miscDraw.get(x).setBackgroundResource(R.drawable.redspinner);
                        count = 0;
                        ret = false;
                    }
                } else {
                    miscDraw.get(x).setBackgroundResource(R.drawable.redspinner);
                    count = 0;
                    ret = false;
                }
            }
        }

        // iterates through list of fs and lb then calculates moment and add to miscmoment list
        if (ret) {
            for (int x = 0; x < listMisclb.size(); x++) {
                listMiscm.add(x, (listMisclb.get(x) * listMiscfs.get(x)) / 10000);
                totalmisclb += listMisclb.get(x);
                totalmiscm += ((listMisclb.get(x) * listMiscfs.get(x)) / 10000);
            }
        }


        return ret;
    }

    private void deleteMisc() {
        vibrate();

        //remove line and my layout-- last two items of list check to prevent out of bounds
        int indxView = miscViews.size() - 1;
        int indxEditText = miscEditTexts.size() - 1;

        //remove last two views from main layout and miscViews list
        for (int x = 0; x < 2; x++) {
            if (indxView >= 0) {
                mainLayout.removeView(miscViews.get(indxView));
                miscViews.remove(indxView);
                indxView = miscViews.size() - 1;
            }
        }

        //remove last 3 EditTexts from miscEditTexts List and remove their respective relative layouts from miscdrawables list
        for (int x = 0; x < 3; x++) {
            if (indxEditText >= 0) {
                miscEditTexts.remove(indxEditText);
                miscDraw.remove(indxEditText);
                indxEditText = miscEditTexts.size() - 1;
            }
        }


    }

    private void createMisc() {
        // inflate line and assin to View
        ViewGroup line = (ViewGroup) inflater.inflate(R.layout.linecopy, null, false);

        //inflate copy and assin to View pass copy of parent parameters
        ViewGroup myLayout = (ViewGroup) inflater.inflate(R.layout.copy, mainLayout, false);

        // finding the edit texts views
        ViewGroup conlay = (ViewGroup) myLayout.getChildAt(0);
        ViewGroup laycent = (ViewGroup) conlay.getChildAt(0);
        EditText weightEditText = (EditText) laycent.getChildAt(0);

        // finding the edit texts views
        ViewGroup layright = (ViewGroup) conlay.getChildAt(1);
        EditText fsEditText = (EditText) layright.getChildAt(0);

        //find last edit text
        ViewGroup layleft = (ViewGroup) conlay.getChildAt(2);
        EditText cargoEditText = (EditText) layleft.getChildAt(0);

        //add layouts to list so we can change background resource
        miscDraw.add((RelativeLayout) layleft);
        miscDraw.add((RelativeLayout) laycent);
        miscDraw.add((RelativeLayout) layright);

        //get the pos from spinner to feed data into edits name lb fs
        int spin = seSpin.getSelectedItemPosition();

        cargoEditText.setText(seNamesArr[spin]);
        weightEditText.setText((seNumsArr[spin * 2]));
        fsEditText.setText(seNumsArr[1 + spin * 2]);


        // add edit text obgects to list
        miscEditTexts.add(cargoEditText);
        miscEditTexts.add(weightEditText);
        miscEditTexts.add(fsEditText);

        //add line and copy to misc View list
        miscViews.add(line);
        miscViews.add(myLayout);

        //add the Views to the parent-mainlayout
        mainLayout.addView(line);
        mainLayout.addView(myLayout);

    }

    private void erToggled(Boolean isChecked) {
        // when er switch is checked and unchecked the adapters are changed from 23lber to 23nonerlb
        vibrate();
        int tank2, tank3;
        if (isChecked) {
            iser = true;
            tank2 = tank2Spin.getSelectedItemPosition();
            tank3 = tank3Spin.getSelectedItemPosition();
            tank2Spin.setAdapter(tank23erlbAdapter);
            tank3Spin.setAdapter(tank23erlbAdapter);
            tank2Spin.setSelection(tank2);
            tank3Spin.setSelection(tank3);
            tank2TextView.setText("Tank 2 ER");
            tank3TextView.setText("Tank 3 ER");
        } else {
            iser = false;
            tank2 = tank2Spin.getSelectedItemPosition();
            tank3 = tank3Spin.getSelectedItemPosition();
            tank2Spin.setAdapter(tank23nonerlbAdapter);
            tank3Spin.setAdapter(tank23nonerlbAdapter);
            tank2TextView.setText("Tank 2");
            tank3TextView.setText("Tank 3");
            //Set non er fuel to closest er equivilant. to prevent outof bounds set 130k
            if (tank2 <= 181) tank2Spin.setSelection(tank2);
            else tank2Spin.setSelection(129);
            if (tank3 <= 181) tank3Spin.setSelection(tank3);
            else tank3Spin.setSelection(129);
        }
    }

    private void flaresClicked(boolean isChecked) {
        vibrate();
        isFlares = isChecked;
    }

    private void armorToggled(Boolean isChecked) {
        vibrate();
        isArmor = isChecked;
    }

    private void addEquipmentWithSwitchesToMiscList() {
        if (isFlares) {
            listMiscName.add("Flrs&Cans");
            listMisclb.add(255.0);
            listMiscfs.add(744);
        }
        if (isArmor) {
            listMiscName.add("Acft Armor");
            listMisclb.add(1125.0);
            listMiscfs.add(217);
        }
    }


    private void showWorkclick() {
        // new bundle and intent to pass values to show work activity
        Intent workIntent = new Intent(MainActivity.this, showwork.class);
        Bundle wBund = new Bundle();


        String totalmiscmformated = format.format(totalmiscm);
        String totalmisclbformated = format.format(totalmisclb);

        //add formated string to list to add into bundle for show work misc inflator NAME, MOMENT, LB

        stringListMiscCargo.clear();

        for (int x = 0; x < listMiscName.size(); x++) {
            stringListMiscCargo.add(listMiscName.get(x));
            stringListMiscCargo.add(format.format(listMiscm.get(x)));
            stringListMiscCargo.add(format.format(listMisclb.get(x)));
        }


        // stringAr percentMACFormated,t1lb,t2lb,t3lb,t4lb,t1m,t2m,t3m,t4m,basicm,basiclb,totm,totm,totlb,totlb,balarm,balarm,permacdec,percentmacformated,totalmiscmformated,totalmisclbformated
        String[] stringAr = new String[]{percentMACFormated, Integer.toString(t1lb), Integer.toString(t2lb), Integer.toString(t3lb), Integer.toString(t4lb),
                Integer.toString(t1m), Integer.toString(t2m), Integer.toString(t3m), Integer.toString(t4m),
                Double.toString(basicm), Double.toString(basiclb), totmforS, totmforS, Double.toString(totallb), Double.toString(totallb), balarmforS, balarmforS, decimalperforS, percentMACFormated, totalmiscmformated, totalmisclbformated};
        wBund.putStringArrayList("miscList", (ArrayList<String>) stringListMiscCargo);
        wBund.putStringArray("stringAr", stringAr);
        wBund.putBoolean("iser", iser);

        workIntent.putExtras(wBund);
        //passes the intent with the bundle and activity to the start activity method
        startActivity(workIntent);
        //CustomIntent.customType(this, "right-to-left");//animation repo https://www.youtube.com/watch?v=C8MrscyUXz8
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

    private void vibrate() {
        v.vibrate(25);
    }

    private void caculateMAC() {
        vibrate();
        showW = false;
        boolean miscret = addMiscvalues();
        // checks to make sure basic mom and basic weight edit text are not null
        if (!basicmEditText.getText().toString().matches("") && !basicweightEditText.getText().toString().matches("")) {

            t1lb = tank14lb[tank1Spin.getSelectedItemPosition()];
            t1m = tank14mom[tank1Spin.getSelectedItemPosition()];
            t4lb = tank14lb[tank4Spin.getSelectedItemPosition()];
            t4m = tank14mom[tank4Spin.getSelectedItemPosition()];

            if (iser) {
                t2lb = tank23erlb[tank2Spin.getSelectedItemPosition()];
                t2m = tank23ermom[tank2Spin.getSelectedItemPosition()];
                t3lb = tank23erlb[tank3Spin.getSelectedItemPosition()];
                t3m = tank23ermom[tank3Spin.getSelectedItemPosition()];
            } else {
                t2lb = tank23nonerlb[tank2Spin.getSelectedItemPosition()];
                t2m = tank23nonermom[tank2Spin.getSelectedItemPosition()];
                t3lb = tank23nonerlb[tank3Spin.getSelectedItemPosition()];
                t3m = tank23nonermom[tank3Spin.getSelectedItemPosition()];
            }

            basicm = Double.parseDouble(basicmEditText.getText().toString());
            if (basicm > 99999 || basicm < 9999) {
                basicMomDraw.setBackgroundResource(R.drawable.redspinner);
            } else {
                basicMomDraw.setBackgroundResource(R.drawable.spinnerdraw);
            }

            basiclb = Double.parseDouble(basicweightEditText.getText().toString());
            if (basiclb > 300000 || basiclb < 260000) {
                basicWDraw.setBackgroundResource(R.drawable.redspinner);
            } else {
                basicWDraw.setBackgroundResource(R.drawable.spinnerdraw);
            }


            if (miscret && basiclb <= 300000 && basiclb >= 260000 && basicm <= 99999 && basicm >= 9999) {
                //get config mom and lb
                // configlb=configlbArray[configSpin.getSelectedItemPosition()];
                //configmom=configmomArray[configSpin.getSelectedItemPosition()];

                //caclulate
                totalm = t1m + t2m + t3m + t4m + basicm + totalmiscm;// + configmom;
                totallb = t1lb + t2lb + t3lb + t4lb + basiclb + totalmisclb;// + configlb;
                balarm = (totalm * 10000.0) / totallb;
                percentMAC = 100.0 * (balarm - lemac) / mac;
                //format for TextViews and Bundle
                totmforS = format.format(totalm);
                balarmforS = format2.format(balarm);
                decimalperforS = format3.format(percentMAC / 100.0);
                percentMACFormated = format1.format(percentMAC);

                //macTextView.setText(percentMACFormated);
                showW = true;
                showWorkclick();
            }


        } else { //check for invalid basic moment or weight input and set error resource

            if (basicmEditText.getText().toString().matches("")) {
                basicMomDraw.setBackgroundResource(R.drawable.redspinner);
            } else {
                basicm = Double.parseDouble(basicmEditText.getText().toString());
                if (basicm > 99999 || basicm < 9999) {
                    basicMomDraw.setBackgroundResource(R.drawable.redspinner);
                } else {
                    basicMomDraw.setBackgroundResource(R.drawable.spinnerdraw);
                }
            }

            if (basicweightEditText.getText().toString().matches("")) {
                basicWDraw.setBackgroundResource(R.drawable.redspinner);
            } else {
                basiclb = Double.parseDouble(basicweightEditText.getText().toString());
                if (basiclb > 300000 || basiclb < 260000) {
                    basicWDraw.setBackgroundResource(R.drawable.redspinner);
                } else {
                    basicWDraw.setBackgroundResource(R.drawable.spinnerdraw);
                }
            }
        }
    }

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
//        vibrate();
//        Intent returnIntent = new Intent();
//        returnIntent.putExtra("result", "2");
//        setResult(RESULT_OK, returnIntent);
//        finish();
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

/*
    public void setDiscalimerinflatedview() {
        botbarlay.setVisibility(View.GONE);
        botbarlay2.setVisibility(View.GONE);
        discalimerparent.addView(discalimerinflatedview);
    }

    public void accept(View view) {
        vibrate();
        botbarlay.setVisibility(View.VISIBLE);
        botbarlay2.setVisibility(View.VISIBLE);
        discalimerparent.removeView(discalimerinflatedview);
    }
*/

}
package yolaslo.opticalcntrex;

import android.content.Context;
import android.content.Intent;
//import android.graphics.Color;
//import android.media.Image;
//import android.net.Uri;
//import android.os.*;
//import android.app.Activity;
import android.graphics.Color;
import android.os.Process;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
//import android.support.v4.widget.Space;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;




//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

//public class OpticalFiberCount extends AppCompatActivity implements Fragment1.OnUpdateStartCountListener{
public class OpticalFiberCount extends AppCompatActivity {



    public static final String FIRST_RUN = "FIRST_RUN";
    public static final String START_COUNT = "START_COUNT";
    private static final String INSTALL_COUNT = "INSTALL_COUNT";
    private static final String CORRECTED_COUNT = "CORRECTED_COUNT";
    private static final String TUBE_NUMBER = "TUBE_NUMBER";
    private static final String FIBER_NUMBER = "FIBER_NUMBER";
    private static final String SERIES_NUMBER = "SERIES_NUMBER";

    public double startcount;
    private double installcount;
    private double correctedcount;
    private double tubenumber;
    private double fibernumber;
    private double seriesnumber;

    private EditText startcountET;
    private EditText installcountET;
    private EditText correctedcountET;
    private EditText tubenumberET;
    private EditText fibernumberET;
    private EditText seriesnumberET;
    private EditText fibercolorET;
    private EditText tubecolorET;

    Button decreasestartButton;
    Button increasestartButton;
    Button decreaseinstallButton;
    Button increaseinstallButton;
    View upperSP;
    View middleSP;
    View lowerSP;



    private static final String TAG = "TAG*****MAIN";




    @Override

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "*********************THISSHOULDBETHERE*********************");


        setContentView(R.layout.activity_optical_fiber_count);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        //
        AdRequest adRequest = new AdRequest.Builder().build();
        //
        // AdRequest request = new AdRequest.Builder()
        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
        // .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
        //         .build();
        mAdView.loadAd(adRequest);

        if (savedInstanceState == null) {
            //just started

            startcount = 1;
            installcount = 1;
            correctedcount = 1;
            tubenumber = 1;
            fibernumber = 1;
            seriesnumber = 1;
        } else {
            //app restored

            startcount = savedInstanceState.getDouble(START_COUNT);
            installcount = savedInstanceState.getDouble(INSTALL_COUNT);
            correctedcount = savedInstanceState.getDouble(CORRECTED_COUNT);
            tubenumber = savedInstanceState.getDouble(TUBE_NUMBER);
            fibernumber = savedInstanceState.getDouble(FIBER_NUMBER);
            seriesnumber = savedInstanceState.getDouble(SERIES_NUMBER);
        }


        //initialize EditTexts

        startcountET = (EditText) findViewById(R.id.editTextstartcount);
        installcountET = (EditText) findViewById(R.id.editTextinstallcount);
        correctedcountET = (EditText) findViewById(R.id.editTextcorrectedcount);
        tubenumberET = (EditText) findViewById(R.id.editTexttubenumber);
        fibernumberET = (EditText) findViewById(R.id.editTextfibernumber);
        seriesnumberET = (EditText) findViewById(R.id.editTextseriesnumber);
        tubecolorET = (EditText) findViewById(R.id.editTexttubecolor);
        fibercolorET = (EditText) findViewById(R.id.editTextfibercolor);

        //initialize buttons
        decreasestartButton = (Button) findViewById(R.id.decreasestartButton);
        increasestartButton = (Button) findViewById(R.id.increasestartButton);
        decreaseinstallButton = (Button) findViewById(R.id.decreaseinstallButton);
        increaseinstallButton = (Button) findViewById(R.id.increaseinstallButton);

        setButtonOnClickListeners();

        //initialize Views (spaces)
        upperSP = (View) findViewById(R.id.editSpaceabove);
        middleSP = (View) findViewById(R.id.editSpacemiddle);
        lowerSP = (View) findViewById(R.id.editSpacebottom);
        //initialize images

        //innerIV = (ImageView) findViewById(R.id.innerimageView);
        //outerIV = (ImageView) findViewById(R.id.outerimageView);


        //add change listener for when startcount is changed
        startcountET.addTextChangedListener(startcountListener);

        //Log.i("TAG", "*********************WHATAREYOUDOING*********************");
        installcountET.addTextChangedListener(installcountListener);





    }



    private TextWatcher startcountListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            startcountET = (EditText) findViewById(R.id.editTextstartcount);
            installcountET = (EditText) findViewById(R.id.editTextinstallcount);

            if (s.length() > 0) {
                double startcount = Double.parseDouble(startcountET.getText().toString());
                double installcount = Double.parseDouble(installcountET.getText().toString());
                if (startcount < 1) {
                    startcountET.setText("1");

                }
                if (installcount < startcount) {
                    installcountET.setText(startcountET.getText().toString());
                }
                updatecorrectedcount();

            }
        }

    };

    private TextWatcher installcountListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            startcountET = (EditText) findViewById(R.id.editTextstartcount);
            installcountET = (EditText) findViewById(R.id.editTextinstallcount);

            if (s.length() > 0){

                double installcount = Double.parseDouble(installcountET.getText().toString());
                double startcount = Double.parseDouble(startcountET.getText().toString());

                if (installcount < startcount){
                    installcountET.setText(startcountET.getText().toString());

                }

                updatecorrectedcount();


            }


        }
    };

    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        outState.putDouble(CORRECTED_COUNT, correctedcount);
        outState.putDouble(INSTALL_COUNT, installcount);
        outState.putDouble(START_COUNT, startcount);
        outState.putDouble(TUBE_NUMBER, tubenumber);
        outState.putDouble(FIBER_NUMBER, fibernumber);
        outState.putDouble(SERIES_NUMBER, seriesnumber);
        super.onSaveInstanceState(outState);
    }

    public void onStop(Bundle outState) {
        Log.d(TAG, "onStop");
        outState.putDouble(CORRECTED_COUNT, correctedcount);
        outState.putDouble(INSTALL_COUNT, installcount);
        outState.putDouble(START_COUNT, startcount);
        outState.putDouble(TUBE_NUMBER, tubenumber);
        outState.putDouble(FIBER_NUMBER, fibernumber);
        outState.putDouble(SERIES_NUMBER, seriesnumber);
        super.onSaveInstanceState(outState);
    };

    private void setButtonOnClickListeners() {
        increasestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (startcountET.getText().toString().length()>0) {
                    double startcount = Double.parseDouble(startcountET.getText().toString());
                    int startcountplus = (int) startcount + 1;

                    startcountET.setText(String.valueOf(startcountplus));
                    //updatecorrectedcount();
                }
            }
        });
        decreasestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startcountET.getText().toString().length()>0) {
                    double startcount = Double.parseDouble(startcountET.getText().toString());

                    int startcountminus = (int) startcount - 1;

                    startcountET.setText(String.valueOf(startcountminus));
                    //updatecorrectedcount();

                }

            }
        });
        increaseinstallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (installcountET.getText().toString().length()>0) {
                    double installcount = Double.parseDouble(installcountET.getText().toString());

                    int installcountplus = (int) installcount + 1;

                    installcountET.setText(String.valueOf(installcountplus));
                    //updatecorrectedcount();

                }
            }
        });

        decreaseinstallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (installcountET.getText().toString().length()>0) {
                    double installcount = Double.parseDouble(installcountET.getText().toString());

                    int installcountminus = (int) installcount - 1;

                    installcountET.setText(String.valueOf(installcountminus));
                    //updatecorrectedcount();

                }
            }
        });


    }

    public void updatecorrectedcount() {

        //reinitializing the edittexts
        Log.d(TAG, "updatecorrectedcount");
        startcountET = (EditText) findViewById(R.id.editTextstartcount);
        installcountET = (EditText) findViewById(R.id.editTextinstallcount);
        correctedcountET = (EditText) findViewById(R.id.editTextcorrectedcount);
        tubenumberET = (EditText) findViewById(R.id.editTexttubenumber);
        fibernumberET = (EditText) findViewById(R.id.editTextfibernumber);
        seriesnumberET = (EditText) findViewById(R.id.editTextseriesnumber);
        tubecolorET = (EditText) findViewById(R.id.editTexttubecolor);
        fibercolorET = (EditText) findViewById(R.id.editTextfibercolor);


        double startcount = Double.parseDouble(startcountET.getText().toString());
        double installcount = Double.parseDouble(installcountET.getText().toString());



        int correctedcount = (int) (installcount - startcount + 1);

        int seriesnumber = ((correctedcount - 1) / 144 + 1);
        correctedcount = ((correctedcount) - (seriesnumber - 1) * 144);


        correctedcountET.setText(String.valueOf(correctedcount));
        seriesnumberET.setText(String.valueOf(seriesnumber));
        int tubenumber =  ((((correctedcount) - 1) / 12) + 1);
        tubenumberET.setText(String.valueOf(tubenumber));

        //Log.i("TAG***********","FRAG2 not REPLACED MARK-1");

        if (tubenumber == 1){
            tubecolorET.setText(R.string.blue);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#006fc0"));
            upperSP.setBackgroundColor(Color.parseColor("#006fc0"));
            lowerSP.setBackgroundColor(Color.parseColor("#006fc0"));
            //outerIV.setImageResource(R.drawable.fiberoutblue);
        }else if (tubenumber == 2){
            tubecolorET.setText(R.string.orange);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#f79546"));
            upperSP.setBackgroundColor(Color.parseColor("#f79546"));
            lowerSP.setBackgroundColor(Color.parseColor("#f79546"));

            //outerIV.setImageResource(R.drawable.fiberoutorange);
        }else if (tubenumber == 3){
            tubecolorET.setText(R.string.green);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#00cc00"));
            upperSP.setBackgroundColor(Color.parseColor("#00cc00"));
            lowerSP.setBackgroundColor(Color.parseColor("#00cc00"));
            //outerIV.setImageResource(R.drawable.fiberoutgreen);
        }else if (tubenumber == 4){
            tubecolorET.setText(R.string.brown);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#964707"));
            upperSP.setBackgroundColor(Color.parseColor("#964707"));
            lowerSP.setBackgroundColor(Color.parseColor("#964707"));
            //outerIV.setImageResource(R.drawable.fiberoutbrown);
        }else if (tubenumber == 5){
            tubecolorET.setText(R.string.slate);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#bebebe"));
            upperSP.setBackgroundColor(Color.parseColor("#bebebe"));
            lowerSP.setBackgroundColor(Color.parseColor("#bebebe"));
            //outerIV.setImageResource(R.drawable.fiberoutslate);
        }else if (tubenumber == 6){
            tubecolorET.setText(R.string.white);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#ffffff"));
            upperSP.setBackgroundColor(Color.parseColor("#ffffff"));
            lowerSP.setBackgroundColor(Color.parseColor("#ffffff"));
            //outerIV.setImageResource(R.drawable.fiberoutwhite);
        }else if (tubenumber == 7){
            tubecolorET.setText(R.string.red);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#ff0000"));
            upperSP.setBackgroundColor(Color.parseColor("#ff0000"));
            lowerSP.setBackgroundColor(Color.parseColor("#ff0000"));
            //outerIV.setImageResource(R.drawable.fiberoutred);
        }else if (tubenumber == 8){
            tubecolorET.setText(R.string.black);
            tubecolorET.setTextColor(Color.WHITE);
            tubecolorET.setBackgroundColor(Color.parseColor("#000000"));
            upperSP.setBackgroundColor(Color.parseColor("#000000"));
            lowerSP.setBackgroundColor(Color.parseColor("#000000"));
            //outerIV.setImageResource(R.drawable.fiberoutblack);
        }else if (tubenumber == 9){
            tubecolorET.setText(R.string.yellow);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#ffff00"));
            upperSP.setBackgroundColor(Color.parseColor("#ffff00"));
            lowerSP.setBackgroundColor(Color.parseColor("#ffff00"));
            //outerIV.setImageResource(R.drawable.fiberoutyellow);
        }else if (tubenumber == 10){
            tubecolorET.setText(R.string.violet);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#9966ff"));
            upperSP.setBackgroundColor(Color.parseColor("#9966ff"));
            lowerSP.setBackgroundColor(Color.parseColor("#9966ff"));
            //outerIV.setImageResource(R.drawable.fiberoutviolet);
        }else if (tubenumber == 11){
            tubecolorET.setText(R.string.rose);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#ffccff"));
            upperSP.setBackgroundColor(Color.parseColor("#ffccff"));
            lowerSP.setBackgroundColor(Color.parseColor("#ffccff"));
            //outerIV.setImageResource(R.drawable.fiberoutrose);
        }else {
            tubecolorET.setText(R.string.aqua);
            tubecolorET.setTextColor(Color.BLACK);
            tubecolorET.setBackgroundColor(Color.parseColor("#66ffff"));
            upperSP.setBackgroundColor(Color.parseColor("#66ffff"));
            lowerSP.setBackgroundColor(Color.parseColor("#66ffff"));
            //outerIV.setImageResource(R.drawable.fiberoutaqua);
        }

        int fibernumber = ((correctedcount) - (tubenumber - 1) * 12);
        fibernumberET.setText(String.valueOf(fibernumber));
        if (fibernumber == 1){
            fibercolorET.setText(R.string.blue);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#006fc0"));
            middleSP.setBackgroundColor(Color.parseColor("#006fc0"));
            //innerIV.setImageResource(R.drawable.fiberinblue);
        }else if (fibernumber == 2){
            fibercolorET.setText(R.string.orange);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#f79546"));
            middleSP.setBackgroundColor(Color.parseColor("#f79546"));
            //innerIV.setImageResource(R.drawable.fiberinorange);
        }else if (fibernumber == 3){
            fibercolorET.setText(R.string.green);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#00cc00"));
            middleSP.setBackgroundColor(Color.parseColor("#00cc00"));
            //innerIV.setImageResource(R.drawable.fiberingreen);
        }else if (fibernumber == 4){
            fibercolorET.setText(R.string.brown);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#964707"));
            middleSP.setBackgroundColor(Color.parseColor("#964707"));
            //innerIV.setImageResource(R.drawable.fiberinbrown);
        }else if (fibernumber == 5){
            fibercolorET.setText(R.string.slate);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#bebebe"));
            middleSP.setBackgroundColor(Color.parseColor("#bebebe"));
            //innerIV.setImageResource(R.drawable.fiberinslate);
        }else if (fibernumber == 6){
            fibercolorET.setText(R.string.white);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#ffffff"));
            middleSP.setBackgroundColor(Color.parseColor("#ffffff"));
            //innerIV.setImageResource(R.drawable.fiberinwhite);
        }else if (fibernumber == 7){
            fibercolorET.setText(R.string.red);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#ff0000"));
            middleSP.setBackgroundColor(Color.parseColor("#ff0000"));
            //innerIV.setImageResource(R.drawable.fiberinred);
        }else if (fibernumber == 8){
            fibercolorET.setText(R.string.black);
            fibercolorET.setTextColor(Color.WHITE);
            fibercolorET.setBackgroundColor(Color.parseColor("#000000"));
            middleSP.setBackgroundColor(Color.parseColor("#000000"));
            //innerIV.setImageResource(R.drawable.fiberinblack);
        }else if (fibernumber == 9){
            fibercolorET.setText(R.string.yellow);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#ffff00"));
            middleSP.setBackgroundColor(Color.parseColor("#ffff00"));
            //innerIV.setImageResource(R.drawable.fiberinyellow);
        }else if (fibernumber == 10){
            fibercolorET.setText(R.string.violet);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#9966ff"));
            middleSP.setBackgroundColor(Color.parseColor("#9966ff"));
            //innerIV.setImageResource(R.drawable.fiberinviolet);
        }else if (fibernumber == 11){
            fibercolorET.setText(R.string.rose);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#ffccff"));
            middleSP.setBackgroundColor(Color.parseColor("#ffccff"));
            //innerIV.setImageResource(R.drawable.fiberinrose);
        }else {
            fibercolorET.setText(R.string.aqua);
            fibercolorET.setTextColor(Color.BLACK);
            fibercolorET.setBackgroundColor(Color.parseColor("#66ffff"));
            middleSP.setBackgroundColor(Color.parseColor("#66ffff"));
            //innerIV.setImageResource(R.drawable.fiberinaqua);
        }


    }






    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        
        getMenuInflater().inflate(R.menu.menu_optical_fiber_count, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected: ");
        int id = item.getItemId();
        startcountET = (EditText) findViewById(R.id.editTextstartcount);
        installcountET = (EditText) findViewById(R.id.editTextinstallcount);
        double startcount = Double.parseDouble(startcountET.getText().toString());
        double installcount = Double.parseDouble(installcountET.getText().toString());
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_table) {
            Log.d(TAG, "onOptionsItemSelected: TABLE");
            
            Intent theIntent = new Intent(getApplication(), TablePage.class);
            theIntent.putExtra(START_COUNT, startcount);
            theIntent.putExtra(INSTALL_COUNT, installcount);
            startActivity(theIntent);

            return true;
        }


        if (id == R.id.action_about) {
            //     showabout();
            Intent theIntent = new Intent(getApplication(), AboutPage.class);
            startActivity(theIntent);

            return true;
        }

        if (id == R.id.action_exit) {
            android.os.Process.killProcess(Process.myPid());
            System.exit(1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }






}
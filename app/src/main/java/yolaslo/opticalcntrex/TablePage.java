package yolaslo.opticalcntrex;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Admin on 12/17/2016.
 */

public class TablePage extends OpticalFiberCount {




    String backcolor;
    String textcolor;
//    private TableLayout tableList;

    private static final String TAG = "TAG*****TABLE";
    private static final String START_COUNT = "START_COUNT";
    private static final String INSTALL_COUNT = "INSTALL_COUNT";

    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_optical_fiber_list);

        Intent theIntent = getIntent();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        //
        AdRequest adRequest = new AdRequest.Builder().build();
        //
        // AdRequest request = new AdRequest.Builder()
        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
        // .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")  // An example device ID
        //         .build();
        mAdView.loadAd(adRequest);



        double startcount = theIntent.getDoubleExtra(START_COUNT, 1.0);
        double installcount = theIntent.getDoubleExtra(INSTALL_COUNT, 1.0);


        TableLayout tableList = (TableLayout) findViewById(R.id.tableList);
        ScrollView mScrollView = (ScrollView) findViewById(R.id.mScrollView);


//start for loop here
        for (int i = 0; i <576; i++) {

            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView tv1 = new TextView(this);
            tv1.setText(String.valueOf((int) startcount + i));
            tv1.setId(Integer.valueOf(i + 1000));
            tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
            int id1 = tv1.getId();
            Log.i(TAG, "id1=" + String.valueOf(id1));
            CheckBox cb1 = new CheckBox(this);

            TextView tv2 = new TextView(this);

            int tubecolorcount;
            int seriesintegercount = (int) ( (i+1) / 144);
            tubecolorcount = (int) (( i - (seriesintegercount * 144)) / 12 + 1);

            if (tubecolorcount == 1) {
                backcolor = "#006fc0";
                textcolor = "#000000";
            } else if (tubecolorcount == 2) {
                backcolor = "#f79546";
                textcolor = "#000000";
            } else if (tubecolorcount == 3) {
                backcolor = "#00cc00";
                textcolor = "#000000";
            } else if (tubecolorcount == 4) {
                backcolor = "#964707";
                textcolor = "#000000";
            } else if (tubecolorcount == 5) {
                backcolor = "#bebebe";
                textcolor = "#000000";
            } else if (tubecolorcount == 6) {
                backcolor = "#ffffff";
                textcolor = "#000000";
            } else if (tubecolorcount == 7) {
                backcolor = "#ff0000";
                textcolor = "#000000";
            } else if (tubecolorcount == 8) {
                backcolor = "#000000";
                textcolor = "#ffffff";
            } else if (tubecolorcount == 9) {
                backcolor = "#ffff00";
                textcolor = "#000000";
            } else if (tubecolorcount == 10) {
                backcolor = "#9966ff";
                textcolor = "#000000";
            } else if (tubecolorcount == 11) {
                backcolor = "#ffccff";
                textcolor = "#000000";
            } else if (tubecolorcount == 12) {
                backcolor = "#66ffff";
                textcolor = "#000000";
            } else {
                backcolor = "#ffffff";
                textcolor = "#000000";
            };
            tv2.setText("TUBE COLOR");
            tv2.setBackgroundColor(Color.parseColor(String.valueOf(backcolor)));
            tv2.setTextColor(Color.parseColor(String.valueOf(textcolor)));
            tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);

            int fiberintegercolorcount =  (int) ( (i+1)/ 12);
            int fibercolorcount = (int) (( (i+1) - fiberintegercolorcount * 12) );
            if (fibercolorcount == 1) {
                backcolor = "#006fc0";
                textcolor = "#000000";
            } else if (fibercolorcount == 2) {
                backcolor = "#f79546";
                textcolor = "#000000";
            } else if (fibercolorcount == 3) {
                backcolor = "#00cc00";
                textcolor = "#000000";
            } else if (fibercolorcount == 4) {
                backcolor = "#964707";
                textcolor = "#000000";
            } else if (fibercolorcount == 5) {
                backcolor = "#bebebe";
                textcolor = "#000000";
            } else if (fibercolorcount == 6) {
                backcolor = "#ffffff";
                textcolor = "#000000";
            } else if (fibercolorcount == 7) {
                backcolor = "#ff0000";
                textcolor = "#000000";
            } else if (fibercolorcount == 8) {
                backcolor = "#000000";
                textcolor = "#ffffff";
            } else if (fibercolorcount == 9) {
                backcolor = "#ffff00";
                textcolor = "#000000";
            } else if (fibercolorcount == 10) {
                backcolor = "#9966ff";
                textcolor = "#000000";
            } else if (fibercolorcount == 11) {
                backcolor = "#ffccff";
                textcolor = "#000000";
            } else if (fibercolorcount == 0) {
                backcolor = "#66ffff";
                textcolor = "#000000";
            } else {
                backcolor = "#ffffff";
                textcolor = "#000000";
            };


            TextView tv3 = new TextView(this);
            tv3.setText("FIBER COLOR");
            tv3.setTextColor(Color.parseColor(String.valueOf(textcolor)));
            tv3.setBackgroundColor(Color.parseColor(String.valueOf(backcolor)));
            tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);

            row.addView(tv1);
            row.addView(cb1);
            row.addView(tv3);
            row.addView(tv2);
            tableList.addView(row);

        }
        for ( int i = 0 ; i < (int) installcount/16; i++) {
            mScrollView.scrollTo(0, mScrollView.getBottom());
        }
        //mScrollView.scrollTo(0, (int) installcount );

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                    @Override
//                    public void onScrollChange(View tabv, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                        if (tabv.getTop() == scrollY) {
//                            Log.e("TAG", "onScrollChange: top");
//                        } else {
//
//                        }
//                    }
//                });
//            } else {
//            }


    }








    //  public void rateme()
    //       {
    //           try
    //           {
    //              Intent rateIntent = rateIntentForUrl("market://details");
    //             startActivity(rateIntent);
    //          }
    //          catch (ActivityNotFoundException e)
    //          {
    //              Intent rateIntent = rateIntentForUrl("http://play.google.com/store/apps/details");
    //              startActivity(rateIntent);
    //          }
    //      }

    //  private Intent rateIntentForUrl(String url)
    //  {
    //      Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("%s?id=%s", url, getPackageName())));
    //      int flags = Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
    //      if (Build.VERSION.SDK_INT >= 21)
    //      {
    //          flags |= Intent.FLAG_ACTIVITY_NEW_DOCUMENT;
    //      }
    //      else
    //      {
    //noinspection deprecation
    //          flags |= Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;
    //      }
    //      intent.addFlags(flags);
    //      return intent;
    //  }

    public void continuebutt (View view) {
        this.callMainActivity(view);
    }



    public void callMainActivity(View view) {

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }




















}

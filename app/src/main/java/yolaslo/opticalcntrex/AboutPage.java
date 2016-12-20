package yolaslo.opticalcntrex;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.*;
import android.view.View;

/**
 * Created by Yo&Laslo on 4/26/2016.
 */
public class AboutPage extends OpticalFiberCount {


    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about_page);
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

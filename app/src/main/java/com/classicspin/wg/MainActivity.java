package com.classicspin.wg;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import com.classicspin.wg.manedger.PreferencesManagerImpl;
import com.classicspin.wg.spin.SpinActivity;
import com.classicspin.wg.spin.SpinSelectActivity;
import com.facebook.applinks.AppLinkData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


import static com.classicspin.wg.Constants.DEPLINK;
import static com.classicspin.wg.Constants.MYLOG_TEG;

;

public class MainActivity extends AppCompatActivity {
    public static  final  String q = "a";
    public static  final  String w = "s.";
    public static  final  String t= "s";
    public static  final  String c = "p";
    public static  final  String rq = "a";
    public static  final  String aw = "c";
    public static  final  String ar = "e";

    public String URL = "wwr";
    private DownloadTask task;
    private CountDownTimer waitToshow;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new DownloadTask();


        waitToshow = new CountDownTimer(3000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                showWeb();
            }
        };

        if (PreferencesManagerImpl.getMyFirstTime()) {
            //the app is being launched for first time, do something
            Log.e(MYLOG_TEG, "First time");
            PreferencesManagerImpl.setURL(null);

            // first time task

            getDeplink();


            // record the fact that the app has been started at least once
            PreferencesManagerImpl.setMyFirstTime(false);


        } else {
            if (PreferencesManagerImpl.getSateStartSte()) {
                waitToshow.start();
            } else if (PreferencesManagerImpl.getGameStart()) {
                showGame();

            }

        }


    }

    private void getDeplink() {
        try {


            AppLinkData.fetchDeferredAppLinkData(this, appLinkData -> {
                AppLinkData appLinkData1 = appLinkData;
                if (appLinkData1 == null || appLinkData1.getTargetUri() == null) {
                    PreferencesManagerImpl.setSateStartSte(true);
                    getUrl();

                } else {

                    String url = appLinkData1.getTargetUri().toString();

                    String string = convertArrayToStringMethod(url.split(DEPLINK));

                    PreferencesManagerImpl.setURL(string);
                    PreferencesManagerImpl.setSateStartSte(true);

                    getUrl();

                    //TODO
                }
            });
        } catch (Exception e) {

            e.printStackTrace();
        }

    }


    private void showGame() {
        Intent intent = new Intent(this, SpinSelectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    private void showWeb() {

        try {
            String PreferencesManagerImplURL = PreferencesManagerImpl.getURL();
            if (PreferencesManagerImplURL != null) {
                uri = PreferencesManagerImplURL;


            }


            final Bitmap backButton = BitmapFactory.decodeResource(getResources(), R.drawable.round_done_black_24dp);

            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            builder.enableUrlBarHiding();
            builder.setToolbarColor(Color.BLACK);
            builder.setShowTitle(false);
            builder.addDefaultShareMenuItem();
            builder.setCloseButtonIcon(backButton);

            CustomTabsIntent customTabsIntent = builder.build();

            boolean chromeInstalled = false;
            for (ApplicationInfo applicationInfo : getPackageManager().getInstalledApplications(0)) {
                if (applicationInfo.packageName.equals("com.android.chrome")) {
                    chromeInstalled = true;
                    break;
                }
            }
            if (chromeInstalled) {
                customTabsIntent.intent.setPackage("com.android.chrome");
            }
            customTabsIntent.launchUrl(this, Uri.parse(uri));
            finish();


        } catch (Resources.NotFoundException e) {

            if (BuildConfig.DEBUG) {


                e.printStackTrace();
            }
        }
    }


    protected void getUrl() {
        try {
            String string = task.execute(URL).get();
            Log.d(MYLOG_TEG, string);
            Log.d(MYLOG_TEG, "sendRequest" + string);
            StringBuilder stringBuilder = new StringBuilder();


            if (string != null && string != "") {
                String managerURL = PreferencesManagerImpl.getURL();
                if (managerURL != null) {
                    stringBuilder.append(string);
                    stringBuilder.append(managerURL);

                    stringBuilder.toString();
                    Log.d(MYLOG_TEG, "stringBuilder " + stringBuilder.toString());
                    PreferencesManagerImpl.setSateStartSte(true);
                    PreferencesManagerImpl.setGameStart(false);

                    PreferencesManagerImpl.setURL(String.valueOf(stringBuilder));
                    waitToshow.start();

                } else if (string != null && string != "") {
                    Log.d(MYLOG_TEG, "stringBuilder  no " + stringBuilder.toString());
                    Log.d(MYLOG_TEG, "sstring " + string);
                    PreferencesManagerImpl.setURL(string);

                    PreferencesManagerImpl.setSateStartSte(true);
                    PreferencesManagerImpl.setGameStart(false);

                    waitToshow.start();
                } else {
                    PreferencesManagerImpl.setSateStartSte(false);
                    PreferencesManagerImpl.setGameStart(true);
                    showGame();

                }


            } else if (string == "") {
                PreferencesManagerImpl.setSateStartSte(false);
                PreferencesManagerImpl.setGameStart(true);
                showGame();

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            java.net.URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                return result.toString();

            }
        }
    }

    public static String convertArrayToStringMethod(String[] strArray) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < strArray.length; i++) {

            stringBuilder.append(strArray[i]);

        }

        return stringBuilder.toString();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

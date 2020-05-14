package com.classicspin.wg.routers.main;

import android.os.Bundle;


import com.classicspin.wg.R;
import com.classicspin.wg.routers.base.BaseRouter;
import com.classicspin.wg.ui.activities.MainActivity;;
import com.classicspin.wg.ui.fragments.start.view.StartFragment;
import com.classicspin.wg.ui.fragments.web.view.WebFragment;

import javax.inject.Inject;


public class MainActivityRouterImpl extends BaseRouter<MainActivity> implements MainActivityRouter {


    @Inject
    MainActivityRouterImpl(MainActivity activity) {
        super(activity);
    }



    @Override
    public void showLogoFragment() {
        if (!isCurrentFragment(R.id.fragment_container, StartFragment.class)) {
            replaceFragment(R.id.fragment_container, new StartFragment());
        }
    }
    @Override
    public void showWebFragment() {
        if(!isCurrentFragment(R.id.fragment_container, WebFragment.class)) {
            replaceFragment(R.id.fragment_container, new WebFragment());
        }
    }

}

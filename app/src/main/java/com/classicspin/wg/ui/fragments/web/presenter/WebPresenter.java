package com.classicspin.wg.ui.fragments.web.presenter;



import com.classicspin.wg.ui.base.BasePresenter;
import com.classicspin.wg.ui.fragments.web.view.WebView;

import javax.inject.Inject;

public class WebPresenter extends BasePresenter<WebView> {

    @Inject
    WebPresenter(){
    }

    public void showSite() {
        getView().showSite();
    }
}

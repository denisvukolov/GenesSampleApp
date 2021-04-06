package ru.denisvukolov.genesapp.presentation.view.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BaseNetworkMvpView  extends MvpView {

    void showLoader();

    void hideLoader();

    void handleNoConnectionError();

    void handleRequestError();

    void handleRetry();
}

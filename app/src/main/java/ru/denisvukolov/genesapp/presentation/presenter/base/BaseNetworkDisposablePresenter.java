package ru.denisvukolov.genesapp.presentation.presenter.base;

import ru.denisvukolov.data.exception.NoConnectivityException;
import ru.denisvukolov.genesapp.presentation.view.base.BaseNetworkMvpView;

public class BaseNetworkDisposablePresenter<T extends BaseNetworkMvpView> extends BaseDisposablePresenter<T> {

    protected void handleRequestErrorByDefault(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof NoConnectivityException) {
            getViewState().handleNoConnectionError();
        } else {
            getViewState().handleRequestError();
        }
        getViewState().hideLoader();
    }
}

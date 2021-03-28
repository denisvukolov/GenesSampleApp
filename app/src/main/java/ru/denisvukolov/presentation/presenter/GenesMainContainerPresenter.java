package ru.denisvukolov.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import ru.denisvukolov.presentation.view.GenesMainContainerView;

@InjectViewState
public class GenesMainContainerPresenter extends MvpPresenter<GenesMainContainerView> {

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showGenesListScreen();
    }

    //end
}

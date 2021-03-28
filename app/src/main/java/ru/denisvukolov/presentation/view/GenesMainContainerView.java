package ru.denisvukolov.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface GenesMainContainerView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showGenesListScreen();
}

package ru.denisvukolov.presentation.view;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.denisvukolov.domain.entity.GeneItem;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface GenesListView extends BaseNetworkMvpView {

    void showGenesList(List<GeneItem> genes);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void openGeneDetailsScreen(int geneId);

}

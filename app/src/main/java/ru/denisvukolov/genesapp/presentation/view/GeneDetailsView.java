package ru.denisvukolov.genesapp.presentation.view;

import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.genesapp.presentation.view.base.BaseNetworkMvpView;


public interface GeneDetailsView extends BaseNetworkMvpView {
   void showDetails(GeneItem geneItem);
}

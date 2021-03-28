package ru.denisvukolov.presentation.view;

import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.presentation.view.base.BaseNetworkMvpView;


public interface GeneDetailsView extends BaseNetworkMvpView {
   void showDetails(GeneItem geneItem);
}

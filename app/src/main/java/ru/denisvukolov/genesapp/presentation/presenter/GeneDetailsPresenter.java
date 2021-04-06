package ru.denisvukolov.genesapp.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.denisvukolov.domain.usecase.GetGeneItemByIdUseCase;
import ru.denisvukolov.genesapp.presentation.presenter.base.BaseNetworkDisposablePresenter;
import ru.denisvukolov.genesapp.presentation.view.GeneDetailsView;

@InjectViewState
public class GeneDetailsPresenter extends BaseNetworkDisposablePresenter<GeneDetailsView> {

    private final int geneId;
    private final GetGeneItemByIdUseCase getGeneItemByIdUseCase;

    //region ===================== Constructor ======================

    public GeneDetailsPresenter(int geneId, GetGeneItemByIdUseCase getGeneItemByIdUseCase) {
        this.geneId = geneId;
        this.getGeneItemByIdUseCase = getGeneItemByIdUseCase;
    }

    //endregion

    //region ===================== Public ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getGenesItemByIdFromServer();
    }

    //endregion

    //region ===================== Public ======================

    public void onRefreshClicked() {
        getGenesItemByIdFromServer();
    }

    public void onRetryClicked() {
        getGenesItemByIdFromServer();
        getViewState().handleRetry();
    }

    //endregion

    //region ===================== Public ======================

    private void getGenesItemByIdFromServer() {
        getViewState().showLoader();
        disposeOnDestroy(
                getGeneItemByIdUseCase.buildUseCaseObservable(geneId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                geneItem -> {
                                    getViewState().showDetails(geneItem);
                                    getViewState().hideLoader();
                                },
                                this::handleRequestErrorByDefault
                        )
        );
    }

    //endregion
}

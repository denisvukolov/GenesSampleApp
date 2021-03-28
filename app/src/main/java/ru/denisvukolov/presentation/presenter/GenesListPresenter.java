package ru.denisvukolov.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.denisvukolov.domain.usecase.GetGenesListUseCase;
import ru.denisvukolov.presentation.presenter.base.BaseNetworkDisposablePresenter;
import ru.denisvukolov.presentation.view.GenesListView;

@InjectViewState
public class GenesListPresenter extends BaseNetworkDisposablePresenter<GenesListView> {

    private final GetGenesListUseCase getGenesListUseCase;

    public GenesListPresenter(GetGenesListUseCase getGenesListUseCase) {
        this.getGenesListUseCase = getGenesListUseCase;
    }

    //region ===================== Public ======================

    public void onGeneItemClicked(int id) {
        getViewState().openGeneDetailsScreen(id);
    }

    public void onRefreshClicked() {
        getGenesFromServer();
    }

    public void onRetryClicked() {
        getGenesFromServer();
        getViewState().handleRetry();
    }

    //endregion

    //region ===================== Lifecycle ======================

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getGenesFromServer();
    }

    //endregion

    //region ===================== Internal ======================

    private void getGenesFromServer() {
        getViewState().showLoader();
        disposeOnDestroy(getGenesListUseCase
                .buildUseCaseObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genes -> {
                            getViewState().showGenesList(genes);
                            getViewState().hideLoader();
                        },
                        this::handleRequestErrorByDefault)
        );
    }

    //endregion
}

package ru.denisvukolov.presentation.presenter;

import com.arellomobile.mvp.InjectViewState;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.denisvukolov.domain.usecase.GetGenesListUseCase;
import ru.denisvukolov.presentation.view.GenesListView;

@InjectViewState
public class GenesListPresenter extends BaseDisposablePresenter<GenesListView> {
    private final GetGenesListUseCase getGenesListUseCase;

    public GenesListPresenter(GetGenesListUseCase getGenesListUseCase) {
        this.getGenesListUseCase = getGenesListUseCase;
    }

    public void onGeneItemClicked(int id) {
        getViewState().openGeneDetailsScreen(id);
    }

    public void onRefreshClicked() {
        getGenesFromServer();
    }

    public void onRetryClicked() {

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getGenesFromServer();
    }

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
                        throwable -> {
                            throwable.printStackTrace();
                            getViewState().hideLoader();
                        })
        );
    }
}

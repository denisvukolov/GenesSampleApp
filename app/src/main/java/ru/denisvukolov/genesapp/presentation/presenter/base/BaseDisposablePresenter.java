package ru.denisvukolov.genesapp.presentation.presenter.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Holds {@link Disposable} object and perform disposal in onDestroy()
 **/
public abstract class BaseDisposablePresenter<T extends MvpView> extends MvpPresenter<T> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeIfNeeded(compositeDisposable);
    }

    protected void disposeOnDestroy(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public  boolean shouldDispose(Disposable disposable) {
        return disposable != null && !disposable.isDisposed();
    }

    public  void disposeIfNeeded(Disposable disposable) {
        if (shouldDispose(disposable)) {
            disposable.dispose();
        }
    }
}
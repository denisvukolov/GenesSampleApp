package ru.denisvukolov.domain.usecase;

import io.reactivex.Observable;

public abstract class ObservableUseCase<Params,ReturnType> {

    public abstract Observable<ReturnType> buildUseCaseObservable(Params params);

    public Observable<ReturnType> buildUseCaseObservable() {
        return buildUseCaseObservable(null);
    }
}

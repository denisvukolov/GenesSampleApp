package ru.denisvukolov.di.features.geneslist;

import dagger.Module;
import dagger.Provides;
import ru.denisvukolov.data.api.Api;
import ru.denisvukolov.data.repositoryimpl.GenesRepositoryImpl;
import ru.denisvukolov.di.scope.GenesScope;
import ru.denisvukolov.domain.repository.GenesRepository;
import ru.denisvukolov.domain.usecase.GetGenesListUseCase;
import ru.denisvukolov.presentation.presenter.GenesListPresenter;

@Module
public class GenesListModule {

    @Provides
    @GenesScope
    GenesListPresenter provideGenesListPresenterPresenter(GetGenesListUseCase getGenesListUseCase){
        return new GenesListPresenter(getGenesListUseCase);
    }

    @Provides
    GenesRepository provideGenesRepository(Api api){
        return new GenesRepositoryImpl(api);
    }

}

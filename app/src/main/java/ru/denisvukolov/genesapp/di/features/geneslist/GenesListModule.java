package ru.denisvukolov.genesapp.di.features.geneslist;

import dagger.Module;
import dagger.Provides;
import ru.denisvukolov.genesapp.OnListItemClickListener;
import ru.denisvukolov.data.api.Api;
import ru.denisvukolov.data.repositoryimpl.GenesRepositoryImpl;
import ru.denisvukolov.genesapp.di.scope.GenesScope;
import ru.denisvukolov.domain.repository.GenesRepository;
import ru.denisvukolov.domain.usecase.GetGenesListUseCase;
import ru.denisvukolov.genesapp.presentation.presenter.GenesListPresenter;
import ru.denisvukolov.genesapp.ui.adapter.GenesAdapter;

@Module
public class GenesListModule {

    private final  OnListItemClickListener onListItemClickListener;

    public GenesListModule(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @Provides
    @GenesScope
    GenesListPresenter provideGenesListPresenterPresenter(GetGenesListUseCase getGenesListUseCase) {
        return new GenesListPresenter(getGenesListUseCase);
    }

    @Provides
    GenesRepository provideGenesRepository(Api api) {
        return new GenesRepositoryImpl(api);
    }

    @Provides
    GenesAdapter provideGenesAdapter() {
        return new GenesAdapter(onListItemClickListener);
    }

}

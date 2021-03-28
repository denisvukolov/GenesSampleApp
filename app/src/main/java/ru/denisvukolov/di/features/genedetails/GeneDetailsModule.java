package ru.denisvukolov.di.features.genedetails;

import dagger.Module;
import dagger.Provides;
import ru.denisvukolov.data.api.Api;
import ru.denisvukolov.data.repositoryimpl.GenesRepositoryImpl;
import ru.denisvukolov.domain.repository.GenesRepository;
import ru.denisvukolov.domain.usecase.GetGeneItemByIdUseCase;
import ru.denisvukolov.presentation.presenter.GeneDetailsPresenter;

@Module
public class GeneDetailsModule {
    private final int geneId;

    public GeneDetailsModule(int geneId) {
        this.geneId = geneId;
    }

    @Provides
    GeneDetailsPresenter provideGeneDetailsPresenter(GetGeneItemByIdUseCase getGeneItemByIdUseCase) {
        return new GeneDetailsPresenter(geneId, getGeneItemByIdUseCase);
    }

    @Provides
    GenesRepository provideGenesRepository(Api api) {
        return new GenesRepositoryImpl(api);
    }
}

package ru.denisvukolov.genesapp.di.features.maincontainer;

import dagger.Module;
import dagger.Provides;
import ru.denisvukolov.genesapp.di.scope.GenesScope;
import ru.denisvukolov.genesapp.presentation.presenter.GenesMainContainerPresenter;

@Module
public class MainContainerModule {

    @Provides
    @GenesScope
    GenesMainContainerPresenter providePresenter() {
        return new GenesMainContainerPresenter();
    }
}

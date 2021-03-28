package ru.denisvukolov.di.features.maincontainer;

import dagger.Module;
import dagger.Provides;
import ru.denisvukolov.di.scope.GenesScope;
import ru.denisvukolov.presentation.presenter.GenesMainContainerPresenter;

@Module
public class MainContainerModule {

    @Provides
    @GenesScope
    GenesMainContainerPresenter providePresenter() {
        return new GenesMainContainerPresenter();
    }
}

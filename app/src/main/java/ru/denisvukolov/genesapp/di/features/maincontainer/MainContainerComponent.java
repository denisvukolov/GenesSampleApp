package ru.denisvukolov.genesapp.di.features.maincontainer;

import dagger.Subcomponent;
import ru.denisvukolov.genesapp.di.scope.GenesScope;
import ru.denisvukolov.genesapp.ui.activity.MainContainerActivity;

@GenesScope
@Subcomponent(modules = {MainContainerModule.class})
public interface MainContainerComponent {

    void inject(MainContainerActivity activity);

}

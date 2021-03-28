package ru.denisvukolov.di.features.maincontainer;

import dagger.Component;
import dagger.Subcomponent;
import ru.denisvukolov.di.scope.GenesScope;
import ru.denisvukolov.ui.activity.MainContainerActivity;

@GenesScope
@Subcomponent(modules = {MainContainerModule.class})
public interface MainContainerComponent {

    void inject(MainContainerActivity activity);

}

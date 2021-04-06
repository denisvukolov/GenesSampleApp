package ru.denisvukolov.genesapp.di.features.geneslist;

import dagger.Subcomponent;
import ru.denisvukolov.genesapp.di.scope.GenesScope;
import ru.denisvukolov.genesapp.ui.fragment.GenesListFragment;

@GenesScope
@Subcomponent(modules = {GenesListModule.class})
public interface GenesListComponent {

    void inject(GenesListFragment genesListFragment);
}

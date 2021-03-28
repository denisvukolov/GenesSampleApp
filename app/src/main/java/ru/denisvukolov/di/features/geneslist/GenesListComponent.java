package ru.denisvukolov.di.features.geneslist;

import dagger.Subcomponent;
import ru.denisvukolov.di.scope.GenesScope;
import ru.denisvukolov.ui.fragment.GenesListFragment;

@GenesScope
@Subcomponent(modules = {GenesListModule.class})
public interface GenesListComponent {

    void inject(GenesListFragment genesListFragment);
}

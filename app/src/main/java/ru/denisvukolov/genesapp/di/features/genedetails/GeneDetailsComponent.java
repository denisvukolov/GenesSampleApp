package ru.denisvukolov.genesapp.di.features.genedetails;

import dagger.Subcomponent;
import ru.denisvukolov.genesapp.di.scope.GenesScope;
import ru.denisvukolov.genesapp.ui.fragment.GeneDetailsFragment;

@GenesScope
@Subcomponent(modules = {GeneDetailsModule.class})
public interface GeneDetailsComponent {

    void inject(GeneDetailsFragment fragment);

}

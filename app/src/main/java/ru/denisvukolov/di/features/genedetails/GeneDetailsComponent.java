package ru.denisvukolov.di.features.genedetails;

import dagger.Subcomponent;
import ru.denisvukolov.di.scope.GenesScope;
import ru.denisvukolov.ui.fragment.GeneDetailsFragment;

@GenesScope
@Subcomponent(modules = {GeneDetailsModule.class})
public interface GeneDetailsComponent {

    void inject(GeneDetailsFragment fragment);

}

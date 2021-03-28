package ru.denisvukolov.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.denisvukolov.di.features.genedetails.GeneDetailsComponent;
import ru.denisvukolov.di.features.genedetails.GeneDetailsModule;
import ru.denisvukolov.di.features.geneslist.GenesListComponent;
import ru.denisvukolov.di.features.geneslist.GenesListModule;
import ru.denisvukolov.di.features.maincontainer.MainContainerComponent;
import ru.denisvukolov.di.features.maincontainer.MainContainerModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    MainContainerComponent plus(MainContainerModule module);

    GenesListComponent plus(GenesListModule module);

    GeneDetailsComponent plus(GeneDetailsModule module);
}

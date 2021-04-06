package ru.denisvukolov.genesapp.di.application;

import javax.inject.Singleton;

import dagger.Component;
import ru.denisvukolov.genesapp.di.features.genedetails.GeneDetailsComponent;
import ru.denisvukolov.genesapp.di.features.genedetails.GeneDetailsModule;
import ru.denisvukolov.genesapp.di.features.geneslist.GenesListComponent;
import ru.denisvukolov.genesapp.di.features.geneslist.GenesListModule;
import ru.denisvukolov.genesapp.di.features.maincontainer.MainContainerComponent;
import ru.denisvukolov.genesapp.di.features.maincontainer.MainContainerModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    MainContainerComponent plus(MainContainerModule module);

    GenesListComponent plus(GenesListModule module);

    GeneDetailsComponent plus(GeneDetailsModule module);
}

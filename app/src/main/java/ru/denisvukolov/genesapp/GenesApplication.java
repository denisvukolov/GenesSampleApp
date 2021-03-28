package ru.denisvukolov.genesapp;

import android.app.Application;

import ru.denisvukolov.di.application.ApplicationComponent;
import ru.denisvukolov.di.application.DaggerApplicationComponent;

public class GenesApplication extends Application {

    private static GenesApplication instance;
    private ApplicationComponent applicationComponent;


    public static GenesApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        applicationComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

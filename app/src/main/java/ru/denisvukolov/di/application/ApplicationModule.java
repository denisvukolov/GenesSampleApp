package ru.denisvukolov.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.denisvukolov.OkHttpClientFactory;
import ru.denisvukolov.data.api.Api;
import ru.denisvukolov.genesapp.GenesApplication;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return GenesApplication.getInstance();
    }

    @Provides
    @Singleton
    Api provideApi() {
        return new Retrofit.Builder()
                .baseUrl("https://open-genes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClientFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }
}

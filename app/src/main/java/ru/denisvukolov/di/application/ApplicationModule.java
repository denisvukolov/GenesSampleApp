package ru.denisvukolov.di.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.denisvukolov.ConnectivityInterceptor;
import ru.denisvukolov.NetworkChecker;
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
    ConnectivityInterceptor provideConnectivityInterceptor(NetworkChecker NetworkChecker) {
        return new ConnectivityInterceptor(NetworkChecker);
    }

    @Provides
    NetworkChecker provideConnectivityManager(Context context) {
        return new NetworkChecker(context);
    }

    @Provides
    @Singleton
    Api provideApi(ConnectivityInterceptor connectivityInterceptor) {
        return new Retrofit.Builder()
                .baseUrl("https://open-genes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClientFactory.create(connectivityInterceptor))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);
    }
}

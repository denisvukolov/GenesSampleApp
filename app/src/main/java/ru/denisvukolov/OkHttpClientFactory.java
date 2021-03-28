package ru.denisvukolov;

import androidx.annotation.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpClientFactory {

    public static OkHttpClient create(@Nullable ConnectivityInterceptor connectivityInterceptor) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        if (connectivityInterceptor != null) {
            builder.addInterceptor(connectivityInterceptor);
        }
        return builder.build();
    }
}

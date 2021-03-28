package ru.denisvukolov;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.denisvukolov.data.exception.NoConnectivityException;

public class ConnectivityInterceptor implements Interceptor {

    private final NetworkChecker networkChecker;

    public ConnectivityInterceptor(NetworkChecker networkChecker) {
        this.networkChecker = networkChecker;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!networkChecker.isNetworkAvailable()) {
            throw new NoConnectivityException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }



}

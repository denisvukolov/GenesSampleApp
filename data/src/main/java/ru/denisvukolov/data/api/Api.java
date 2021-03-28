package ru.denisvukolov.data.api;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.denisvukolov.domain.entity.GeneItem;

public interface Api {

    @GET("api/gene/")
    Observable<List<GeneItem>> getGenes();

    @GET("api/gene/{id}")
    Observable<GeneItem> getGeneById(@Path("id") String id);
}

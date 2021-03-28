package ru.denisvukolov.data.repositoryimpl;

import java.util.List;

import io.reactivex.Observable;
import ru.denisvukolov.data.api.Api;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.domain.repository.GenesRepository;

public class GenesRepositoryImpl implements GenesRepository {

    private final Api api;

    public GenesRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Observable<List<GeneItem>> getGenes() {
        return api.getGenes();
    }

    @Override
    public Observable<GeneItem> getGeneById(int id) {
        return api.getGeneById(id);
    }
}

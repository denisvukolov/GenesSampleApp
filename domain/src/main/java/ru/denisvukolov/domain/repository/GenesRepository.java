package ru.denisvukolov.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.denisvukolov.domain.entity.GeneItem;

public interface GenesRepository {

    Observable<List<GeneItem>> getGenes();

    Observable<GeneItem> getGeneById(String id);
}

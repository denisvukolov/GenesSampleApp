package ru.denisvukolov.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.domain.repository.GenesRepository;

public class GetGenesListUseCase extends ObservableUseCase<Void, List<GeneItem>> {

    private final GenesRepository genesRepository;

    @Inject
    public GetGenesListUseCase(GenesRepository genesRepository) {
        this.genesRepository = genesRepository;
    }

    @Override
    public Observable<List<GeneItem>> buildUseCaseObservable(Void aVoid) {
        return genesRepository.getGenes();
    }
}

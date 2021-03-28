package ru.denisvukolov.domain.usecase;



import javax.inject.Inject;

import io.reactivex.Observable;
import ru.denisvukolov.domain.entity.GeneItem;
import ru.denisvukolov.domain.repository.GenesRepository;

public class GetGeneItemByIdUseCase extends ObservableUseCase<Integer, GeneItem> {

    private  final GenesRepository genesRepository;

    @Inject
    public GetGeneItemByIdUseCase(GenesRepository genesRepository) {
        this.genesRepository = genesRepository;
    }

    @Override
    public Observable<GeneItem> buildUseCaseObservable(Integer geneId) {
        return genesRepository.getGeneById(geneId);
    }
}

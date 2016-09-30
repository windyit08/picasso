package com.theitfox.picasso.domain.usecases;

import com.theitfox.picasso.data.gson.ExpandableItem;
import com.theitfox.picasso.data.repositories.ExpandableItemRepository;
import com.theitfox.picasso.domain.common.UseCase;

import java.util.List;

import rx.Observable;

/**
 * Created by btquanto on 30/09/2016.
 */

public class FetchExpandableItemsUseCase extends UseCase<List<ExpandableItem>> {
    private ExpandableItemRepository repository;

    public FetchExpandableItemsUseCase() {
        super();
        this.repository = new ExpandableItemRepository();
    }

    @Override
    protected Observable<List<ExpandableItem>> buildUseCaseObservable() {
        return repository.getExpandableItems();
    }
}

package com.theitfox.picasso.presentation.ui.expandablelistview.presenters;

import com.theitfox.picasso.domain.usecases.FetchExpandableItemsUseCase;
import com.theitfox.picasso.presentation.ui.expandablelistview.presenters.abstracts.ExpandableListViewPresenter;

/**
 * Created by btquanto on 30/09/2016.
 */

public class ExpandableListViewPresenterImpl extends ExpandableListViewPresenter {
    @Override
    public void fetchExpandableItems() {
        FetchExpandableItemsUseCase useCase = new FetchExpandableItemsUseCase();
        subscription.add(useCase
                .onNext(items -> {
                    if (isViewAttached()) {
                        view.updateRecyclerView(items);
                    }
                }).execute());
    }
}

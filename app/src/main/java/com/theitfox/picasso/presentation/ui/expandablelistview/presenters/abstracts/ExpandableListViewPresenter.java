package com.theitfox.picasso.presentation.ui.expandablelistview.presenters.abstracts;

import com.theitfox.picasso.presentation.common.Presenter;
import com.theitfox.picasso.presentation.ui.expandablelistview.views.abstracts.ExpandableRecyclerView;

/**
 * Created by btquanto on 30/09/2016.
 */

public abstract class ExpandableListViewPresenter extends Presenter<ExpandableRecyclerView> {
    public abstract void fetchExpandableItems();
}

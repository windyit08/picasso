package com.theitfox.picasso.presentation.ui.expandablelistview.views.abstracts;

import com.theitfox.picasso.data.gson.ExpandableItem;
import com.theitfox.picasso.presentation.common.BaseView;

import java.util.List;

/**
 * Created by btquanto on 30/09/2016.
 */

public interface ExpandableRecyclerView extends BaseView {
    public abstract void updateRecyclerView(List<ExpandableItem> items);
}

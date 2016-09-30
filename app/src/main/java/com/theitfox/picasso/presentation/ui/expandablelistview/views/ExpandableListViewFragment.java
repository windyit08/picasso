package com.theitfox.picasso.presentation.ui.expandablelistview.views;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.theitfox.picasso.R;
import com.theitfox.picasso.data.gson.ExpandableItem;
import com.theitfox.picasso.presentation.ui.expandablelistview.adapters.ExpandableListViewAdapter;
import com.theitfox.picasso.presentation.ui.expandablelistview.presenters.ExpandableListViewPresenterImpl;
import com.theitfox.picasso.presentation.ui.expandablelistview.presenters.abstracts.ExpandableListViewPresenter;
import com.theitfox.picasso.presentation.ui.expandablelistview.views.abstracts.ExpandableRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by btquanto on 30/09/2016.
 */

public class ExpandableListViewFragment extends Fragment implements ExpandableRecyclerView {

    private ExpandableListViewPresenter presenter;
    private List<ExpandableItem> items;
    private ExpandableListViewAdapter adapter;

    public ExpandableListViewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.items = new ArrayList<>();
        this.adapter = new ExpandableListViewAdapter(getContext(), items);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (this.presenter == null) {
            this.presenter = new ExpandableListViewPresenterImpl();
        }
        this.presenter.attachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.fetchExpandableItems();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.presenter.detachView();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expandable_recycler_view, container, false);
        ExpandableListView rvExpandableItems = (ExpandableListView) view.findViewById(R.id.rv_expandable_items);
        rvExpandableItems.setAdapter(adapter);
        return view;
    }

    @Override
    public void updateRecyclerView(List<ExpandableItem> items) {
        this.items.clear();
        this.items.addAll(items);
        this.adapter.notifyDataSetChanged();
    }
}

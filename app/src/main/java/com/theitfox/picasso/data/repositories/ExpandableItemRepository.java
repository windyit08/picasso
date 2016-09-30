package com.theitfox.picasso.data.repositories;

import com.theitfox.picasso.data.gson.ChildItem;
import com.theitfox.picasso.data.gson.ExpandableItem;

import java.util.LinkedList;
import java.util.List;

import rx.Observable;

/**
 * Created by btquanto on 30/09/2016.
 */

public class ExpandableItemRepository {
    public ExpandableItemRepository() {
    }

    public List<ExpandableItem> getItems() {
        List<ExpandableItem> items = new LinkedList();
        for (int i = 1; i < 100; i += 2) {
            LinkedList<ChildItem> children = new LinkedList<>();
            for (int j = 1; j < 5; j++) {
                children.add(new ChildItem("Child Item " + j));
            }
            items.add(new ExpandableItem("Expandable Item " + i, children));
        }
        return items;

    }

    public Observable<List<ExpandableItem>> getExpandableItems() {
        return Observable.create(subscriber -> {
            List<ExpandableItem> items = new LinkedList();
            for (int i = 1; i < 100; i++) {
                LinkedList<ChildItem> children = new LinkedList<>();
                for (int j = 1; j < 5; j++) {
                    children.add(new ChildItem("Child Item " + j));
                }
                items.add(new ExpandableItem("Expandable Item " + i, children));
            }
            subscriber.onNext(items);
            subscriber.onCompleted();
        });
    }
}

package com.theitfox.picasso.data.gson;

import java.util.List;

/**
 * Created by btquanto on 30/09/2016.
 */

public class ExpandableItem {
    private String title;
    private List<ChildItem> children;

    public ExpandableItem(String title, List<ChildItem> children) {
        this.title = title;
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public List<ChildItem> getChildren() {
        return children;
    }

}

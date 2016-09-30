package com.theitfox.picasso.presentation.ui.expandablelistview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.theitfox.picasso.R;
import com.theitfox.picasso.presentation.ui.expandablelistview.views.ExpandableListViewFragment;

/**
 * Created by btquanto on 26/09/2016.
 */

public class ExpandableListViewActivity extends AppCompatActivity {
    private static final String TAG_EXPANDABLE_RECYCLER_VIEW_FRAGMENT = "TAG_EXPANDABLE_RECYCLER_VIEW_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(TAG_EXPANDABLE_RECYCLER_VIEW_FRAGMENT);
        if (fragment == null) {
            fragment = new ExpandableListViewFragment();
            fm.beginTransaction()
                    .add(R.id.fl_fragment_container, fragment, TAG_EXPANDABLE_RECYCLER_VIEW_FRAGMENT)
                    .commit();
        }
    }
}

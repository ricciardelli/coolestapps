/*
 * Copyright 2016 Richard Ricciardelli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.grability.coolestapps.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.coolestapps.R;
import com.grability.coolestapps.adapter.CardAdapter;
import com.grability.coolestapps.model.Category;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Instances of this class are fragments representing a list of entries from our collection.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class CategoryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.list)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The last two arguments ensure LayoutParams are inflated properly.
        View view = inflater.inflate(R.layout.categories_layout, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        // TODO Get this number from the preferences
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        Bundle args = getArguments();
        Category category = (Category) args.getSerializable(Constants.CATEGORY_KEY);
        Feed feed = (Feed) args.getSerializable(Constants.FEED_KEY);

        if (feed != null && category != null) {
            List<Entry> entries = new ArrayList<>();
            for(Entry entry : feed.getEntry()) {
                if (entry.getCategory().equals(category)) {
                    entries.add(entry);
                }
            }
            CardAdapter cardAdapter = new CardAdapter(getContext(), entries);
            mRecyclerView.setAdapter(cardAdapter);
        } else {
            Log.d(LOG_TAG, "Category :: " + category);
            Log.d(LOG_TAG, "Feed :: " + feed);
        }
        return view;
    }

    @Override
    public void onRefresh() {
        Log.d(LOG_TAG, "Refreshing");
    }
}

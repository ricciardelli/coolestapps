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

package com.grability.coolestapps.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.grability.coolestapps.R;
import com.grability.coolestapps.detail.DetailActivity;
import com.grability.coolestapps.model.Category;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Instances of this class are fragments representing a list of entries from our collection.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class EntryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.list)
    ListView mListView;

    private Feed mFeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The last two arguments ensure LayoutParams are inflated properly.
        View view = inflater.inflate(R.layout.categories_layout, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        Bundle args = getArguments();
        Category category = (Category) args.getSerializable(Constants.CATEGORY_KEY);
        mFeed = (Feed) args.getSerializable(Constants.FEED_KEY);

        if (mFeed != null && category != null) {
            List<Entry> entries = new ArrayList<>();
            for (Entry entry : mFeed.getEntry()) {
                if (entry.getCategory().equals(category)) {
                    entries.add(entry);
                }
            }
            EntryListItemAdapter adapter = new EntryListItemAdapter(getContext(), entries);
            mListView.setAdapter(adapter);
        } else {
            Log.d(LOG_TAG, "Category :: " + category);
            Log.d(LOG_TAG, "Feed :: " + mFeed);
        }
        return view;
    }

    @Override
    public void onRefresh() {
        Log.d(LOG_TAG, "Refreshing");
    }

    @OnItemClick(R.id.list)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(LOG_TAG, "On item click :: position :: " + position + " || id :: " + id);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        Entry selectedEntry = null;
        for (Entry entry : mFeed.getEntry()) {
            if (entry.getId().getAttributes().getId().equalsIgnoreCase(String.valueOf(id))) {
                selectedEntry = entry;
                break;
            }
        }
        intent.putExtra(Constants.ENTRY_KEY, selectedEntry);
        startActivity(intent);
    }
}

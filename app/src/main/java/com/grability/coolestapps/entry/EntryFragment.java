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

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Category;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.service.ServiceBundle;
import com.grability.coolestapps.service.ServiceBundleListener;
import com.grability.coolestapps.service.ServiceResponse;
import com.grability.coolestapps.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Instances of this class are fragments representing a list of entries from our collection.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class EntryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ServiceBundleListener {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.list)
    RecyclerView mRecyclerView;

    private Feed mFeed;

    private Category mCategory;

    private EntryListItemAdapter mEntryListItemAdapter;

    private List<Entry> mEntries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // The last two arguments ensure LayoutParams are inflated properly.
        View view = inflater.inflate(R.layout.entries_layout, container, false);
        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        Bundle args = getArguments();
        mCategory = (Category) args.getSerializable(Constants.CATEGORY_KEY);
        mFeed = (Feed) args.getSerializable(Constants.FEED_KEY);

        mRecyclerView.setLayoutManager(getLayoutManager());

        if (mFeed != null && mCategory != null) {
            mEntries = getEntries();
            mEntryListItemAdapter = new EntryListItemAdapter(getContext(), mEntries);
            mRecyclerView.setAdapter(mEntryListItemAdapter);
        } else {
            Log.d(LOG_TAG, "Category :: " + mCategory);
            Log.d(LOG_TAG, "Feed :: " + mFeed);
        }
        return view;
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        // TODO Get this number from preferences as well
        return isLargeScreen()
                ? new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
                : new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    /**
     * Returns whether the screen is large or not
     *
     * @return {¢code true} if large, {@code false} otherwise
     */
    private boolean isLargeScreen() {
        int screenLayout = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        return screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE || screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Returns the list of entries
     *
     * @return List of entries
     */
    private List<Entry> getEntries() {
        List<Entry> entries = new ArrayList<>();
        for (Entry entry : mFeed.getEntry()) {
            if (entry.getCategory().equals(mCategory)) {
                entries.add(entry);
            }
        }
        return entries;
    }

    /**
     * Refreshes the data from service
     */
    private void refresh() {
        ServiceBundle.getInstance().getFeed(getActivity(), this);
    }

    /**
     * Cancels the refreshing animation
     */
    private void cancelRefreshing() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    /**
     * Shows a toast with a message to the user about the retrying connection process.
     *
     * @param res The string resource to show as text on the toast
     */
    private void showToast(int res) {
        Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        Log.d(LOG_TAG, "Refreshing");
        refresh();
    }

    @Override
    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
        Log.d(LOG_TAG, "Refreshing response :: " + response);
        cancelRefreshing();
        if (response.isSuccessful()) {
            mFeed = response.body().getFeed();
            mEntries.clear();
            mEntries.addAll(getEntries());
            mEntryListItemAdapter.notifyDataSetChanged();
            showToast(R.string.retry_success_title);
        } else {
            showToast(R.string.error_retry);
        }
    }

    @Override
    public void onFailure(Call<ServiceResponse> call, Throwable t) {
        Log.e(LOG_TAG, "Error while refreshing :: ", t);
        cancelRefreshing();
        showToast(R.string.error_retry);
    }
}

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

package com.grability.coolestapps.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.coolestapps.R;
import com.grability.coolestapps.category.CategoryTabsAdapter;
import com.grability.coolestapps.model.Category;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeActivityFragment extends Fragment {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.pager)
    ViewPager mViewPager;

    private CategoryTabsAdapter mCategoryTabsAdapter;

    public HomeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        Bundle args = getArguments();
        Feed feed = (Feed) args.getSerializable(Constants.FEED_KEY);
        if (feed != null) {
            List<Category> categories = new ArrayList<>();
            for (Entry entry : feed.getEntry()) {
                if (!categories.contains(entry.getCategory())) {
                    categories.add(entry.getCategory());
                }
            }
            mCategoryTabsAdapter = new CategoryTabsAdapter(getChildFragmentManager(), categories, feed);
            mViewPager.setAdapter(mCategoryTabsAdapter);
        } else {
            Log.e(LOG_TAG, "Perfect moment to throw a ShouldNotHappenException");
        }
        return view;
    }


}

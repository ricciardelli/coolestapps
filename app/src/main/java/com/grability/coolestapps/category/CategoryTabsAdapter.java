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

package com.grability.coolestapps.category;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.grability.coolestapps.category.CategoryFragment;
import com.grability.coolestapps.model.Category;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.util.Constants;

import java.util.List;

/**
 * Since this is an object collection, use a FragmentStatePagerAdapter, and NOT a FragmentPagerAdapter.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class CategoryTabsAdapter extends FragmentStatePagerAdapter {

    private List<Category> categories;

    private Feed feed;

    public CategoryTabsAdapter(FragmentManager fragmentManager, List<Category> categories, Feed feed) {
        super(fragmentManager);
        this.categories = categories;
        this.feed = feed;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.CATEGORY_KEY, categories.get(position));
        args.putSerializable(Constants.FEED_KEY, feed);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getAttributes().getLabel();
    }
}

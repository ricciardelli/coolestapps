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

package com.grability.coolestapps.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Entry;
import com.grability.coolestapps.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Shows the details of an app.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class DetailActivity extends AppCompatActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Entry entry = (Entry) getIntent().getSerializableExtra(Constants.ENTRY_KEY);
        if (entry != null) {
            Fragment fragment = new DetailActivityFragment();
            Bundle args = new Bundle();
            args.putSerializable(Constants.ENTRY_KEY, entry);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        } else {
            Log.wtf(LOG_TAG, "This should not happen :: Entry is null");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                Log.wtf(LOG_TAG, "No menu item");
        }
        return super.onOptionsItemSelected(item);
    }
}

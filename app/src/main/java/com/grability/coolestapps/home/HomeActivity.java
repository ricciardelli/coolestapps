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
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.util.Constants;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.empty)
    View mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        Feed feed = (Feed) getIntent().getSerializableExtra(Constants.FEED_KEY);

        if (feed == null) {
            Snackbar.make(mToolbar, R.string.error_connection, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.action_retry, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(LOG_TAG, "Retry connection");
                        }
                    }).show();
            // TODO Retrieve JSON from preferences
            showEmptyView();
        } else {
            Fragment fragment = new HomeActivityFragment();
            Bundle args = new Bundle();
            args.putSerializable(Constants.FEED_KEY, feed);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        }
    }

    /**
     * Shows empty view when no results were found.
     * <p/>
     * This will probably happen just the first time the app is launched.
     */
    private void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

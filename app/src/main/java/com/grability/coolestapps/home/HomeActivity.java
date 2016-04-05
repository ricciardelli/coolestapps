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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.grability.coolestapps.R;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.service.ServiceBundle;
import com.grability.coolestapps.service.ServiceBundleListener;
import com.grability.coolestapps.service.ServiceResponse;
import com.grability.coolestapps.util.Constants;
import com.grability.coolestapps.util.FeedBackup;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements ServiceBundleListener {

    private final String LOG_TAG = getClass().getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.empty)
    View mEmptyView;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        Feed feed = (Feed) getIntent().getSerializableExtra(Constants.FEED_KEY);

        if (feed == null) {
            showErrorSnackbar();
            feed = FeedBackup.retrieveFeed(this);
            if (feed == null) {
                showEmptyView();
            } else {
                Log.d(LOG_TAG, "Retrieved feed from preferences :: " + feed);
                showFragment(feed);
            }
        } else {
            showFragment(feed);
        }
    }

    /**
     * Retries connection to update data.
     */
    private void retry() {
        mProgressDialog = ProgressDialog.show(this, "", getString(R.string.loading_title), true);
        ServiceBundle.getInstance().getFeed(this);
    }

    /**
     * Shows a snackbar with a message to retry to update the data
     */
    private void showErrorSnackbar() {
        Snackbar.make(mToolbar, R.string.error_connection, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.action_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(LOG_TAG, "Retry connection");
                        retry();
                    }
                }).show();
    }

    /**
     * Shows a toast with a message to the user about the retrying connection process.
     *
     * @param res The string resource to show as text on the toast
     */
    private void showToast(int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows fragment to render the categories
     *
     * @param feed Response to render on activity
     */
    private void showFragment(Feed feed) {
        Fragment fragment = new HomeActivityFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.FEED_KEY, feed);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
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

    @Override
    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
        Log.d(LOG_TAG, "Response when retrying :: " + response);
        mProgressDialog.dismiss();
        if (response.isSuccessful()) {
            showToast(R.string.retry_success_title);
            showFragment(response.body().getFeed());
        } else {
            showToast(R.string.error_retry);
            showErrorSnackbar();
        }
    }

    @Override
    public void onFailure(Call<ServiceResponse> call, Throwable t) {
        Log.e(LOG_TAG, "Error while retrying connection", t);
        mProgressDialog.dismiss();
        showToast(R.string.error_retry);
        showErrorSnackbar();
    }
}

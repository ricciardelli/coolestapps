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

package com.grability.coolestapps.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.grability.coolestapps.home.HomeActivity;
import com.grability.coolestapps.model.Feed;
import com.grability.coolestapps.service.ServiceBundle;
import com.grability.coolestapps.service.ServiceBundleListener;
import com.grability.coolestapps.service.ServiceResponse;
import com.grability.coolestapps.util.Constants;
import com.grability.coolestapps.util.FeedBackup;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Shows the splash screen following best practices.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class SplashActivity extends AppCompatActivity implements ServiceBundleListener {

    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceBundle.getInstance().getFeed(this);
    }

    @Override
    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
        Log.d(LOG_TAG, "ServiceResponse object :: " + response);
        if (response.isSuccessful()) {
            Log.d(LOG_TAG, "ServiceResponse body :: " + response.body());
            showMainActivity(response.body().getFeed());
            FeedBackup.saveFeed(this, response.body().getFeed());
        } else {
            showMainActivity(null);
        }
    }

    @Override
    public void onFailure(Call<ServiceResponse> call, Throwable t) {
        Log.e(LOG_TAG, "Error connecting to the service", t);
        showMainActivity(null);
    }

    /**
     * Shows the main activity once a response is retrieved from the server
     *
     * @param feed Response to render on main activity
     */
    private void showMainActivity(Feed feed) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.FEED_KEY, feed);
        startActivity(intent);
        finish();
    }
}

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

package com.grability.coolestapps.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.grability.coolestapps.R;
import com.grability.coolestapps.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class ServiceBundle implements Callback<ServiceResponse> {

    private final String LOG_TAG = getClass().getSimpleName();

    private static ServiceBundle ourInstance = new ServiceBundle();

    private Context context;

    private ServiceBundleListener serviceBundleListener;

    public static ServiceBundle getInstance() {
        return ourInstance;
    }

    private ServiceBundle() {
    }

    /**
     * Starts connection to the server and calls the getFeed method
     *
     * @param serviceBundleListener Listener on server response
     */
    public void getFeed(Context context, ServiceBundleListener serviceBundleListener) {
        this.context = context;
        this.serviceBundleListener = serviceBundleListener;

        Service service = getService();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String limit = preferences.getString(context.getString(R.string.preference_limit_key),
                context.getString(R.string.default_limit));

        Log.d(LOG_TAG, "Limit retrieved from preferences :: " + limit);

        service.getFeed(Integer.parseInt(limit)).enqueue(this);
    }

    /**
     * Returns the service connection ready to call methods
     *
     * @return Service utility
     */
    private Service getService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service.class);
    }

    @Override
    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
        if (serviceBundleListener != null)
            serviceBundleListener.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<ServiceResponse> call, Throwable t) {
        if (serviceBundleListener != null)
            serviceBundleListener.onFailure(call, t);
    }
}

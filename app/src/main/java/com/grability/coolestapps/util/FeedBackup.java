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

package com.grability.coolestapps.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.grability.coolestapps.model.Feed;

/**
 * Utilities to retrieve and save feed into preferences.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class FeedBackup {

    /**
     * Saves feed into preferences
     *
     * @param context Context to get shared preferences
     * @param feed    Feed to save
     */
    public static void saveFeed(Context context, Feed feed) {
        Gson gson = new Gson();
        String jsonFeed = gson.toJson(feed);
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFERENCES_NAME,
                Context.MODE_PRIVATE).edit();
        editor.putString(Constants.FEED_KEY, jsonFeed);
        editor.commit();
        Log.d("FeedBackup", "Feed successfully saved");
    }

    /**
     * Retrieves feed from preferences
     *
     * @param context Context to get shared preferences
     * @return Feed to retrieve
     */
    public static Feed retrieveFeed(Context context) {
        Log.d("FeedBackup", "Retrieving feed");
        String jsonFeed = context.getSharedPreferences(Constants.PREFERENCES_NAME,
                Context.MODE_PRIVATE).getString(Constants.FEED_KEY, "");
        return new Gson().fromJson(jsonFeed, Feed.class);
    }
}

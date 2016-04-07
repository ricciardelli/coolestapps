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
import android.preference.PreferenceManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.grability.coolestapps.R;

/**
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class AnimationUtils {

    /**
     * Default cool animation for required views
     *
     * @param context Context to handle preferences
     * @return Cool animation
     */
    public static Animation getAnimation(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        boolean animated = preferences.getBoolean(context.getString(R.string.preference_animation_key),
                false);

        if (animated) {
            ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1);
            animation.setDuration(1000);
            animation.setFillAfter(true);
            return animation;
        }

        return null;
    }
}
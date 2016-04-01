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

package com.grability.coolestapps.model;

import com.google.gson.annotations.SerializedName;
import com.grability.coolestapps.util.Constants;

/**
 * Defines the {@link Id} model attributes.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class IdAttributes {

    @SerializedName(Constants.IM_ID)
    private String id;

    @SerializedName(Constants.IM_BUNDLE_ID)
    private String bundleId;

    public IdAttributes(String id, String bundleId) {
        this.id = id;
        this.bundleId = bundleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    @Override
    public String toString() {
        return "IdAttributes{" +
                "id='" + id + '\'' +
                ", bundleId='" + bundleId + '\'' +
                '}';
    }
}

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
 * Defines the {@link Category} model attributes.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class CategoryAttributes {

    @SerializedName(Constants.IM_ID)
    private String id;
    private String term;
    private String scheme;
    private String label;

    public CategoryAttributes(String id, String term, String scheme, String label) {
        this.id = id;
        this.term = term;
        this.scheme = scheme;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "CategoryAttributes{" +
                "id='" + id + '\'' +
                ", term='" + term + '\'' +
                ", scheme='" + scheme + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}

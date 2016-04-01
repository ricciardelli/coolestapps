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

/**
 * Author model
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class Author {

    private Name name;
    private Uri uri;

    public Author(Name name, Uri uri) {
        this.name = name;
        this.uri = uri;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name=" + name +
                ", uri=" + uri +
                '}';
    }
}

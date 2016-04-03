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

/**
 * String constants used through the whole application.
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public class Constants {

    /**
     * JSON Service Base URL
     */
    public static final String SERVICE_BASE_URL = "https://itunes.apple.com/us/rss/topfreeapplications/";

    /**
     * Param identifier
     */
    public static final String LIMIT_PARAM = "limit";

    /**
     * JSON Service param to retrieve the top free applications.
     */
    public static final String LIMIT_PARAM_URL = "limit={" + LIMIT_PARAM + "}/json";

    /**
     * Feed key to use among intent calls.
     */
    public static final String FEED_KEY = "feed_key";

    /**
     * Category key to use among fragments and intent calls.
     */
    public static final String CATEGORY_KEY = "category_key";

    public static final String IM_NAME = "im:name";
    public static final String IM_IMAGE = "im:image";
    public static final String IM_PRICE = "im:price";
    public static final String IM_CONTENT_TYPE = "im:contentType";
    public static final String IM_ID = "im:id";
    public static final String IM_BUNDLE_ID = "im:bundleId";
    public static final String IM_ARTIST = "im:artist";
    public static final String IM_RELEASE_DATE = "im:releaseDate";
}

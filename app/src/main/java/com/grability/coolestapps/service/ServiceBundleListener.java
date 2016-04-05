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

import retrofit2.Call;
import retrofit2.Response;

/**
 * Custom listener to handle the service response
 *
 * @author Richard Ricciardelli (ricciardelli2021@gmail.com)
 * @version 1.0
 */
public interface ServiceBundleListener {

    /**
     * Service returned a response
     *
     * @param call     Details of the call
     * @param response Response
     */
    void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response);

    /**
     * Service thrown an exception and we couldn't connect to the server
     *
     * @param call Details of the call
     * @param t    Details of the exception thrown
     */
    void onFailure(Call<ServiceResponse> call, Throwable t);

}

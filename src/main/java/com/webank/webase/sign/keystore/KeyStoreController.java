/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.webank.webase.sign.keystore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webank.webase.sign.base.BaseController;
import com.webank.webase.sign.base.exception.BaseException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "/key", tags = "keystore interface")
@RestController
@RequestMapping(value = "/key")
public class KeyStoreController extends BaseController {
    @Autowired
    private KeyStoreService keyStoreService;

    /**
     * getKey.
     * 
     * @return
     * @throws BaseException
     */
    @ApiOperation(value = "get key", notes = "get key")
    @GetMapping
    public KeyStoreInfo getKey() throws BaseException {
        return keyStoreService.getKey();
    }
}
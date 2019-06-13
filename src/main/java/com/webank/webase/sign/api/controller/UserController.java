/**
 * Copyright 2014-2019  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.sign.api.controller;

import com.webank.webase.sign.api.service.UserService;
import com.webank.webase.sign.exception.BaseException;
import com.webank.webase.sign.pojo.po.UserInfoPo;
import com.webank.webase.sign.pojo.vo.BaseRspVo;
import com.webank.webase.sign.pojo.vo.ReqNewUserVo;
import com.webank.webase.sign.pojo.vo.RspUserInfoVo;
import com.webank.webase.sign.util.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller.
 */
@Api(value = "user", tags = "user interface")
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * new user.
     *
     * @param req parameter
     * @param result checkResult
     */
    @ApiOperation(value = "new user", notes = "new user")
    @ApiImplicitParam(name = "req", value = "user info", required = true, dataType = "ReqNewUserVo")
    @PostMapping("/newUser")
    public BaseRspVo newUser(@Valid @RequestBody ReqNewUserVo req, BindingResult result)
        throws BaseException {
        CommonUtils.checkParamBindResult(result);
        //new user
        RspUserInfoVo userInfo = userService.newUser(req);
        return CommonUtils.buildSuccessRspVo(userInfo);
    }

    /**
     * get user.
     */
    @ApiOperation(value = "get user info", notes = "get user by groupId and address")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "groupId", value = "user groupId", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "address", value = "user address", required = true, dataType = "String"),
    })
    @GetMapping("/{groupId}/{address}/userInfo")
    public BaseRspVo getUserInfo(@PathVariable("groupId") Integer groupId,
        @PathVariable("address") String address) throws BaseException {
        //new user
        UserInfoPo userInfo = userService.findByAddressAndGroupId(groupId,address);
        RspUserInfoVo rspUserInfoVo = new RspUserInfoVo();
        Optional.ofNullable(userInfo).ifPresent(u -> BeanUtils.copyProperties(u, rspUserInfoVo));
        return CommonUtils.buildSuccessRspVo(rspUserInfoVo);
    }


   /* @ApiOperation(value = "import PrivateKey", notes = "import PrivateKey")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "privateKey", value = "private key", required = true, dataType = "String"),
        @ApiImplicitParam(name = "userName", value = "user name", required = true, dataType = "String"),
    })
    @RequestMapping(method = RequestMethod.GET, value = "/import")
    public BaseRspVo importPrivateKey(String privateKey, String userName) throws BaseException {
        RspUserInfoVo storeInfo = userService.importUser(privateKey, userName);
        return CommonUtils.buildSuccessRspVo(storeInfo);
    }*/
}
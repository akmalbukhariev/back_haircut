package com.barbie.haircut.api.user.controller;

import com.barbie.haircut.api.BaseController;
import com.barbie.haircut.api.VersionResponseResult;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegistrationParam;
import com.barbie.haircut.api.user.service.IUserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@Tag(name = "User Common", description = "User API")
@RequestMapping(value={"/haircut/api/v1/user"})
public class UserCommonController extends BaseController {

    private final IUserService userService;

    @Operation(tags = {"User"}, summary = "1. login", description = "login", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/login", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> login(@RequestBody UserParam userParam){
        VersionResponseResult result = null;

        try
        {
            int resultNum = userService.login(userParam);

            if(resultNum == Result.USER_NOT_EXIST.getCode()){
                result = setResult(Result.USER_NOT_EXIST);
            }
            else if(resultNum == Result.USER_PASSWORD_NOT_MATCHED.getCode()){
                result = setResult(Result.USER_PASSWORD_NOT_MATCHED);
            }
            else if(resultNum != 0) {
                result = setResult(Result.SUCCESS);
            }else {
                result = setResult(Result.SERVER_ERROR);
            }
        }
        catch(Exception e)
        {
            result = setResult(Result.SERVER_ERROR);
        }

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"User"}, summary = "2. register", description = "register", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/register", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> register(@RequestBody UserRegistrationParam param){
        VersionResponseResult result = null;

        try {
            int resultNum = userService.register(param);
            if(resultNum == Result.USER_EXIST.getCode()){
                result = setResult(Result.USER_EXIST);
            }
            else if(resultNum != 0) {
                result = setResult(Result.SUCCESS);
            }else {
                result = setResult(Result.SERVER_ERROR);
            }
        } catch (Exception e) {
            //log.error(ExceptionUtils.getStackTrace(e));
            result = setResult(Result.SERVER_ERROR);
        }

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}

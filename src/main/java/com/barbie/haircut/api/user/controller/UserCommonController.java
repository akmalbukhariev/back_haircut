package com.barbie.haircut.api.user.controller;

import com.barbie.haircut.api.BaseController;
import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.VersionResponseResult;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.dto.FavoriteHairdresserDto;
import com.barbie.haircut.api.dto.UserBookedInfoDto;
import com.barbie.haircut.api.dto.UserInfoDto;
import com.barbie.haircut.api.param.FavoriteHairdresserParam;
import com.barbie.haircut.api.param.UserInfoParam;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegistrationParam;
import com.barbie.haircut.api.user.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(tags = {"User"}, summary = "3. get user", description = "get user info", hidden = false,
            responses = {@ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getUser/{phone}", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getUser(@PathVariable String phone){
        VersionResponseResult result = null;

        try {
            CamelCaseMap map = userService.getUser(phone);
            if(map == null){
                result = setResult(Result.USER_NOT_EXIST);
            }
            else{
                UserInfoDto info = new UserInfoDto();
                info.setPhone(map.get("phone").toString());
                info.setName(map.get("name").toString());
                info.setSurname(map.get("surname").toString());
                info.setLocation(map.get("location").toString());
                info.setLanguage(map.get("language").toString());
                info.setNotification(map.get("notification").toString());
                info.setIs_customer(map.get("isCustomer").toString());
                info.setIs_hairdresser(map.get("isHairdresser").toString());

                result = setResult(Result.USER_EXIST, info);
            }
        } catch (Exception e) {
            //log.error(ExceptionUtils.getStackTrace(e));
            result = setResult(Result.SERVER_ERROR);
        }

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"User"}, summary = "4. update user", description = "get user is_customer", hidden = false,
            responses = {@ApiResponse(responseCode = "200", description = "success")
            })
    @PutMapping(value= "/updateUserCustomer", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> updateUserCustomer(@RequestBody UserInfoParam param){
        VersionResponseResult result = null;

        try {
            int resultNum = userService.updateUserCustomer(param);
            if(resultNum == 0){
                result = setResult(Result.FAILED);
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

    @Operation(tags = {"User"}, summary = "5. update user", description = "get user is_hairdresser", hidden = false,
            responses = {@ApiResponse(responseCode = "200", description = "success")
            })
    @PutMapping(value= "/updateUserHairdreser", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> updateUserHairdresser(@RequestBody UserInfoParam param){
        VersionResponseResult result = null;

        try {
            int resultNum = userService.updateUserHairdresser(param);
            if(resultNum == 0){
                result = setResult(Result.FAILED);
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

    @Operation(tags = {"User"}, summary = "6. get user booked history", description = "user info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getUserBookedList/{phone}", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getUserBookedList(@PathVariable String phone) throws Exception
    {
        VersionResponseResult result = null;
        try {
            List<UserBookedInfoDto> resultData = userService.getUserBookedList(phone);

            if(resultData.size() != 0 && !resultData.isEmpty()) {
                result = setResult(Result.SUCCESS , resultData );
            }else {
                result = setResult(Result.SERVER_ERROR);
            }

        } catch(Exception e){
            log.error(e.getMessage());
            result = setResult(Result.SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"User"}, summary = "7. add favorite hairdresser", description = "add favorite hairdresser", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/addFavoriteHairdresser", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> addFavoriteHairdresser(@RequestBody FavoriteHairdresserParam param){
        VersionResponseResult result = null;

        try {
            int resultNum = userService.addFavoriteHairdresser(param);
            if(resultNum != 0) {
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

    @Operation(tags = {"User"}, summary = "8. get all favorite hairdresser", description = "get all favorite hairdresser", hidden = false,
            responses = {@ApiResponse(responseCode = "200", description = "success")
            })
    @GetMapping(value= "/getAllFavoriteHairdresser/{phone}", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getAllFavoriteHairdresser(@PathVariable String phone){
        VersionResponseResult result = null;
        try {
            List<FavoriteHairdresserDto> resultData = userService.getAllFavoriteHairdresser(phone);

            if(resultData.size() != 0 && !resultData.isEmpty()) {
                result = setResult(Result.SUCCESS , resultData );
            }else {
                result = setResult(Result.SERVER_ERROR);
            }

        } catch(Exception e){
            log.error(e.getMessage());
            result = setResult(Result.SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"User"}, summary = "9. update user info", description = "update usr info", hidden = false,
            responses = {@ApiResponse(responseCode = "200", description = "success")
            })
    @PutMapping(value= "/updateUserInfo", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> updateUserInfo(@RequestBody UserInfoParam param){
        VersionResponseResult result = null;

        try {
            int resultNum = userService.updateUserInfo(param);
            if(resultNum == 0){
                result = setResult(Result.FAILED);
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

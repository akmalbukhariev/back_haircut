package com.barbie.haircut.api.hairdresser.controller;

import com.barbie.haircut.api.BaseController;
import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.VersionResponseResult;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.convert.HairdresserDtoConverter;
import com.barbie.haircut.api.dto.*;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.param.HairdresserBookedClientHimSelftDto;
import com.barbie.haircut.api.param.HairdresserClientBookParam;
import com.barbie.haircut.api.param.HairdresserRegParam;
import com.barbie.haircut.api.param.UserBookedParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/haircut/api/v1/hairdresser"})
public class HairdresserController extends BaseController {

    private  final IServiceHairdresser serviceHairdresser;
    private final HairdresserDtoConverter hairdresserDtoConverter;

    @Operation(tags = {"Hairdresser"}, summary = "1. get the all hairdresser info for user main page", description = "get all hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getAllHairdresserForUserMainPage", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getAllHairdresserForUserMainPage() throws Exception
    {
        VersionResponseResult result = null;
        try {
            List<HairdresserInfoDto> resultData = serviceHairdresser.getAllHairdresserForUserMainPage();

            if(resultData.size() != 0 && !resultData.isEmpty()) {
                result = setResult(Result.SUCCESS , resultData );
            }else {
                result = setResult(Result.SERVER_ERROR);
            }

        } catch(Exception e){

        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "2. get a detail hairdresser info", description = "hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getHairdresserDetailInfo/{phone}", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getHairdresserDetailInfo(@PathVariable String phone) throws Exception
    {
        VersionResponseResult result = null;
        try {
            HairdresserInfoDto resultData = serviceHairdresser.getHairdresserDetailInfo(phone);

            if(resultData != null) {
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

    @Operation(tags = {"Hairdresser"}, summary = "3. insert booked clients", description = "insert booked clients", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/insertBookedClient", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> addBookedClient(@RequestBody UserBookedParam param) throws Exception
    {
        VersionResponseResult result = null;
        try {
            int resultData = serviceHairdresser.insertBookedClient(param);

            if(resultData > 0) {
                result = setResult(Result.SUCCESS, resultData);
            }else {
                result = setResult(Result.SERVER_ERROR);
            }
        } catch (Exception e) {

            //log.error(ExceptionUtils.getStackTrace(e));
            result = setResult(Result.SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "4. get hairdresser info", description = "get hairdresser info", hidden = false,
            responses = {@ApiResponse(responseCode = "200", description = "success")
            })
    @GetMapping(value= "/getHairdresser/{phone}", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getHairdresser(@PathVariable String phone){
        VersionResponseResult result = null;

        try {
            CamelCaseMap map = serviceHairdresser.getHairdresser(phone);
            if(map == null){
                result = setResult(Result.USER_NOT_EXIST);
            }
            else{
                HairdresserInfoDto info = new HairdresserInfoDto();
                info.setPhone(map.get("phone").toString());
                info.setName(map.get("name").toString());
                info.setSurname(map.get("surname").toString());
                info.setLanguage(map.get("language").toString());
                info.setNotification(map.get("notification").toString());

                result = setResult(Result.USER_EXIST, info);
            }
        } catch (Exception e) {
            //log.error(ExceptionUtils.getStackTrace(e));
            result = setResult(Result.SERVER_ERROR);
        }

        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "5. insert hairdresser", description = "insert hairdresser", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/register", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> register(@RequestBody HairdresserRegParam param) throws Exception
    {
        VersionResponseResult result = null;
        try {
            int resultData = serviceHairdresser.register(param);

            if(resultData > 0) {
                result = setResult(Result.SUCCESS, resultData);
            }else {
                result = setResult(Result.SERVER_ERROR);
            }
        } catch (Exception e) {

            //log.error(ExceptionUtils.getStackTrace(e));
            result = setResult(Result.SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "6. get the all booked clients for hairdresser", description = "get all hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/getBookedClients", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getBookedClients(@RequestBody HairdresserClientBookParam param) throws Exception
    {
        VersionResponseResult result = null;
        try {
            List<HairdresserBookedClientDto> resultData = serviceHairdresser.getBookedClients(param);

            if(resultData.size() != 0 && !resultData.isEmpty()) {
                result = setResult(Result.SUCCESS , resultData );
            }else {
                result = setResult(Result.SERVER_ERROR);
            }

        } catch(Exception e){

        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "7. insert hairdresser booked client", description = "insert hairdresser booked client", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PostMapping(value= "/addHairdresserBookedClient", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> addHairdresserBookedClient(@RequestBody HairdresserBookedClientHimSelftDto param) throws Exception
    {
        VersionResponseResult result = null;
        try {
            int resultData = serviceHairdresser.insertHairdresserBookedClient(param);

            if(resultData > 0) {
                result = setResult(Result.SUCCESS, resultData);
            }else {
                result = setResult(Result.SERVER_ERROR);
            }
        } catch (Exception e) {

            //log.error(ExceptionUtils.getStackTrace(e));
            result = setResult(Result.SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "8. get hairdresser services", description = "get hairdresser services", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getHairdresserServices/{phone}", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getHairdresserServices(@PathVariable String phone) throws Exception
    {
        VersionResponseResult result = null;
        try {
            List<HairdresserServiceDto> resultData = serviceHairdresser.getHairdresserServiceList(phone);

            if(resultData.size() != 0 && !resultData.isEmpty()) {
                result = setResult(Result.SUCCESS , resultData );
            }else {
                result = setResult(Result.SERVER_ERROR);
            }

        } catch(Exception e){
            result = setResult(Result.SERVER_ERROR);
        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }
}

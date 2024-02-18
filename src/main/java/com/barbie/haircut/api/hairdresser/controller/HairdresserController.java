package com.barbie.haircut.api.hairdresser.controller;

import com.barbie.haircut.api.BaseController;
import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.VersionResponseResult;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.convert.HairdresserDtoConverter;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.dto.HairdresserInfoDto;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.hairdresser.service.IServiceImage;
import com.barbie.haircut.api.param.HairdresserParam;
import com.barbie.haircut.api.param.HairdresserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/haircut/api/v1/hairdresser"})
public class HairdresserController extends BaseController {

    private  final IServiceHairdresser iServiceHairdresser;
    private final HairdresserDtoConverter hairdresserDtoConverter;

    @Operation(tags = {"Hairdresser"}, summary = "1. create a hairdresser", description = "create a hairdresser", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createHairdresser(HairdresserParam hairdresserParam) throws Exception {

        hairdresserParam = iServiceHairdresser.createNewHairdresser(hairdresserParam);

        if(hairdresserParam != null) return new ResponseEntity<Object>("Successfully created", HttpStatus.CREATED);
        else return  new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @Operation(tags = {"Hairdresser"}, summary = "2. get a hairdresser info by id", description = "get a hairdresser info by id", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping({"Id"})
    public  ResponseEntity<Object> getHairdresserById(@RequestParam int Id) throws Exception {

        HairdresserResponse hairdresserResponse = iServiceHairdresser.getHairdresserbyNo(Id);

        return new ResponseEntity(hairdresserResponse, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "3. get all hairdresser info", description = "get all hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping()
    public ResponseEntity<List<Object>> getHairdresserAll() throws Exception
    {
        List<HairdresserResponse> hairdresserResponsesList = iServiceHairdresser.getHairdresserAll();

        if(hairdresserResponsesList == null) return new ResponseEntity<List<Object>>(Collections.singletonList("There aren't data"), HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<Object>>(Arrays.asList(hairdresserResponsesList.toArray()), HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "6. get the all hairdresser info for user main page", description = "get all hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getAllHairdresserForUserMainPage", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getAllHairdresserForUserMainPage() throws Exception
    {
        VersionResponseResult result = null;
        try {
            List<HairdresserInfoDto> resultData = iServiceHairdresser.getAllHairdresserForUserMainPage();

            if(resultData.size() != 0 && !resultData.isEmpty()) {
                result = setResult(Result.SUCCESS , resultData );
            }else {
                result = setResult(Result.SERVER_ERROR);
            }

        } catch(Exception e){

        }
        return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "7. get a detail hairdresser info", description = "hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping(value= "/getHairdresserDetailInfo", headers = { "Content-type=application/json" })
    public ResponseEntity<Object> getHairdresserDetailInfo(String phone) throws Exception
    {
        VersionResponseResult result = null;
        try {
            List<HairdresserInfoDto> resultData = iServiceHairdresser.getHairdresserDetailInfo(phone);

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

    @Operation(tags = {"Hairdresser"}, summary = "4. update hairdresser by id", description = "update hairdresser", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  ResponseEntity<Object> updateHairdresser( @RequestPart("hairdresserParam") HairdresserParam hairdresserParam,
                                                      @RequestParam int Id) throws Exception
    {
        HairdresserParam result =  iServiceHairdresser.updateHairdresserbyNo(hairdresserParam, Id);
        if(result == null)
            return new ResponseEntity<Object>(hairdresserParam, HttpStatus.BAD_REQUEST);

       return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

    @Operation(tags = {"Hairdresser"}, summary = "5. delete hairdresser by id", description = "delete hairdresser by id", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @DeleteMapping({"Id"})
    public ResponseEntity<Object> deleteHairdresserById(@RequestParam int Id) throws Exception {

        HairdresserResponse hairdresserResponse = iServiceHairdresser.getHairdresserbyNo(Id);
        if(hairdresserResponse == null)
            return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);

        if(iServiceHairdresser.deleteHairdresserbyNo(Id)> 0)
        {
            return new ResponseEntity<Object>("Successfully deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<Object>("Failed", HttpStatus.EXPECTATION_FAILED);
    }
}

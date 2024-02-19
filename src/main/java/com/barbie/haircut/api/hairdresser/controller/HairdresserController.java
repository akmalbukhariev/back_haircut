package com.barbie.haircut.api.hairdresser.controller;

import com.barbie.haircut.api.BaseController;
import com.barbie.haircut.api.ResponseResult;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.param.hairdresser.HairdresserParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Hairdresser", description = "Hairdresser API")
@RequestMapping(value = {"/haircut/api/v1/hairdresser"})
public class HairdresserController extends BaseController {

    private  final IServiceHairdresser iServiceHairdresser;
    private final ResponseResult _hairdresserResponse;

    @Operation(tags = {"Hairdresser"}, summary = "1. create", description = "create a hairdresser", hidden = false, responses = {
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

    @Operation(tags = {"Hairdresser"}, summary = "2. get", description = "get a hairdresser info by id", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping("/{Id}")
    public  ResponseEntity<Object> getHairdresserById(@PathVariable int Id) throws Exception {

        HairdresserDto hairdresserDto = iServiceHairdresser.getHairdresserbyNo(Id);
        if(hairdresserDto == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(hairdresserDto);
    }

    @Operation(tags = {"Hairdresser"}, summary = "3. get", description = "get all hairdresser info", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @GetMapping()
    public ResponseEntity<Object> getHairdresserAll() throws Exception
    {
        List<HairdresserDto> hairdresserDtoList = iServiceHairdresser.getHairdresserAll();

        if(hairdresserDtoList == null)
        {
            return ResponseEntity.notFound().build();
        }

        _hairdresserResponse.setResultData(hairdresserDtoList);
        _hairdresserResponse.setResultCode(Result.SUCCESS.getCodeToString());
        _hairdresserResponse.setResultMsg(Result.SUCCESS.getMessage());

        return  ResponseEntity.ok(_hairdresserResponse);
    }

    @Operation(tags = {"Hairdresser"}, summary = "4. update", description = "update hairdresser", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  ResponseEntity<Object> updateHairdresser( @RequestPart("hairdresserParam") HairdresserParam hairdresserParam,
                                                      @RequestParam int Id) throws Exception
    {
        HairdresserParam result =  iServiceHairdresser.updateHairdresserbyNo(hairdresserParam, Id);
        if(result == null)
            return ResponseEntity.badRequest().body(Result.USER_NOT_EXIST.getCodeToString());

        _hairdresserResponse.setResultData(result);
        _hairdresserResponse.setResultCode(Result.SUCCESS.getCodeToString());
       return ResponseEntity.ok().body(_hairdresserResponse);
    }

    @Operation(tags = {"Hairdresser"}, summary = "5. delete", description = "delete hairdresser by id", hidden = false, responses = {
            @ApiResponse(responseCode = "200", description = "success")
    })
    @DeleteMapping("/{Id}")
    public ResponseEntity<Object> deleteHairdresserById(@PathVariable int Id) throws Exception {

        HairdresserDto hairdresserDto = iServiceHairdresser.getHairdresserbyNo(Id);
        if(hairdresserDto == null)
            return ResponseEntity.notFound().build();

        if(iServiceHairdresser.deleteHairdresserbyNo(Id)> 0)
        {
            _hairdresserResponse.setResultMsg("Successfully deleted");
            _hairdresserResponse.setResultData(hairdresserDto);
            _hairdresserResponse.setResultCode(Result.SUCCESS.getCodeToString());

            return ResponseEntity.ok(_hairdresserResponse);
        }
        else
            return ResponseEntity.of(Optional.of(Result.FAILED));
    }
}

package com.barbie.haircut.api.hairdresser.controller;

import com.barbie.haircut.api.BaseController;
import com.barbie.haircut.api.convert.HairdresserDtoConverter;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.hairdresser.service.IServiceImage;
import com.barbie.haircut.api.param.HairdresserParam;
import com.barbie.haircut.api.param.HairdresserResponse;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/haircut/api/v1/hairdresser"})
public class HairdresserController extends BaseController {

    private  final IServiceHairdresser iServiceHairdresser;
    private final HairdresserDtoConverter hairdresserDtoConverter;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createHairdresser(HairdresserParam hairdresserParam) throws Exception {

        hairdresserParam = iServiceHairdresser.createNewHairdresser(hairdresserParam);

        if(hairdresserParam != null) return new ResponseEntity<Object>("Successfully created", HttpStatus.CREATED);
        else return  new ResponseEntity<Object>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"Id"})
    public  ResponseEntity<Object> getHairdresserById(@RequestParam int Id) throws Exception {

        HairdresserResponse hairdresserResponse = iServiceHairdresser.getHairdresserbyNo(Id);

        return new ResponseEntity(hairdresserResponse, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Object>> getHairdresserAll() throws Exception
    {
        List<HairdresserResponse> hairdresserResponsesList = iServiceHairdresser.getHairdresserAll();

        if(hairdresserResponsesList == null) return new ResponseEntity<List<Object>>(Collections.singletonList("There aren't data"), HttpStatus.NO_CONTENT);

        return  new ResponseEntity<List<Object>>(Arrays.asList(hairdresserResponsesList.toArray()), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public  ResponseEntity<Object> updateHairdresser( @RequestPart("hairdresserParam") HairdresserParam hairdresserParam,
                                                      @RequestParam int Id) throws Exception
    {
        HairdresserParam result =  iServiceHairdresser.updateHairdresserbyNo(hairdresserParam, Id);
        if(result == null)
            return new ResponseEntity<Object>(hairdresserParam, HttpStatus.BAD_REQUEST);

       return new ResponseEntity<Object>(result, HttpStatus.OK);
    }

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
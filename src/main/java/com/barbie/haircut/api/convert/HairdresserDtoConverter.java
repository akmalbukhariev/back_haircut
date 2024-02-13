package com.barbie.haircut.api.convert;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.param.HairdresserParam;
import com.barbie.haircut.api.param.HairdresserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.stereotype.Component;

@Component
public class HairdresserDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public HairdresserDto convertHairdresserParamToDto(HairdresserParam hairdresserParam )
    {
        HairdresserDto hairdresserDto =  modelMapper.map(hairdresserParam, HairdresserDto.class);
        return hairdresserDto;
    }

    public HairdresserParam convertHairdresserDtoParam(HairdresserDto hairdresserDto)
    {
        HairdresserParam hairdresserParam =  modelMapper.map(hairdresserDto, HairdresserParam.class);
        return hairdresserParam;
    }
    public HairdresserResponse convertHairdresserDtoToResponse(HairdresserDto hairdresserDto)
    {
        HairdresserResponse hairdresserResponse = modelMapper.map(hairdresserDto, HairdresserResponse.class);
        return hairdresserResponse;
    }
    public  <T> T convertCamelCaseMapToObject(CamelCaseMap camelCaseMap, Class<T> destinationClass)
    {
        return modelMapper.map(camelCaseMap, destinationClass);
    }
}

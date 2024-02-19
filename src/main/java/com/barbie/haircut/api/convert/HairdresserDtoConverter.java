package com.barbie.haircut.api.convert;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.HairdresserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HairdresserDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public HairdresserDto convertHairdresserObjectToDto(Object object)
    {
        HairdresserDto hairdresserDto =  modelMapper.map(object, HairdresserDto.class);
        return hairdresserDto;
    }

    public <T> T convertHairdresserDtoToObject(HairdresserDto hairdresserDto , Class<T> destinationClass)
    {
        return modelMapper.map(hairdresserDto, destinationClass);
    }


    public  <T> T convertCamelCaseMapToObject(CamelCaseMap camelCaseMap, Class<T> destinationClass)
    {
        return modelMapper.map(camelCaseMap, destinationClass);
    }
}

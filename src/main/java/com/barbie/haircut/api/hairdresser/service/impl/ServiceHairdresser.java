package com.barbie.haircut.api.hairdresser.service.impl;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.convert.HairdresserDtoConverter;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.hairdresser.service.HairdresserMapper;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.hairdresser.service.IServiceImage;
import com.barbie.haircut.api.hairdresser.service.UploadFile;
import com.barbie.haircut.api.param.hairdresser.HairdresserParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceHairdresser implements IServiceHairdresser {

    private final HairdresserMapper hairdresserMapper;
    private final IServiceImage iServiceImage;

    @Autowired
    private final HairdresserDtoConverter hairdresserDtoConverter;
    @Override
    public HairdresserDto getHairdresserbyNo(int no) throws Exception {
        CamelCaseMap map = hairdresserMapper.selectHairdresserbyNo(no);

        if(map == null)
            return  null;

        HairdresserDto hairdresserDto =
                hairdresserDtoConverter.convertCamelCaseMapToObject(map, HairdresserDto.class);

        return hairdresserDto;
    }

    @Override
    public List<HairdresserDto> getHairdresserAll() throws Exception {

        List<CamelCaseMap> camelCaseMaps =  hairdresserMapper.selectHairdresserAll();

        if(camelCaseMaps ==null) return null;

        List<HairdresserDto> listDto = new ArrayList<>();
        for(CamelCaseMap map: camelCaseMaps)
        {
            listDto.add(hairdresserDtoConverter.convertCamelCaseMapToObject(map, HairdresserDto.class));
        }

        return  listDto;
    }

    @Override
    public HairdresserParam createNewHairdresser(HairdresserParam hairdresserParam) throws Exception {

        HairdresserDto hairdresserDto = hairdresserDtoConverter.convertHairdresserObjectToDto(hairdresserParam);

        UploadFile uploadFile = iServiceImage.storeFile(hairdresserParam.getImage());

        hairdresserDto.setUploadImage(uploadFile.getUploadFilename());
        hairdresserDto.setStoreImage(uploadFile.getStoreFilename());

        if(hairdresserMapper.insertNewHairdresser(hairdresserDto) > 0)
        {
            hairdresserParam = hairdresserDtoConverter.convertHairdresserDtoToObject(hairdresserDto, HairdresserParam.class);
            return  hairdresserParam;
        }
        else return null;
    }

    @Override
    public int deleteHairdresserbyNo(int no) throws Exception {

        CamelCaseMap map = hairdresserMapper.selectHairdresserbyNo(no);

        if(map == null) return 0;

        HairdresserDto hairdresserDto = hairdresserDtoConverter.convertCamelCaseMapToObject(map, HairdresserDto.class);

        int result =hairdresserMapper.deleteHairdresserbyNo(no);

        if( result == 1)
        {
            iServiceImage.deletestoreFile(hairdresserDto.getStoreImage());
        }

        return result;
    }

    @Override
    public HairdresserParam updateHairdresserbyNo(HairdresserParam hairdresserParam, int Id) throws Exception {

        CamelCaseMap map = hairdresserMapper.selectHairdresserbyNo(Id);
        if(map == null) return  null;

        HairdresserDto convertedDto = hairdresserDtoConverter.convertHairdresserObjectToDto(hairdresserParam);
        convertedDto.setNo(Id);

        if(hairdresserMapper.updateHairdresserbyNo(convertedDto) > 0)
        {
            hairdresserParam =  hairdresserDtoConverter.convertHairdresserDtoToObject(convertedDto, HairdresserParam.class);
            return hairdresserParam;
        }else return  null;
    }
}

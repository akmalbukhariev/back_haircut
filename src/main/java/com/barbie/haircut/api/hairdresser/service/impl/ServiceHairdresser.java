package com.barbie.haircut.api.hairdresser.service.impl;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.convert.HairdresserDtoConverter;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.hairdresser.service.HairdresserMapper;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.hairdresser.service.IServiceImage;
import com.barbie.haircut.api.hairdresser.service.UploadFile;
import com.barbie.haircut.api.param.HairdresserParam;
import com.barbie.haircut.api.param.HairdresserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
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
    public HairdresserResponse getHairdresserbyNo(int no) throws Exception {
        CamelCaseMap map = hairdresserMapper.selectHairdresserbyNo(no);

        if(map == null)
            return  null;

        HairdresserDto hairdresserDto =
                hairdresserDtoConverter.convertCamelCaseMapToObject(map, HairdresserDto.class);

        HairdresserResponse hairdresserResponse = new HairdresserResponse();

        hairdresserResponse.setName(hairdresserDto.getName());
        hairdresserResponse.setSurname( hairdresserDto.getSurname());
        hairdresserResponse.setPhone(hairdresserDto.getPhone());
        hairdresserResponse.setAddress( hairdresserDto.getAddress());
        hairdresserResponse.setWorkingHour(hairdresserDto.getWorkingHour());

        Resource resource =  iServiceImage.getFullPath(hairdresserDto.getStoreImage());
        if(resource !=null)
        {
            hairdresserResponse.setImageUri(resource.getURL().toString());
            hairdresserResponse.setImageName(hairdresserDto.getUploadImage());
        }

        hairdresserResponse.setDocument(hairdresserDto.getDocument());
        hairdresserResponse.setAwards(hairdresserDto.getAwards());

        return hairdresserResponse;
    }

    @Override
    public List<HairdresserResponse> getHairdresserAll() throws Exception {

        List<CamelCaseMap> camelCaseMaps =  hairdresserMapper.selectHairdresserAll();

        if(camelCaseMaps ==null) return null;

        List<HairdresserDto> listDto = new ArrayList<>();
        for(CamelCaseMap map: camelCaseMaps)
        {
            listDto.add(hairdresserDtoConverter.convertCamelCaseMapToObject(map, HairdresserDto.class));
        }

        List<HairdresserResponse> hairdresserResponsesList = new ArrayList<>();
        for(HairdresserDto hairdresserDto : listDto)
        {
            HairdresserResponse hairdresserResponse = hairdresserDtoConverter.convertHairdresserDtoToResponse(hairdresserDto);

            if(!hairdresserDto.getStoreImage().isEmpty()) {
                Resource resource = iServiceImage.getFullPath(hairdresserDto.getStoreImage());

                if(resource !=null)
                {
                    hairdresserResponse.setImageUri(resource.getURL().toString());
                    hairdresserResponse.setImageName(hairdresserDto.getUploadImage());
                }
            }

            hairdresserResponsesList.add(hairdresserResponse);
        }

        return  hairdresserResponsesList;
    }

    @Override
    public HairdresserParam createNewHairdresser(HairdresserParam hairdresserParam) throws Exception {

        HairdresserDto hairdresserDto = hairdresserDtoConverter.convertHairdresserParamToDto(hairdresserParam);

        UploadFile uploadFile = iServiceImage.storeFile(hairdresserParam.getImage());

        hairdresserDto.setUploadImage(uploadFile.getUploadFilename());
        hairdresserDto.setStoreImage(uploadFile.getStoreFilename());

        if(hairdresserMapper.insertNewHairdresser(hairdresserDto) > 0)
        {
            hairdresserParam = hairdresserDtoConverter.convertHairdresserDtoParam(hairdresserDto);
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

        HairdresserDto convertedDto = hairdresserDtoConverter.convertHairdresserParamToDto(hairdresserParam);
        convertedDto.setNo(Id);

        if(hairdresserMapper.updateHairdresserbyNo(convertedDto) > 0)
        {
            hairdresserParam =  hairdresserDtoConverter.convertHairdresserDtoParam(convertedDto);
            return hairdresserParam;
        }else return  null;
    }
}

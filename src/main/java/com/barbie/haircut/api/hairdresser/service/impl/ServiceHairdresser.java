package com.barbie.haircut.api.hairdresser.service.impl;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.convert.HairdresserDtoConverter;
import com.barbie.haircut.api.dto.*;
import com.barbie.haircut.api.hairdresser.service.HairdresserMapper;
import com.barbie.haircut.api.hairdresser.service.IServiceHairdresser;
import com.barbie.haircut.api.hairdresser.service.IServiceImage;
import com.barbie.haircut.api.param.HairdresserBookedClientHimSelftDto;
import com.barbie.haircut.api.param.HairdresserClientBookParam;
import com.barbie.haircut.api.param.HairdresserRegParam;
import com.barbie.haircut.api.param.UserBookedParam;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public CamelCaseMap getHairdresser(String hairdresserPhone) throws Exception {
        return hairdresserMapper.selectHairdresser(hairdresserPhone);
    }

    @Override
    public List<HairdresserInfoDto> getAllHairdresserForUserMainPage() throws Exception {

        List<CamelCaseMap> dataMap = hairdresserMapper.selectAllHairdresserForUserMainPage();

        List<HairdresserInfoDto> resultData = new ArrayList<>();
        for(CamelCaseMap map: dataMap)
        {
            HairdresserInfoDto newItem = new HairdresserInfoDto();
            newItem.setName(map.get("name").toString());
            newItem.setSurname(map.get("surname").toString());
            newItem.setPhone(map.get("phone").toString());
            newItem.setAddress(map.get("address").toString());
            newItem.setImageUri(map.get("storeimage").toString());
            newItem.setProfession(map.get("profession").toString());
            //newItem.setServices(map.get("services").toString());
            newItem.setAllScores(map.get("allScores").toString());

            resultData.add(newItem);
        }

        return resultData;
    }

    @Override
    public List<HairdresserServiceDto> getHairdresserServiceList(String phone) throws Exception {

        List<CamelCaseMap> dataMap = hairdresserMapper.selectHairdresserService(phone);

        List<HairdresserServiceDto> resultData = new ArrayList<>();
        for(CamelCaseMap map: dataMap)
        {
            HairdresserServiceDto newItem = new HairdresserServiceDto();
            newItem.setNo(map.get("no").toString());
            newItem.setHairdresser_no(map.get("hairdresserNo").toString());
            newItem.setService(map.get("services").toString());
            newItem.setPrice(map.get("price").toString());
            newItem.setColor(map.get("color").toString());

            resultData.add(newItem);
        }
        return resultData;
    }

    @Override
    public HairdresserInfoDto getHairdresserDetailInfo(String phone) throws Exception {

        CamelCaseMap dataMap = hairdresserMapper.selectHairdresserDetailInfo(phone);

        ObjectMapper objectMapper = new ObjectMapper();

        HairdresserInfoDto resultData = new HairdresserInfoDto();
        resultData.setName(dataMap.get("name").toString());
        resultData.setSurname(dataMap.get("surname").toString());
        resultData.setPhone(dataMap.get("phone").toString());
        resultData.setWorkingHour(dataMap.get("workinghour").toString());
        resultData.setAddress(dataMap.get("address").toString());
        resultData.setImageUri(dataMap.get("storeimage").toString());
        resultData.setProfession(dataMap.get("profession").toString());
        resultData.setServices(objectMapper.readValue(dataMap.get("services").toString(), new TypeReference<List<ServiceInfo>>() {}));
        //resultData.setServices(dataMap.get("services").toString());
        resultData.setScores(dataMap.get("scores").toString());
        resultData.setAllScores(dataMap.get("allScores").toString());
        resultData.setAverageScores(dataMap.get("averageScores").toString());

        String[] scoreList = resultData.getScores().split(",");
        if(scoreList.length == 5){
            String strPercentage = "";
            int allScore = Integer.parseInt(resultData.getAllScores());
            int score1 = Integer.parseInt(scoreList[0]);
            int score2 = Integer.parseInt(scoreList[1]);
            int score3 = Integer.parseInt(scoreList[2]);
            int score4 = Integer.parseInt(scoreList[3]);
            int score5 = Integer.parseInt(scoreList[4]);
            score1 = (score1 * 100) / allScore;
            score2 = (score2 * 100) / allScore;
            score3 = (score3 * 100) / allScore;
            score4 = (score4 * 100) / allScore;
            score5 = (score5 * 100) / allScore;

            resultData.setPercentageScore(String.valueOf(score1) + "," +
                                          String.valueOf(score2) + "," +
                                          String.valueOf(score3) + "," +
                                          String.valueOf(score4) + "," +
                                          String.valueOf(score5));
        }

        return resultData;
    }

    @Override
    public int insertBookedClient(UserBookedParam param) throws Exception {
        ModelMapper mapper = new ModelMapper();
        UserBookedDto dto = mapper.map(param, UserBookedDto.class);
        return hairdresserMapper.insertBookedClient(dto);
    }

    @Override
    public int insertHairdresserBookedClient(HairdresserBookedClientHimSelftDto param) throws Exception {
        return hairdresserMapper.insertHairdresserBookedClient(param);
    }

    @Override
    public int register(HairdresserRegParam param) throws Exception {
        return hairdresserMapper.insertHairdresser(param);
    }

    @Override
    public List<HairdresserBookedClientDto> getBookedClients(HairdresserClientBookParam param) throws Exception {

        List<CamelCaseMap> dataMap = hairdresserMapper.selectBookedClients(param);

        List<HairdresserBookedClientDto> resultData = new ArrayList<>();
        for(CamelCaseMap map: dataMap)
        {
            HairdresserBookedClientDto newItem = mapToHairdresserBookedClientDto(map);
            resultData.add(newItem);
        }

        dataMap = hairdresserMapper.selectBookedClientsHimself(param);
        for(CamelCaseMap map: dataMap)
        {
            HairdresserBookedClientDto newItem = mapToHairdresserBookedClientDto(map);
            resultData.add(newItem);
        }

        return resultData;
    }

    @Override
    public int updateHairdresserInfo(HairdresserInfoDto dto) throws Exception {
        return hairdresserMapper.updateHairdresserInfo(dto);
    }

    private HairdresserBookedClientDto mapToHairdresserBookedClientDto(CamelCaseMap map){
        HairdresserBookedClientDto newItem = new HairdresserBookedClientDto();
        newItem.setName(map.get("name").toString());
        newItem.setSurname(map.get("surname").toString());
        newItem.setPhone(map.get("phone").toString());
        newItem.setServices(map.get("services").toString());
        newItem.setColors(map.get("colors").toString());
        String strDate = map.get("date").toString();

        if(!strDate.isEmpty()){
            List<String> dList = List.of(strDate.split("-"));
            if(dList.size() == 2){
                List<String> tList1 = List.of(dList.get(0).split("/"));
                List<String> tList2 = List.of(dList.get(1).split("/"));
                if(tList1.size() == 2 && tList2.size() == 2){
                    newItem.setDate(tList1.get(0));
                    newItem.setStartTime(tList1.get(1));
                    newItem.setEndTime(tList2.get(1));
                }
            }
        }

        return newItem;
    }
}

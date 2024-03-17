package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.param.HairdresserBookedClientHimSelftDto;
import com.barbie.haircut.api.dto.UserBookedDto;
import com.barbie.haircut.api.param.HairdresserClientBookParam;
import com.barbie.haircut.api.param.HairdresserRegParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HairdresserMapper {
    CamelCaseMap selectHairdresser(String hairdresserPhone) throws  Exception;
    List<CamelCaseMap> selectAllHairdresserForUserMainPage() throws  Exception;
    List<CamelCaseMap> selectHairdresserService(String phone) throws  Exception;
    CamelCaseMap selectHairdresserDetailInfo(String hairdresserPhone) throws  Exception;
    int insertBookedClient(UserBookedDto dto) throws  Exception;
    int insertHairdresserBookedClient(HairdresserBookedClientHimSelftDto dto) throws  Exception;
    int insertHairdresser(HairdresserRegParam param) throws  Exception;
    List<CamelCaseMap> selectBookedClients(HairdresserClientBookParam param) throws  Exception;
}

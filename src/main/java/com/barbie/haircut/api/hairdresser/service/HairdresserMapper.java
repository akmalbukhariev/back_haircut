package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.HairdresserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HairdresserMapper {
    public CamelCaseMap selectHairdresserbyNo(int no) throws Exception;
    public List<CamelCaseMap> selectHairdresserAll() throws  Exception;
    public int insertNewHairdresser(HairdresserDto hairdresserDto) throws  Exception;
    public  int deleteHairdresserbyNo(int no) throws  Exception;
    public  int updateHairdresserbyNo(HairdresserDto hairdresserDto) throws Exception;
}

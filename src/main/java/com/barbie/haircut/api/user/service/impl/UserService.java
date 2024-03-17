package com.barbie.haircut.api.user.service.impl;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.dto.FavoriteHairdresserDto;
import com.barbie.haircut.api.dto.HairdresserInfoDto;
import com.barbie.haircut.api.dto.UserBookedInfoDto;
import com.barbie.haircut.api.dto.UserInfoDto;
import com.barbie.haircut.api.param.FavoriteHairdresserParam;
import com.barbie.haircut.api.param.UserInfoParam;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegParam;
import com.barbie.haircut.api.user.service.IUserService;
import com.barbie.haircut.api.user.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final PasswordEncoder psssEncoder;

    @Override
    public int login(UserParam param) throws Exception {

        CamelCaseMap map = userMapper.selectUser(param.getUserId());
        if(map == null){
            return Result.USER_NOT_EXIST.getCode();
        }

        /*Login with password
        String strPassword = map.get("password").toString();
        if(!psssEncoder.matches(param.getPasswd(), strPassword)){
            return Result.USER_PASSWORD_NOT_MATCHED.getCode();
        }*/
        return Result.SUCCESS.getCode();
    }

    @Override
    public CamelCaseMap getUser(String phone) throws Exception {
        return userMapper.selectUser(phone);
    }

    @Override
    public int register(UserRegParam param) throws Exception {

        CamelCaseMap map = userMapper.selectUser(param.phone);
        if(map != null && !map.isEmpty()){
            return Result.USER_EXIST.getCode();
        }

        //param.setPassword(psssEncoder.encode(param.getPassword()));
        return userMapper.insertUser(param);
    }

    @Override
    public int updateUserCustomer(UserInfoParam param) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        return userMapper.updateUserCustomer(modelMapper.map(param, UserInfoDto.class));
    }

    @Override
    public int updateUserHairdresser(UserInfoParam param) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        return userMapper.updateUserHairdresser(modelMapper.map(param, UserInfoDto.class));
    }

    @Override
    public List<UserBookedInfoDto> getUserBookedList(String userPhone) throws Exception {
        List<CamelCaseMap> dataMap = userMapper.selectUserBookedList(userPhone);

        List<UserBookedInfoDto> resultData = new ArrayList<>();
        for(CamelCaseMap map: dataMap)
        {
            UserBookedInfoDto newItem = new UserBookedInfoDto();
            newItem.setName(map.get("name").toString());
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
            resultData.add(newItem);
        }
        return resultData;
    }

    @Override
    public int addFavoriteHairdresser(FavoriteHairdresserParam param) throws Exception {
        return userMapper.insertFavoriteHairdresser(param);
    }

    @Override
    public List<FavoriteHairdresserDto> getAllFavoriteHairdresser(String userPhone) throws Exception {

        List<CamelCaseMap> dataMap = userMapper.selectFavoriteHardresser(userPhone);

        List<FavoriteHairdresserDto> resultData = new ArrayList<>();
        for(CamelCaseMap map: dataMap)
        {
            FavoriteHairdresserDto newItem = new FavoriteHairdresserDto();
            newItem.setImage(map.get("image").toString());
            newItem.setName(map.get("name").toString());
            newItem.setProfession(map.get("profession").toString());

            resultData.add(newItem);
        }

        return resultData;
    }

    @Override
    public int updateUserInfo(UserInfoParam param) throws Exception {
        return userMapper.updateUserInfo(param);
    }
}

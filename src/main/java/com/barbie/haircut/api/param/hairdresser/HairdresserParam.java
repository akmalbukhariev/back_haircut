package com.barbie.haircut.api.param.hairdresser;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class HairdresserParam {
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String workingHour;
    private MultipartFile image;
    private String document;
    private String awards;
    private String profession;
}

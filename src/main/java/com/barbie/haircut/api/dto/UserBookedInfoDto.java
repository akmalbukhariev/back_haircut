package com.barbie.haircut.api.dto;

import lombok.Data;

@Data
public class UserBookedInfoDto {
    private String name;
    private String services;
    private String colors;
    private String startTime;
    private String endTime;
    private String date;
}

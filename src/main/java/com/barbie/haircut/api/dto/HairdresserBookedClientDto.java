package com.barbie.haircut.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class HairdresserBookedClientDto {
    private String name;
    private String surname;
    private String phone;
    private String services;
    private String colors;
    private String startTime;
    private String endTime;
    private String date;
}

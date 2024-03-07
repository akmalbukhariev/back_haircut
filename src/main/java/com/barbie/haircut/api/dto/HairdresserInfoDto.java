package com.barbie.haircut.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class HairdresserInfoDto {
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String workingHour;
    private String scores;
    private List<ServiceInfo> services;
    private String imageUri;
    private String imageName;
    private String document;
    private String awards;
    private String profession;
    private String allScores;
    private String averageScores;
    private String percentageScore;
}

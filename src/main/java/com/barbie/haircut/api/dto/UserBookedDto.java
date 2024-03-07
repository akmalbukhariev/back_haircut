package com.barbie.haircut.api.dto;

import lombok.Data;

@Data
public class UserBookedDto {
    private String userPhone;
    private String hairdresserPhone;
    private String services;
    private String date;
}

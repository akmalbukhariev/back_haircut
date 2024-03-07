package com.barbie.haircut.api.param;

import lombok.Data;

@Data
public class UserBookedParam {
    private String userPhone;
    private String hairdresserPhone;
    private String services;
    private String date;
}

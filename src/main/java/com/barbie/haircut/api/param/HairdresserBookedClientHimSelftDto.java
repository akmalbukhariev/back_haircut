package com.barbie.haircut.api.param;

import lombok.Data;

@Data
public class HairdresserBookedClientHimSelftDto {
    private String hairdresser_phone;
    private String client_phone;
    private String client_name;
    private String service;
    private String date;
    private String note;
}

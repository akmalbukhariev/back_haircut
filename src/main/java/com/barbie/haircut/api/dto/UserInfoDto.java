package com.barbie.haircut.api.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    public String phone;
    public String name;
    public String surname;
    public String password;
    public String location;
    public String language;
    public String notification;
    public String is_customer;
    public String is_hairdresser;
}

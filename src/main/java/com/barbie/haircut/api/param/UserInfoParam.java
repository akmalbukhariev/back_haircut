package com.barbie.haircut.api.param;

import lombok.Data;

@Data
public class UserInfoParam {
    public String phone;
    public String name;
    public String password;
    public String location;
    public String is_customer;
    public String is_hairdresser;
}

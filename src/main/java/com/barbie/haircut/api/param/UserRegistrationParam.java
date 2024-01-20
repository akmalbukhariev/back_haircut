package com.barbie.haircut.api.param;

import lombok.Data;

@Data
public class UserRegistrationParam {
    public String phone;
    public String name;
    public String password;
    public String is_customer;
    public String location;
}

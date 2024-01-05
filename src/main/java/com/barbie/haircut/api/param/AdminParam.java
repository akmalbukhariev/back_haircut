package com.barbie.haircut.api.param;

import lombok.Data;

@Data
public class AdminParam {
    private String adminNm;
    private String adminId;
    private String passwd;
    private String email;
    private String phoneNo;
}

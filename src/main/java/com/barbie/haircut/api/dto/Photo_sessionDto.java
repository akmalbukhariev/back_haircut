package com.barbie.haircut.api.dto;

import lombok.Data;

@Data
public class Photo_sessionDto {
    private int no;
    private int hairdresser_no;
    private String storeFileName;
    private String uploadFileName;
}

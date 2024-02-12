package com.barbie.haircut.api.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class HairdresserResponse {
    private String name;
    private String surname;
    private String phone;
    private String address;
    private String workingHour;
    private String imageUri;
    private String imageName;
    private String document;
    private String awards;
}

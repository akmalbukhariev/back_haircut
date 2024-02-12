package com.barbie.haircut.api.dto;

import lombok.Data;

@Data
public class HairdresserDto {
   private int    no;
   private String name;
   private String surname;
   private String phone;
   private String address;
   private String workingHour;
   private String uploadImage;
   private String storeImage;
   private String document;
   private String awards;
}

package com.barbie.haircut.api;

import lombok.Data;

@Data
public class VersionResponseResult extends ResponseResult {
    private String apiVersion;
    private String webVersion;
}

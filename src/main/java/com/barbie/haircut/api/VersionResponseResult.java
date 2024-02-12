package com.barbie.haircut.api;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VersionResponseResult extends ResponseResult {
    private String apiVersion;
    private String webVersion;
}

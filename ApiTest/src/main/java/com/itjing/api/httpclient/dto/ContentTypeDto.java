package com.itjing.api.httpclient.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ContentTypeDto {
    private String name;
    private Date beginTime;
    private Integer status;
}

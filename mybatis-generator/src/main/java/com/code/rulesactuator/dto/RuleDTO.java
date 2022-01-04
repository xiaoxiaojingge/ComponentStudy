package com.code.rulesactuator.dto;

import lombok.*;

/**
 * @author lijing
 * @date 2022年01月04日 11:09
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RuleDTO {

    private String address;

    private Integer age;

    private String name;

    private String subject;
}

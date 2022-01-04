package com.code.rulesactuator.dto;

import lombok.*;

/**
 * @author lijing
 * @date 2022年01月04日 11:13
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NationalityRuleDTO extends RuleDTO {
    private String nationality;
}
package com.code.rulesactuator.rule;

import com.code.rulesactuator.dto.RuleDTO;

/**
 * @author lijing
 * @date 2022年01月04日 11:19
 * @description 具体规则-实例四
 */
public class NameRule extends AbstractRule {

    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NameRule invoke!");
        RuleDTO ruleDTO = (RuleDTO) t;
        if (ruleDTO.getName().contains("张")) {
            return true;
        }
        return false;
    }
}
package com.code.rulesactuator.rule;

import com.code.rulesactuator.constant.RuleConstant;
import com.code.rulesactuator.dto.NationalityRuleDTO;
import com.code.rulesactuator.dto.RuleDTO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lijing
 * @date 2022年01月04日 11:17
 * @description 具体规则-实例二
 */
public class NationalityRule extends AbstractRule {
    @Override
    protected <T> T convert(RuleDTO dto) {
        NationalityRuleDTO nationalityRuleDto = new NationalityRuleDTO();
        if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START)) {
            nationalityRuleDto.setNationality(RuleConstant.MATCH_NATIONALITY_START);
        }
        return (T) nationalityRuleDto;
    }


    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationalityRule invoke!");
        NationalityRuleDTO nationalityRuleDto = (NationalityRuleDTO) t;
        if (StringUtils.isNotBlank(nationalityRuleDto.getNationality()) && nationalityRuleDto.getNationality()
                .startsWith(RuleConstant.MATCH_NATIONALITY_START)) {
            return true;
        }
        return false;
    }
}
package com.code.rulesactuator.rule;

import com.code.rulesactuator.constant.RuleConstant;
import com.code.rulesactuator.dto.RuleDTO;
import com.code.rulesactuator.dto.SubjectDTO;
import org.apache.commons.lang3.StringUtils;

/**
 * @author lijing
 * @date 2022年01月04日 11:19
 * @description 具体规则-实例五
 */
public class SubjectRule extends AbstractRule {

    @Override
    protected <T> T convert(RuleDTO dto) {
        SubjectDTO subjectDTO = new SubjectDTO();
        if (StringUtils.equals(dto.getSubject(), RuleConstant.MATCH_SUBJECT_MATH)) {
            subjectDTO.setSort(RuleConstant.MATCH_SUBJECT_SCIENCE);
        }
        return (T) subjectDTO;
    }

    @Override
    protected <T> boolean executeRule(T t) {
        SubjectDTO subjectDTO = (SubjectDTO) t;
        System.out.println("SubjectRule invoke!");
        if (StringUtils.equals(subjectDTO.getSort(), RuleConstant.MATCH_SUBJECT_SCIENCE)) {
            return true;
        }
        return false;
    }
}
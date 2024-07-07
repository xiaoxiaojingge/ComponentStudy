package com.code.rulesactuator.rule;

import com.code.rulesactuator.dto.RuleDTO;

/**
 * @author lijing
 * @date 2022年01月04日 11:18
 * @description 具体规则-实例三
 */
public class AgeRule extends AbstractRule {

	@Override
	protected <T> boolean executeRule(T t) {
		System.out.println("AgeRule invoke!");
		RuleDTO ruleDTO = (RuleDTO) t;
		if (ruleDTO.getAge() > 18) {
			return true;
		}
		return false;
	}

}

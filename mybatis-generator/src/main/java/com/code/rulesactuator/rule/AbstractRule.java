package com.code.rulesactuator.rule;

import com.code.rulesactuator.dto.RuleDTO;

/**
 * @author lijing
 * @date 2022年01月04日 11:15
 * @description 规则模板
 */
public abstract class AbstractRule implements BaseRule {

	protected <T> T convert(RuleDTO dto) {
		return (T) dto;
	}

	@Override
	public boolean execute(RuleDTO dto) {
		return executeRule(convert(dto));
	}

	protected <T> boolean executeRule(T t) {
		return true;
	}

}
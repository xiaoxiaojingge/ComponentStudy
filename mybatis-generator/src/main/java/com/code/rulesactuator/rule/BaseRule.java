package com.code.rulesactuator.rule;

import com.code.rulesactuator.dto.RuleDTO;

/**
 * @author lijing
 * @date 2022年01月04日 11:14
 * @description 规则抽象
 */
public interface BaseRule {

	boolean execute(RuleDTO dto);

}
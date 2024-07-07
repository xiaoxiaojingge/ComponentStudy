package com.code.rulesactuator.rule;

import com.code.rulesactuator.constant.RuleConstant;
import com.code.rulesactuator.dto.RuleDTO;

/**
 * @author lijing
 * @date 2022年01月04日 11:16
 * @description 具体规则-实例一
 */
public class AddressRule extends AbstractRule {

	@Override
	public boolean execute(RuleDTO dto) {
		System.out.println("AddressRule invoke!");
		if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START)) {
			return true;
		}
		return false;
	}

}

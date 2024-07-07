package com.itjing.api.sqlparser.items;

import com.itjing.api.sqlparser.ParserItem;
import net.sf.jsqlparser.statement.select.SubJoin;

public class SqlSubJoin implements ParserItem {

	private SubJoin subJoin;

	public SqlSubJoin(SubJoin subJoin) {
		this.subJoin = subJoin;
	}

	public void parser() {

	}

}

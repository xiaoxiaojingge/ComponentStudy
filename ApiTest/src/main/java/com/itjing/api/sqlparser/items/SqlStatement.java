package com.itjing.api.sqlparser.items;

import com.itjing.api.sqlparser.ParserItem;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

public class SqlStatement implements ParserItem {

	private String sql;

	private SqlSelectStatement selectStatement;

	public SqlStatement(String sql) {
		this.sql = sql;
	}

	public void parser() throws JSQLParserException {
		Statement statement = CCJSqlParserUtil.parse(sql);
		if (statement instanceof Select) {
			Select select = (Select) statement;
			selectStatement = new SqlSelectStatement(select);
			selectStatement.parser();
		}
	}

	public String getSql() {
		return sql;
	}

	public SqlSelectStatement getSelectStatement() {
		return selectStatement;
	}

}

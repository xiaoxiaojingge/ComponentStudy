package com.itjing.api.sqlparser.items;

import com.itjing.api.sqlparser.ParserItem;
import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.SubSelect;

public class SqlWhere implements ParserItem, ExpressionVisitor {

	private Expression where;

	public SqlWhere(Expression where) {
		this.where = where;
	}

	public void parser() {
		where.accept(this);
	}

	@Override
	public void visit(NullValue nullValue) {

	}

	@Override
	public void visit(Function function) {

	}

	@Override
	public void visit(SignedExpression signedExpression) {

	}

	@Override
	public void visit(JdbcParameter jdbcParameter) {

	}

	@Override
	public void visit(JdbcNamedParameter jdbcNamedParameter) {

	}

	@Override
	public void visit(DoubleValue doubleValue) {

	}

	@Override
	public void visit(LongValue longValue) {

	}

	@Override
	public void visit(HexValue hexValue) {

	}

	@Override
	public void visit(DateValue dateValue) {

	}

	@Override
	public void visit(TimeValue timeValue) {

	}

	@Override
	public void visit(TimestampValue timestampValue) {

	}

	@Override
	public void visit(Parenthesis parenthesis) {

	}

	@Override
	public void visit(StringValue stringValue) {

	}

	@Override
	public void visit(Addition addition) {

	}

	@Override
	public void visit(Division division) {

	}

	@Override
	public void visit(Multiplication multiplication) {

	}

	@Override
	public void visit(Subtraction subtraction) {

	}

	@Override
	public void visit(AndExpression andExpression) {

	}

	@Override
	public void visit(OrExpression orExpression) {

	}

	@Override
	public void visit(Between between) {

	}

	@Override
	public void visit(EqualsTo equalsTo) {

	}

	@Override
	public void visit(GreaterThan greaterThan) {

	}

	@Override
	public void visit(GreaterThanEquals greaterThanEquals) {

	}

	@Override
	public void visit(InExpression inExpression) {

	}

	@Override
	public void visit(IsNullExpression isNullExpression) {

	}

	@Override
	public void visit(LikeExpression likeExpression) {

	}

	@Override
	public void visit(MinorThan minorThan) {

	}

	@Override
	public void visit(MinorThanEquals minorThanEquals) {

	}

	@Override
	public void visit(NotEqualsTo notEqualsTo) {

	}

	@Override
	public void visit(Column tableColumn) {

	}

	@Override
	public void visit(SubSelect subSelect) {

	}

	@Override
	public void visit(CaseExpression caseExpression) {

	}

	@Override
	public void visit(WhenClause whenClause) {

	}

	@Override
	public void visit(ExistsExpression existsExpression) {

	}

	@Override
	public void visit(AllComparisonExpression allComparisonExpression) {

	}

	@Override
	public void visit(AnyComparisonExpression anyComparisonExpression) {

	}

	@Override
	public void visit(Concat concat) {

	}

	@Override
	public void visit(Matches matches) {

	}

	@Override
	public void visit(BitwiseAnd bitwiseAnd) {

	}

	@Override
	public void visit(BitwiseOr bitwiseOr) {

	}

	@Override
	public void visit(BitwiseXor bitwiseXor) {

	}

	@Override
	public void visit(CastExpression cast) {

	}

	@Override
	public void visit(Modulo modulo) {

	}

	@Override
	public void visit(AnalyticExpression aexpr) {

	}

	@Override
	public void visit(WithinGroupExpression wgexpr) {

	}

	@Override
	public void visit(ExtractExpression eexpr) {

	}

	@Override
	public void visit(IntervalExpression iexpr) {

	}

	@Override
	public void visit(OracleHierarchicalExpression oexpr) {

	}

	@Override
	public void visit(RegExpMatchOperator rexpr) {

	}

	@Override
	public void visit(JsonExpression jsonExpr) {

	}

	@Override
	public void visit(RegExpMySQLOperator regExpMySQLOperator) {

	}

	@Override
	public void visit(UserVariable var) {

	}

	@Override
	public void visit(NumericBind bind) {

	}

	@Override
	public void visit(KeepExpression aexpr) {

	}

	@Override
	public void visit(MySQLGroupConcat groupConcat) {

	}

	@Override
	public void visit(RowConstructor rowConstructor) {

	}

	@Override
	public void visit(OracleHint hint) {

	}

}

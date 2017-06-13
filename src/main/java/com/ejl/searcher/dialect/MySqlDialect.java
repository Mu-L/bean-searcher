package com.ejl.searcher.dialect;


/**
 * MySql 方言实现
 *  
 * @author Troy.Zhou
 * 
 * */
public class MySqlDialect implements Dialect {


	
	@Override
	public void toUpperCase(StringBuilder builder, String dbField) {
		builder.append("upper").append("(").append(dbField).append(")");
	}

	@Override
	public void truncateToDateStr(StringBuilder builder, String dbField) {
		builder.append("date_format(").append(dbField).append(", '%Y-%m-%d')");
	}
	

}

package com.revature.p1SCS.orm.services;

public class ORMDelete implements ORMInterface{

	@Override
	public String makeSQLStatement(Object obj) {
		String sql = "DELETE ";
		return sql;
	}

}

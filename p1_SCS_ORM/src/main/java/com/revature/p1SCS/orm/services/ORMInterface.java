package com.revature.p1SCS.orm.services;

public interface ORMInterface {
	/**
	 * Writes a SQL statement based on the object's fields
	 * 
	 * @param obj Object whose fields are being accessed
	 * @return returns sql statement as a String
	 */
	public String makeSQLStatement(Object obj);
}

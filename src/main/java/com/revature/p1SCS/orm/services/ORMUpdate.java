package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMUpdate implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class<?> objClass = null;

	@Override
	/*Writes a SQL create statement based on the object's fields*/
	public String makeSQLStatement(Object o) {
		/*Local Variables*/
		String sql = "UPDATE ";
		String table = "";
		int index = 0;
		List<String> fields = new ArrayList<>(),
				values = new ArrayList<>(),
				filters = new ArrayList<>(),
				argTypes = new ArrayList<>(),
				filterValues = new ArrayList<>();
		
		/*Function*/
		try {
			//Setting Variables
			setObj(o);
			setObjClass(o.getClass());
			table = getTableName();
			fields = getFields();
			values = getValues();
			filters = getFilters();
			argTypes = getArgTypes();
			filterValues = getFilterValues();
			index = 0;
			
			//SQL Creation
			sql += table + " SET ";
			for (String s: fields) {
				sql += s + " = '" + values.get(index) + "'";
				if (index != (fields.size() - 1)) {
					sql += ", ";
				}
				index++;
			}
			
			sql += " WHERE ";
			index = 0;
			for (String s : filters) {
				sql += s + " " + argTypes.get(index) + " '"+ filterValues.get(index) + "'";
				if (index != (filters.size() - 1)) {
					sql += " AND ";
				}
				index++;
			}
			
		}
		catch (Exception e) {
			//Exception logger
			//e.printStackTrace();
		}
		
		/*Return*/
		return sql;
	}
	
	/**
	 * Retrieves table name of the object accessed through reflection
	 * 
	 * @return a String of the table name
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected String getTableName() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field tableName = objClass.getDeclaredField("tableName");
		String result = "";
		
		/*Function*/
		tableName.setAccessible(true);
		result = tableName.get(obj).toString();
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves fields of the object class through reflection
	 * 
	 * @return a List of the fields contained in the object class
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getFields() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldNameList = objClass.getDeclaredField("fieldNameList");
		List<String> result;
		
		/*Function*/
		fieldNameList.setAccessible(true);
		result = (List<String>) fieldNameList.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves values related to object fields
	 * 
	 * @return a List of values associated with fields
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getValues() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldValueList = objClass.getDeclaredField("fieldValueList");
		List<String> result;
		
		/*Function*/
		fieldValueList.setAccessible(true);
		result = (List<String>) fieldValueList.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves filters used for conditions in the SQL statement
	 * 
	 * @return a List of filters used for the WHERE/AND clause
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getFilters() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field filterList = objClass.getDeclaredField("filterList");
		List<String> result;
		
		/*Function*/
		filterList.setAccessible(true);
		result = (List<String>) filterList.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves conditional operations used for conditions in
	 * the SQL statement such as (>, <, ==, >=, <=)
	 * 
	 * @return a List of conditional operators used in the SQL statement
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getArgTypes() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field argumentTypes = objClass.getDeclaredField("argumentTypes");
		List<String> result;
		
		/*Function*/
		argumentTypes.setAccessible(true);
		result = (List<String>) argumentTypes.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves values used for conditionals in the SQL statement
	 * 
	 * @return a List of values used in filter conditions
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getFilterValues() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field filterValueList = objClass.getDeclaredField("filterValueList");
		List<String> result;
		
		/*Function*/
		filterValueList.setAccessible(true);
		result = (List<String>) filterValueList.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * The object whose class is being accessed and mapped to a
	 * relational database
	 * 
	 * @param obj The object whose class is being accessed
	 */
	public void setObj(Object o) {
		this.obj = o;
	}
	
	/**
	 * The object's class that is being accessed through reflection
	 * 
	 * @param objClass The class of the object being accessed through reflection
	 */
	public void setObjClass(Class<?> c) {
		this.objClass = c;
	}

}

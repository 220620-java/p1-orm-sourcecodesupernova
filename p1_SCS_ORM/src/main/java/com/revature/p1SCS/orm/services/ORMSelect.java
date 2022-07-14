package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMSelect implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class objClass = null;
	
	@Override
	public String makeSQLStatement(Object obj) {
		String sql = "SELECT ";
		String table = "";
		int index = 0;
		List<String> fields = new ArrayList<>(),
					 filters = new ArrayList<>(),
					 argTypes = new ArrayList<>(),
					 filterValues = new ArrayList<>();
		
		try {
			setObj(obj);
			setObjClass(obj.getClass());
			table = getTableName();
			fields = getFields();
			filters = getFilters();
			argTypes = getArgTypes();
			filterValues = getFilterValues();
			index = 0;
			
			// SELECT fields FROM table WHERE filters (argTypes) values AND filters (argTypes) values
			for(String s : fields) {
				sql += s;
				if(index != (fields.size() - 1)) {
					sql += ", ";
				}
				index++;
			}
			
			sql += " FROM " + table;
			
			if (filters.size() != 0) {
				sql += " WHERE ";
				index = 0;
				for(String s : filters) {
					sql += s + " " + argTypes.get(index) + " '" + filterValues.get(index) + "'";
					if(index != (filters.size() - 1)) {
						sql += " AND ";
					}
					index++;
				}
			}
			sql += ";";
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
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
	protected List<String> getFields() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldNameList = objClass.getDeclaredField("fieldNameList");
		List<String> result = new ArrayList<>();
		
		/*Function*/
		fieldNameList.setAccessible(true);
		result = (List<String>) fieldNameList.get(obj);
		
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
	protected List<String> getFilters() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field filterList = objClass.getDeclaredField("filterList");
		List<String> result = new ArrayList<>();
		
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
	protected List<String> getArgTypes() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field argumentTypes = objClass.getDeclaredField("argumentTypes");
		List<String> result = new ArrayList<>();
		
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
	protected List<String> getFilterValues() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field filterValueList = objClass.getDeclaredField("filterValueList");
		List<String> result = new ArrayList<>();
		
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
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	/**
	 * The object's class that is being accessed through reflection
	 * 
	 * @param objClass The class of the object being accessed through reflection
	 */
	public void setObjClass(Class objClass) {
		this.objClass = objClass;
	}
	
	

}

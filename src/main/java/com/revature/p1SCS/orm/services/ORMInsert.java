package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ORMInsert implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class<?> objClass = null;

	@Override
	/*Writes a SQL create statement based on the object's fields*/
	public String makeSQLStatement(Object o) {
		/*Local Variables*/
		String sql = "INSERT INTO ";
		String table = "";
		int index = 0;
		List<String> fields = new ArrayList<>(),
				     keys = new ArrayList<>(),
				     values = new ArrayList<>();
		
		/*Function*/
		try {
			//Setting Variables
			setObj(o);
			setObjClass(o.getClass());
			keys = getKeys();
			table = getTableName();
			fields = getFields();
			values = getValues();
			index = 0;
			
			//SQL Creation
			sql += table + "(";
			for (String s: fields) {
				sql += s;
				if(index == (fields.size() - 1)) {
					sql += ") ";
				}
				else{
					sql += ", ";
				}
				index++;
			}
			
			sql += "VALUES(";
			index = 0;
			for (String s: values) {
				if (keys.contains(fields.get(index))) { //The field is meant to be set automatically
					sql += "default";
				}
				else {
					sql += "'" + s + "'";
				}
				if(index == (values.size() - 1)) {
					sql += ");";
				}
				else{
					sql += ", ";
				}
				index++;
			}
		}
		catch (Exception e) {
			//e.printStackTrace();
			//Exception logger
		}
		
		/*Return*/
		return sql;
	}
	
	/**
	 * Retrieves keys associated with object class
	 * 
	 * @return a List of keys related to object class
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	protected List<String> getKeys() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field keys = objClass.getDeclaredField("keys");
		String[] value = {""};
		List<String> result;
		
		/*Function*/
		keys.setAccessible(true);
		value = (String[]) keys.get(obj);
		
//		for (String s : value) {
//			result.add(s);
//		}
		result = Arrays.asList(value);
		
		/*Return*/
		return result;
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

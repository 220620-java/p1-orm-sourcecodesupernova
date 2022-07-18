package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMInsert implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class objClass = null;

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
			e.printStackTrace();
			//TODO Exception logger
		}
		
		/*Return*/
		return sql;
	}
	
	protected List<String> getKeys() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field keys = objClass.getDeclaredField("keys");
		String[] value = {""};
		List<String> result = new ArrayList<>();
		
		/*Function*/
		keys.setAccessible(true);
		value = (String[]) keys.get(obj);
		
		for (String s : value) {
			result.add(s);
		}
		
		/*Return*/
		return result;
	}
	
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
	
	protected List<String> getValues() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldValueList = objClass.getDeclaredField("fieldValueList");
		List<String> result = new ArrayList<>();
		
		/*Function*/
		fieldValueList.setAccessible(true);
		result = (List<String>) fieldValueList.get(obj);
		
		/*Return*/
		return result;
	}
	
	public void setObj(Object o) {
		this.obj = o;
	}
	
	public void setObjClass(Class c) {
		this.objClass = c;
	}

}

package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMUpdate implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class objClass = null;
	
	private void runSQLStatement(String sql) {
		//TODO DataAccessObjects, Database
	}

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
				if (index != (fields.size() - 1)) {
					sql += " AND ";
				}
				index++;
			}
			
		}
		catch (Exception e) {
			//TODO Exception logger
			e.printStackTrace();
		}
		
		/*Return*/
		return sql;
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
	
	public void setObj(Object o) {
		this.obj = o;
	}
	
	public void setObjClass(Class c) {
		this.objClass = c;
	}

}

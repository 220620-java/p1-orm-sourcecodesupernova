package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMSelect implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class objClass = null;
	
	private void runSQLStatement(String sql) {
		//TODO DataAccessObjects, Database
	}
	
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
			//e.printStackTrace();
		}
		
		
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
	
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public void setObjClass(Class objClass) {
		this.objClass = objClass;
	}
	
	

}

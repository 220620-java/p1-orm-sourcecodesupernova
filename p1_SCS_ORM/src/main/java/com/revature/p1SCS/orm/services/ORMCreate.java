package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMCreate implements ORMInterface{
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
		String sql = "CREATE ";
		String table = "";
		int index = 0;
		List<String> fields = new ArrayList<>(),
				dataTypes = new ArrayList<>(),
				modifiers = new ArrayList<>(),
				foreignReferences = new ArrayList<>();
		
		/*Function*/
		try {
			//Setting Variables
			setObj(o);
			setObjClass(o.getClass());
			table = getTableName();
			fields = getFields();
			dataTypes = getTypes();
			modifiers = getModifiers();
			foreignReferences = getForeignReferences();
			index = 0;
			
			//SQL Creation
			sql += table + "(";
			for (String s: fields) {
				sql += s + " " + dataTypes.get(index);
				
				//Adding modifiers if present
				if (modifiers.get(index) != null) {
					
					//Primary Keys already have UNIQUE and NOT NULL, so adding them is redundant if PRIMARY KEY is present
					if (modifiers.get(index).contains("p")) {
						sql += " PRIMARY KEY";
					}
					else {
						if(modifiers.get(index).contains("u")) {
							sql += " UNIQUE";
						}
						if(modifiers.get(index).contains("n")) {
							sql += " NOT NULL";
						}
					}
					
					//Foreign Keys need to have a table to reference in order to be placed in the SQL query
					if(modifiers.get(index).contains("f") && foreignReferences.get(index) != null) {
						sql += " FOREIGN KEY REFERENCES " + foreignReferences.get(index);
					}
				}
				
				//Ends the SQL statement if there are no more fields to add, otherwise adds a comma
				if (index == (fields.size() - 1)) {
					sql += "); ";
				}
				else {
					sql += ", ";
				}
				index++;
			}
		}
		catch (Exception e) {
			//TODO Exception logger
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
	
	protected List<String> getTypes() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldTypeList = objClass.getDeclaredField("fieldTypeList");
		List<String> result = new ArrayList<>();
		
		/*Function*/
		fieldTypeList.setAccessible(true);
		result = (List<String>) fieldTypeList.get(obj);
		
		/*Return*/
		return result;
	}
	
	protected List<String> getModifiers() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldModifiersList = objClass.getDeclaredField("fieldModifiersList");
		List<String> result = new ArrayList<>();
		
		/*Function*/
		fieldModifiersList.setAccessible(true);
		result = (List<String>) fieldModifiersList.get(obj);
		
		/*Return*/
		return result;
	}
	
	protected List<String> getForeignReferences() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field foreignReferenceList = objClass.getDeclaredField("foreignReferenceList");
		List<String> result = new ArrayList<>();
		
		/*Function*/
		foreignReferenceList.setAccessible(true);
		result = (List<String>) foreignReferenceList.get(obj);
		
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

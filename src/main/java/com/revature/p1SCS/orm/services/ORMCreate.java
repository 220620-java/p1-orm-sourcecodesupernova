package com.revature.p1SCS.orm.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ORMCreate implements ORMInterface{
	/*Class Variables*/
	private Object obj = null;
	private Class<?> objClass = null;

	@Override
	/*Writes a SQL create statement based on the object's fields*/
	public String makeSQLStatement(Object o) {
		/*Local Variables*/
		String sql = "CREATE TABLE ";
		String table = "";
		int fkIndex = 0; // foreign key index
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
			fkIndex = 0;
			index = 0;
			
			//SQL Creation
			sql += table + " (";
			for (String s: fields) {
				sql += s + " " + dataTypes.get(index);
				
				//Adding modifiers if present
				if (modifiers.get(index) != null) {
					
					//Primary Keys already have UNIQUE and NOT NULL, so adding them is redundant
					if (modifiers.get(index).contains("PRIM")) {
						sql += " PRIMARY KEY";
					}
					else {
						if(modifiers.get(index).contains("UNI")) {
							sql += " UNIQUE";
						}
						if(modifiers.get(index).contains("NOT")) {
							sql += " NOT NULL";
						}
					}
					
					//Foreign Keys need to have a table to reference in order to be placed in the SQL query
					if(modifiers.get(index).contains("FOREIGN") && foreignReferences.get(fkIndex) != null) {
						sql += " REFERENCES " + foreignReferences.get(fkIndex);
						fkIndex++;
					}
				}
				
				//Ends the SQL statement if there are no more fields to add, otherwise adds a comma
				if (index == (fields.size() - 1)) {
					sql += ");";
				}
				else {
					sql += ", ";
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
	 * Retrieves the types of the fields accessed by reflection
	 * 
	 * @return a List of the types associated with the object class fields
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getTypes() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldTypeList = objClass.getDeclaredField("fieldTypeList");
		List<String> result;
		
		/*Function*/
		fieldTypeList.setAccessible(true);
		result = (List<String>) fieldTypeList.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves the modifiers related to fields accessed through reflection
	 * 
	 * @return a List of the modifiers associated with object class fields
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getModifiers() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field fieldModifiersList = objClass.getDeclaredField("fieldModifiersList");
		List<String> result;
		
		/*Function*/
		fieldModifiersList.setAccessible(true);
		result = (List<String>) fieldModifiersList.get(obj);
		
		/*Return*/
		return result;
	}
	
	/**
	 * Retrieves foreign key references related to the object class
	 * 
	 * @return a List of the foreign references associated with the object
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	protected List<String> getForeignReferences() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		/*Local Variables*/
		Field foreignReferenceList = objClass.getDeclaredField("foreignReferenceList");
		List<String> result;
		
		/*Function*/
		foreignReferenceList.setAccessible(true);
		result = (List<String>) foreignReferenceList.get(obj);
		
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

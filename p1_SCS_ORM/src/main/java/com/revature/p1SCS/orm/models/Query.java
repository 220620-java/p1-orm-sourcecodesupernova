package com.revature.p1SCS.orm.models;

import java.util.ArrayList;
import java.util.List;

public class Query {
	/*General Variables*/
	//All of the lists are meant to be parallel arrays. So when you set the value for one, make sure to set the others as well, even if its new ArrayList<>()
	private String tableName = "";
	private List<String> fieldNameList = new ArrayList<>(),
			fieldTypeList = new ArrayList<>(),
			fieldValueList = new ArrayList<>(),
			fieldModifiersList = new ArrayList<>(); //Will be a string of identifiers. 'p' for PK, 'u' for unique, 'n' for not new ArrayList<>(), 'f' for FK
	
	/*Insert Statement Variables*/
	private String[] keys = null;
	
	/*Create Statement Variables*/	
	private List<String> foreignReferenceList = new ArrayList<>(); //This will be parallel with the others as well. Set to new ArrayList<>() if the field does not reference a foreign table
	
	/*Where Clause Variables*/
	private List<String> filterList = new ArrayList<>(),
			filterValueList = new ArrayList<>(),
			argumentTypes = new ArrayList<>(); //Will be LIKE, >, <, >=, <=, == 
	
	/*Getters, Setters. Adders*/
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public List<String> getFieldNameList() {
		return fieldNameList;
	}
	
	public void setFieldNameList(List<String> fieldNameList) {
		this.fieldNameList = fieldNameList;
	}
	
	public List<String> getFieldTypeList() {
		return fieldTypeList;
	}
	
	public void setFieldTypeList(List<String> fieldTypeList) {
		this.fieldTypeList = fieldTypeList;
	}
	
	public List<String> getFieldValueList() {
		return fieldValueList;
	}
	
	public void setFieldValueList(List<String> fieldValueList) {
		this.fieldValueList = fieldValueList;
	}
	
	public List<String> getFieldModifiersList() {
		return fieldModifiersList;
	}
	
	public void setFieldModifiersList(List<String> fieldModifiersList) {
		this.fieldModifiersList = fieldModifiersList;
	}
	
	public List<String> getForeignReferenceList() {
		return foreignReferenceList;
	}
	
	public void setForeignReferenceList(List<String> foreignReferenceList) {
		this.foreignReferenceList = foreignReferenceList;
	}
	
	public List<String> getFilterList() {
		return filterList;
	}
	
	public void setFilterList(List<String> filterList) {
		this.filterList = filterList;
	}
	
	public List<String> getFilterValueList() {
		return filterValueList;
	}
	
	public void setFilterValueList(List<String> filterValueList) {
		this.filterValueList = filterValueList;
	}
	
	public List<String> getArgumentTypes() {
		return argumentTypes;
	}
	
	public void setArgumentTypes(List<String> argumentType) {
		this.argumentTypes = argumentType;
	}
	

	public String[] getKeys() {
		return keys;
	}
	
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	
}

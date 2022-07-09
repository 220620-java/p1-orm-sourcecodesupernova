package com.revature.p1SCS.orm.models;

import java.util.ArrayList;
import java.util.List;

public class Query {
	/*General Variables*/
	//All of the lists are meant to be parallel arrays. So when you set the value for one, make sure to set the others as well, even if its null
	private String tableName = "";
	private String queryType = ""; //Will be SELECT, CREATE, UPDATE, DELETE
	private List<String> fieldNameList = new ArrayList<>();
	private List<String> fieldTypeList = new ArrayList<>();
	private List<String> fieldValueList = new ArrayList<>();
	private List<String> fieldModifiersList = new ArrayList<>(); //Will be a string of identifiers. 'p' for PK, 'u' for unique, 'n' for not null, 'f' for FK
	
	/*Create Statement Variables*/	
	private List<String> foreignReferenceList = new ArrayList<>(); //This will be parallel with the others as well. Set to null if the field does not reference a foreign table
	
	/*Where Clause Variables*/
	private List<String> fieldFilterByList = new ArrayList<>();
	private List<String> fieldFilterValueList = new ArrayList<>();
	private List<String> argumentType = new ArrayList<>(); //Will be LIKE, >, <, >=, <=, ==
	
	/*Getters, Setters. Adders*/
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getQueryType() {
		return queryType;
	}
	
	public void setQueryType(String queryType) {
		this.queryType = queryType;
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
	
	public List<String> getFieldFilterByList() {
		return fieldFilterByList;
	}
	
	public void setFieldFilterByList(List<String> fieldFilterByList) {
		this.fieldFilterByList = fieldFilterByList;
	}
	
	public List<String> getFieldFilterValueList() {
		return fieldFilterValueList;
	}
	
	public void setFieldFilterValueList(List<String> fieldFilterValueList) {
		this.fieldFilterValueList = fieldFilterValueList;
	}
	
	public List<String> getArgumentType() {
		return argumentType;
	}
	
	public void setArgumentType(List<String> argumentType) {
		this.argumentType = argumentType;
	}
}

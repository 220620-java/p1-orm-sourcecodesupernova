package com.revature.p1SCS.orm.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.p1SCS.orm.models.Query;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ORMCreateTest {
	Query testObj = new Query();
	ORMCreate sql = new ORMCreate();
	
	@BeforeEach
	public void beforeEach() {
		testObj = new Query();
		sql = new ORMCreate();
	}
	
	@Test
	public void makeSQLStatementTestUsingCreate() {
		/*Local Variables*/
		List<String> fieldNames = new ArrayList<>(),
				     fieldDataTypes = new ArrayList<>(),
				     fieldModifiers = new ArrayList<>(),
				     foreignReferences = new ArrayList<>();
		
		String expected = "CREATE TABLE test ("
						+ "field1 SERIAL PRIMARY KEY, "
						+ "field2 VARCHAR(20) UNIQUE NOT NULL, "
						+ "field3 INTEGER REFERENCES test_account"
						+ ");";
				     
		/*Variable Setup*/
		fieldNames.add("field1");
		fieldNames.add("field2");
		fieldNames.add("field3");
		fieldDataTypes.add("SERIAL");
		fieldDataTypes.add("VARCHAR(20)");
		fieldDataTypes.add("INTEGER");
		fieldModifiers.add("PRIMARY KEY");
		fieldModifiers.add("UNIQUE NOT NULL");
		fieldModifiers.add("FOREIGN KEY");
		foreignReferences.add("test_account");
		
		
		/*Object Setup*/
		testObj.setTableName("test");
		testObj.setFieldNameList(fieldNames);
		testObj.setFieldTypeList(fieldDataTypes);
		testObj.setFieldModifiersList(fieldModifiers);
		testObj.setForeignReferenceList(foreignReferences);
		
		/*Function*/
		String actual = sql.makeSQLStatement(testObj);
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void makeSQLStatementTestUsingCreateWithNoForeign() {
		/*Local Variables*/
		List<String> fieldNames = new ArrayList<>(),
				     fieldDataTypes = new ArrayList<>(),
				     fieldModifiers = new ArrayList<>(),
				     foreignReferences = new ArrayList<>();
		
		String expected = "CREATE TABLE test ("
						+ "field1 SERIAL PRIMARY KEY, "
						+ "field2 VARCHAR(20) UNIQUE"
						+ ");";
				     
		/*Variable Setup*/
		fieldNames.add("field1");
		fieldNames.add("field2");
		fieldDataTypes.add("SERIAL");
		fieldDataTypes.add("VARCHAR(20)");
		fieldModifiers.add("PRIMARY KEY");
		fieldModifiers.add("UNIQUE");
		
		
		/*Object Setup*/
		testObj.setTableName("test");
		testObj.setFieldNameList(fieldNames);
		testObj.setFieldTypeList(fieldDataTypes);
		testObj.setFieldModifiersList(fieldModifiers);
		testObj.setForeignReferenceList(foreignReferences);
		
		/*Function*/
		String actual = sql.makeSQLStatement(testObj);
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	public void makeSQLStatementThrowsExceptionTest() {
		/*Local Variables*/
		testObj = null;
		
		/*Test*/
		Assertions.assertThrows(Exception.class, () -> testObj.getClass());
	}
	
	@Test
	public void testQuerygetTableName() {
		testObj.setTableName("test");
		
		String actual = testObj.getTableName();
		
		Assertions.assertEquals("test", actual);
	}
	
	
	
	
	
}

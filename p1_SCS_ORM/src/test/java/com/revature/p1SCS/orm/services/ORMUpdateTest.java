package com.revature.p1SCS.orm.services;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import com.revature.p1SCS.orm.models.Query;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ORMUpdateTest {
	/*Class Variables*/
	Query testObj = new Query();
	ORMUpdate sql = new ORMUpdate();
	
	@BeforeEach
	public void beforeEach() {
		testObj = new Query();
		sql = new ORMUpdate();
	}
	
	@Test
	public void makeSQLStatementTest() {
		/*Local Variables*/
		List<String> fieldNames = new ArrayList<>(),
				fieldValues = new ArrayList<>(),
				filterNames = new ArrayList<>(),
				filterValues = new ArrayList<>(),
				argTypes = new ArrayList<>();
		String expected = "UPDATE test SET field1 = 'value1', field2 = 'value2' "
				+ "WHERE filter1 >= 'filval1' AND filter2 == 'filval2';",
				actual = "";
		
		/*Variable Setup*/
		fieldNames.add("field1");
		fieldNames.add("field2");
		fieldValues.add("value1");
		fieldValues.add("value2");
		filterNames.add("filter1");
		filterNames.add("filter2");
		filterValues.add("filval1");
		filterValues.add("filval2");
		argTypes.add(">=");
		argTypes.add("==");
		
		/*Object Setup*/
		testObj.setTableName("test");
		testObj.setFieldNameList(fieldNames);
		testObj.setFieldValueList(fieldValues);
		testObj.setFilterList(filterNames);
		testObj.setFilterValueList(filterValues);
		testObj.setArgumentTypes(argTypes);
		
		/*Function*/
		actual = sql.makeSQLStatement(testObj);
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
//	@Test
//	public void testUpdateSqlStatementWithStreams() { //STILL IN PROGRESS
//		/*Local Variables*/
//		List<String> fieldNames = new ArrayList<>(),
//				fieldValues = new ArrayList<>(),
//				filterNames = new ArrayList<>(),
//				filterValues = new ArrayList<>(),
//				argTypes = new ArrayList<>();
//		String expected = "UPDATE test SET field1 = 'value1', field2 = 'value2' "
//				+ "WHERE filter1 >= 'filval1' AND filter2 == 'filval2';",
//				actual = "";
//		
//		/*Variable Setup*/
//		fieldNames.add("field1");
//		fieldNames.add("field2");
//		fieldValues.add("value1");
//		fieldValues.add("value2");
//		filterNames.add("filter1");
//		filterNames.add("filter2");
//		filterValues.add("filval1");
//		filterValues.add("filval2");
//		argTypes.add(">=");
//		argTypes.add("==");
//		
//		/*Object Setup*/
//		testObj.setTableName("test");
//		testObj.setFieldNameList(fieldNames);
//		testObj.setFieldValueList(fieldValues);
//		testObj.setFilterList(filterNames);
//		testObj.setFilterValueList(filterValues);
//		testObj.setArgumentTypes(argTypes);
//		
//		Assertions.fail("Still working on creating a functioning Stream to test");
//	
//	}
	
	
	
}

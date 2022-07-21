package com.revature.p1SCS.orm.services;

import java.util.ArrayList;
import java.util.List;

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
public class ORMDeleteTest {
	Query testObj = new Query();
	ORMDelete sql = new ORMDelete();
	
	@BeforeEach
	public void beforeEach() {
		testObj = new Query();
		sql = new ORMDelete();
	}
	
	@Test
	public void makeSQLStatementTestUsingDelete() {
		/*Local Variables*/
		List<String> fieldNames = new ArrayList<>(),
				fieldValues = new ArrayList<>(),
				filterNames = new ArrayList<>(),
				filterValues = new ArrayList<>(),
				argTypes = new ArrayList<>();
		String expected = "DELETE FROM test WHERE filter1 == 'filval1';";
		//String expected = "DELETE FROM test LIKE '%@revature.net';";
		
		/*Variable Setup*/
		fieldNames.add("field1");
		fieldNames.add("field2");
//		fieldValues.add("value1");
//		fieldValues.add("value2");
		filterNames.add("filter1");
//		filterNames.add("filter2");
		filterValues.add("filval1");
//		filterValues.add("filval2");
//		argTypes.add(">=");
		argTypes.add("==");
		
		/*Object Setup*/
		testObj.setTableName("test");
		testObj.setFieldNameList(fieldNames);
		testObj.setFieldValueList(fieldValues);
		testObj.setFilterList(filterNames);
		testObj.setFilterValueList(filterValues);
		testObj.setArgumentTypes(argTypes);
		
		/*Function*/
		String actual = sql.makeSQLStatement(testObj);
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
//	@Test
//	public void makeSQLStatementTestUsingDeleteAndLike() {
//		/*Local Variables*/
//		List<String> fieldNames = new ArrayList<>(),
//				fieldValues = new ArrayList<>(),
//				filterNames = new ArrayList<>(),
//				filterValues = new ArrayList<>(),
//				argTypes = new ArrayList<>();
//		String expected = "DELETE FROM test LIKE '%@revature.net';";
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
//		/*Function*/
//		String actual = sql.makeSQLStatement(testObj);
//		
//		/*Test*/
//		Assertions.assertEquals(expected, actual);
//	}
	
	
	
}

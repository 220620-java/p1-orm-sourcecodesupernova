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
public class ORMInsertTest {
	/*Class Variables*/
	Query testObj = new Query();
	ORMInsert sql = new ORMInsert();
	
	@BeforeEach
	public void beforeEach() {
		testObj = new Query();
		sql = new ORMInsert();
	}
	
	@Test
	public void makeSQLStatementTest() {
		/*Local Variables*/
		List<String> fieldNames = new ArrayList<>(),
				fieldValues = new ArrayList<>();
		String expected = "INSERT INTO test(field1, field2) VALUES(value1, value2);",
				actual = "";
		
		/*Variable Setup*/
		fieldNames.add("field1");
		fieldNames.add("field2");
		fieldValues.add("value1");
		fieldValues.add("value2");
		
		/*Object Setup*/
		testObj.setTableName("test");
		testObj.setFieldNameList(fieldNames);
		testObj.setFieldValueList(fieldValues);
		
		/*Function*/
		actual = sql.makeSQLStatement(testObj);
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
}

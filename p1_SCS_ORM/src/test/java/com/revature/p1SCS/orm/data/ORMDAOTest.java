package com.revature.p1SCS.orm.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import com.revature.p1SCS.orm.models.Query;
import com.revature.p1SCS.orm.services.ORMDelete;
import com.revature.p1SCS.orm.services.ORMInsert;
import com.revature.p1SCS.orm.services.ORMSelect;
import com.revature.p1SCS.orm.services.ORMUpdate;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ORMDAOTest {
	@InjectMocks
	private ORMDAO sql = new ORMDAO();

	@Mock
	private ORMDelete ormDel = new ORMDelete();
	
	@Mock
	private ORMInsert ormIns = new ORMInsert();
	
	@Mock
	private ORMSelect ormSel = new ORMSelect();
	
	@Mock
	private ORMUpdate ormUpd = new ORMUpdate();
	
	/*Testing delete function of ORMDAO*/
	@Test
	@Order(1)
	public void deleteORMDAOTest() {
		/*Local Variables*/
		Boolean expected = true,
				actual = false;
		
		/*Mocks*/
		Mockito.when(ormDel.makeSQLStatement(null)).thenReturn("DELETE FROM tbl_users WHERE useremail LIKE '%@revature.net';");
		
		/*Function*/
		if(sql.delete(null) != -1) {
			actual = true;
		}
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	/*Testing insert function of ORMDAO*/
	@Test
	@Order(2)
	public void insertORMDAOTest() {
		/*Local Variables*/
		Boolean expected = true,
				actual = false;
		
		/*Mocks*/
		Mockito.when(ormIns.makeSQLStatement(null)).thenReturn("INSERT INTO tbl_users(userid, useremail, userpassword, userfname, userminit, userlname) "
				+ "VALUES(default, 'test@revature.net', 'password', 'test', 'a', 'user');");
		
		/*Function*/
		if(sql.insert(null) != -1) {
			actual = true;
		}
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	/*Testing insert function again. This is just to add records to be changed by update later*/
	@Test
	@Order(3)
	public void insertORMDAOTestv2() {
		/*Local Variables*/
		Boolean expected = true,
				actual = false;
		
		/*Mocks*/
		Mockito.when(ormIns.makeSQLStatement(null)).thenReturn("INSERT INTO tbl_users(userid, useremail, userpassword, userfname, userminit, userlname) "
				+ "VALUES(default, 'unchanged@revature.net', 'password', 'test', 'a', 'user');");
		
		/*Function*/
		if(sql.insert(null) != -1) {
			actual = true;
		}
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	/*Testing select function of ORMDAO*/
	@Test
	@Order(5)
	public void selectORMDAOTest() {
		/*Local Variables*/
		List<Query> result = null;
		Boolean expected = true,
				actual = false;
		
		/*Mocks*/
		Mockito.when(ormSel.makeSQLStatement(null)).thenReturn("SELECT * FROM tbl_users;");
		
		/*Function*/
		result = sql.select(null);
		if (result != null) {
			actual = true;
			result.stream().forEach(x -> {
				try {
					Field values = x.getClass().getDeclaredField("fieldValueList");
					Field names = x.getClass().getDeclaredField("fieldNameList");
					values.setAccessible(true);
					names.setAccessible(true);
					System.out.println(names.get(x));
					System.out.println(values.get(x));
				}
				catch (Exception e) {
					//TODO Exception Logger
				}
				});
		}
		
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
	
	/*Testing update function of ORMDAO*/
	@Test
	@Order(4)
	public void updateORMDAOTest() {
		/*Local Variables*/
		Boolean expected = true,
				actual = false;
		
		/*Mocks*/
		Mockito.when(ormUpd.makeSQLStatement(null)).thenReturn("UPDATE tbl_users SET userpassword = 'security#100' "
				+ "WHERE useremail = 'unchanged@revature.net'");
		
		/*Function*/
		if(sql.update(null) != -1) {
			actual = true;
		}
		/*Test*/
		Assertions.assertEquals(expected, actual);
	}
}

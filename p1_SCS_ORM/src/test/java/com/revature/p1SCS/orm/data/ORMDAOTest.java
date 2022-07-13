package com.revature.p1SCS.orm.data;

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
import com.revature.p1SCS.orm.services.ORMDelete;
import com.revature.p1SCS.orm.services.ORMInsert;
import com.revature.p1SCS.orm.services.ORMSelect;
import com.revature.p1SCS.orm.services.ORMUpdate;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
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
	private ORMUpdate ormUpdate = new ORMUpdate();
}

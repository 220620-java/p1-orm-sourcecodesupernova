package com.revature.p1SCS.orm.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.revature.p1SCS.orm.models.Query;
import com.revature.p1SCS.orm.services.ORMDelete;
import com.revature.p1SCS.orm.services.ORMInsert;
import com.revature.p1SCS.orm.services.ORMSelect;
import com.revature.p1SCS.orm.services.ORMUpdate;

import java.util.List;
import com.revature.p1SCS.util.SQLConnect;
import java.util.ArrayList;

public class ORMDAO {
	private SQLConnect sqlConn = SQLConnect.getSQLConnect();
	private ORMDelete ormDel = new ORMDelete();
	private ORMInsert ormIns = new ORMInsert();	
	private ORMSelect ormSel = new ORMSelect();
	private ORMUpdate ormUpd = new ORMUpdate();
	
	/**
	 * Executes a delete SQL statement based on information of 
	 * the obj being passed
	 * 
	 * @param obj Object whose info is being passed to SQL delete statement
	 * @return returns 1 if statement is sucessful, otherwise 0
	 */
	public int delete(Query obj) {
		/*Local Variables*/
		String sql = "";
		int result = -1;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()){
			sql = ormDel.makeSQLStatement(obj);
			PreparedStatement stmt =  conn.prepareStatement(sql);
			result = stmt.executeUpdate();
			stmt.close(); // don't know if this is necessary
		}
		catch (Exception e){
			//Exception Logger
			//e.printStackTrace();
		}
		return result;
	}

	/**
	 * Executes an insert SQL statement based on information
	 * being passed by the obj
	 * 
	 * @param obj Object whose info is being translated to a SQL statement
	 * @return returns 1 if statement is sucessful, otherwise 0
	 */
	public int insert(Query obj) {
		/*Local Variables*/
		String sql = "";
		String[] keys = obj.getKeys();
		int result = -1;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()){
			sql = ormIns.makeSQLStatement(obj);
			PreparedStatement stmt =  conn.prepareStatement(sql, keys);
			result = stmt.executeUpdate();
			//stmt.close();
		}
		catch (Exception e){
			//Exception Logger
			//e.printStackTrace();
		}
		/*Return*/
		return result;
	}
	
	/**
	 * Executes a select SQL statement based on information
	 * being passed by the obj
	 * 
	 * @param obj Object whose info is being fetched by select statement
	 * @return returns List of fields and values from database
	 */
	public List<Query> select(Query obj) {
		/*Local Variables*/
		String sql = "";
		int columns = 0;
		ResultSet result = null;
		ResultSetMetaData resMeta = null;
		List<Query> objList = null;
		List<String> fields = new ArrayList<>();
		List<String> values = new ArrayList<>();
		Query in = null;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()){
			sql = ormSel.makeSQLStatement(obj);
			PreparedStatement stmt =  conn.prepareStatement(sql);
			result = stmt.executeQuery();
			objList = new ArrayList<>();
			
			//Getting field names
			resMeta = result.getMetaData();
			columns = resMeta.getColumnCount();
			for(int i = 1; i <= columns; i++) {
				fields.add(resMeta.getColumnName(i));
			}
			
			//Getting values from query and adding to an object
			while(result.next()) {
				values = new ArrayList<>();
				in = new Query();
				for (int i = 1; i <= columns; i++) {
					values.add(result.getString(i));
				}
				
				in.setFieldNameList(fields);
				in.setFieldValueList(values);
				objList.add(in);
			}
		}
		catch (Exception e){
			//TODO Exception Logger
			//e.printStackTrace();
			objList = new ArrayList<>();
		}
		/*Return*/
		return objList;
	}
	
	/**
	 * Executes update SQL statement based on information
	 * being passed by the obj
	 * 
	 * @param obj Object whose info is being translated to a SQL statement
	 * @return returns 1 if statement is sucessful, otherwise 0
	 */
	public int update(Query obj) {
		/*Local Variables*/
		String sql = "";
		int result = -1;
		
		/*Function*/
		try (Connection conn = sqlConn.getConnection()){
			sql = ormUpd.makeSQLStatement(obj);
			PreparedStatement stmt =  conn.prepareStatement(sql);
			result = stmt.executeUpdate();
			//stmt.close();
		}
		catch (Exception e){
			//Exception Logger
		}
		/*Return*/
		return result;
	}
	
}

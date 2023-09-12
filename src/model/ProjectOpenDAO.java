package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ProjectOpenVO;

public class ProjectOpenDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@192.168.0.90:1521:air3"; //ip:post:DB��
	private String id="tokki";
	private String pass="so";
	PreparedStatement ps = null;
	Statement stmt = null;
	ArrayList list = null;
	ProjectOpenVO vo = null;
	
	Connection con;
	
	public ProjectOpenDAO() throws Exception{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pass);
	}
	
	public ArrayList ProjectSearch(int sel, String test)throws Exception {
		String[] selCol = {"p.pro_name", "t.team_name"};
		String sql = "select p.pro_no proNo, p.pro_name proName, p.pro_start proStart, p.pro_end proEnd, t.team_name teamName, p.pro_state proState"
				+ " from project p, business_management bm, team t"
				+ " where t.team_no=bm.team_no"
				+ " and p.pro_no=bm.pro_no"
				+ " and " +selCol[sel] + " like '%" + test + "%' ";
		
		stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		list = new ArrayList();
		
		while (rs.next()) { 
			ArrayList temp = new ArrayList();
			temp.add(rs.getInt("proNo"));		// 
			temp.add(rs.getString("proName"));		// 
			temp.add(rs.getString("proStart"));		// 
			temp.add(rs.getString("proEnd"));		//
			temp.add(rs.getString("teamName"));		// 
			temp.add(rs.getString("proState"));		// 
			
			list.add(temp);
			
		}
		return list;
	}
	
	
	
	
}

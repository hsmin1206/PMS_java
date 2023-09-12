package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.ClientVO;

public class ClientDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@192.168.0.90:1521:air3";
	private String id = "tokki";
	private String pass = "so";
	PreparedStatement ps = null;
	Statement stmt = null;
	ClientVO vo;
	ArrayList list;

	public ClientDAO() throws Exception {
		// TODO Auto-generated constructor stub
		connectDB();
	}
	void connectDB() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(url,id,pass);
	}

	public ArrayList searcProject(String name) throws Exception {
		String sql = "select p.pa_company, s.sm_date, s.sm_content, s.sm_count, s.sm_amount "
				+ "from partner p, supply_management s, business_management b, project pro "
				+ "where p.part_no = s.part_no and b.bm_no = s.bm_no and pro.pro_no = b.pro_no and "
				+ "pro.pro_name like '%" + name + "%'";
		
		list = new ArrayList();
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("pa_company"));
			temp.add(rs.getString("sm_date"));
			temp.add(rs.getString("sm_content"));
			temp.add(rs.getInt("sm_count"));
			temp.add(rs.getInt("sm_amount"));
			list.add(temp);
		}
		rs.close();
		stmt.close();
		return list;
	}
}
		
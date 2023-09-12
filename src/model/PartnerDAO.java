package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.PartnerVO;

public class PartnerDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String jdbc_url = "jdbc:oracle:thin:@192.168.0.90:1521:air3"; // ip:post:DB占쏙옙
	private String db_id = "tokki";
	private String db_pwd = "so";
	private String sql;
	PartnerVO vo;
	ArrayList list = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	private String pd_name;

	// constructor(占쏙옙占쏙옙占쏙옙)
	// 1. 占쏙옙占쏙옙譴占쏙옙占� 占쌨모리울옙 占싸듸옙
	// 2. Connection 占쏙옙占쏙옙占쏙옙
	public PartnerDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
	}

	public void partnerInsert(PartnerVO vo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("gg");
		String sql = "insert into partner values(s_partner_part_no.nextval,?,?,?,?,?,(select pd_no from partner_detail_field where pd_name=?))";
		
		ps = conn.prepareStatement(sql);
//		System.out.println(vo.getPa_company() + vo.getPa_dam() + vo.getPa_tal() + vo.getPa_email() + vo.getPd_name()
//				+ vo.getPa_loc()); consol占쏙옙 占쌩댐옙 창

		
		ps.setString(1, vo.getPa_company());
		ps.setString(2, vo.getPa_dam());
		ps.setString(3, vo.getPa_email());
		ps.setString(4, vo.getPa_tal());
		ps.setString(5, vo.getPa_loc());
		ps.setString(6, vo.getPd_name());

		ps.executeUpdate();
		ps.close();

	}

	public ArrayList partnerCheck () throws Exception {
		String sql = "select p.pa_company, p.pa_dam, p.pa_email, p.pa_tel, pd.pd_name, p.pa_loc " 
				+ "from partner p, partner_detail_field pd "
				+ "where p.pd_no = pd.pd_no "
				+ "order by p.part_no desc ";


		stmt = conn.createStatement();
//		ps.setString(1, category);
		list = new ArrayList();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("pa_company"));
			temp.add(rs.getString("pa_dam"));
			temp.add(rs.getString("pa_email"));
			temp.add(rs.getString("pa_tel"));
			temp.add(rs.getString("pd_name"));
			temp.add(rs.getString("pa_loc"));

			list.add(temp);

		}
		return list;

	}
	
	public ArrayList partnerCheck1 (String pd_name) throws Exception {
		String sql = "select p.pa_company, p.pa_dam, p.pa_tel, p.pa_email, pd.pd_name, p.pa_loc "
				+ "from partner p, partner_detail_field pd "
				+ "where p.pd_no = pd.pd_no and pd.pd_name = ?";

		ps = conn.prepareStatement(sql);
	    ps.setString(1, pd_name);
	    
	    list = new ArrayList();
	    ResultSet rs = ps.executeQuery();
	    

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("pa_company"));
			temp.add(rs.getString("pa_dam"));
			temp.add(rs.getString("pa_tel"));
			temp.add(rs.getString("pa_email"));
			temp.add(rs.getString("pd_name"));
			temp.add(rs.getString("pa_loc"));
		
			list.add(temp);
		}
		rs.close();
		ps.close();
		return list;
		
	}

}
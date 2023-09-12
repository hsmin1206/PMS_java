package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.ProjectPutupVO;

public class ProjectPutuDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@192.168.0.90:1521:air3"; //
	private String id="tokki";
	private String pass="so";
	PreparedStatement ps = null;
	PreparedStatement ps2 = null;
	Statement stmt = null;
	ProjectPutupVO vo = null;
	
	Connection con;
	
	public ProjectPutuDAO() throws Exception{
		Class.forName(driver);
		conn = DriverManager.getConnection(url, id, pass);
	}

	public int regist(ProjectPutupVO vo) throws Exception{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO project(pro_no, pro_name, pro_state, pro_budget, pro_start, pro_end, bdf_no)"
				+ " VALUES(s_project_pro_no.nextval,?,?,?,?,?,(select bdf_no from business_detail_field where bdf_name = ?))";
		
		String sql2 ="INSERT INTO business_management(bm_no, pro_no, team_no)"
				+ " VALUES(s_bm_no.nextval, (select pro_no from project where pro_name = ?), (select team_no from team where team_name= ?))";
		ps = conn.prepareStatement(sql);
		ps2 = conn.prepareStatement(sql2);
		
		ps.setString(1, vo.getName());
		ps.setString(2, "N");
		ps.setString(3, vo.getBudget());
		ps.setString(4, vo.getGo());
		ps.setString(5, vo.getEnd());
		ps.setString(6, vo.getDetails());
		
		ps2.setString(1, vo.getName());
		ps2.setString(2, vo.getTim());
		System.out.println(vo.getTim() + "  : ");

		int cnt = ps.executeUpdate();
		cnt += ps2.executeUpdate();
		ps.close();
		return cnt;
	}

	public int modify(ProjectPutupVO vo, int vNum) throws Exception{
		// TODO Auto-generated method stub
		// project 
		String sql = "update project set pro_name=?,  pro_state=?, pro_budget=?, pro_start=?, pro_end=?,"
				+ " bdf_no=(select bdf_no from business_detail_field where bdf_name = ?)"
				+ " where pro_no= ?";
		
		// business_management 
		String sql2 = "update business_management set team_no = (select team_no from team where team_name = ?)"
				+ " where pro_no = ?";
		ps = conn.prepareStatement(sql);
		ps2 = conn.prepareStatement(sql2);
		
		ps.setString(1, vo.getName());
		ps.setString(2, "Y");
		ps.setString(3, vo.getBudget());
		ps.setString(4, vo.getGo());
		ps.setString(5, vo.getEnd());
		ps.setString(6, vo.getDetails());
		ps.setInt(7, vNum);

		ps2.setString(1, vo.getTim());
		ps2.setInt(2, vNum);
		System.out.println(vo.getTim());
		
		int cnt = ps.executeUpdate();
		cnt += ps2.executeUpdate();
		ps.close();
		ps2.close();
		return cnt;
	}

	public ProjectPutupVO modifyinfo(int vNum) throws Exception {
		String sql ="select p.pro_name, p.pro_budget, p.pro_start, p.pro_end, (select bf.bdf_name from business_detail_field bf where bf.bdf_no=p.bdf_no),"
				+ " (select team_name from business_management bm, team t"
				+ " where t.team_no=bm.team_no"
				+ " and bm.pro_no = " + vNum+") "
				+ " from project p "
				+ " where pro_no=" + vNum;

		ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		ProjectPutupVO vo = new ProjectPutupVO();
		
		if (rs.next()) {
			vo.setName(rs.getString(1));
			vo.setBudget(rs.getString(2)); 
			vo.setGo(rs.getString(3)); 
			vo.setEnd(rs.getString(4)); 
			vo.setDetails(rs.getString(5)); 
			vo.setTim(rs.getString(6)); 

		}
		
		rs.close();
		ps.close();
		return vo;
	}

}

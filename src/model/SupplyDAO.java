package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;

import model.rec.SupplyVO;

public class SupplyDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String jdbc_url = "jdbc:oracle:thin:@192.168.0.90:1521:air3"; // 
	private String db_id = "tokki";
	private String db_pwd = "so";
	private String sql;
	ArrayList list = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	private String pa_company;
	SupplyVO vo = null;

	// constructor(
	// 1. 
	public SupplyDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
	}
	
	public SupplyVO companyname(String pa_company) throws Exception {
		// TODO Auto-generated method stub
		SupplyVO vo = new SupplyVO();
		String pa_dam;
		String pa_tel;
		String pa_email;
		String sql = "select pa_company, pa_dam, pa_tel, pa_email from partner where pa_company=? ";
		ps = conn.prepareStatement(sql);
		ps.setString(1, pa_company);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			vo.setPa_company(rs.getString("pa_company"));
			vo.setPa_dam(rs.getString("pa_dam"));
			vo.setPa_tel(rs.getString("pa_tel"));
			vo.setPa_email(rs.getString("pa_email"));
		}
		rs.close();
		ps.close();
		return vo;
	}
	

	
	public SupplyVO findProjectName(int bmno) throws Exception{
		SupplyVO vo = new SupplyVO();
		String sql = "select p.pro_name from project p, business_management b "
	             + "where b.bm_no = ? and p.pro_no = b.pro_no";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, bmno);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			vo.setPro_name(rs.getString("pro_name"));
		}
		rs.close();
		ps.close();
		return vo;		
	}
	
	public SupplyVO findBmno(int bmno) throws Exception{
		SupplyVO vo = new SupplyVO();
		String sql = "select bm_no from business_Management where bm_no = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, bmno);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			vo.setBm_no(rs.getInt("bm_no"));
		}
		rs.close();
		ps.close();
		return vo;		
	}
	
	public void insertSupply(String sm_contents, int sm_count, int sm_amount, String sm_date, int bm_no, int part_no) throws Exception {
	    String sql = "insert into supply_management(sm_no, sm_content, sm_count, sm_amount, sm_date, bm_no, part_no) "
	                + "values(s_supply_management_sm_no.nextval, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
        System.out.println(bm_no);
        System.out.println(part_no);
	    ps = conn.prepareStatement(sql);
	    ps.setString(1, sm_contents);
	    ps.setInt(2, sm_count);
	    ps.setInt(3, sm_amount);
	    ps.setString(4, sm_date);
	    ps.setInt(5, bm_no);
	    ps.setInt(6, part_no);
	    ps.executeUpdate();
	    ps.close();
	}
		
	   // pro_name
    public int convertProNameToBmNo(String pro_name) throws Exception {
        int bm_no = 0;

        String sql = "SELECT b.bm_no FROM business_Management b, project p WHERE pro_name = ? AND b.pro_no = p.pro_no";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pro_name);
        ResultSet rs = pstmt.executeQuery();

        // 
        if (rs.next()) {
            bm_no = rs.getInt("bm_no");
        }

 
        rs.close();
        pstmt.close();

        return bm_no;
    }


    public int convertPaCompanyToPartNo(String pa_company) throws Exception {
        int part_no = 0;

 
        String sql = "SELECT part_no FROM partner WHERE pa_company = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pa_company);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            part_no = rs.getInt("part_no");
        }

        rs.close();
        pstmt.close();

        return part_no;
    }
	
	
	
	public ArrayList<Integer> getAllBusinessManagementNumbers() throws Exception {
	    ArrayList<Integer> bmNoList = new ArrayList<>();
	    String sql = "SELECT bm_no FROM business_Management";
	    stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(sql);

	    while (rs.next()) {
	        int bmNo = rs.getInt("bm_no");
	        bmNoList.add(bmNo);
	    }
	    
	    rs.close();
	    stmt.close();
	    return bmNoList;
	}
	
	public ArrayList supplyselect(int bm_no) throws Exception {
		String sql = "select p.pa_company, pr.pro_name, o.om_content, o.om_count, o.om_amount, o.om_date, p.pa_dam, p.pa_tel, p.pa_email "
				+ "from partner p, order_management o, business_management b, project pr "
				+ "where b.bm_no = ? and p.part_no = o.part_no and o.bm_no = b.bm_no and b.pro_no = pr.pro_no ";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, bm_no);

		list = new ArrayList();
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("pa_company"));
			temp.add(rs.getString("pro_name"));
			temp.add(rs.getString("om_content"));
			temp.add(rs.getInt("om_count"));
			temp.add(rs.getInt("om_amount"));
			temp.add(rs.getString("om_date"));
			temp.add(rs.getString("pa_dam"));
			temp.add(rs.getString("pa_tel"));
			temp.add(rs.getString("pa_email"));

			list.add(temp);
		}

		rs.close();
		ps.close();
		return list;

	}
	
	public int SupplyInsert(SupplyVO vo, int count) throws Exception {
		String sql = "insert into order_management(pa_company, pro_name, pa_dam, pa_tel, pa_email, bm_no, om_content, om_count, om_amount, om_date, part_no)values(s_partner_part_no.nextval,?,?,?,?,?,sysdate)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getPa_company());
		ps.setString(2, vo.getPro_name());
		ps.setString(3, vo.getPa_dam());
		ps.setString(4, vo.getPa_tel());
		ps.setString(5, vo.getPa_email());
		ps.setString(6, vo.getSm_content());		
		ps.setString(7, vo.getSm_date());
	
		int cnt = 0;
  
		for (int i = 0; i < count; i++) {
			cnt += ps.executeUpdate(); //
		}
		ps.close();
		return cnt;

	}
	public SupplyVO companyname() {
		// TODO Auto-generated method stub
		return null;
	}

	}



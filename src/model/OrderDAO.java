package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.rec.OrderVO;

public class OrderDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String jdbc_url = "jdbc:oracle:thin:@192.168.0.90:1521:air3"; // ip:post:DB占쏙옙
	private String db_id = "tokki";
	private String db_pwd = "so";
	private String sql;
	ArrayList list = null;
	Statement stmt = null;
	PreparedStatement ps = null;
	private String pa_company;
	OrderVO vo = null;

	// constructor(占쏙옙占쏙옙占쏙옙)
	// 1. 占쏙옙占쏙옙譴占쏙옙占� 占쌨모리울옙 占싸듸옙
	// 2. Connection 占쏙옙占쏙옙占쏙옙
	public OrderDAO() throws Exception {
		Class.forName(driver);
		conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
	}
	//占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙
	public OrderVO companyname(String pa_company) throws Exception {
		// TODO Auto-generated method stub
		OrderVO vo = new OrderVO();
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
	

	
	public OrderVO findProjectName(int bmno) throws Exception{
		OrderVO vo = new OrderVO();
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
	
	public OrderVO findBmno(int bmno) throws Exception{
		OrderVO vo = new OrderVO();
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
	
	public void insertOrder(String om_contents, int om_count, int om_amount, String om_date, int bm_no, int part_no) throws Exception {
	    String sql = "insert into order_management(om_no, om_content, om_count, om_amount, om_date, bm_no, part_no) "
	                + "values(s_order_management_om_no.nextval, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
        System.out.println(bm_no);
        System.out.println(part_no);
	    ps = conn.prepareStatement(sql);
	    ps.setString(1, om_contents);
	    ps.setInt(2, om_count);
	    ps.setInt(3, om_amount);
	    ps.setString(4, om_date);
	    ps.setInt(5, bm_no);
	    ps.setInt(6, part_no);
	    ps.executeUpdate();
	    ps.close();
	}
		
	   // pro_name�쓣 湲곕컲�쑝濡� bm_no瑜� 蹂��솚�븯�뒗 硫붿꽌�뱶
    public int convertProNameToBmNo(String pro_name) throws Exception {
        int bm_no = 0;

        // �뜲�씠�꽣踰좎씠�뒪 �뿰寃� 諛� 荑쇰━ �떎�뻾
        String sql = "SELECT b.bm_no FROM business_Management b, project p WHERE pro_name = ? AND b.pro_no = p.pro_no";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pro_name);
        ResultSet rs = pstmt.executeQuery();

        // 寃곌낵 泥섎━
        if (rs.next()) {
            bm_no = rs.getInt("bm_no");
        }

        // �옄�썝 �빐�젣
        rs.close();
        pstmt.close();

        return bm_no;
    }

    // pa_company瑜� 湲곕컲�쑝濡� part_no瑜� 蹂��솚�븯�뒗 硫붿꽌�뱶
    public int convertPaCompanyToPartNo(String pa_company) throws Exception {
        int part_no = 0;

        // �뜲�씠�꽣踰좎씠�뒪 �뿰寃� 諛� 荑쇰━ �떎�뻾
        String sql = "SELECT part_no FROM partner WHERE pa_company = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pa_company);
        ResultSet rs = pstmt.executeQuery();

        // 寃곌낵 泥섎━
        if (rs.next()) {
            part_no = rs.getInt("part_no");
        }

        // �옄�썝 �빐�젣
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
	
}

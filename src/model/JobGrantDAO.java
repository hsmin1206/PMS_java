package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.rec.JobGrantVO;

public class JobGrantDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String jdbc_url="jdbc:oracle:thin:@192.168.0.90:1521:air3"; //ip:post:DB占쏙옙
	private String db_id="tokki";
	private String db_pwd="so";
	JobGrantVO jobGrantVO;
	
	

	
	

	public JobGrantVO jobGrant() throws Exception{ 
		System.out.println("jobGrant 占쌨소듸옙");
		
		
//		int found = jgNum; // jgNum 占쏙옙占쏙옙 found 占쏙옙占쏙옙占쏙옙 占쌀댐옙
//        	String sql = "SELECT jg_no, jg_grant FROM JOB_GRANT WHERE jg_no = ?";
		String sql = "SELECT jg_no, jg_grant FROM JOB_GRANT WHERE jg_no = ?";
        	jobGrantVO = new JobGrantVO();
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
            
//            SELECT jg_no, jg_grant 
//            FROM JOB_GRANT
//            WHERE jg_no = 1;
            
            String jg_no = "1";
           
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, jg_no);
//            jobGrantVO.getJg_no();
//            System.out.println("DAO jgNum(jgNo) : " + jobGrantVO.getJg_no());
            rs = pstmt.executeQuery();
            
            System.out.println("----------------------------------------------");
            
            
            if(rs.next()) {
            	System.out.println("rs.next 占쏙옙載э옙占쏙옙占� 확占쏙옙");

            	jobGrantVO.setJg_no(rs.getString("jg_no"));
            	jobGrantVO.setJg_grant(rs.getString("jg_grant"));
            	
//            	rs.getString("jg_no");
//            	rs.getString("jg_grant");
            	
            	String aaa = jobGrantVO.getJg_no();
            	System.out.println("占쏙옙 占쏙옙占쏙옙 aaa : " + aaa);
            	String bbb = jobGrantVO.getJg_grant();
            	System.out.println("占쏙옙 占쏙옙占쏙옙 bbb : " + bbb);
            	
                System.out.printf("占쏙옙占쌨깍옙占쏙옙占쌘듸옙 : %s \n", rs.getString("jg_no"));
		        System.out.printf("占쏙옙占쌨깍옙占쏙옙 : %s \n", rs.getString("jg_grant"));
		        System.out.println();
            }
            
            rs.close();
            pstmt.close();
            return jobGrantVO;
            
//            while (rs.next()) {;
//            
//            	found = rs.getInt("jg_no");
//            	
////                int empNo = rs.getInt("emp_no");
////                String empName = rs.getString("emp_name");
////                String empJob = rs.getString("emp_job");
////                String empPw = rs.getString("emp_pw");
////                 rs.getInt("jg_no");
//                String jgGrant = rs.getString("jg_grant");
//            	
//                System.out.printf("占쏙옙占쌨깍옙占쏙옙占쌘듸옙 : %d \n", found);
//		        System.out.printf("占쏙옙占쌨깍옙占쏙옙 : %s \n", jgGrant);
//		        System.out.println();
//		        
//            }
        }

}

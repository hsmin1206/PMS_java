package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.rec.AdministratorVO;
import model.rec.AdministratorVOList;

public class AdministratorDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String jdbc_url="jdbc:oracle:thin:@192.168.0.90:1521:air3"; //ip:post:DB紐�
	private String db_id="tokki";
	private String db_pwd="so";
//	EmpVO empVO = null;
	AdministratorVO administratorVO = null;
	AdministratorVOList administratorVOList = null;
	



	
	  public AdministratorVOList SelectEmpCombo() {
		  administratorVOList = new AdministratorVOList();
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
	            String sql = "SELECT emp_no, emp_name, emp_job, emp_pw, jg_no, d_no FROM emp";
	            
	            pstmt = conn.prepareStatement(sql);
//	            pstmt.setInt(1, emp_no);
//	            pstmt.setString(1, empId);
	            
	            rs = pstmt.executeQuery();
	            System.out.println("SelectEmpCombo------------------------------------------");
	            while (rs.next()) {
	            	// 湲곗〈 肄붾뱶�뿉�꽌 emp_no瑜� 由ъ뒪�듃�뿉 異붽��븯�뒗 遺�遺꾩쓣 �닔�젙
	            	administratorVOList.getEmp_no().add(rs.getInt("emp_no"));
	            	
	            	administratorVOList.getJg_no().add(rs.getInt("jg_no"));
	                
			        System.out.println();
			        
	            }
	        }
	        catch (ClassNotFoundException e) {
	            System.out.println("db." + e.getMessage());
	            e.printStackTrace();
	        }
	        catch (SQLException se) {
	            System.out.println(se.getMessage());
	            se.printStackTrace();
	        }
	        
	        finally {
	        		try {
	        			if(rs != null) {
	        				rs.close();
	        			}
	        		} catch (SQLException e) {
	        			System.out.println("RESULTSET ERROR");
	        			e.printStackTrace();
	        		}
	        		try {
	        			if(pstmt != null) {
	        				pstmt.close();
	        			}
					} catch (Exception e) {
						System.out.println("PREPAREDSTATEMENT ERROR");
						e.printStackTrace();
					}
	        		try {
	        			if(conn != null) {
	        				conn.close();
	        			}
	        		}
	        		catch (Exception e) {
	        			System.out.println("CONNECTION ERROR");
	        			e.printStackTrace();
	        		}
	        }
		  return administratorVOList;
	}
	  
	  public AdministratorVO SelectEmp(String empId) {
		  administratorVO = new AdministratorVO();
	        try {
	            Class.forName("oracle.jdbc.driver.OracleDriver");
	            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
	            String sql = "SELECT emp_no, emp_name, emp_job, emp_pw, jg_no, d_no FROM emp where emp_no = ?";
	            
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, empId);
	            
	            rs = pstmt.executeQuery();
	            System.out.println("SelectEmp------------------------------------------");
	            while (rs.next()) {
	            	// 湲곗〈 肄붾뱶�뿉�꽌 emp_no瑜� 由ъ뒪�듃�뿉 異붽��븯�뒗 遺�遺꾩쓣 �닔�젙
	            	administratorVO.setEmp_no(rs.getInt("emp_no"));
	            	
	            	
//            	setEmp_no.(rs.getInt("emp_no")); //�궡媛� 蹂묒떊媛숈씠 肄붾뵫 �빐�꽌 �븞�릱�뜕 肄붾뱶
	            	
	            	administratorVO.setEmp_name(rs.getString("emp_name"));
	            	administratorVO.setEmp_job(rs.getString("emp_job"));
	            	administratorVO.setEmp_pw(rs.getString("emp_pw"));
	            	administratorVO.setJg_no(rs.getInt("jg_no"));
	            	administratorVO.setD_no(rs.getInt("d_no"));
	                
	            	
	                System.out.printf("�궗�썝踰덊샇 : %d \n", rs.getInt("emp_no"));
			        System.out.printf("�씠    由� : %s \n", rs.getString("emp_name"));
			        System.out.printf("吏�	梨�: %s \n", rs.getString("emp_job"));
			        System.out.printf("鍮꾨�踰덊샇 : %s \n", rs.getString("emp_pw"));
			        System.out.printf("吏곴툒沅뚰븳肄붾뱶 : %d \n", rs.getInt("jg_no"));
			        System.out.printf("遺��꽌踰덊샇 : %d \n", rs.getInt("d_no"));
			        System.out.println();
			        
	            }
	        }
	        catch (ClassNotFoundException e) {
	            System.out.println("오류." + e.getMessage());
	            e.printStackTrace();
	        }
	        catch (SQLException se) {
	            System.out.println(se.getMessage());
	            se.printStackTrace();
	        }
	        
	        finally {
	        		try {
	        			if(rs != null) {
	        				rs.close();
	        			}
	        		} catch (SQLException e) {
	        			System.out.println("RESULTSET ERROR");
	        			e.printStackTrace();
	        		}
	        		try {
	        			if(pstmt != null) {
	        				pstmt.close();
	        			}
					} catch (Exception e) {
						System.out.println("PREPAREDSTATEMENT ERROR");
						e.printStackTrace();
					}
	        		try {
	        			if(conn != null) {
	        				conn.close();
	        			}
	        		}
	        		catch (Exception e) {
	        			System.out.println("CONNECTION ERROR");
	        			e.printStackTrace();
	        		}
	        }
		  return administratorVO;
	}
	
	   //吏곴툒沅뚰븳 蹂�寃�
	   public int EmpGrantModify(int jgNo, String empJob, int empNo) throws Exception {
//	public int EmpGrantModify(AdministratorVO administratorVO) throws Exception {
           System.out.println(jgNo);
           System.out.println(empJob);
           System.out.println(empNo);
		   Class.forName("oracle.jdbc.driver.OracleDriver");
           conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
		   String sql = "update EMP " 
				   + "set jg_no = ?, emp_job = ? where emp_no = ?";
		   pstmt = conn.prepareStatement(sql);
		   System.out.println("吏곴툒沅뚰븳 蹂�寃� 荑쇰━ �깘");
//		   int jgNo = administratorVO.getJg_no();
//		   String empJob = administratorVO.getEmp_job();
//		   String empNo = administratorVO.getEmp_no();
//		   System.out.println("jgNo : " + jgNo);
//		   System.out.println("empJob : " + empJob);
//		   System.out.println("empno : " + empNo);
//		   pstmt.setInt(1, jgNo);
//		   pstmt.setString(2, empJob);
//		   pstmt.setString(3, empNo);
		   pstmt.setInt(1, jgNo);
		   pstmt.setString(2, empJob);
		   pstmt.setInt(3, empNo);
		    
		   int rownum = pstmt.executeUpdate();
		   pstmt.close();
		   return rownum;
	   }

	
	

}

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.rec.EmpVO;

public class EmpDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String jdbc_url="jdbc:oracle:thin:@192.168.0.90:1521:air3"; //ip:post:DB占쏙옙
	private String db_id="tokki";
	private String db_pwd="so";
	EmpVO empVO = null;
	
	public void ListSee(){System.out.println("ListSee 占쏙옙占쏙옙");
		int cnt=1;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
            stmt = conn.createStatement();
            
          
            ResultSet rs = stmt.executeQuery("select emp_no, emp_name, emp_job, emp_pw, jg_no, d_no from emp");
            
            System.out.println("------------------------------------------------------------------------------");
            while (rs.next()) {
            	int empNo = rs.getInt("emp_no");
                String empName = rs.getString("emp_name");
                String empJob = rs.getString("emp_job");
                String empPw = rs.getString("emp_pw");
                int jgNo = rs.getInt("jg_no");
                int dNo = rs.getInt("d_no");
               
               System.out.printf("%d : ",cnt);
               System.out.printf("%d %s  %s %s %d %d \n",empNo, empName, empJob, empPw, jgNo, dNo);
               cnt++;
            }
          
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("占쌔댐옙 클占쏙옙占쏙옙占쏙옙 찾占쏙옙 占쏙옙 占쏙옙占쏙옙占싹댐옙." + cnfe.getMessage());
        }
        catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        
        finally {
            try {
                stmt.close();
            }
            catch (Exception ignored) {
            }
            try {
                conn.close();
            }
            catch (Exception ignored) {
            }
        }
    }
	
	
	public boolean SerchInfo(String id, String password){ 
		boolean found = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
            String query = "SELECT emp_no, emp_name, emp_job, emp_pw, jg_no, d_no FROM emp WHERE emp_no = ? AND emp_pw = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
//            pstmt.setString(3, jgNum);
            
            rs = pstmt.executeQuery();
//            System.out.println("----------------------------------------------");
            while (rs.next()) {
            	found = true;
            	
                int empNo = rs.getInt("emp_no");
                String empName = rs.getString("emp_name");
                String empJob = rs.getString("emp_job");
                String empPw = rs.getString("emp_pw");
                int jgNo = rs.getInt("jg_no");
                int dNo = rs.getInt("d_no");
                
                System.out.println("jgNo : " + jgNo);
            	
                System.out.printf("占쏙옙占쏙옙占싫� : %d \n", empNo);
		        System.out.printf("占쏙옙    占쏙옙 : %s \n", empName);
		        System.out.printf("占쏙옙	책: %s \n", empJob);
		        System.out.printf("占쏙옙橘占싫� : %s \n", empPw);
		        System.out.printf("占쏙옙占쌨깍옙占쏙옙占쌘듸옙 : %d \n", jgNo);
		        System.out.printf("占싸쇽옙占쏙옙호 : %d \n", dNo);
		        System.out.println();
		        
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("占쌔댐옙 클占쏙옙占쏙옙占쏙옙 찾占쏙옙 占쏙옙 占쏙옙占쏙옙占싹댐옙." + e.getMessage());
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
        return found;
    }
	
	
	public EmpVO SerchInfo2(String id, String password){
		empVO = new EmpVO();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
            String query = "SELECT emp_no, emp_name, emp_job, emp_pw, jg_no, d_no FROM emp WHERE emp_no = ? AND emp_pw = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
//            pstmt.setInt(2, );
            
            rs = pstmt.executeQuery();
            System.out.println("2----------------------------------------------2");
            if (rs.next()) {
            	
                empVO.setEmp_no(rs.getInt("emp_no"));
                empVO.setEmp_name(rs.getString("emp_name"));
                empVO.setEmp_job(rs.getString("emp_job"));
                empVO.setEmp_pw(rs.getString("emp_pw"));
                empVO.setJg_no(rs.getInt("jg_no"));
                empVO.setD_no(rs.getInt("d_no"));
                
            	
                System.out.printf("占쏙옙占쏙옙占싫� : %d \n", rs.getInt("emp_no"));
		        System.out.printf("占쏙옙    占쏙옙 : %s \n", rs.getString("emp_name"));
		        System.out.printf("占쏙옙	책: %s \n", rs.getString("emp_job"));
		        System.out.printf("占쏙옙橘占싫� : %s \n", rs.getString("emp_pw"));
		        System.out.printf("占쏙옙占쌨깍옙占쏙옙占쌘듸옙 : %d \n", rs.getInt("jg_no"));
		        System.out.printf("占싸쇽옙占쏙옙호 : %d \n", rs.getInt("d_no"));
		        System.out.println();
		        
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("占쌔댐옙 클占쏙옙占쏙옙占쏙옙 찾占쏙옙 占쏙옙 占쏙옙占쏙옙占싹댐옙." + e.getMessage());
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
        return empVO;
    }
	
//	  public EmpVO SelectEmp() {
//			empVO = new EmpVO();
//	        try {
//	            Class.forName("oracle.jdbc.driver.OracleDriver");
//	            conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
//	            String sql = "SELECT emp_no, emp_name, emp_job, emp_pw, jg_no, d_no FROM emp";
//	            
//	            pstmt = conn.prepareStatement(sql);
////	            pstmt.setString(1, id);
//	            
//	            rs = pstmt.executeQuery();
//	            System.out.println("selectEmp------------------------------------------");
//	            while (rs.next()) {
//	            	
//	                empVO.setEmp_no(rs.getInt("emp_no"));
////	                empVO.setEmp_name(rs.getString("emp_name"));
////	                empVO.setEmp_job(rs.getString("emp_job"));
////	                empVO.setEmp_pw(rs.getString("emp_pw"));
////	                empVO.setJg_no(rs.getInt("jg_no"));
////	                empVO.setD_no(rs.getInt("d_no"));
////	                empVO.setEmp_noL(rs.getInt("emp_no"));
//	                
//	            	
//	                System.out.printf("占쏙옙占쏙옙占싫� : %d \n", rs.getInt("emp_no"));
////			        System.out.printf("占쏙옙    占쏙옙 : %s \n", rs.getString("emp_name"));
////			        System.out.printf("占쏙옙	책: %s \n", rs.getString("emp_job"));
////			        System.out.printf("占쏙옙橘占싫� : %s \n", rs.getString("emp_pw"));
////			        System.out.printf("占쏙옙占쌨깍옙占쏙옙占쌘듸옙 : %d \n", rs.getInt("jg_no"));
////			        System.out.printf("占싸쇽옙占쏙옙호 : %d \n", rs.getInt("d_no"));
////	                System.out.println("占쏙옙占쏙옙占싫� : " + empVO.getEmp_noL());
//			        System.out.println();
//			        
//	            }
//	        }
//	        catch (ClassNotFoundException e) {
//	            System.out.println("占쌔댐옙 클占쏙옙占쏙옙占쏙옙 찾占쏙옙 占쏙옙 占쏙옙占쏙옙占싹댐옙." + e.getMessage());
//	            e.printStackTrace();
//	        }
//	        catch (SQLException se) {
//	            System.out.println(se.getMessage());
//	            se.printStackTrace();
//	        }
//	        
//	        finally {
//	        		try {
//	        			if(rs != null) {
//	        				rs.close();
//	        			}
//	        		} catch (SQLException e) {
//	        			System.out.println("RESULTSET ERROR");
//	        			e.printStackTrace();
//	        		}
//	        		try {
//	        			if(pstmt != null) {
//	        				pstmt.close();
//	        			}
//					} catch (Exception e) {
//						System.out.println("PREPAREDSTATEMENT ERROR");
//						e.printStackTrace();
//					}
//	        		try {
//	        			if(conn != null) {
//	        				conn.close();
//	        			}
//	        		}
//	        		catch (Exception e) {
//	        			System.out.println("CONNECTION ERROR");
//	        			e.printStackTrace();
//	        		}
//	        }
//		  return empVO;
//	}
//	
//	   //占쏙옙占쌨깍옙占쏙옙 占쏙옙占쏙옙
//	   public int EmpGrantModify(EmpVO empVO) throws Exception {
//           Class.forName("oracle.jdbc.driver.OracleDriver");
//           conn = DriverManager.getConnection(jdbc_url, db_id, db_pwd);
//		   String sql = "update EMP " 
//				   + "set jg_no = ?, emp_job = ? where emp_no = ?";
//		   pstmt = conn.prepareStatement(sql);
//		   
//		   pstmt.setInt(1, empVO.getJg_no());
//		   pstmt.setString(2, empVO.getEmp_job());
//		   pstmt.setInt(3, empVO.getD_no());
//		    
//		   int rownum = pstmt.executeUpdate();
//		   pstmt.close();
//		   return rownum;
//	   }

	
	

}

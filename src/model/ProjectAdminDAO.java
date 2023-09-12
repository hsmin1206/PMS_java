package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import model.rec.ProjectAdminVO;


public class ProjectAdminDAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url="jdbc:oracle:thin:@192.168.0.90:1521:air3";
	private String id = "tokki";
	private String pass="so";
	PreparedStatement ps = null;
	Statement stmt = null;
	ArrayList list = null;
	ProjectAdminVO vo = null;
	

	
	   public ProjectAdminDAO() throws Exception
	   {          connectDB();
	   
	   vo = new ProjectAdminVO();
	   
	   }
	   
	   Connection  con;
	   
	void connectDB() throws Exception {
		// TODO Auto-generated method stub
		Class.forName(driver);
		conn=DriverManager.getConnection(url, id, pass);
	}
	
	   public ArrayList<ArrayList<String>> searchTeam(String team_name) throws Exception {
	  
		   String sql = "select t.team_name, p.pro_name, r.process_name, r.end_date "
		           + "from road_map r, project p , business_management b, team t "
		           + "where r.pro_no = p. pro_no and p.pro_no = b.pro_no and b.team_no = 			t.team_no "
		           + "and t.team_name = ? ";
		ps = conn.prepareStatement(sql);
		ps.setString(1, team_name);
//  	    ps.executeUpdate();
		
  	    
  	    ResultSet rs = ps.executeQuery();
  	    ArrayList teamInfo = new ArrayList();
  	    
  	    while (rs.next()) {
  	        ArrayList<String> temp = new ArrayList<>();
  	        temp.add(rs.getString("team_name"));
  	        temp.add(rs.getString("pro_name"));
  	        temp.add(rs.getString("process_name"));
  	        LocalDate endDate = rs.getDate("end_date").toLocalDate();
  	        LocalDate currentDate = LocalDate.now();
  	        long daysDifference = ChronoUnit.DAYS.between(currentDate, endDate);
  	        temp.add(String.valueOf(daysDifference));
  	        teamInfo.add(temp);
  	    }
  	    

  	    rs.close();
  	    ps.close();
  	    return teamInfo;
  	    
	   }
}



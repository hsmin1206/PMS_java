package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.rec.MemoVO;
import model.rec.ProjectVO;

public class ProjectDAO {
   private Connection conn = null;
   private String driver = "oracle.jdbc.driver.OracleDriver";
   private String url="jdbc:oracle:thin:@192.168.0.90:1521:air3"; //ip:post:DB명
   private String id="tokki";
   private String pass="so";
   
   PreparedStatement ps = null;
   Statement stmt = null;
   ResultSet rs = null;
   ArrayList list = null;
   ProjectVO vo = null;
   MemoVO mvo = null;
   Connection con;
   
   public ProjectDAO() throws Exception{
      Class.forName(driver);
      conn = DriverManager.getConnection(url, id, pass);
   }
   
   public ProjectVO ProjectOut(int vNum) throws Exception{
      String sql = "select p.pro_name proName, p.pro_budget proBudget, p.pro_start proStart, p.pro_end proEnd, p.pro_state proState, t.team_name teamName, bd.bdf_name bdfName"
            + " from project p, business_detail_field bd, business_management bm, team t"
            + " where bd.bdf_no = p.bdf_no"
            + " and p.pro_no = bm.pro_no"
            + " and t.team_no = bm.team_no"
            + " and p.pro_no = " + vNum;
      ps = conn.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      ProjectVO vo = new ProjectVO();
      
      if(rs.next()) {
         vo.setProName(rs.getString("proName"));
         vo.setProBudget(rs.getInt("proBudget"));
         vo.setProStart(rs.getString("proStart"));
         vo.setProEnd(rs.getString("proEnd"));
         vo.setProState(rs.getString("proState"));
         vo.setTeamName(rs.getString("teamName"));
         vo.setBdfName(rs.getNString("bdfName"));
      }
      
      rs.close();
      ps.close();
      return vo;
   }
   
   
   public ArrayList graphContent(int vNum)throws Exception{
      System.out.println("graphContent 다오와따");
      list = new ArrayList();
      String sql = "select rm.process_name, rm.start_date, rm.end_date from project p, road_map rm"
            + " where p.pro_no = rm.pro_no"
            + " and p.pro_no="+ vNum;
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
         ArrayList temp = new ArrayList();
         temp.add(rs.getString(1));
         temp.add(rs.getString(2)); //
         temp.add(rs.getString(3)); //


         list.add(temp);
      }
      rs.close();
      stmt.close();
      return list;
   }

   public ArrayList MemoTable(int vNum) throws Exception {
      // TODO Auto-generated method stub
      System.out.println("다오도착");
      list = new ArrayList();
      String sql = "select pm_no, pm_name, pm_title, pm_date from project_memo"
            + " where pro_no = " + vNum + "order by 1";
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      
      while (rs.next ()) {
         ArrayList temp = new ArrayList();
         temp.add(rs.getInt(1));
         temp.add(rs.getString(2));
         temp.add(rs.getString(3));
         temp.add(rs.getString(4));

         list.add(temp);
      }
      rs.close();
      stmt.close();
      return list;
   }
   public MemoVO memowriter(String id) {
      MemoVO mvo = new MemoVO();
      try {
         String sql = "Select emp_name from emp where emp_no = ?";
         ps = conn.prepareStatement(sql);
         ps.setString(1, id);
         ResultSet rs = ps.executeQuery();
         
         if (rs.next()) {
            mvo = new MemoVO();
            mvo.setMemo_write(rs.getString("emp_name"));
         }
         rs.close();
         ps.close();      
      } catch (Exception e) {
         // TODO: handle exception
      }
      return mvo;
   }
   
   public String teams(String empId) throws Exception {

         String teamName = null;
         String sql = "select t.team_name from emp e, department_team dt, team t"
                  + " where e.emp_no = dt.emp_no and dt.team_no = t.team_no and e.emp_no = " + empId;
         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         
         if (rs.next()) {
            teamName = rs.getString("team_name");
         }
         
         rs.close();
         ps.close();
      return teamName;
   }
   
   public MemoVO memoaddOpen(String empId)throws Exception {
      MemoVO mvo = new MemoVO();
      try {
      String sql = "select p.pro_name, e.emp_name from"
               + " project p, emp e, department_team dt, team t, business_management b"
               + " where e.emp_no = dt.emp_no and t.team_no = dt.team_no"
               + " and t.team_no = b.team_no and p.pro_no = b.pro_no and"
               + " e.emp_no = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, empId);
      ResultSet rs = ps.executeQuery();
      
      if (rs.next()) {
         mvo = new MemoVO();
         mvo.setPro_name(rs.getString("pro_name"));
         mvo.setMemo_write(rs.getString("emp_name"));         
      }
      rs.close();
      ps.close();
      }catch (Exception e) {
         // TODO: handle exception
      }
      return mvo;
   }
   
   
   
   
   
   public void MemoClicked(int vMemo, String empId) throws Exception{
      String sql = "update memo_check set mc_state = 'Y'"
            + " where pm_no = ? and mc_state='N' and"
            + " mc_name = (select emp_name from emp where emp_no = ?)";
      ps = conn.prepareStatement(sql);
      
      ps.setInt(1, vMemo);
      ps.setString(2, empId);
      
      ps.executeUpdate();
      ps.close();
   }
   
   
   
   
   
   public int Memoadd(MemoVO mvo, int vNum) throws Exception {
	   
	  String sql1 = "select s_project_memo_pm_no.nextval from dual";
	  stmt = conn.createStatement();
	  rs = stmt.executeQuery(sql1);
	  int memoNum = 0;
	  if (rs.next()) {
		  memoNum = rs.getInt(1);
	  }
	  rs.close();
	  stmt.close();
	   
      String sql = "INSERT INTO project_memo(pm_no, pm_contents, pm_date, pm_name, pro_no, pm_title)VALUES"
            + "(s_project_memo.nextval, ?, sysdate, (select emp_name from emp where emp_no = ?),?,?)";
      ps = conn.prepareStatement(sql);
      ps.setString(1, mvo.getMemo_contents());
      ps.setString(2, mvo.getEmpId());
      ps.setInt(3, vNum);
      ps.setString(4, mvo.getMemo_title());
      
      int cnt = ps.executeUpdate();
      ps.close();
      
//      String sql2 = "" 
      
//    String sql2 = 


      return memoNum;
   }
      
   
   public void MemoCheckListAdd(int memoList, int vNum) throws Exception {
      list = new ArrayList();
      String sql = "select e.emp_name from business_management b, team t, department_team dt, emp e "
            + " where b.team_no = t.team_no and t.team_no = dt.team_no and dt.emp_no = e.emp_no and b.pro_no = " + vNum;
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      
      while (rs.next ()) {
         String empName = rs.getString("emp_name");
         list.add(empName);
      }
      stmt.close();
      rs.close();
      
      for (int i = 0; i < list.size(); i++) {
         String insertSql = "insert into memo_check(mc_no, mc_name, mc_state, pm_no)"
               + "values(s_project_memo_check.nextval, '" + list.get(i) + "', 'N', " + memoList +")";
         stmt = conn.createStatement();
         stmt.executeUpdate(insertSql);
         stmt.close();
//         ResultSet rs1 = stmt.executeQuery(insertSql);
         System.out.println(list.get(i));
      }
   }
   
   public ArrayList MemoOpenCheck(int vMemo) throws Exception {
      list = new ArrayList();
         String sql = "select mc_name from memo_check where pm_no= " + vMemo;

         stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql);
         
         while (rs.next()) {
            String mc_name = rs.getString("mc_name");
            list.add(mc_name);
            }
         rs.close();
         stmt.close();
         return list;
         }
   
   public ArrayList MemoOpenNoCheck(int vMemo) throws Exception {
      list = new ArrayList();
      String sql = "select mc_name from memo_check where mc_state = 'N' and pm_no = " + vMemo;
      
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      
      while (rs.next()) {
         String mc_name = rs.getString("mc_name");
         list.add(mc_name);
      }
      rs.close();
      stmt.close();
      return list;
   }
   
   public MemoVO memoOpen(int vMemo)throws Exception {
      MemoVO mvo = new MemoVO();
      try {
         String sql = "select pm.pm_title, pm.pm_date, p.pro_name, pm.pm_name, pm.pm_contents from"
               + " project_memo pm, project p where p.pro_no = pm.pro_no and pm.pm_no = ?";
         ps = conn.prepareStatement(sql);
         ps.setInt(1, vMemo);
         ResultSet rs = ps.executeQuery();
         
         if(rs.next()) {
            mvo.setMemo_title(rs.getString("pm_title"));
            mvo.setMemo_date(rs.getString("pm_date"));
            mvo.setPro_name(rs.getString("pro_name"));
            mvo.setMemo_write(rs.getString("pm_name"));
            mvo.setMemo_contents(rs.getString("pm_contents"));
            
            rs.close();
            ps.close();   
         }
      } catch (Exception e) {
         // TODO: handle exception
      }
      return mvo;
   }
   
   
   
   		

   
   
      
   
   
   
   }
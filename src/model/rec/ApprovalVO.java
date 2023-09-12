package model.rec;

import java.sql.Date;

public class ApprovalVO {

   int app_no, emp_no, pro_no, team_no;

   String app_manager, app_master, app_title, app_contents, app_writer, app_check;
   Date app_date, write_date;
    private String field3;
   private String field4;
   
   public ApprovalVO() {
      
   }
   
   public ApprovalVO(int app_no, int emp_no, int pro_no, String app_manager, String app_master, String app_title, 
         String app_contents, String app_writer, String app_check, Date app_date, Date write_date) {
      
      this.app_no = app_no;
      this.app_manager = app_manager;
      this.app_master = app_master;
      this.app_title = app_title;
      this.app_contents = app_contents;
      this.app_writer = app_writer;
      this.app_date = app_date;
      this.write_date = write_date;
      this.app_check = app_check;
      this.emp_no = emp_no;
      this.pro_no = pro_no;
      
      
      
   }

   public int getApp_no() {
      return app_no;
   }

   public void setApp_no(int app_no) {
      this.app_no = app_no;
   }

   public int getEmp_no() {
      return emp_no;
   }

   public void setEmp_no(int emp_no) {
      this.emp_no = emp_no;
   }

   public int getPro_no() {
      return pro_no;
   }

   public void setPro_no(int pro_no) {
      this.pro_no = pro_no;
   }

   public String getApp_manager() {
      return app_manager;
   }

   public void setApp_manager(String app_manager) {
      this.app_manager = app_manager;
   }

   public String getApp_master() {
      return app_master;
   }

   public void setApp_master(String app_master) {
      this.app_master = app_master;
   }

   public String getApp_title() {
      return app_title;
   }

   public void setApp_title(String app_title) {
      this.app_title = app_title;
   }

   public String getApp_contents() {
      return app_contents;
   }

   public void setApp_contents(String app_contents) {
      this.app_contents = app_contents;
   }

   public String getApp_writer() {
      return app_writer;
   }

   public void setApp_writer(String app_writer) {
      this.app_writer = app_writer;
   }

   public String getApp_check() {
      return app_check;
   }

   public void setApp_check(String app_check) {
      this.app_check = app_check;
   }

   public Date getApp_date() {
      return app_date;
   }

   public void setApp_date(Date app_date) {
      this.app_date = app_date;
   }

   public Date getWrite_date() {
      return write_date;
   }

   public void setWrite_date(Date write_date) {
      this.write_date = write_date;
   }
   
   public String getEmp_name() {
         return app_writer;   
   
   }
   
    public String getField3() {
        return field3;
    }

    // Setter 메서드
    public void setField3(String field3) {
        this.field3 = field3;
    }
   
    public String getField4() {
        return field4;
    }

    // Setter 메서드
    public void setField4(String field4) {
        this.field4 = field4;
    }

    public int getTeam_no() {
        return team_no;
     }

     public void setTeam_no(int team_no) {
        this.team_no = team_no;
     }

}
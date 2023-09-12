package model.rec;

import java.sql.Date;

public class ProjectAdminVO {
		
	String pro_name, process_name, pro_state, team_name, d_name;
	Date end_date;
	
	public ProjectAdminVO() {
		
	}


	public ProjectAdminVO(String pro_name, String process_name, String pro_state, String team_name, String d_name, Date end_date) {
		this.pro_name = pro_name;
		this.process_name = process_name;
		this.pro_state = pro_state;
		this.team_name = team_name;
		this.d_name = d_name;
		this.end_date = end_date;
		
	}
	
	public String getD_name() {
		return d_name;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public void setD_name(String d_name) {
		this.d_name = d_name;
	}


	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}


	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getProcess_name() {
		return process_name;
	}

	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}

	public String getPro_state() {
		return pro_state;
	}

	public void setPro_state(String pro_state) {
		this.pro_state = pro_state;
	}



	
	
	
}

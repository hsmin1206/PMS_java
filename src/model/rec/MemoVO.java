package model.rec;

import java.util.Date;

public class MemoVO {
	String memo_title, team_no, memo_name, memo_date, memo_contents, memo_write, pro_name, empId;
	int department_no;
	Date pm_date;
	
	void MemoVo() {
		
	}
	
	public MemoVO(String memo_title, String empId, String contents) {
		this.memo_title = memo_title;
		this.empId = empId;
		this.memo_contents = contents;
	}
	
	
	public MemoVO(String memo_title, String team_no,String memo_name, String contents,String memo_date, String memo_write, String pro_name, Date pm_date, int department_no,String empId) {
		this.memo_title = memo_title;
		this.memo_name = memo_name;
		this.memo_date = memo_date;
		this.memo_write = memo_write;
		this.team_no = team_no;
		this.memo_contents = contents;
		this.pro_name = pro_name;
		this.pm_date = pm_date;
		this.department_no = department_no;
		this.empId = empId;
	}



	public String getTeam_no() {
		return team_no;
	}

	public void setTeam_no(String team_no) {
		this.team_no = team_no;
	}

	public int getDepartment_no() {
		return department_no;
	}


	public void setDepartment_no(int department_no) {
		this.department_no = department_no;
	}


	public Date getPm_date() {
		return pm_date;
	}


	public void setPm_date(Date pm_date) {
		this.pm_date = pm_date;
	}


	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getMemo_title() {
		return memo_title;
	}

	public void setMemo_title(String memo_title) {
		this.memo_title = memo_title;
	}

	public String getMemo_name() {
		return memo_name;
	}

	public void setMemo_name(String memo_name) {
		this.memo_name = memo_name;
	}

	public String getMemo_date() {
		return memo_date;
	}

	public void setMemo_date(String memo_date) {
		this.memo_date = memo_date;
	}

	public String getMemo_contents() {
		return memo_contents;
	}

	public void setMemo_contents(String memo_contents) {
		this.memo_contents = memo_contents;
	}

	public String getMemo_write() {
		return memo_write;
	}

	public void setMemo_write(String memo_write) {
		this.memo_write = memo_write;
	}
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public MemoVO() {
		// TODO Auto-generated constructor stub
	}
	
}
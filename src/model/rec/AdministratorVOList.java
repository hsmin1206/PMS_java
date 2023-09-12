package model.rec;

import java.util.ArrayList;
import java.util.List;

public class AdministratorVOList {
	
	private List<Integer> emp_no;
//	private String emp_name;
//	private String emp_job;
	private List<Integer> jg_no;
	
	/*
	 * AdministratorVO 클래스의 emp_no 필드가 null로 
	 * 초기화되어 있기 때문에 getEmp_no() 메서드를 호출하면
	 * NullPointerException이 발생합니다. 
	 * 따라서 emp_no 필드를 초기화하는 코드를 추가해야 합니다.
	 * 아래는 getEmp_no() 메서드에서 필드를 초기화하는 방법입니다.
	 * 이렇게 코드를 수정하면 getEmp_no() 메서드가 호출될 때 
	 * emp_no 필드가 null인 경우에만 초기화가 수행되고, 
	 * 이미 초기화된 경우에는 기존의 리스트를 반환합니다.
	 */
  	public List<Integer> getEmp_no() {
  		if(emp_no == null) {
  			emp_no = new ArrayList<Integer>();
  		}
  		return emp_no;
  	}
  	
	public void setEmp_no(List<Integer> emp_no) {
		
		this.emp_no = emp_no;
	}
	
	public List<Integer> getJg_no() {
		if(jg_no == null) {
			jg_no = new ArrayList<Integer>();
		}
		return jg_no;
	}
	
//	public void setJg_no(List<Integer> jg_no) {
//		this.jg_no = jg_no;
//	}
//
//	public String getEmp_name() {
//		return emp_name;
//	}
//
//	public void setEmp_name(String emp_name) {
//		this.emp_name = emp_name;
//	}
//
//	public String getEmp_job() {
//		return emp_job;
//	}
//
//	public void setEmp_job(String emp_job) {
//		this.emp_job = emp_job;
//	}
	
	

}

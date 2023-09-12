package model.rec;

public class PartnerVO {
	String pa_company, pa_dam, pa_tel, pa_email, pd_name, pa_loc;


	//�⺻ ������
	public PartnerVO() {

	}

	//�����ε� ������	
	public PartnerVO(String pa_comapany, String pa_dam, String pa_email, String pa_tel,  String pd_name, String pa_loc) {
		this.pa_company = pa_comapany;
		this.pa_dam = pa_dam;
		this.pa_email = pa_email;
		this.pa_tel = pa_tel;
		this.pd_name = pd_name;
		this.pa_loc = pa_loc;		

	}


	public String getPa_company() {
		return pa_company;
	}

	public void setPa_company(String pa_company) {
		this.pa_company = pa_company;
	}

	public String getPa_dam() {
		return pa_dam;
	}

	public void setPa_dam(String pa_dam) {
		this.pa_dam = pa_dam;
	}

	public String getPa_tal() {
		return pa_tel;
	}

	public void setPa_tal(String pa_tal) {
		this.pa_tel = pa_tal;
	}

	public String getPa_email() {
		return pa_email;
	}

	public void setPa_email(String pa_email) {
		this.pa_email = pa_email;
	}


	public String getPd_name() {
		return pd_name;
	}
	
	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}
	public String getPa_loc() {
		return pa_loc;
	}

	public void setPa_loc(String pa_loc) {
		this.pa_loc = pa_loc;
	}

}

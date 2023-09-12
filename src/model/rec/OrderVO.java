package model.rec;

public class OrderVO {
	String om_content, om_count, om_amount, om_date,pa_company,pro_name, pa_dam,pa_tel,pa_email;
	int  bm_no, part_no;
	

	public OrderVO() {

	}
	public OrderVO(String pa_company, String pa_dam,String pa_tel, String pa_email) {
		this.pa_company = pa_company;
		this.pa_dam = pa_dam;
		this.pa_tel = pa_tel;
		this.pa_email = pa_email;
		
	}

	public OrderVO(String pa_company, String pro_name, String pa_dam,String pa_tel, String pa_email, int bm_no, String om_content, String om_count, String om_amount,  String om_date, int part_no) {	
		this.pa_company = pa_company;
		this.pro_name = pro_name;
		this.pa_dam = pa_dam;
		this.pa_tel = pa_tel;
		this.pa_email = pa_email;
		this.bm_no = bm_no;
		this.om_content = om_content;
		this.om_count = om_count;
		this.om_amount = om_amount;
		this.om_date = om_date;
		this.part_no = part_no;
	}

	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
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

	public String getPa_tel() {
		return pa_tel;
	}

	public void setPa_tel(String pa_tel) {
		this.pa_tel = pa_tel;
	}

	public String getPa_email() {
		return pa_email;
	}

	public void setPa_email(String pa_email) {
		this.pa_email = pa_email;
	}

	public String getOm_content() {
		return om_content;
	}

	public void setOm_content(String om_content) {
		this.om_content = om_content;
	}

	public String getOm_amount() {
		return om_amount;
	}

	public void setOm_amount(String om_amount) {
		this.om_amount = om_amount;
	}

	public String getOm_count() {
		return om_count;
	}

	public void setOm_count(String om_count) {
		this.om_count = om_count;
	}

	public String getOm_date() {
		return om_date;
	}

	public void setOm_date(String om_date) {
		this.om_date = om_date;
	}

	public int getBm_no() {
		return bm_no;
	}

	public void setBm_no(int bm_no) {
		this.bm_no = bm_no;
	}

	public int getPart_no() {
		return part_no;
	}

	public void setPart_no(int part_no) {
		this.part_no = part_no;
	}

}

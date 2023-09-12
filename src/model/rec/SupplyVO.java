package model.rec;

public class SupplyVO {
	String sm_content, sm_amount, sm_count, sm_date, pa_company,pro_name, pa_dam,pa_tel,pa_email;
	int sm_no, part_no, bm_no;

	public SupplyVO() {

	}

	public String getSm_content() {
		return sm_content;
	}

	public void setSm_content(String sm_content) {
		this.sm_content = sm_content;
	}

	public String getSm_amount() {
		return sm_amount;
	}

	public void setSm_amount(String sm_amount) {
		this.sm_amount = sm_amount;
	}

	public String getSm_count() {
		return sm_count;
	}

	public void setSm_count(String sm_count) {
		this.sm_count = sm_count;
	}

	public String getSm_date() {
		return sm_date;
	}

	public void setSm_date(String sm_date) {
		this.sm_date = sm_date;
	}

	public String getPa_company() {
		return pa_company;
	}

	public void setPa_company(String pa_company) {
		this.pa_company = pa_company;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
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

	public int getSm_no() {
		return sm_no;
	}

	public void setSm_no(int sm_no) {
		this.sm_no = sm_no;
	}

	public int getPart_no() {
		return part_no;
	}

	public void setPart_no(int part_no) {
		this.part_no = part_no;
	}

	public int getBm_no() {
		return bm_no;
	}

	public void setBm_no(int bm_no) {
		this.bm_no = bm_no;
	}

	public SupplyVO(String sm_content, String sm_amount, String sm_count, String sm_date, int sm_no, int part_no, int bm_no, String pa_company, String pa_dam,String pa_tel, String pa_email, String pro_name) {
		this.sm_content = sm_content;
		this.sm_amount = sm_amount;
		this.sm_count = sm_count;
		this.sm_date = sm_date;
		this.sm_no = sm_no;
		this.part_no = part_no;
		this.bm_no = bm_no;
		this.pa_company = pa_company;
		this.pa_dam = pa_dam;
		this.pa_tel = pa_tel;
		this.pa_email = pa_email;
		this.pro_name = pro_name;
		
	}


}

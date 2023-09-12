package model.rec;

public class ClientVO {
	String company, date, content;
	int count, amount;
	
	public ClientVO() {
		// TODO Auto-generated constructor stub
	}

	public ClientVO(String company, String date, String content, int count, int amount) {
		this.company = company;
		this.date = date;
		this.content = content;
		this.count = count;
		this.amount = amount;		
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}

package model.rec;

public class ProjectPutupVO {
	String name, tim, budget, go,end,details;
	
	public ProjectPutupVO(){}
	
	public ProjectPutupVO(String name, String tim, String budget,String go,String end,String details) {
		this.name = name;
		this.tim = tim;
		this.budget = budget;
		this.go = go;
		this.end = end;
		this.details = details;
	}
	
	public String getTim() {
		return tim;
	}

	public void setTim(String tim) {
		this.tim = tim;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getGo() {
		return go;
	}

	public void setGo(String go) {
		this.go = go;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}

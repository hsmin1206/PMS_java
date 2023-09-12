package model.rec;

public class ProjectVO {
	String proName, proStart, proEnd, proState, teamName, bdfName;	// 占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙占썅여占쏙옙, 占쏙옙占쏙옙, 占쏙옙占쏙옙占쏙옙觀棘占�
	int proNo, proBudget;		// 占쏙옙占쏙옙占쏙옙호, 占쏙옙占쏙옙占싼울옙占쏙옙
	
	public ProjectVO() {}
	
	public ProjectVO(String proName, String proStart, String proEnd, String proState, String teamName, String bdfName, int proNo, int proBudget) {
		
	}
	
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProStart() {
		return proStart;
	}
	public void setProStart(String proStart) {
		this.proStart = proStart;
	}
	public String getProEnd() {
		return proEnd;
	}
	public void setProEnd(String proEnd) {
		this.proEnd = proEnd;
	}
	public String getProState() {
		return proState;
	}
	public void setProState(String proState) {
		this.proState = proState;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getBdfName() {
		return bdfName;
	}
	public void setBdfName(String bdfName) {
		this.bdfName = bdfName;
	}
	public int getProNo() {
		return proNo;
	}
	public void setProNo(int proNo) {
		this.proNo = proNo;
	}
	public int getProBudget() {
		return proBudget;
	}
	public void setProBudget(int proBudget) {
		this.proBudget = proBudget;
	}
	
}

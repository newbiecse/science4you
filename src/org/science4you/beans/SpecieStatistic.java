package org.science4you.beans;

public class SpecieStatistic {

	private int id;
	
	private String wissname;
	
	private String trivialname;
	
	private int minimumlevel;
	
	private String sensitiv;
	
	private String groupname;

	public SpecieStatistic() {
		
	}
	
	public SpecieStatistic(int id, String wissname, String trivialname, 
			int minimumlevel, String sensitiv, String groupname) {
		
		this.id = id;
		this.wissname = wissname;
		this.trivialname = trivialname;
		this.minimumlevel = minimumlevel;
		this.sensitiv = sensitiv;
		this.groupname = groupname;
	}	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
	public String getWissname() {
		return wissname;
	}

	public void setWissname(String wissname) {
		this.wissname = wissname;
	}

	public String getTrivialname() {
		return trivialname;
	}

	public void setTrivialname(String trivialname) {
		this.trivialname = trivialname;
	}

	public int getMinimumlevel() {
		return minimumlevel;
	}

	public void setMinimumlevel(int miniumlevel) {
		this.minimumlevel = miniumlevel;
	}

	public String getSensitiv() {
		return sensitiv;
	}

	public void setSensitiv(String sensitiv) {
		this.sensitiv = sensitiv;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}

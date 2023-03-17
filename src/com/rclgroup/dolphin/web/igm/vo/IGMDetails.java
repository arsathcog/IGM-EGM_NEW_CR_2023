package com.rclgroup.dolphin.web.igm.vo;

public class IGMDetails {

	private String igmNumber;
	private String igmDate;
	private String blNo;
	
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public String getIgmNumber() {
		return igmNumber;
	}
	public void setIgmNumber(String igmNumber) {
		this.igmNumber = igmNumber;
	}
	public String getIgmDate() {
		return igmDate;
	}
	public void setIgmDate(String igmDate) {
		this.igmDate = igmDate;
	}
	@Override
	public String toString() {
		return "IGMDetails [igmNumber=" + igmNumber + ", igmDate=" + igmDate + ", blNo=" + blNo + "]";
	}
	
}

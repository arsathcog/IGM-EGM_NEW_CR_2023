package com.rclgroup.dolphin.web.igm.vo.sdn;

public class VoyageTransportEquipmentSDN {
	private String quipmentSequenceNo;
	private String quipmentId;
	private String quipmentType;
	//private String quipmentSize;
	private String quipmentLoadStatus;
	private String socFlag;
	

	public String getQuipmentSequenceNo() {
		return quipmentSequenceNo;
	}

	public void setQuipmentSequenceNo(String quipmentSequenceNo) {
		this.quipmentSequenceNo = quipmentSequenceNo;
	}

	// (Already in IGM screen in container section. Container no) >mandatory
	public String getQuipmentId() {
		return quipmentId;
	}

	public void setQuipmentId(String quipmentId) {
		this.quipmentId = quipmentId;
	}

	// (Use value as CN) >mandatory
	public String getQuipmentType() {
		return quipmentType;
	}

	public void setQuipmentType(String quipmentType) {
		this.quipmentType = quipmentType;
	}
	public String getQuipmentLoadStatus() {
		return quipmentLoadStatus;
	}

	public void setQuipmentLoadStatus(String quipmentLoadStatus) {
		this.quipmentLoadStatus = quipmentLoadStatus;
	}

	public String getSocFlag() {
		return socFlag;
	}

	public void setSocFlag(String socFlag) {
		this.socFlag = socFlag;
	}

}

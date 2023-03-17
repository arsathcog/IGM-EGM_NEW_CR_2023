package com.rclgroup.dolphin.web.igm.vo;

public class MarksNumber {

	private String blNO;

	private String marksNumbers;

	private String description;

	private String droRemarks;
	
	private String bldate;

	private String notifyPartyCode;
	private String previous_declaration = "N";
	private String split_indicator;
	private String csn_number;

	private String csn_date;

	private String previous_mcin;

	private String previous_pcin;
	
	public String getBldate() {
		return bldate;
	}

	public void setBldate(String bldate) {
		this.bldate = bldate;
	}

	public String getBlNO() {
		return blNO;
	}

	public void setBlNO(String blNO) {
		this.blNO = blNO;
	}

	public String getMarksNumbers() {
		return marksNumbers;
	}

	public void setMarksNumbers(String marksNumbers) {
		this.marksNumbers = marksNumbers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDroRemarks() {
		return droRemarks;
	}

	public void setDroRemarks(String droRemarks) {
		this.droRemarks = droRemarks;
	}

	public String getNotifyPartyCode() {
		return notifyPartyCode;
	}

	public void setNotifyPartyCode(String notifyPartyCode) {
		this.notifyPartyCode = notifyPartyCode;
	}

	public String getPrevious_declaration() {
		return previous_declaration;
	}

	public void setPrevious_declaration(String previous_declaration) {
		this.previous_declaration = previous_declaration;
	}

	public String getSplit_indicator() {
		return split_indicator;
	}

	public void setSplit_indicator(String split_indicator) {
		this.split_indicator = split_indicator;
	}

	public String getCsn_number() {
		return csn_number;
	}

	public void setCsn_number(String csn_number) {
		this.csn_number = csn_number;
	}

	public String getCsn_date() {
		return csn_date;
	}

	public void setCsn_date(String csn_date) {
		this.csn_date = csn_date;
	}

	public String getPrevious_mcin() {
		return previous_mcin;
	}

	public void setPrevious_mcin(String previous_mcin) {
		this.previous_mcin = previous_mcin;
	}

	public String getPrevious_pcin() {
		return previous_pcin;
	}

	public void setPrevious_pcin(String previous_pcin) {
		this.previous_pcin = previous_pcin;
	}

	// Remarks dro

}

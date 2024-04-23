package com.rclgroup.dolphin.web.igm.vo;
//This class keeping BLDetails'PreviousDeclaration data in it.
public class PreviousDeclaration {
	
	private String blNo;
	
	private String previous_declaration = "N";
	
	private String split_indicator;
	
	private String csn_number;
	
	private String previous_pcin;
	
	private String csn_date;
	
	private String previous_mcin;
	
	
	
	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
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
	
	public String getPrevious_pcin() {
		return previous_pcin;
	}

	public void setPrevious_pcin(String previous_pcin) {
		this.previous_pcin = previous_pcin;
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


	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PreviousDeclaration [blNo=");
		builder.append(blNo);
		builder.append(", previous_declaration=");
		builder.append(previous_declaration);
		builder.append(", split_indicator=");
		builder.append(split_indicator);
		builder.append(", csn_number=");
		builder.append(csn_number);
		builder.append(", previous_pcin=");
		builder.append(previous_pcin);
		builder.append(", csn_date=");
		builder.append(csn_date);
		builder.append(", previous_mcin=");
		builder.append(previous_mcin);
		builder.append("]");
		return builder.toString();
	}
	
	
}

package com.rclgroup.dolphin.web.igm.vo;

public class DropDownMod {

	private String partnerviews;
	private String partnerValuedre;
	private String descriptiondrw;
	private String podValue;
	
	
	public String getPartnerValuedre() {
		return partnerValuedre;
	}
	public void setPartnerValuedre(String partnerValuedre) {
		this.partnerValuedre = partnerValuedre;
	}
	
	public String getDescriptiondrw() {
		return descriptiondrw;
	}
	public void setDescriptiondrw(String descriptiondrw) {
		this.descriptiondrw = descriptiondrw;
	}
	public String getPodValue() {
		return podValue;
	}
	public void setPodValue(String podValue) {
		this.podValue = podValue;
	}
	public String getPartnerviews() {
		return partnerviews;
	}
	public void setPartnerviews(String partnerviews) {
		this.partnerviews = partnerviews;
	}
	
	@Override
	public String toString() {
		return "DropDownMod [partnerviews=" + partnerviews + ", partnerValuedre=" + partnerValuedre
				+ ", descriptiondrw=" + descriptiondrw + ", podValue=" + podValue + "]";
	}
	
}

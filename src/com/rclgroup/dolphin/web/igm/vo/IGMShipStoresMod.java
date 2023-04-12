package com.rclgroup.dolphin.web.igm.vo;

public class IGMShipStoresMod {

	private String blNo;
	
	private String articleNameCdd;
	
	private String articleNameText;
	
	private String locOnbrdText;
	
	private String qntyOnbrd;
	
	private String qntyCdOnbrd;
	
	private String vessel;
	
	private String voyage ;
	
	private String pod ;
	
	private String $$hashKey;
	
	private String vesselSrno;
	
	public String getVesselSrno() {
		return vesselSrno;
	}

	public void setVesselSrno(String vesselSrno) {
		this.vesselSrno = vesselSrno;
	}

	public String get$$hashKey() {
		return $$hashKey;
	}

	public void set$$hashKey(String $$hashKey) {
		this.$$hashKey = $$hashKey;
	}

	public String getArticleNameCdd() {
		return articleNameCdd;
	}

	public void setArticleNameCdd(String articleNameCdd) {
		this.articleNameCdd = articleNameCdd;
	}

	public String getArticleNameText() {
		return articleNameText;
	}

	public void setArticleNameText(String articleNameText) {
		this.articleNameText = articleNameText;
	}

	public String getLocOnbrdText() {
		return locOnbrdText;
	}

	public void setLocOnbrdText(String locOnbrdText) {
		this.locOnbrdText = locOnbrdText;
	}

	public String getQntyOnbrd() {
		return qntyOnbrd;
	}

	public void setQntyOnbrd(String qntyOnbrd) {
		this.qntyOnbrd = qntyOnbrd;
	}

	public String getQntyCdOnbrd() {
		return qntyCdOnbrd;
	}

	public void setQntyCdOnbrd(String qntyCdOnbrd) {
		this.qntyCdOnbrd = qntyCdOnbrd;
	}

	public String getBlNo() {
		return blNo;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getPod() {
		return pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

	@Override
	public String toString() {
		return "IGMShipStoresMod [blNo=" + blNo + ", articleNameCdd=" + articleNameCdd + ", articleNameText="
				+ articleNameText + ", locOnbrdText=" + locOnbrdText + ", qntyOnbrd=" + qntyOnbrd + ", qntyCdOnbrd="
				+ qntyCdOnbrd + ", vessel=" + vessel + ", voyage=" + voyage + ", pod=" + pod + ", $$hashKey="
				+ $$hashKey + ", vesselSrno=" + vesselSrno + "]";
	}
	
}

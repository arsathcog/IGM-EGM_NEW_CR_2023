package com.rclgroup.dolphin.web.igm.vo.sdm;
public class ShipStoresSDM{

	private int seqNmbr;
	private String articleNameCdd;
	private String articleNameText;
	private String locOnbrdText;
	private int qntyOnbrd;
	private String qntyCdOnbrd;


	public int getSeqNmbr() {
		return seqNmbr;
	}
	public void setSeqNmbr(int seqNmbr) {
		this.seqNmbr = seqNmbr;
	}
	public void setQntyOnbrd(int qntyOnbrd) {
		this.qntyOnbrd = qntyOnbrd;
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
	
	public int getQntyOnbrd() {
		return qntyOnbrd;
	}
	public String getQntyCdOnbrd() {
		return qntyCdOnbrd;
	}
	public void setQntyCdOnbrd(String qntyCdOnbrd) {
		this.qntyCdOnbrd = qntyCdOnbrd;
	}
}

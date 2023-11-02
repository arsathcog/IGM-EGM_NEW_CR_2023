package com.rclgroup.dolphin.web.igm.vo.sam;

public class PrsnOnBoardSAM {
	
	private int prsnOnBoardSeqNmbr;
	private PrsnDtlsSAM prsnDtls;
	private PrsnIdSAM prsnId;
	private VisaDtlsSAM visaDtls;
//	private String prsnTypCdd;
	
	public int getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}
	public void setPrsnOnBoardSeqNmbr(int prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}
	
	public PrsnDtlsSAM getPrsnDtls() {
		return prsnDtls;
	}
	
	public void setPrsnDtls(PrsnDtlsSAM prsnDtls) {
		this.prsnDtls = prsnDtls;
	}
	public PrsnIdSAM getPrsnId() {
		return prsnId;
	}
	public void setPrsnId(PrsnIdSAM prsnId) {
		this.prsnId = prsnId;
	}
	public VisaDtlsSAM getVisaDtls() {
		return visaDtls;
	}
	public void setVisaDtls(VisaDtlsSAM visaDtls) {
		this.visaDtls = visaDtls;
	}
	
	@Override
	public String toString() {
		return "PrsnOnBoardSAM [prsnOnBoardSeqNmbr=" + prsnOnBoardSeqNmbr + ", prsnDtls=" + prsnDtls + ", prsnId="
				+ prsnId + ", visaDtls=" + visaDtls + "]";
	}
	
	
//	public String getPrsnTypCdd() {
//		return prsnTypCdd;
//	}
//	public void setPrsnTypCdd(String prsnTypCdd) {
//		this.prsnTypCdd = prsnTypCdd;
//	}

	
	
	
	 

	
}

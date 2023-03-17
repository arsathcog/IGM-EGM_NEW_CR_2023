package com.rclgroup.dolphin.web.igm.vo.sdm;

  //# 
public  class VisaDtlsSDM{
	
	private String prsnVisa  = ""; //  70
	private String pnrNmbr   = ""; //   20
	
	public String getPrsnVisa() {
		return prsnVisa;
	}
	public void setPrsnVisa(String prsnVisa) {
		this.prsnVisa = prsnVisa;
	}
	public String getPnrNmbr() {
		return pnrNmbr;
	}
	public void setPnrNmbr(String pnrNmbr) {
		this.pnrNmbr = pnrNmbr;
	}
	
	@Override
	public String toString() {
		return "VisaDtlsSDM [prsnVisa=" + prsnVisa + ", pnrNmbr=" + pnrNmbr + "]";
	}
	
	
    }
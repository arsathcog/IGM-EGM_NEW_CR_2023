package com.rclgroup.dolphin.web.igm.vo.sei;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.PrsnDtls;
import com.rclgroup.dolphin.web.igm.vo.PrsnId;

public class PrsnOnBoardSEI {
	
	/*
	 * private String prsnOnBoardSeqNmbr; private List<PrsnDtlsSEI> prsnDtls;
	 * private List<PrsnIdSEI> prsnId;
	 * 
	 * public String getPrsnOnBoardSeqNmbr() { return prsnOnBoardSeqNmbr; } public
	 * void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
	 * this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr; } public List<PrsnDtlsSEI>
	 * getPrsnDtls() { return prsnDtls; } public void setPrsnDtls(List<PrsnDtlsSEI>
	 * prsnDtls) { this.prsnDtls = prsnDtls; } public List<PrsnIdSEI> getPrsnId() {
	 * return prsnId; } public void setPrsnId(List<PrsnIdSEI> prsnId) { this.prsnId
	 * = prsnId; }
	 */

	
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
	
}

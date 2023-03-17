package com.rclgroup.dolphin.web.igm.vo.scu;

import java.util.List;

public class PrsnOnBoardSCU {
	private String prsnOnBoardSeqNmbr;
	private List<PrsnDtlsSCU> prsnDtls;
	private List<PrsnIdSCU> prsnId;
	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}
	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}
	public List<PrsnDtlsSCU> getPrsnDtls() {
		return prsnDtls;
	}
	public void setPrsnDtls(List<PrsnDtlsSCU> prsnDtls) {
		this.prsnDtls = prsnDtls;
	}
	public List<PrsnIdSCU> getPrsnId() {
		return prsnId;
	}
	public void setPrsnId(List<PrsnIdSCU> prsnId) {
		this.prsnId = prsnId;
	}

	
}

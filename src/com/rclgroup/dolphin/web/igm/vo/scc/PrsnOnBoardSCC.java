package com.rclgroup.dolphin.web.igm.vo.scc;

import java.util.List;

public class PrsnOnBoardSCC {
	private String prsnOnBoardSeqNmbr;
	private List<PrsnDtlsSCC> prsnDtls;
	private List<PrsnIdSCC> prsnId;
	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}
	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}
	public List<PrsnDtlsSCC> getPrsnDtls() {
		return prsnDtls;
	}
	public void setPrsnDtls(List<PrsnDtlsSCC> prsnDtls) {
		this.prsnDtls = prsnDtls;
	}
	public List<PrsnIdSCC> getPrsnId() {
		return prsnId;
	}
	public void setPrsnId(List<PrsnIdSCC> prsnId) {
		this.prsnId = prsnId;
	}

	
}

package com.rclgroup.dolphin.web.igm.vo.sdn;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.PrsnDtls;
import com.rclgroup.dolphin.web.igm.vo.PrsnId;

public class PrsnOnBoardSDN {
	private String prsnOnBoardSeqNmbr;
	private List<PrsnDtlsSDN> prsnDtls;
	private List<PrsnIdSDN> prsnId;

	public List<PrsnDtlsSDN> getPrsnDtls() {
		return prsnDtls;
	}

	public void setPrsnDtls(List<PrsnDtlsSDN> prsnDtlsList) {
		this.prsnDtls = prsnDtlsList;
	}

	public List<PrsnIdSDN> getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(List<PrsnIdSDN> prsnId) {
		this.prsnId = prsnId;
	}

	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}

	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}

}

package com.rclgroup.dolphin.web.igm.vo.sca;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.PrsnDtls;
import com.rclgroup.dolphin.web.igm.vo.PrsnId;

public class PrsnOnBoardSCA {
	private String prsnOnBoardSeqNmbr;
	private List<PrsnDtlsSCA> prsnDtls;
	private List<PrsnIdSCA> prsnId;
	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}
	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}
	public List<PrsnDtlsSCA> getPrsnDtls() {
		return prsnDtls;
	}
	public void setPrsnDtls(List<PrsnDtlsSCA> prsnDtls) {
		this.prsnDtls = prsnDtls;
	}
	public List<PrsnIdSCA> getPrsnId() {
		return prsnId;
	}
	public void setPrsnId(List<PrsnIdSCA> prsnId) {
		this.prsnId = prsnId;
	}

	
}

package com.rclgroup.dolphin.web.igm.vo.saa;

import java.util.List;

public class PrsnOnBoardSAA {
	private String prsnOnBoardSeqNmbr;
	private List<CrewEfctSAA> crewEfct;
	private List<PrsnDtlsSAA> prsnDtls;
	private List<PrsnIdSAA> prsnId;

	public List<PrsnDtlsSAA> getPrsnDtls() {
		return prsnDtls;
	}

	public void setPrsnDtls(List<PrsnDtlsSAA> prsnDtlsList) {
		this.prsnDtls = prsnDtlsList;
	}

	public List<PrsnIdSAA> getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(List<PrsnIdSAA> prsnId) {
		this.prsnId = prsnId;
	}

	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}

	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}

	public List<CrewEfctSAA> getCrewEfct() {
		return crewEfct;
	}

	public void setCrewEfct(List<CrewEfctSAA> crewEfct) {
		this.crewEfct = crewEfct;
	}

}

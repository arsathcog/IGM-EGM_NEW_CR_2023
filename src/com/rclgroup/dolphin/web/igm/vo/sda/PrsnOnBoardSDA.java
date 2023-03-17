package com.rclgroup.dolphin.web.igm.vo.sda;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.PrsnDtls;
import com.rclgroup.dolphin.web.igm.vo.PrsnId;
import com.rclgroup.dolphin.web.igm.vo.saa.CrewEfctSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.PrsnDtlsSAA;
import com.rclgroup.dolphin.web.igm.vo.saa.PrsnIdSAA;

public class PrsnOnBoardSDA {
	private String prsnOnBoardSeqNmbr;
	private List<PrsnDtlsSDA> prsnDtls;
	private List<PrsnIdSDA> prsnId;
	private List<CrewEfctSDA> crewEfct;
	
	public List<PrsnDtlsSDA> getPrsnDtls() {
		return prsnDtls;
	}

	public void setPrsnDtls(List<PrsnDtlsSDA> prsnDtlsList) {
		this.prsnDtls = prsnDtlsList;
	}

	public List<PrsnIdSDA> getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(List<PrsnIdSDA> prsnId) {
		this.prsnId = prsnId;
	}

	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}

	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}

	public List<CrewEfctSDA> getCrewEfct() {
		return crewEfct;
	}

	public void setCrewEfct(List<CrewEfctSDA> crewEfct) {
		this.crewEfct = crewEfct;
	}

}

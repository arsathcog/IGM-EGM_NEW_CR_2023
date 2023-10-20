package com.rclgroup.dolphin.web.igm.vo.sdm;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.PrsnDtls;
import com.rclgroup.dolphin.web.igm.vo.PrsnId;
import com.rclgroup.dolphin.web.igm.vo.saa.CrewEfctSAA;

public class PrsnOnBoardSDM {
	
	private int prsnOnBoardSeqNmbr;
	private PrsnDtlsSDM prsnDtls;
	private PrsnIdSDM prsnId;
	private VisaDtlsSDM visaDtls;
	private CrewEfctSDM crewEfct;
	
	public VisaDtlsSDM getVisaDtls() {
		return visaDtls;
	}
	public void setVisaDtls(VisaDtlsSDM visaDtls) {
		this.visaDtls = visaDtls;
	}
	
	public int getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}
	public void setPrsnOnBoardSeqNmbr(int prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}
	public PrsnDtlsSDM getPrsnDtls() {
		return prsnDtls;
	}
	public void setPrsnDtls(PrsnDtlsSDM prsnDtls) {
		this.prsnDtls = prsnDtls;
	}
	
	public PrsnIdSDM getPrsnId() {
		return prsnId;
	}
	public void setPrsnId(PrsnIdSDM prsnId) {
		this.prsnId = prsnId;
	}

	public CrewEfctSDM getCrewEfct() {
		return crewEfct;
	}
	public void setCrewEfct(CrewEfctSDM crewEfct) {
		this.crewEfct = crewEfct;
	}
	@Override
	public String toString() {
		return "PrsnOnBoardSDM [prsnOnBoardSeqNmbr=" + prsnOnBoardSeqNmbr + ", prsnDtls=" + prsnDtls + ", prsnId="
				+ prsnId + ", crewEfct=" + crewEfct + ", visaDtls=" + visaDtls + "]";
	}
		 
}

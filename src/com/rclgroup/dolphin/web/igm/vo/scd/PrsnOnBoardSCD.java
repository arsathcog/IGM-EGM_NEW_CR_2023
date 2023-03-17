package com.rclgroup.dolphin.web.igm.vo.scd;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.PrsnDtls;
import com.rclgroup.dolphin.web.igm.vo.PrsnId;

public class PrsnOnBoardSCD {
	private String prsnOnBoardSeqNmbr;
	private List<PrsnDtlsSCD> prsnDtls;
	private List<PrsnIdSCD> prsnId;
	
	 private String amendment;
	  	
	  	public String getAmendment() {
	  		return amendment;
	  	}

	  	public void setAmendment(String amendment) {
	  		this.amendment = amendment;
	  	}
	public String getPrsnOnBoardSeqNmbr() {
		return prsnOnBoardSeqNmbr;
	}
	public void setPrsnOnBoardSeqNmbr(String prsnOnBoardSeqNmbr) {
		this.prsnOnBoardSeqNmbr = prsnOnBoardSeqNmbr;
	}
	public List<PrsnDtlsSCD> getPrsnDtls() {
		return prsnDtls;
	}
	public void setPrsnDtls(List<PrsnDtlsSCD> prsnDtls) {
		this.prsnDtls = prsnDtls;
	}
	public List<PrsnIdSCD> getPrsnId() {
		return prsnId;
	}
	public void setPrsnId(List<PrsnIdSCD> prsnId) {
		this.prsnId = prsnId;
	}

	
}

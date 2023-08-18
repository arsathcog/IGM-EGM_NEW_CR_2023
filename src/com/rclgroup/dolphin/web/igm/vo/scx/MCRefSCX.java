package com.rclgroup.dolphin.web.igm.vo.scx;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class MCRefSCX {
	private Integer lineNo;
	private String mstrBlNo;
	private String mstrBlDt;
	private String consolidatedIndctr;
	private String prevDec;
	private String consolidatorPan;
//	private String amendment;

//  	public String getAmendment() {
//  		return amendment;
//  	}
//
//  	public void setAmendment(String amendment) {
//  		this.amendment = amendment;
	// }

//Same as Item Number from current screen "Item Number"

	// Same as Item Number from current screen "BL#"
	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public String getMstrBlNo() {
		return mstrBlNo;
	}

	public void setMstrBlNo(String mstrBlNo) {
//		mstrBlNo = FiledValidation.isNullAndSetlength(mstrBlNo, 7);
		this.mstrBlNo = mstrBlNo;
	}

	// Same as Item Number from current screen "BL_Date"
	public String getMstrBlDt() {
		return mstrBlDt;
	}

	public void setMstrBlDt(String mstrBlDt) {
		this.mstrBlDt = mstrBlDt;
	}

	// Same as Item Number from current screen "Consolidated Indicator"
	public String getConsolidatedIndctr() {
		return consolidatedIndctr;
	}

	public void setConsolidatedIndctr(String consolidatedIndctr) {
//		consolidatedIndctr = FiledValidation.isNullAndSetlength(consolidatedIndctr, 4);
		this.consolidatedIndctr = consolidatedIndctr;
	}

	// Same as Item Number from current screen "Previous Declaration"
	public String getPrevDec() {
		return prevDec;
	}

	public void setPrevDec(String prevDec) {
//		prevDec = FiledValidation.isNullAndSetlength(prevDec, 4);
		this.prevDec = prevDec;
	}

	// Same as Item Number from current screen "Consolidator PAN"
	public String getConsolidatorPan() {
		return consolidatorPan;
	}

	public void setConsolidatorPan(String consolidatorPan) {
//		consolidatorPan = FiledValidation.isNullAndSetlength(consolidatorPan, 35);
		this.consolidatorPan = consolidatorPan;
	}
}
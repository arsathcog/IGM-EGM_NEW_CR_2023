package com.rclgroup.dolphin.web.igm.vo.sam;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rclgroup.dolphin.web.igm.vo.FiledValidation;


@JsonPropertyOrder({"lineNo","mstrBlNo","mstrBlDt","consolidatedIndctr","prevDec","consolidatorPan"})
public class MCRefSAM {
	
	private Integer lineNo;
	private String mstrBlNo;
	private String mstrBlDt ;
	private String consolidatedIndctr;
	private String prevDec;
	private String consolidatorPan;

//Same as Item Number from current screen "Item Number"


   //Same as Item Number from current screen "BL#"
	public String getMstrBlNo() {
		return mstrBlNo;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public void setMstrBlNo(String mstrBlNo) {
		//mstrBlNo = FiledValidation.isNullAndSetlength(mstrBlNo, 7);
		this.mstrBlNo = mstrBlNo;
	}

   //Same as Item Number from current screen "BL_Date"
	public String getMstrBlDt() {
		return mstrBlDt;
	}

	public void setMstrBlDt(String mstrBlDt) {
		try {
			if(mstrBlDt == null || mstrBlDt.equals("")) {
				this.mstrBlDt = mstrBlDt;
			}else if(mstrBlDt.contains("/")) {
				this.mstrBlDt = mstrBlDt;
			}else if(mstrBlDt.contains("/")) {
				this.mstrBlDt = mstrBlDt; //29122020
			}else {
				this.mstrBlDt = mstrBlDt.substring(4,8)+""+mstrBlDt.substring(2,4)+""+mstrBlDt.substring(0,2);
				
			}
			}catch (Exception e) {
				return;
			}
	}

   //Same as Item Number from current screen "Consolidated Indicator"
	public String getConsolidatedIndctr() {
		return consolidatedIndctr;
	}

	public void setConsolidatedIndctr(String consolidatedIndctr) {
//		consolidatedIndctr = FiledValidation.isNullAndSetlength(consolidatedIndctr, 4);
		this.consolidatedIndctr = consolidatedIndctr;
	}
    //Same as Item Number from current screen "Previous Declaration"
	public String getPrevDec() {
		return prevDec;
	}

	public void setPrevDec(String prevDec) {
		prevDec = FiledValidation.isNullAndSetlength(prevDec, 4);
		this.prevDec = prevDec;
	}
    //Same as Item Number from current screen  "Consolidator PAN"
	public String getConsolidatorPan() {
		return consolidatorPan;
	}

	public void setConsolidatorPan(String consolidatorPan) {
		consolidatorPan = FiledValidation.isNullAndSetlength(consolidatorPan, 35);
		this.consolidatorPan = consolidatorPan;
	}
}
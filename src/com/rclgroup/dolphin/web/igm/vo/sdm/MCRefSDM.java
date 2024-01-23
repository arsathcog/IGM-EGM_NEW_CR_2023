package com.rclgroup.dolphin.web.igm.vo.sdm;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class MCRefSDM {
	private Integer lineNo;
	private String mstrBlNo;
	private String mstrBlDt;
	private String consolidatedIndctr;
	private String prevDec;
	private String consolidatorPan;

	// Same as Item Number from current screen "Item Number"

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
		// mstrBlNo = FiledValidation.isNullAndSetlength(mstrBlNo, 7);
		this.mstrBlNo = mstrBlNo;
	}

	// Same as Item Number from current screen "BL_Date"
//		public String getMstrBlDt() {
//			return mstrBlDt;
//		}
//
//		public void setMstrBlDt(String mstrBlDt) {
//			if (mstrBlDt==null) {
//				this.mstrBlDt = mstrBlDt;
//			} else {
//				if (mstrBlDt.contains("/")) {
//					String dateArray[] = mstrBlDt.split("/");
//					mstrBlDt = dateArray[2] + dateArray[1] + dateArray[0];
//					this.mstrBlDt = mstrBlDt;
//				}
//			}
//		}

	// Same as Item Number from current screen "Consolidated Indicator"
	public String getConsolidatedIndctr() {
		return consolidatedIndctr;
	}

	public String getMstrBlDt() {
		return mstrBlDt;
	}

	public void setMstrBlDt(String mstrBlDt) {
		try {
		if(mstrBlDt == null || mstrBlDt.equals("")) {
		String day = mstrBlDt.substring(0, 2);
		String month = mstrBlDt.substring(2, 4);
		String year = mstrBlDt.substring(4);
		String newDate = year + month + day;
		this.mstrBlDt = mstrBlDt;
		}
		}catch (Exception e) {
			return;
		}
	}

	public void setConsolidatedIndctr(String consolidatedIndctr) {
		consolidatedIndctr = FiledValidation.isNullAndSetlength(consolidatedIndctr, 4);
		this.consolidatedIndctr = consolidatedIndctr;
	}

	// Same as Item Number from current screen "Previous Declaration"
	public String getPrevDec() {
		return prevDec;
	}

	public void setPrevDec(String prevDec) {
//			prevDec = FiledValidation.isNullAndSetlength(prevDec, 4);
		this.prevDec = prevDec;
	}

	// Same as Item Number from current screen "Consolidator PAN"
	public String getConsolidatorPan() {
		return consolidatorPan;
	}

	public void setConsolidatorPan(String consolidatorPan) {
		// consolidatorPan = FiledValidation.isNullAndSetlength(consolidatorPan, 35);
		this.consolidatorPan = consolidatorPan;
	}
}
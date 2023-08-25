package com.rclgroup.dolphin.web.igm.vo.sce;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class MCRefSCE {
	private Integer lineNo;
	private String mstrBlNo;
	private String mstrBlDt ;
	private String consolidatedIndctr;
	private String prevDec;
	private String consolidatorPan;
//	private String amendment;
//
//    public String getAmendment() {
//			return amendment;
//		}
//
//	public void setAmendment(String amendment) {
//			this.amendment = amendment;
//		}
//Same as Item Number from current screen "Item Number"

	
		/* int i = Integer.parseInt(lineNo); if (1.0 < i || i < 9999999.0) { lineNo =
		 * Integer.toString(i); lineNo = FiledValidation.isNullAndSetlength(lineNo,
		 * 100); }
		 */

	
   //Same as Item Number from current screen "BL#"

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
		this.mstrBlNo = mstrBlNo;
	}

   //Same as Item Number from current screen "BL_Date"
	
	
	
//	public String getMstrBlDt() {
//		return mstrBlDt;
//	}
//
//	public void setMstrBlDt(String mstrBlDt) {
//		if (mstrBlDt==null) {
//			this.mstrBlDt = mstrBlDt;
//		} else {
//			if (mstrBlDt.contains("/")) {
//				String dateArray[] = mstrBlDt.split("/");
//				mstrBlDt = dateArray[2] + dateArray[1] + dateArray[0];
//				this.mstrBlDt = mstrBlDt;
//			}
//		}
//	}

   //Same as Item Number from current screen "Consolidated Indicator"
	public String getConsolidatedIndctr() {
		return consolidatedIndctr;
	}

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


	public void setConsolidatedIndctr(String consolidatedIndctr) {
		consolidatedIndctr = FiledValidation.isNullAndSetlength(consolidatedIndctr, 4);
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
//		consolidatorPan = FiledValidation.isNullAndSetlength(consolidatorPan, 35);
		this.consolidatorPan = consolidatorPan;
	}
}
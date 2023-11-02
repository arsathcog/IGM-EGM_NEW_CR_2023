package com.rclgroup.dolphin.web.igm.vo.sam;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class ItnrySAM {

	private int prtOfCallSeqNmbr;
	private String prtOfCallCdd;
	private String prtOfCallName;
	private String nxtPrtOfCallCdd;
	private String nxtPrtOfCallName;
	private int modeOfTrnsprt;

	// This value is come from BL Json object "Port of call sequence numbe"

	public int getPrtOfCallSeqNmbr() {
		return prtOfCallSeqNmbr;
	}

	public void setPrtOfCallSeqNmbr(int prtOfCallSeqNmbr) {
		this.prtOfCallSeqNmbr = prtOfCallSeqNmbr;
	}

	// This value is come from BL Json object "Port of Call Coded"
	public String getPrtOfCallCdd() {
		return prtOfCallCdd;
	}

	public void setPrtOfCallCdd(String prtOfCallCdd) {
//		prtOfCallCdd = FiledValidation.isNullAndSetlength(prtOfCallCdd, 10);
		this.prtOfCallCdd = prtOfCallCdd;
	}

	// This value is come from BL Json object "portofCallname"
	public String getPrtOfCallName() {
		return prtOfCallName;
	}

	public void setPrtOfCallName(String prtOfCallName) {

		this.prtOfCallName = prtOfCallName;
	}

	// This value is come from BL Json object "Next port of call coded"
	public String getNxtPrtOfCallCdd() {
		return nxtPrtOfCallCdd;
	}

	public void setNxtPrtOfCallCdd(String nxtPrtOfCallCdd) {
//		nxtPrtOfCallCdd = FiledValidation.isNullAndSetlength(nxtPrtOfCallCdd, 10);
		this.nxtPrtOfCallCdd = nxtPrtOfCallCdd;
	}

	// This value is come from "of Next Port of Call,Text"
	public String getNxtPrtOfCallName() {
		return nxtPrtOfCallName;
	}

	public void setNxtPrtOfCallName(String nxtPrtOfCallName) {
		nxtPrtOfCallName = FiledValidation.isNullAndSetlength(nxtPrtOfCallName, 256);
		this.nxtPrtOfCallName = nxtPrtOfCallName;
	}

	public int getModeOfTrnsprt() {
		return modeOfTrnsprt;
	}

	public void setModeOfTrnsprt(int modeOfTrnsprt) {
		this.modeOfTrnsprt = modeOfTrnsprt;
	}

//This value is come from BL Json object   "modeofTransport"

}
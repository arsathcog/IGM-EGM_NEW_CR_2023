package com.rclgroup.dolphin.web.igm.vo.sei;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class DecRefSEI {
	
	private String msgTyp;
	private String prtofRptng;
	private String jobNo;
	private String jobDt;
	private String rptngEvent;
	private String mnfstNoRotnNo;
	private String mnfstDtRotnDt;
	private String vesselTypMvmt;
	
	private String dptrMnfstNo;
	private String dptrMnfstDt;

	public String getMsgTyp() {
		return msgTyp;
	}

	public void setMsgTyp(String msgTyp) {
		msgTyp = FiledValidation.isNullAndSetlength(msgTyp, 1);
		this.msgTyp = msgTyp;
	}

	// This value is come from BL Json object "porTrept"
	public String getPrtofRptng() {
		return prtofRptng;
	}

	public void setPrtofRptng(String prtofRptng) {
		prtofRptng = FiledValidation.isNullAndSetlength(prtofRptng, 6);
		this.prtofRptng = prtofRptng;
	}

	// This value is come from BL Json object "jobNum"
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		jobNo = FiledValidation.isNullAndSetlength(jobNo, 7);
		this.jobNo = jobNo;
	}

	// This value is come from BL Json object "jobDate"
	public String getJobDt() {
		return jobDt;
	}

	public void setJobDt(String jobDt) {
		if (jobDt == null) {
			this.jobDt = jobDt;
		} else {
			if (jobDt.contains("/")) {
				String dateArray[] = jobDt.split("/");
				jobDt = dateArray[2] + dateArray[1] + dateArray[0];
				this.jobDt = jobDt;
			}
			this.jobDt = jobDt;
		}
	}

	// This value is come from BL Json object "reportEvent"
	public String getRptngEvent() {
		return rptngEvent;
	}

	public void setRptngEvent(String rptngEvent) {
		rptngEvent = FiledValidation.isNullAndSetlength(rptngEvent, 4);
		this.rptngEvent = rptngEvent;
	}

	// This value is come from BL Json object "mainNocsnno"
	public String getMnfstNoRotnNo() {
		return mnfstNoRotnNo;
	}

	public void setMnfstNoRotnNo(String mnfstNoRotnNo) {
		mnfstNoRotnNo = FiledValidation.isNullAndSetlength(mnfstNoRotnNo, 100);
		this.mnfstNoRotnNo = mnfstNoRotnNo;
	}

	// This value is come from BL Json object "manifestdateCsndate"
	public String getMnfstDtRotnDt() {
		return mnfstDtRotnDt;
	}

	public void setMnfstDtRotnDt(String mnfstDtRotnDt) {
		if (mnfstDtRotnDt == null) {
			System.out.println("this.mnfstDtRotnDt 2 = " + mnfstDtRotnDt);
			this.mnfstDtRotnDt = "";
		} else {
			if (mnfstDtRotnDt.contains("/")) {
				String dateArray[] = mnfstDtRotnDt.split("/");
				mnfstDtRotnDt = dateArray[2] + dateArray[1] + dateArray[0];
				this.mnfstDtRotnDt = mnfstDtRotnDt;
			}
			this.mnfstDtRotnDt = mnfstDtRotnDt;
		}
	}

	// This value is come from BL Json object "vesType"
	public String getVesselTypMvmt() {
		return vesselTypMvmt;
	}

	public void setVesselTypMvmt(String vesselTypMvmt) {
		vesselTypMvmt = FiledValidation.isNullAndSetlength(vesselTypMvmt, 2);
		this.vesselTypMvmt = vesselTypMvmt;
	}

	public String getDptrMnfstNo() {
		return dptrMnfstNo;
	}

	public void setDptrMnfstNo(String dptrMnfstNo) {
		this.dptrMnfstNo = dptrMnfstNo;
	}

	public String getDptrMnfstDt() {
		return dptrMnfstDt;
	}

	public void setDptrMnfstDt(String dptrMnfstDt) {
		this.dptrMnfstDt = dptrMnfstDt;
	}

	@Override
	public String toString() {
		return "DecRefSEI [msgTyp=" + msgTyp + ", prtofRptng=" + prtofRptng + ", jobNo=" + jobNo + ", jobDt=" + jobDt
				+ ", rptngEvent=" + rptngEvent + ", mnfstNoRotnNo=" + mnfstNoRotnNo + ", mnfstDtRotnDt=" + mnfstDtRotnDt
				+ ", vesselTypMvmt=" + vesselTypMvmt + ", dptrMnfstNo=" + dptrMnfstNo + ", dptrMnfstDt=" + dptrMnfstDt
				+ "]";
	}

	
	
}
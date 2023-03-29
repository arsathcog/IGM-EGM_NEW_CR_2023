package com.rclgroup.dolphin.web.igm.vo.sce;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class TrnsprtEqmtSCE {
	private String eqmtSeqNo;
	private String eqmtId;
	private String eqmtTyp;
	private String eqmtSize;
	private String eqmtLoadStatus;
//	private String adtnlEqmtHold; not required(on hold)
//	private String finalDestLoc;  not required
//	private String eventDt; not required
	private String eqmtSealTyp; 
	private String eqmtSealNmbr;
//	private String otherEqmtId; //not required
// 	private String eqmtStatus; //not required
	private String socFlag;
    private String cntrAgntCd;
    private String cntrWeight;
	private String totalNmbrOfPkgs;
//	 private String amendment;
//
//		public String getAmendment() {
//			return amendment;
//		}
//
//		public void setAmendment(String amendment) {
//			this.amendment = amendment;
//		}


		// (Already in IGM screen in container section. Use same seq from there.)
		public String getEqmtSeqNo() {
			return eqmtSeqNo;
		}

		public void setEqmtSeqNo(String eqmtSeqNo) {
			this.eqmtSeqNo = eqmtSeqNo;
		}

		// (Container number from IGM screen.)>mandatory
		public String getEqmtId() {
			return eqmtId;
		}

		public void setEqmtId(String eqmtId) {
			eqmtId = FiledValidation.isNullAndSetlength(eqmtId, 17);
			this.eqmtId = eqmtId;
		}

		// This value is come from BL Json object "Equipment Type" (Value will be always
		// as CN)> mandatory
		public String getEqmtTyp() {
			return eqmtTyp;
		}

		public void setEqmtTyp(String eqmtTyp) {
			eqmtTyp = FiledValidation.isNullAndSetlength(eqmtTyp, 3);
			this.eqmtTyp = eqmtTyp;
		}

		// (This is ISO code from current IGM screen container section. Add size/type in
		// container ).>optional
		public String getEqmtSize() {
			return eqmtSize;
		}

		public void setEqmtSize(String eqmtSize) {
			eqmtSize = FiledValidation.isNullAndSetlength(eqmtSize, 4);
			this.eqmtSize = eqmtSize;
		}

		// This value is come from BL Json object "Equipment Load Status" (Add new
		// columnin container Load status(FCL/LCL/EMP). Default value will be FCL.)>
		// mandatory
		public String getEqmtLoadStatus() {
			return eqmtLoadStatus;
		}

		public void setEqmtLoadStatus(String eqmtLoadStatus) {
			eqmtLoadStatus = FiledValidation.isNullAndSetlength(eqmtLoadStatus, 3);
			this.eqmtLoadStatus = eqmtLoadStatus;
		}

		// (The Identifier forAdditional Equipment used for Hold )
//		public String getAdtnlEqmtHold() {
//			return adtnlEqmtHold;
//		}
//
//		public void setAdtnlEqmtHold(String adtnlEqmtHold) {
//			adtnlEqmtHold = FiledValidation.isNullAndSetlength(adtnlEqmtHold, 256);
//			this.adtnlEqmtHold = adtnlEqmtHold;
//		}

		// This value is come from BL Json object "Equipment seal type"
		public String getEqmtSealTyp() {
			return eqmtSealTyp;
		}

		public void setEqmtSealTyp(String eqmtSealTyp) {
			eqmtSealTyp = FiledValidation.isNullAndSetlength(eqmtSealTyp, 5);
			this.eqmtSealTyp = eqmtSealTyp;
		}

		// This value is come from BL Json object "Equipment Seal Number"
		public String getEqmtSealNmbr() {
			return eqmtSealNmbr;
		}

		public void setEqmtSealNmbr(String eqmtSealNmbr) {
			eqmtSealNmbr = FiledValidation.isNullAndSetlength(eqmtSealNmbr, 15);
			this.eqmtSealNmbr = eqmtSealNmbr;
		}

		// (The Identifier of Other Additional Equipment used for Carriage)
//		public String getOtherEqmtId() {
//			return otherEqmtId;
//		}
//
//		public void setOtherEqmtId(String otherEqmtId) {
//			otherEqmtId = FiledValidation.isNullAndSetlength(otherEqmtId, 256);
//			this.otherEqmtId = otherEqmtId;
//		}

		// This value is come from BL Json object "SOC Flagrl"
		public String getSocFlag() {
			return socFlag;
		}

		public void setSocFlag(String socFlag) {
			socFlag = FiledValidation.isNullAndSetlength(socFlag, 1);
			this.socFlag = socFlag;
		}

//		public String getFinalDestLoc() {
//			return finalDestLoc;
//		}
//
//		public void setFinalDestLoc(String finalDestLoc) {
//			this.finalDestLoc = finalDestLoc;
//		}

//		public String getEventDt() {
//			return eventDt;
//		}
//
//		public void setEventDt(String eventDt) {
//			this.eventDt = eventDt;
//		}

//		public String getEqmtStatus() {
//			return eqmtStatus;
//		}
//
//		public void setEqmtStatus(String eqmtStatus) {
//			this.eqmtStatus = eqmtStatus;
//		}

		public String getCntrAgntCd() {
			return cntrAgntCd;
		}

		public void setCntrAgntCd(String cntrAgntCd) {
			this.cntrAgntCd = cntrAgntCd;
		}

		public String getCntrWeight() {
			return cntrWeight;
		}

		public void setCntrWeight(String cntrWeight) {
			this.cntrWeight = cntrWeight;
		}

		public String getTotalNmbrOfPkgs() {
			return totalNmbrOfPkgs;
		}

		public void setTotalNmbrOfPkgs(String totalNmbrOfPkgs) {
			this.totalNmbrOfPkgs = totalNmbrOfPkgs;
		}

		// This value is come from BL Json object "containerAgentCode" (From current IGM
		// screen from container section)
		/*public String getCntrAgntCd() {
			return cntrAgntCd;
		}

		public void setCntrAgntCd(String cntrAgntCd) {
			cntrAgntCd = FiledValidation.isNullAndSetlength(cntrAgntCd, 1);
			this.cntrAgntCd = cntrAgntCd;
		}

		// This value is come from BL Json object "containerWeight" (Use from EZDL.)
		public String getCntrWeight() {
			return cntrWeight;
		}

		public void setCntrWeight(String cntrWeight) {
			this.cntrWeight = cntrWeight;
		}

		// This value is come from BL Json object "totalNumberOfPackagesInContainer"
		// (Packages in Container from IGM container section. )
		public String getTotalNmbrOfPkgs() {
			return totalNmbrOfPkgs;
		}

		public void setTotalNmbrOfPkgs(String totalNmbrOfPkgs) {
			this.totalNmbrOfPkgs = totalNmbrOfPkgs;
		}*/
		
}

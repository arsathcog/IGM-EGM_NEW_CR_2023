package com.rclgroup.dolphin.web.igm.vo.sce;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class VoyageDtlsSCE {
//	private String voyageNo; //not required
	private String cnvnceRefNmbr;
	private int totalNoOfTrnsprtEqmtMnfsted;
//	private String crgoDescCdd; not required 
//	private String briefCrgoDesc; not required 
	private int totalNmbrOfLines; 
//	private String exptdDtAndTimeOfArvl; not  required 
//	private String exptdDtAndTimeOfDptr; not required 
//	private String nmbrOfPsngrsMnfsted;  not required 
//	private String nmbrOfCrewMnfsted;  not required 
//	private String amendment;
//	private List<ShipItnrySCE> shipItnry;

//	public String getAmendment() {
//		return amendment;
//	}
//
//	public void setAmendment(String amendment) {
//		this.amendment = amendment;
//	}
	// (Same as Voyage from current screen )
//	public String getVoyageNo() {
//		return voyageNo;
//	}
//
//	public void setVoyageNo(String voyageNo) {
//		voyageNo = FiledValidation.isNullAndSetlength(voyageNo, 10);
//		this.voyageNo = voyageNo;
//	}

	// "conveyanceReferenceNumber"
	public String getCnvnceRefNmbr() {
		return cnvnceRefNmbr;
	}

	public void setCnvnceRefNmbr(String cnvnceRefNmbr) {
		cnvnceRefNmbr = FiledValidation.isNullAndSetlength(cnvnceRefNmbr, 35);
		this.cnvnceRefNmbr = cnvnceRefNmbr;
	}

	// "totalNoofTransportEquipmentManifested" (Total number of equipment's add in
	// screen Vessel/Voyage section)


	// "cargoDescription" (Add new column with free text Vessel/Voyage )
//	public String getCrgoDescCdd() {
//		return crgoDescCdd;
//	}
//
//	public void setCrgoDescCdd(String crgoDescCdd) {
//		crgoDescCdd = FiledValidation.isNullAndSetlength(crgoDescCdd, 3);
//		this.crgoDescCdd = crgoDescCdd;
//	}

	// "briefCargoDescription" (Add new column with free text Vessel/Voyage section)
//	public String getBriefCrgoDesc() {
//		return briefCrgoDesc;
//	}
//
//	public void setBriefCrgoDesc(String briefCrgoDesc) {
//		briefCrgoDesc = FiledValidation.isNullAndSetlength(briefCrgoDesc, 30);
//		this.briefCrgoDesc = briefCrgoDesc;
//	}

	// "totalNoofTransportEquipmentManifested" (Same as Total Items from current
	// screen)
	public int getTotalNmbrOfLines() {
		return totalNmbrOfLines;
	}

	public void setTotalNmbrOfLines(int totalNmbrOfLines) {
		this.totalNmbrOfLines = totalNmbrOfLines;
	}


	public int getTotalNoOfTrnsprtEqmtMnfsted() {
		return totalNoOfTrnsprtEqmtMnfsted;
	}

	public void setTotalNoOfTrnsprtEqmtMnfsted(int totalNoOfTrnsprtEqmtMnfsted) {
		this.totalNoOfTrnsprtEqmtMnfsted = totalNoOfTrnsprtEqmtMnfsted;
	}


//	public String getExptdDtAndTimeOfArvl() {
//		return exptdDtAndTimeOfArvl;
//	}
//
//	public void setExptdDtAndTimeOfArvl(String exptdDtAndTimeOfArvl) {
//		exptdDtAndTimeOfArvl = FiledValidation.isNullAndSetlength(exptdDtAndTimeOfArvl, 100);
//		this.exptdDtAndTimeOfArvl = exptdDtAndTimeOfArvl;
//	}
//
//	public String getExptdDtAndTimeOfDptr() {
//		return exptdDtAndTimeOfDptr;
//	}
//
//	public void setExptdDtAndTimeOfDptr(String exptdDtAndTimeOfDptr) {
//		this.exptdDtAndTimeOfDptr = exptdDtAndTimeOfDptr;
//	}

//	public String getNmbrOfPsngrsMnfsted() {
//		return nmbrOfPsngrsMnfsted;
//	}
//
//	public void setNmbrOfPsngrsMnfsted(String nmbrOfPsngrsMnfsted) {
//		this.nmbrOfPsngrsMnfsted = nmbrOfPsngrsMnfsted;
//	}

//	public String getNmbrOfCrewMnfsted() {
//		return nmbrOfCrewMnfsted;
//	}
//
//	public void setNmbrOfCrewMnfsted(String nmbrOfCrewMnfsted) {
//		this.nmbrOfCrewMnfsted = nmbrOfCrewMnfsted;
//	}
//	
//	public List<ShipItnrySCE> getShipItnry() {
//		return shipItnry;
//	}
//	public void setShipItnry(List<ShipItnrySCE> shipItnry) {
//		this.shipItnry = shipItnry;
//	}
// 	
}

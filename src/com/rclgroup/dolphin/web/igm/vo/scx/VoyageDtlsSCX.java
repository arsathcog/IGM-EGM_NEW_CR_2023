package com.rclgroup.dolphin.web.igm.vo.scx;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;
import com.rclgroup.dolphin.web.igm.vo.ShipItnry;
import com.rclgroup.dolphin.web.igm.vo.sca.ShipItnrySCA;

public class VoyageDtlsSCX {
//	private String voyageNo; 	//Not required
	private String cnvnceRefNmbr;
	private String totalNoOfTrnsprtEqmtMnfsted;
	//Not required
//	private String crgoDescCdd;
//	private String briefCrgoDesc;
	private String totalNmbrOfLines;
	//Not required
//	private String exptdDtAndTimeOfArvl;
//	private String exptdDtAndTimeOfDptr;
//	private String nmbrOfPsngrsMnfsted;
//	private String nmbrOfCrewMnfsted;
//	private String amendment;
	
	//not reqwuired
//	private String nmbrOfPsngrs;
//	private String nmbrOfCrew;
//	private String totalNoOfCntrsLanded;
//	private String totalNoOfCntrsLoaded;
//	private String totalNmbrOfPrsnsOnBoard;
//	private String totalNoOfTrnsprtEqmtRprtdOnArvlDptr;
//	private String totalNmbrOfTrnsprtContractsRprtdOnArvlDptr;

	
	//not required 
//	private List<ShipItnrySCX> shipItnry;
//	public List<ShipItnrySCX> getShipItnry() {
//		return shipItnry;
//	}
//
//	public void setShipItnry(List<ShipItnrySCX> shipItnry) {
//		this.shipItnry = shipItnry;
//	}
//	public String getAmendment() {
//		return amendment;
//	}
//
//	public void setAmendment(String amendment) {
//		this.amendment = amendment;
//	}
//	// (Same as Voyage from current screen )
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
	public String getTotalNoOfTrnsprtEqmtMnfsted() {
		return totalNoOfTrnsprtEqmtMnfsted;
	}

	public void setTotalNoOfTrnsprtEqmtMnfsted(String totalNoOfTrnsprtEqmtMnfsted) {
//		totalNoOfTrnsprtEqmtMnfsted = FiledValidation.isNullAndSetlength(totalNoOfTrnsprtEqmtMnfsted, 5);
		this.totalNoOfTrnsprtEqmtMnfsted = totalNoOfTrnsprtEqmtMnfsted;
	}

	// "cargoDescription" (Add new column with free text Vessel/Voyage )
//	public String getCrgoDescCdd() {
//		return crgoDescCdd;
//	}
//
//	public void setCrgoDescCdd(String crgoDescCdd) {
//		crgoDescCdd = FiledValidation.isNullAndSetlength(crgoDescCdd, 3);
//		this.crgoDescCdd = crgoDescCdd;
//	}
//
//	// "briefCargoDescription" (Add new column with free text Vessel/Voyage section)
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
	public String getTotalNmbrOfLines() {
		return totalNmbrOfLines;
	}

	public void setTotalNmbrOfLines(String totalNmbrOfLines) {

//		totalNmbrOfLines = FiledValidation.isNullAndSetlength(totalNmbrOfLines, 100);
		this.totalNmbrOfLines = totalNmbrOfLines;
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
//
//	public String getNmbrOfPsngrsMnfsted() {
//		return nmbrOfPsngrsMnfsted;
//	}
//
//	public void setNmbrOfPsngrsMnfsted(String nmbrOfPsngrsMnfsted) {
//		this.nmbrOfPsngrsMnfsted = nmbrOfPsngrsMnfsted;
//	}
//
//	public String getNmbrOfCrewMnfsted() {
//		return nmbrOfCrewMnfsted;
//	}
//
//	public void setNmbrOfCrewMnfsted(String nmbrOfCrewMnfsted) {
//		this.nmbrOfCrewMnfsted = nmbrOfCrewMnfsted;
//	}
	

	//not required 	

//	public String getNmbrOfPsngrs() {
//		return nmbrOfPsngrs;
//	}
//
//	public void setNmbrOfPsngrs(String nmbrOfPsngrs) {
//		this.nmbrOfPsngrs = nmbrOfPsngrs;
//	}
//
//	public String getNmbrOfCrew() {
//		return nmbrOfCrew;
//	}
//
//	public void setNmbrOfCrew(String nmbrOfCrew) {
//		this.nmbrOfCrew = nmbrOfCrew;
//	}
//
//	public String getTotalNoOfCntrsLanded() {
//		return totalNoOfCntrsLanded;
//	}
//
//	public void setTotalNoOfCntrsLanded(String totalNoOfCntrsLanded) {
//		this.totalNoOfCntrsLanded = totalNoOfCntrsLanded;
//	}
//
//	public String getTotalNoOfCntrsLoaded() {
//		return totalNoOfCntrsLoaded;
//	}
//
//	public void setTotalNoOfCntrsLoaded(String totalNoOfCntrsLoaded) {
//		this.totalNoOfCntrsLoaded = totalNoOfCntrsLoaded;
//	}
//
//	public String getTotalNmbrOfPrsnsOnBoard() {
//		return totalNmbrOfPrsnsOnBoard;
//	}
//
//	public void setTotalNmbrOfPrsnsOnBoard(String totalNmbrOfPrsnsOnBoard) {
//		this.totalNmbrOfPrsnsOnBoard = totalNmbrOfPrsnsOnBoard;
//	}
//
//	public String getTotalNoOfTrnsprtEqmtRprtdOnArvlDptr() {
//		return totalNoOfTrnsprtEqmtRprtdOnArvlDptr;
//	}
//
//	public void setTotalNoOfTrnsprtEqmtRprtdOnArvlDptr(String totalNoOfTrnsprtEqmtRprtdOnArvlDptr) {
//		this.totalNoOfTrnsprtEqmtRprtdOnArvlDptr = totalNoOfTrnsprtEqmtRprtdOnArvlDptr;
//	}
//
//	public String getTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr() {
//		return totalNmbrOfTrnsprtContractsRprtdOnArvlDptr;
//	}
//
//	public void setTotalNmbrOfTrnsprtContractsRprtdOnArvlDptr(String totalNmbrOfTrnsprtContractsRprtdOnArvlDptr) {
//		this.totalNmbrOfTrnsprtContractsRprtdOnArvlDptr = totalNmbrOfTrnsprtContractsRprtdOnArvlDptr;
//	}
	
}

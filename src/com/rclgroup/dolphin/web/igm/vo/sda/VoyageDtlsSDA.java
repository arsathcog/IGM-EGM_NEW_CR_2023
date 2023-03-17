package com.rclgroup.dolphin.web.igm.vo.sda;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;
import com.rclgroup.dolphin.web.igm.vo.ShipItnry;

public class VoyageDtlsSDA {
	private String voyageNo;
	private String cnvnceRefNmbr;
	private String totalNoOfTrnsprtEqmtMnfsted;
	private String crgoDescCdd;
	private String briefCrgoDesc;
	private String totalNmbrOfLines;
	private String exptdDtAndTimeOfArvl;
	private String exptdDtAndTimeOfDptr;
	private String nmbrOfPsngrsMnfsted;
	private String nmbrOfCrewMnfsted;
	private List<ShipItnrySDA> shipItnry;

	public List<ShipItnrySDA> getShipItnry() {
		return shipItnry;
	}

	public void setShipItnry(List<ShipItnrySDA> shipItnry2) {
		this.shipItnry = shipItnry2;
	}

	// (Same as Voyage from current screen )
	public String getVoyageNo() {
		return voyageNo;
	}

	public void setVoyageNo(String voyageNo) {
		voyageNo = FiledValidation.isNullAndSetlength(voyageNo, 10);
		this.voyageNo = voyageNo;
	}

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
		totalNoOfTrnsprtEqmtMnfsted = FiledValidation.isNullAndSetlength(totalNoOfTrnsprtEqmtMnfsted, 5);
		this.totalNoOfTrnsprtEqmtMnfsted = totalNoOfTrnsprtEqmtMnfsted;
	}

	// "cargoDescription" (Add new column with free text Vessel/Voyage )
	public String getCrgoDescCdd() {
		return crgoDescCdd;
	}

	public void setCrgoDescCdd(String crgoDescCdd) {
		crgoDescCdd = FiledValidation.isNullAndSetlength(crgoDescCdd, 3);
		this.crgoDescCdd = crgoDescCdd;
	}

	// "briefCargoDescription" (Add new column with free text Vessel/Voyage section)
	public String getBriefCrgoDesc() {
		return briefCrgoDesc;
	}

	public void setBriefCrgoDesc(String briefCrgoDesc) {
		briefCrgoDesc = FiledValidation.isNullAndSetlength(briefCrgoDesc, 30);
		this.briefCrgoDesc = briefCrgoDesc;
	}

	// "totalNoofTransportEquipmentManifested" (Same as Total Items from current
	// screen)
	public String getTotalNmbrOfLines() {
		return totalNmbrOfLines;
	}

	public void setTotalNmbrOfLines(String totalNmbrOfLines) {

		totalNmbrOfLines = FiledValidation.isNullAndSetlength(totalNmbrOfLines, 100);
		this.totalNmbrOfLines = totalNmbrOfLines;

	}

	// "expectedDate=TOFDept" (Same as Arrival Date Arrival Time From current screen
	// )
	public String getExptdDtAndTimeOfArvl() {
		return exptdDtAndTimeOfArvl;
	}

	public void setExptdDtAndTimeOfArvl(String exptdDtAndTimeOfArvl) {
		exptdDtAndTimeOfArvl = FiledValidation.isNullAndSetlength(exptdDtAndTimeOfArvl, 100);
		this.exptdDtAndTimeOfArvl = exptdDtAndTimeOfArvl;
	}

	// "expectedDate=TOFDept" (Add new field in vessel section same as ETA)
	public String getExptdDtAndTimeOfDptr() {
		return exptdDtAndTimeOfDptr;
	}

	public void setExptdDtAndTimeOfDptr(String exptdDtAndTimeOfDptr) {
		exptdDtAndTimeOfDptr = FiledValidation.isNullAndSetlength(exptdDtAndTimeOfDptr, 100);
		this.exptdDtAndTimeOfDptr = exptdDtAndTimeOfDptr;

	}

	// (NA. This is for passenger vessel)
	public String getNmbrOfPsngrsMnfsted() {
		return nmbrOfPsngrsMnfsted;
	}

	public void setNmbrOfPsngrsMnfsted(String nmbrOfPsngrsMnfsted) {
		nmbrOfPsngrsMnfsted = FiledValidation.isNullAndSetlength(nmbrOfPsngrsMnfsted, 100);
		this.nmbrOfPsngrsMnfsted = nmbrOfPsngrsMnfsted;
	}

	// "numberofCrewManifested" (Change Crew List Declaration field to free numeric
	// value in current screen)
	public String getNmbrOfCrewMnfsted() {
		return nmbrOfCrewMnfsted;
	}

	public void setNmbrOfCrewMnfsted(String nmbrOfCrewMnfsted) {

		nmbrOfCrewMnfsted = FiledValidation.isNullAndSetlength(nmbrOfCrewMnfsted, 100);

		this.nmbrOfCrewMnfsted = nmbrOfCrewMnfsted;
	}

}

package com.rclgroup.dolphin.web.igm.vo.sdm;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class VoyageDtlsSDM {
	private String voyageNo;
	private String cnvnceRefNmbr;
	private int totalNoOfTrnsprtEqmtMnfsted;
	private String crgoDescCdd;
	private String briefCrgoDesc;
	private int totalNmbrOfLines;
   // private String exptdDtAndTimeOfArvl;
   private String exptdDtAndTimeOfDptr;
	private int nmbrOfPsngrsMnfsted;
	private int nmbrOfCrewMnfsted;
	private List<ShipItnrySDM> shipItnry;
	
	public List<ShipItnrySDM> getShipItnry() {
		return shipItnry;
	}

	public void setShipItnry(List<ShipItnrySDM> shipItnry2) {
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

	// "cargoDescription" (Add new column with free text Vessel/Voyage )
	public String getCrgoDescCdd() {
		return crgoDescCdd;
	}

	public int getTotalNoOfTrnsprtEqmtMnfsted() {
		return totalNoOfTrnsprtEqmtMnfsted;
	}

	public void setTotalNoOfTrnsprtEqmtMnfsted(int totalNoOfTrnsprtEqmtMnfsted) {
		this.totalNoOfTrnsprtEqmtMnfsted = totalNoOfTrnsprtEqmtMnfsted;
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

	// "expectedDate=TOFDept" (Add new field in vessel section same as ETA)
	public String getExptdDtAndTimeOfDptr() {
		return exptdDtAndTimeOfDptr;
	}

	public int getTotalNmbrOfLines() {
		return totalNmbrOfLines;
	}

	public void setTotalNmbrOfLines(int totalNmbrOfLines) {
		this.totalNmbrOfLines = totalNmbrOfLines;
	}

	public void setExptdDtAndTimeOfDptr(String exptdDtAndTimeOfDptr) {
		exptdDtAndTimeOfDptr = FiledValidation.isNullAndSetlength(exptdDtAndTimeOfDptr, 100);
		this.exptdDtAndTimeOfDptr = exptdDtAndTimeOfDptr;

	}

	public int getNmbrOfPsngrsMnfsted() {
		return nmbrOfPsngrsMnfsted;
	}

	public void setNmbrOfPsngrsMnfsted(int nmbrOfPsngrsMnfsted) {
		this.nmbrOfPsngrsMnfsted = nmbrOfPsngrsMnfsted;
	}


	public int getNmbrOfCrewMnfsted() {
		return nmbrOfCrewMnfsted;
	}

	public void setNmbrOfCrewMnfsted(int nmbrOfCrewMnfsted) {
		this.nmbrOfCrewMnfsted = nmbrOfCrewMnfsted;
	}

	@Override
	public String toString() {
		return "VoyageDtlsSDM [voyageNo=" + voyageNo + ", cnvnceRefNmbr=" + cnvnceRefNmbr
				+ ", totalNoOfTrnsprtEqmtMnfsted=" + totalNoOfTrnsprtEqmtMnfsted + ", crgoDescCdd=" + crgoDescCdd
				+ ", briefCrgoDesc=" + briefCrgoDesc + ", totalNmbrOfLines=" + totalNmbrOfLines
				+ ", exptdDtAndTimeOfDptr=" + exptdDtAndTimeOfDptr + ", nmbrOfPsngrsMnfsted=" + nmbrOfPsngrsMnfsted
				+ ", nmbrOfCrewMnfsted=" + nmbrOfCrewMnfsted + ", shipItnry=" + shipItnry + "]";
	}

	 
}

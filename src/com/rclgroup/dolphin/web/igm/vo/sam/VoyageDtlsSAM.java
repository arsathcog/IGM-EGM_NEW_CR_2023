package com.rclgroup.dolphin.web.igm.vo.sam;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;
import com.rclgroup.dolphin.web.igm.vo.ShipItnry;

public class VoyageDtlsSAM {
	
	private String voyageNo;
	private String cnvnceRefNmbr;
	private int totalNoOfTrnsprtEqmtMnfsted;
	private String crgoDescCdd;
	private String briefCrgoDesc;
	private String totalNmbrOfLines;
	private String exptdDtAndTimeOfArvl;
	private String nmbrOfPsngrsMnfsted;
	private String nmbrOfCrewMnfsted;
	
	private List<ShipItnrySAM> shipItnry;



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
	public int getTotalNoOfTrnsprtEqmtMnfsted() {
		return totalNoOfTrnsprtEqmtMnfsted;
	}

	public void setTotalNoOfTrnsprtEqmtMnfsted(int totalNoOfTrnsprtEqmtMnfsted) {
		this.totalNoOfTrnsprtEqmtMnfsted = totalNoOfTrnsprtEqmtMnfsted;
	}

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
	public String getExptdDtAndTimeOfArvl() {
		return exptdDtAndTimeOfArvl;
	}

	public void setExptdDtAndTimeOfArvl(String exptdDtAndTimeOfArvl) {
		exptdDtAndTimeOfArvl = FiledValidation.isNullAndSetlength(exptdDtAndTimeOfArvl, 100);
		this.exptdDtAndTimeOfArvl = exptdDtAndTimeOfArvl;
	}

	public String getNmbrOfPsngrsMnfsted() {
		return nmbrOfPsngrsMnfsted;
	}

	public void setNmbrOfPsngrsMnfsted(String nmbrOfPsngrsMnfsted) {
		this.nmbrOfPsngrsMnfsted = nmbrOfPsngrsMnfsted;
	}

	public String getNmbrOfCrewMnfsted() {
		return nmbrOfCrewMnfsted;
	}

	public void setNmbrOfCrewMnfsted(String nmbrOfCrewMnfsted) {
		this.nmbrOfCrewMnfsted = nmbrOfCrewMnfsted;
	}
	
	public List<ShipItnrySAM> getShipItnry() {
		return shipItnry;
	}
	
	public void setShipItnry(List<ShipItnrySAM> shipItnry) {
		this.shipItnry = shipItnry;
	}

	@Override
	public String toString() {
		return "VoyageDtlsSAM [voyageNo=" + voyageNo + ", cnvnceRefNmbr=" + cnvnceRefNmbr
				+ ", totalNoOfTrnsprtEqmtMnfsted=" + totalNoOfTrnsprtEqmtMnfsted + ", crgoDescCdd=" + crgoDescCdd
				+ ", briefCrgoDesc=" + briefCrgoDesc + ", totalNmbrOfLines=" + totalNmbrOfLines
				+ ", exptdDtAndTimeOfArvl=" + exptdDtAndTimeOfArvl + ", nmbrOfPsngrsMnfsted=" + nmbrOfPsngrsMnfsted
				+ ", nmbrOfCrewMnfsted=" + nmbrOfCrewMnfsted + ", shipItnry=" + shipItnry + "]";
	}
	
}

package com.rclgroup.dolphin.web.igm.vo.sce;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class TrnsprtDocMsrSCE {
	private String nmbrOfPkgs;
	private String typsOfPkgs;
	private String marksNoOnPkgs;
	private String grossWeight;
// 	private String netWeight; no need
	private String unitOfWeight;
//	private String invoiceValueOfCnsgmt;
//	private String crncyCd;
	private String grossVolume;
	private String unitOfVolume;
	 private String amendment;
	public String getNmbrOfPkgs() {
		return nmbrOfPkgs;
	}
	public void setNmbrOfPkgs(String nmbrOfPkgs) {
		this.nmbrOfPkgs = nmbrOfPkgs;
	}
	public String getTypsOfPkgs() {
		return typsOfPkgs;
	}
	public void setTypsOfPkgs(String typsOfPkgs) {
		this.typsOfPkgs = typsOfPkgs;
	}
//  "marksNumberDtlstls"  (Use Marks & Numbers from IGM screen. First 512 characters) > mandatory
	public String getMarksNoOnPkgs() {
		return marksNoOnPkgs;
	}

	public void setMarksNoOnPkgs(String marksNoOnPkgs) {
		marksNoOnPkgs = FiledValidation.isNullAndSetlength(marksNoOnPkgs, 512);
		this.marksNoOnPkgs = marksNoOnPkgs;
	}
    
	  //This value is come from BL Json object  "GrossWeightVessel" , "Gross Cargo Weight BL level" (Use Gross Cargo Weight  of BL while generating json file)
	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		//grossWeight = FiledValidation.isNullAndSetlength(grossWeight, 512);
		this.grossWeight = grossWeight;
	}
     
	  //This value is come from BL Json object "NetWeightVessel" (Use general tab Net Cargo Weight of BL while generating JSON file)>optional
//	public String getNetWeight() {
//		return netWeight;
//	}
//
//	public void setNetWeight(String netWeight) {
//		this.netWeight = netWeight;
//	}
  //(Use System Parameters Maintenance under DocumentationUnit of Measurement) >optional
	public String getUnitOfWeight() {
		return unitOfWeight;
	}

	public void setUnitOfWeight(String unitOfWeight) {
		unitOfWeight = FiledValidation.isNullAndSetlength(unitOfWeight, 3);
		this.unitOfWeight = unitOfWeight;
	}

   // given in red color ms doc. not clear Guru
//	public String getInvoiceValueOfCnsgmt() {
//		return invoiceValueOfCnsgmt;
//	}
//
//	public void setInvoiceValueOfCnsgmt(String invoiceValueOfCnsgmt) {
//		this.invoiceValueOfCnsgmt = invoiceValueOfCnsgmt;
//	}
//  // given in red color ms doc. not clear Guru
//	public String getCrncyCd() {
//		return crncyCd;
//	}
//
//	public void setCrncyCd(String crncyCd) {
//		this.crncyCd = crncyCd;
//	}

	public String getGrossVolume() {
		return grossVolume;
	}

	public void setGrossVolume(String grossVolume) {
		this.grossVolume = grossVolume;
	}

	public String getUnitOfVolume() {
		return unitOfVolume;
	}

	public void setUnitOfVolume(String unitOfVolume) {
		this.unitOfVolume = unitOfVolume;
	}
	public String getAmendment() {
		return amendment;
	}
	public void setAmendment(String amendment) {
		this.amendment = amendment;
	}
 	
}

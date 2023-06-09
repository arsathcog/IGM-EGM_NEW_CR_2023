package com.rclgroup.dolphin.web.igm.vo.sdn;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class TrnsprtDocMsrSDN {
//	private String nmbrOfPkgs;
//	private String typsOfPkgs;
	private String marksNoOnPkgs;
	private String grossWeight;
	private String netWeight;
	private String unitOfWeight;
	private String invoiceValueOfCnsgmt;
	private String crncyCd;

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
		public String getNetWeight() {
			return netWeight;
		}

		public void setNetWeight(String netWeight) {
			this.netWeight = netWeight;
		}
	  //(Use System Parameters Maintenance under DocumentationUnit of Measurement) >optional
		public String getUnitOfWeight() {
			return unitOfWeight;
		}

		public void setUnitOfWeight(String unitOfWeight) {
			unitOfWeight = FiledValidation.isNullAndSetlength(unitOfWeight, 3);
			this.unitOfWeight = unitOfWeight;
		}

	   // given in red color ms doc. not clear Guru
		public String getInvoiceValueOfCnsgmt() {
			return invoiceValueOfCnsgmt;
		}

		public void setInvoiceValueOfCnsgmt(String invoiceValueOfCnsgmt) {
			this.invoiceValueOfCnsgmt = invoiceValueOfCnsgmt;
		}
	  // given in red color ms doc. not clear Guru
		public String getCrncyCd() {
			return crncyCd;
		}

		public void setCrncyCd(String crncyCd) {
			this.crncyCd = crncyCd;
		}    
}

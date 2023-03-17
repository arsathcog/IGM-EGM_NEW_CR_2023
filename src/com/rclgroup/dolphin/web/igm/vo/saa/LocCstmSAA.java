package com.rclgroup.dolphin.web.igm.vo.saa;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class LocCstmSAA {
	private String firstPrtOfEntry;
	private String destPrt;
	private String nxtPrtOfUnlading;
	private String typOfCrgo;
	private String itemTyp;
	private String crgoMvmt;
	private String natrOfCrgo;
//	public String splitIndctr;
//	public String nmbrOfPkgs;
//	public String typOfPackage;

//This value is come from BL Json object  "First Port of Entry/Last Port of Departure"
	public String getFirstPrtOfEntry() {
		return firstPrtOfEntry;
	}

	public void setFirstPrtOfEntry(String firstPrtOfEntry) {
		firstPrtOfEntry = FiledValidation.isNullAndSetlength(firstPrtOfEntry, 6);
		this.firstPrtOfEntry = firstPrtOfEntry;
	}

//This value is come from BL Json object "MC Location Customsl"
	public String getDestPrt() {
		return destPrt;
	}
  
	public void setDestPrt(String destPrt) {
		destPrt = FiledValidation.isNullAndSetlength(destPrt, 10);
		this.destPrt = destPrt;
	}
//This value is come from BL Json object "MC Location Customsl"
	public String getNxtPrtOfUnlading() {
		return nxtPrtOfUnlading;
	}

	public void setNxtPrtOfUnlading(String nxtPrtOfUnlading) {
		nxtPrtOfUnlading = FiledValidation.isNullAndSetlength(nxtPrtOfUnlading, 10);
		this.nxtPrtOfUnlading = nxtPrtOfUnlading;
	}
   //This value is come from BL Json object "Type Of Cargo"
	public String getTypOfCrgo() {
		return typOfCrgo;
	}

	public void setTypOfCrgo(String typOfCrgo) {
		typOfCrgo = FiledValidation.isNullAndSetlength(typOfCrgo, 2);
		this.typOfCrgo = typOfCrgo;
	}
//This value is come from BL Json object  "Item Type"
	public String getItemTyp() {
		return itemTyp;
	}

	public void setItemTyp(String itemTyp) {
		itemTyp = FiledValidation.isNullAndSetlength(itemTyp, 2);
		this.itemTyp = itemTyp;
	}

//This value is come from BL Json object  "Cargo Movement"
	public String getCrgoMvmt() {
		return crgoMvmt;
	}

	public void setCrgoMvmt(String crgoMvmt) {
		crgoMvmt = FiledValidation.isNullAndSetlength(crgoMvmt, 4);
		this.crgoMvmt = crgoMvmt;
	}

//This value is come from BL Json object "Cargo Nature"
	public String getNatrOfCrgo() {
		return natrOfCrgo;
	}

	public void setNatrOfCrgo(String natrOfCrgo) {
		natrOfCrgo = FiledValidation.isNullAndSetlength(natrOfCrgo, 4);
		this.natrOfCrgo = natrOfCrgo;
	}
}
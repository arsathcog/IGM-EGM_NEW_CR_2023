package com.rclgroup.dolphin.web.igm.vo.saa;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class PrevRefSAA {
	private String firstPrtOfEntry ;
	private String destPrt;
	private String nxtPrtOfUnlading;
	private String typOfCrgo;
	private String itemTyp;
	private String crgoMvmt;
	private String natrOfCrgo;
	private String splitIndctr;
	private String nmbrOfPkgs;
	private String typOfPackage;

//new doc feild     
	private String cinTyp;
	private String mcinPcin;
	private String csnSbmtdTyp;
	private String csnSbmtdBy;
	private String csnRptngTyp;
	private String csnSiteId;
	private String csnNmbr;
	private String csnDt;

    //This value is come from BL Json object "First Port of Entry/Last Port of Departure"
	public String getFirstPrtOfEntry() {
		return firstPrtOfEntry;
	}

	public void setFirstPrtOfEntry(String firstPrtOfEntry) {
		firstPrtOfEntry = FiledValidation.isNullAndSetlength(firstPrtOfEntry, 6);
		this.firstPrtOfEntry = firstPrtOfEntry;
	}

   //Same as CFS-Custom Code from current screen for LC movement . In case of TI, CFS-Custom Code will be blank. In such case destination port will same as  DEL of BL. 
	public String getDestPrt() {
		return destPrt;
	}

	public void setDestPrt(String destPrt) {
		destPrt = FiledValidation.isNullAndSetlength(destPrt, 10);
		this.destPrt = destPrt;
	}

    //red color not clreared by GURU
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

       //This value is come from BL Json object "Cargo Movement"
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

     //This value is come from BL Json object "Split Indicato"
	public String getSplitIndctr() {
		return splitIndctr;
	}

	public void setSplitIndctr(String splitIndctr) {
		splitIndctr = FiledValidation.isNullAndSetlength(splitIndctr, 2);
		this.splitIndctr = splitIndctr;
	}

      //This value is come from BL Json object  "Number of Packages"
	public String getNmbrOfPkgs() {
		return nmbrOfPkgs;
	}

	public void setNmbrOfPkgs(String nmbrOfPkgs) {
		this.nmbrOfPkgs = nmbrOfPkgs;
	}

     //This value is come from BL Json object "Type of Package"
	public String getTypOfPackage() {
		return typOfPackage;
	}

	public void setTypOfPackage(String typOfPackage) {
		typOfPackage = FiledValidation.isNullAndSetlength(typOfPackage, 4);
		this.typOfPackage = typOfPackage;
	}

	public String getCinTyp() {
		return cinTyp;
	}

	public void setCinTyp(String cinTyp) {
		this.cinTyp = cinTyp;
	}

	public String getMcinPcin() {
		return mcinPcin;
	}

	public void setMcinPcin(String mcinPcin) {
		this.mcinPcin = mcinPcin;
	}

	public String getCsnSbmtdTyp() {
		return csnSbmtdTyp;
	}

	public void setCsnSbmtdTyp(String csnSbmtdTyp) {
		this.csnSbmtdTyp = csnSbmtdTyp;
	}

	public String getCsnSbmtdBy() {
		return csnSbmtdBy;
	}

	public void setCsnSbmtdBy(String csnSbmtdBy) {
		this.csnSbmtdBy = csnSbmtdBy;
	}

	public String getCsnRptngTyp() {
		return csnRptngTyp;
	}

	public void setCsnRptngTyp(String csnRptngTyp) {
		this.csnRptngTyp = csnRptngTyp;
	}

	public String getCsnSiteId() {
		return csnSiteId;
	}

	public void setCsnSiteId(String csnSiteId) {
		this.csnSiteId = csnSiteId;
	}

	public String getCsnNmbr() {
		return csnNmbr;
	}

	public void setCsnNmbr(String csnNmbr) {
		this.csnNmbr = csnNmbr;
	}

	public String getCsnDt() {
		return csnDt;
	}

	public void setCsnDt(String csnDt) {
		this.csnDt = csnDt;
	}

}
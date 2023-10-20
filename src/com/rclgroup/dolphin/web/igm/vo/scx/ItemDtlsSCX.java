package com.rclgroup.dolphin.web.igm.vo.scx;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class ItemDtlsSCX {

	// # not given in scma

	private int crgoItemSeqNmbr;
	private String hsCd;
	private String crgoItemDesc;
	private String unoCd;
	private String imdgCd;
	private int nmbrOfPkgs;
	private String typOfPkgs;
//	 private String amendment;
//
//	  	public String getAmendment() {
//	  		return amendment;
//	  	}
//
//	  	public void setAmendment(String amendment) {
//	  		this.amendment = amendment;
//	  	}


	// This value is come from BL Json object "Cargo Item Sequence No"

	public int getCrgoItemSeqNmbr() {
		return crgoItemSeqNmbr;
	}

	public void setCrgoItemSeqNmbr(int crgoItemSeqNmbr) {
		this.crgoItemSeqNmbr = crgoItemSeqNmbr;
	}
	
	public String getHsCd() {
		return hsCd;
	}


	public void setHsCd(String hsCd) {
		hsCd = FiledValidation.isNullAndSetlength(hsCd, 8);
		this.hsCd = hsCd;
	}

	// This value is come from BL Json object "Cargo Item Description"
	public String getCrgoItemDesc() {
		return crgoItemDesc;
	}

	public void setCrgoItemDesc(String crgoItemDesc) {
		crgoItemDesc = FiledValidation.isNullAndSetlength(crgoItemDesc, 256);
		this.crgoItemDesc = crgoItemDesc;
	}

	// This value is come from BL Json object "UNO Cod"
	public String getUnoCd() {
		return unoCd;
	}

	public void setUnoCd(String unoCd) {
		if (unoCd == null || unoCd == "") {
			this.unoCd = "ZZZZZ";
		} else {
			unoCd = FiledValidation.isNullAndSetlength(unoCd, 5);
			this.unoCd = unoCd;
		}
	}

	// This value is come from BL Json object "IMDG Code"
	public String getImdgCd() {
		return imdgCd;
	}

	public void setImdgCd(String imdgCd) {
		if (imdgCd == null || imdgCd == "") {
			this.imdgCd = "ZZZ";
		} else {
			imdgCd = FiledValidation.isNullAndSetlength(imdgCd, 4);
			this.imdgCd = imdgCd;
		}
	}

	// added new field in current BL "Number of Packages"

	public int getNmbrOfPkgs() {
		return nmbrOfPkgs;
	}

	public void setNmbrOfPkgs(int nmbrOfPkgs) {
		this.nmbrOfPkgs = nmbrOfPkgs;
	}


	// added new field in current BL "Type of Package"
	public String getTypOfPkgs() {
		return typOfPkgs;
	}

	
	public void setTypOfPkgs(String typOfPkgs) {
//		typOfPkgs = FiledValidation.isNullAndSetlength(typOfPkgs, 3);
		this.typOfPkgs = typOfPkgs;
	}
}
package com.rclgroup.dolphin.web.igm.vo.sei;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class AuthPrsnSEI {
	
	private String sbmtrTyp ;
	private String sbmtrCd ;
	private String authReprsntvCd ;
	private String authSeaCarrierCd ;
	private String masterName ;
	private String trmnlOprtrCd ;


    //The value of "submitterType" current screen
	public String getSbmtrTyp() {
		return sbmtrTyp;
	}

	public void setSbmtrTyp(String sbmtrTyp) {
		sbmtrTyp = FiledValidation.isNullAndSetlength(sbmtrTyp, 4);
		this.sbmtrTyp = sbmtrTyp;
	}
  //The value of  "submitterCode" current screen
	public String getSbmtrCd() {
		return sbmtrCd;
	}

	public void setSbmtrCd(String sbmtrCd) {
		sbmtrCd = FiledValidation.isNullAndSetlength(sbmtrCd, 15);
		this.sbmtrCd = sbmtrCd;
	}
    //The value of  "authorizedRepresentativeCode" current screen
	public String getAuthReprsntvCd() {
		return authReprsntvCd;
	}

	public void setAuthReprsntvCd(String authReprsntvCd) {
		authReprsntvCd = FiledValidation.isNullAndSetlength(authReprsntvCd, 10);
		this.authReprsntvCd = authReprsntvCd;
	}
  
     //The value of  "authoseaCarcode" current screen
	public String getAuthSeaCarrierCd() {
		return authSeaCarrierCd;
	}

	public void setAuthSeaCarrierCd(String authSeaCarrierCd) {
		authSeaCarrierCd = FiledValidation.isNullAndSetlength(authSeaCarrierCd, 10);
		this.authSeaCarrierCd = authSeaCarrierCd;
	}
   //The value of  "MasterName" current screen
	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		masterName = FiledValidation.isNullAndSetlength(masterName, 30);
		this.masterName = masterName;
	}
    //The value of  "shippingLineBondNumber" current screen
 
     //The value of  "Custom Terminal Code" current screen  
	public String getTrmnlOprtrCd() {
		return trmnlOprtrCd;
	}

	public void setTrmnlOprtrCd(String trmnlOprtrCd) {
		trmnlOprtrCd = FiledValidation.isNullAndSetlength(trmnlOprtrCd, 10);
		this.trmnlOprtrCd = trmnlOprtrCd;
	}

	@Override
	public String toString() {
		return "AuthPrsnSEI [sbmtrTyp=" + sbmtrTyp + ", sbmtrCd=" + sbmtrCd + ", authReprsntvCd=" + authReprsntvCd
				+ ", authSeaCarrierCd=" + authSeaCarrierCd + ", masterName=" + masterName + ", trmnlOprtrCd="
				+ trmnlOprtrCd + "]";
	}
	
	
}

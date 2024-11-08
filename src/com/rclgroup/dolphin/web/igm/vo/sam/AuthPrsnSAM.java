package com.rclgroup.dolphin.web.igm.vo.sam;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class AuthPrsnSAM {
	
	private String  shpngLineBondNmbr;
	private String trmnlOprtrCd ;
	private String masterName ;
	private String authSeaCarrierCd ;
	private String shpngLineCd  ;
	private String authReprsntvCd ;
	private String sbmtrCd ;
	private String sbmtrTyp ;
//	private String authRepCd;


//	  public String getAuthRepCd() {
//		return authRepCd;
//	}
//
//	public void setAuthRepCd(String authRepCd) {
//		this.authRepCd = authRepCd;
//	}

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
	     //The value of  "shilinCode" current screen
		public String getShpngLineCd() {
			return shpngLineCd;
		}

		public void setShpngLineCd(String shpngLineCd) {
			shpngLineCd = FiledValidation.isNullAndSetlength(shpngLineCd, 10);
			this.shpngLineCd = shpngLineCd;
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
			/*
			 * public String getShpngLineBondNmbr() { return shpngLineBondNmbr; }
			 * 
			 * public void setShpngLineBondNmbr(String shpngLineBondNmbr) {
			 * shpngLineBondNmbr = FiledValidation.isNullAndSetlength(shpngLineBondNmbr,
			 * 10); this.shpngLineBondNmbr = shpngLineBondNmbr; }
			 */
	      //The value of  "Custom Terminal Code" current screen  
		public String getTrmnlOprtrCd() {
			return trmnlOprtrCd;
		}

		public void setTrmnlOprtrCd(String trmnlOprtrCd) {
			trmnlOprtrCd = FiledValidation.isNullAndSetlength(trmnlOprtrCd, 10);
			this.trmnlOprtrCd = trmnlOprtrCd;
		}
		

		public String getShpngLineBondNmbr() {
			return shpngLineBondNmbr;
		}

		public void setShpngLineBondNmbr(String shpngLineBondNmbr) {
			this.shpngLineBondNmbr = shpngLineBondNmbr;
		}

		@Override
		public String toString() {
			return "AuthPrsnSAM [shpngLineBondNmbr=" + shpngLineBondNmbr + ", trmnlOprtrCd=" + trmnlOprtrCd
					+ ", masterName=" + masterName + ", authSeaCarrierCd=" + authSeaCarrierCd + ", shpngLineCd="
					+ shpngLineCd + ", authReprsntvCd=" + authReprsntvCd + ", sbmtrCd=" + sbmtrCd + ", sbmtrTyp="
					+ sbmtrTyp + "]";
		}

		
   
		
		
}

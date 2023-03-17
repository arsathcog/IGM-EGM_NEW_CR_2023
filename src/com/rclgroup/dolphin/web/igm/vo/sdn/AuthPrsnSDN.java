package com.rclgroup.dolphin.web.igm.vo.sdn;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class AuthPrsnSDN {
	private String sbmtrTyp ;
	private String sbmtrCd ;
	private String authReprsntvCd ;
	private String authSeaCarrierCd ;
	private String shpngLineBondNmbr ;
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
	  
		public String getAuthSeaCarrierCd() {
			return authSeaCarrierCd;
		}

		public void setAuthSeaCarrierCd(String authSeaCarrierCd) {
			authSeaCarrierCd = FiledValidation.isNullAndSetlength(authSeaCarrierCd, 10);
			this.authSeaCarrierCd = authSeaCarrierCd;
		}
	     //The value of  "shippingLineBondNumber" current screen
		public String getShpngLineBondNmbr() {
			return shpngLineBondNmbr;
		}

		public void setShpngLineBondNmbr(String shpngLineBondNmbr) {
			shpngLineBondNmbr = FiledValidation.isNullAndSetlength(shpngLineBondNmbr, 10);
			this.shpngLineBondNmbr = shpngLineBondNmbr;
		}
	      //The value of  "Custom Terminal Code" current screen  
		public String getTrmnlOprtrCd() {
			return trmnlOprtrCd;
		}

		public void setTrmnlOprtrCd(String trmnlOprtrCd) {
			trmnlOprtrCd = FiledValidation.isNullAndSetlength(trmnlOprtrCd, 10);
			this.trmnlOprtrCd = trmnlOprtrCd;
		}
    
}

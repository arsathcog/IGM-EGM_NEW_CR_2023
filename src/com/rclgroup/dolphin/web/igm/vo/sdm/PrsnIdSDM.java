package com.rclgroup.dolphin.web.igm.vo.sdm;

import com.rclgroup.dolphin.web.igm.vo.FiledValidation;

public class PrsnIdSDM {
	
	private String prsnIdDocExpiryDt;
	private String prsnIdOrTravelDocIssuingNationCdd;
	private String prsnIdOrTravelDocNmbr;
	private String prsnIdOrTravelDocTypCdd;

	public String getPrsnIdDocExpiryDt() {
		return prsnIdDocExpiryDt;
	}

	public void setPrsnIdDocExpiryDt(String prsnIdDocExpiryDt) {
		if ("".equals("prsnIdDocExpiryDt")) {
			this.prsnIdDocExpiryDt = prsnIdDocExpiryDt;
		} else {
			if (prsnIdDocExpiryDt.contains("/")) {
				String dateArray[] = prsnIdDocExpiryDt.split("/");
				prsnIdDocExpiryDt = dateArray[2] + dateArray[1] + dateArray[0];
				this.prsnIdDocExpiryDt = prsnIdDocExpiryDt;
			}else if(prsnIdDocExpiryDt.contains("-")) {
				String dateArray[] = prsnIdDocExpiryDt.split("-");
				prsnIdDocExpiryDt = dateArray[2] + dateArray[1] + dateArray[0];
				this.prsnIdDocExpiryDt =prsnIdDocExpiryDt;
			}
		}
	}

	public String getPrsnIdOrTravelDocIssuingNationCdd() {
		return prsnIdOrTravelDocIssuingNationCdd;
	}

	public void setPrsnIdOrTravelDocIssuingNationCdd(String prsnIdOrTravelDocIssuingNationCdd) {
		prsnIdOrTravelDocIssuingNationCdd = FiledValidation.isNullAndSetlength(prsnIdOrTravelDocIssuingNationCdd, 2);
		this.prsnIdOrTravelDocIssuingNationCdd = prsnIdOrTravelDocIssuingNationCdd;
	}

	public String getPrsnIdOrTravelDocNmbr() {
		return prsnIdOrTravelDocNmbr;
	}

	public void setPrsnIdOrTravelDocNmbr(String prsnIdOrTravelDocNmbr) {
		prsnIdOrTravelDocNmbr = FiledValidation.isNullAndSetlength(prsnIdOrTravelDocNmbr, 70);
		this.prsnIdOrTravelDocNmbr = prsnIdOrTravelDocNmbr;
	}

	public String getPrsnIdOrTravelDocTypCdd() {
		return prsnIdOrTravelDocTypCdd;
	}

	public void setPrsnIdOrTravelDocTypCdd(String prsnIdOrTravelDocTypCdd) {
		prsnIdOrTravelDocTypCdd = FiledValidation.isNullAndSetlength(prsnIdOrTravelDocTypCdd, 3);
		this.prsnIdOrTravelDocTypCdd = prsnIdOrTravelDocTypCdd;
	}
}
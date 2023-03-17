package com.rclgroup.dolphin.web.igm.vo.scc;

public class VesselDtlsSCC {
	private String modeOfTrnsprt;
	private String typOfTrnsprtMeans;
	private String trnsprtMeansId;
	private String shipTyp;
	private String purposeOfCall;
	private String amendment;
	
	public String getAmendment() {
		return amendment;
	}

	public void setAmendment(String amendment) {
		this.amendment = amendment;
	}

//(Default value as sea =1, (1=Sea, 2=Rail, 3=Truck, 4=Air). Add new column in vessel voyage . user will select manually.). mandatory
	public String getModeOfTrnsprt() {
		return modeOfTrnsprt;
	}

	public void setModeOfTrnsprt(String modeOfTrnsprt) {
		this.modeOfTrnsprt = modeOfTrnsprt;
	}

	public String getTypOfTrnsprtMeans() {
		return typOfTrnsprtMeans;
	}

	public void setTypOfTrnsprtMeans(String typOfTrnsprtMeans) {
		this.typOfTrnsprtMeans = typOfTrnsprtMeans;
	}

	public String getTrnsprtMeansId() {
		return trnsprtMeansId;
	}

	public void setTrnsprtMeansId(String trnsprtMeansId) {
		this.trnsprtMeansId = trnsprtMeansId;
	}
//(Add Ship Type in current screen with free text. No default value).>optional
	public String getShipTyp() {
		return shipTyp;
	}

	public void setShipTyp(String shipTyp) {
		this.shipTyp = shipTyp;
	}

//  (Value will be always 1 as mentioned in PURP_CALL)
	public String getPurposeOfCall() {
		return purposeOfCall;
	}

	public void setPurposeOfCall(String purposeOfCall) {
		this.purposeOfCall = purposeOfCall;
	}
	
	

}
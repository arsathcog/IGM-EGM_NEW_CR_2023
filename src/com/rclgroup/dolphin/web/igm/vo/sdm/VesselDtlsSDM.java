package com.rclgroup.dolphin.web.igm.vo.sdm;

public class VesselDtlsSDM {
	private String modeOfTrnsprt;
	private String typOfTrnsprtMeans;
	private String trnsprtMeansId;
	private String shipTyp;
	private String purposeOfCall;
    private String natnltyOfShip;
	
	/*
	 * Before it was in yellow as guru asked to 
	 * implement all the yellow part we have added this 
	 * */
	private String prtOfRegistry;
	private String registryDt;
	private String registryNmbr;
	private String netTonnage;
	private String grossTonnage;



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
	public String getPurposeOfCall() {
		return purposeOfCall;
	}

	public void setPurposeOfCall(String purposeOfCall) {
		this.purposeOfCall = purposeOfCall;
	}
	
	

	public String getNatnltyOfShip() {
		return natnltyOfShip;
	}

	public void setNatnltyOfShip(String natnltyOfShip) {
		this.natnltyOfShip = natnltyOfShip;
	}

	public String getPrtOfRegistry() {
		return prtOfRegistry;
	}

	public void setPrtOfRegistry(String prtOfRegistry) {
		this.prtOfRegistry = prtOfRegistry;
	}

	public String getRegistryDt() {
		return registryDt;
	}

	public void setRegistryDt(String registryDt) {
		this.registryDt = registryDt;
	}

	public String getRegistryNmbr() {
		return registryNmbr;
	}

	public void setRegistryNmbr(String registryNmbr) {
		this.registryNmbr = registryNmbr;
	}

	public String getNetTonnage() {
		return netTonnage;
	}

	public void setNetTonnage(String netTonnage) {
		this.netTonnage = netTonnage;
	}

	public String getGrossTonnage() {
		return grossTonnage;
	}

	public void setGrossTonnage(String grossTonnage) {
		this.grossTonnage = grossTonnage;
	}

	@Override
	public String toString() {
		return "VesselDtlsSDM [modeOfTrnsprt=" + modeOfTrnsprt + ", typOfTrnsprtMeans=" + typOfTrnsprtMeans
				+ ", trnsprtMeansId=" + trnsprtMeansId + ", shipTyp=" + shipTyp + ", purposeOfCall=" + purposeOfCall
				+ ", natnltyOfShip=" + natnltyOfShip + ", prtOfRegistry=" + prtOfRegistry + ", registryDt=" + registryDt
				+ ", registryNmbr=" + registryNmbr + ", netTonnage=" + netTonnage + ", grossTonnage=" + grossTonnage
				+ "]";
	}

		
}
package com.rclgroup.dolphin.web.igm.vo.sdm;

public class DigSignSDM{
	private String startSignature;
	private String startCertificate;
	private String signerVersion ;

   
	public String getStartSignature() {
		return startSignature;
	}

	public void setStartSignature(String startSignature) {
		this.startSignature = startSignature;
	}

	public String getStartCertificate() {
		return startCertificate;
	}

	public void setStartCertificate(String startCertificate) {
		this.startCertificate = startCertificate;
	}

	public String getSignerVersion() {
		return signerVersion;
	}

	public void setSignerVersion(String signerVersion) {
		this.signerVersion = signerVersion;
	}
}

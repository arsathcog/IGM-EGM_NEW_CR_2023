package com.rclgroup.dolphin.web.igm.vo.scd;

public class DigSignSCD{
	private String startSignature;
	private String startCertificate;
	private String signerVersion ;
   private String amendment;
	
	public String getAmendment() {
		return amendment;
	}

	public void setAmendment(String amendment) {
		this.amendment = amendment;
	}

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

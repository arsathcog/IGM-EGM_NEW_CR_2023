package com.rclgroup.dolphin.web.igm.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class ImportGeneralManifestInput {
	
	/** The service. */
	private  ImportGeneralManifestMod service;

	 
	
	private String sequence;
	
	
	/** The bls. */
	private List<ImportGeneralManifestMod> bls;

	private String saveBlPhase;
	
	
	public ImportGeneralManifestMod getService() {
		return service;
	}

	public void setService(ImportGeneralManifestMod service) {
		this.service = service;
	}

	public String getSequence() {
		return sequence;
	}
	
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public List<ImportGeneralManifestMod> getBls() {
		return bls;
	}

	public void setBls(List<ImportGeneralManifestMod> bls) {
		this.bls = bls;
	}

	public String getSaveBlPhase() {
		return saveBlPhase;
	}

	public void setSaveBlPhase(String saveBlPhase) {
		this.saveBlPhase = saveBlPhase;
	}

	@Override
	public String toString() {
		return "ImportGeneralManifestInput [service=" + service + ", sequence=" + sequence + ", bls=" + bls
				+ ", saveBlPhase=" + saveBlPhase + "]";
	}
	
	
	

}

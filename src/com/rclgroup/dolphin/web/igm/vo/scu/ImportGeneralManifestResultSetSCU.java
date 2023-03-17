package com.rclgroup.dolphin.web.igm.vo.scu;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

/**
 * The Class ImportGeneralManifestResultSetSDN.
 */
public class ImportGeneralManifestResultSetSCU {

	/** The service. */
	private ImportGeneralManifestMod service;

	/** The sequence. */
	private int sequence;

	/** The bls. */
	private List<ImportGeneralManifestMod> BLS;

	/**
	 * Instantiates a new import general manifest result set.
	 */
	public ImportGeneralManifestResultSetSCU() {
		super();
	}

	/**
	 * Instantiates a new import general manifest result set.
	 *
	 * @param service  the service
	 * @param sequence the sequence
	 * @param bLS      the b LS
	 */
	public ImportGeneralManifestResultSetSCU(ImportGeneralManifestMod service, int sequence,
			List<ImportGeneralManifestMod> bLS) {
		super();
		this.service = service;
		this.sequence = sequence;
		BLS = bLS;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public ImportGeneralManifestMod getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(ImportGeneralManifestMod service) {
		this.service = service;
	}

	/**
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * Sets the sequence.
	 *
	 * @param sequence the new sequence
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * Gets the bls.
	 *
	 * @return the bls
	 */
	public List<ImportGeneralManifestMod> getBLS() {
		return BLS;
	}

	/**
	 * Sets the bls.
	 *
	 * @param bLS the new bls
	 */
	public void setBLS(List<ImportGeneralManifestMod> bLS) {
		BLS = bLS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ImportGeneralManifestResultSet [service=" + service + ", sequence=" + sequence + ", BLS=" + BLS + "]";
	}

}

package com.rclgroup.dolphin.web.igm.vo.sei;

import java.util.List;

public class MasterSEI {
	
	private DecRefSEI decRef;
	private AuthPrsnSEI authPrsn;
	private VesselDtlsSEI vesselDtls;
	private List<TmSuprtDocsSEI> tmSuprtDocs;
	private List<TmAdtnlDecSEI> tmAdtnlDec ;
	private List<PrsnOnBoardSEI> prsnOnBoard;
	private ArvlDtlsSEI arvlDtls;
	
	//private List<VoyageDtlsSEI> voyageDtls;
	//private List<MastrCnsgmtDecSEI> mastrCnsgmtDec;
	//private List<VoyageTransportEquipmentSEI> voyageTransportEquipment;
	 
	public List<PrsnOnBoardSEI> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSEI> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<TmSuprtDocsSEI> getTmSuprtDocs() {
		return tmSuprtDocs;
	}
	public void setTmSuprtDocs(List<TmSuprtDocsSEI> tmSuprtDocs) {
		this.tmSuprtDocs = tmSuprtDocs;
	}
	public List<TmAdtnlDecSEI> getTmAdtnlDec() {
		return tmAdtnlDec;
	}
	public void setTmAdtnlDec(List<TmAdtnlDecSEI> tmAdtnlDec) {
		this.tmAdtnlDec = tmAdtnlDec;
	}
	
	
	public DecRefSEI getDecRef() {
		return decRef;
	}
	public void setDecRef(DecRefSEI decRef) {
		this.decRef = decRef;
	}
	public AuthPrsnSEI getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(AuthPrsnSEI authPrsn) {
		this.authPrsn = authPrsn;
	}
	public VesselDtlsSEI getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(VesselDtlsSEI vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public ArvlDtlsSEI getArvlDtls() {
		return arvlDtls;
	}
	public void setArvlDtls(ArvlDtlsSEI arvlDtls) {
		this.arvlDtls = arvlDtls;
	}
	@Override
	public String toString() {
		return "MasterSEI [decRef=" + decRef + ", authPrsn=" + authPrsn + ", vesselDtls=" + vesselDtls
				+ ", tmSuprtDocs=" + tmSuprtDocs + ", tmAdtnlDec=" + tmAdtnlDec + ", prsnOnBoard=" + prsnOnBoard
				+ ", arvlDtls=" + arvlDtls + "]";
	}
	
}

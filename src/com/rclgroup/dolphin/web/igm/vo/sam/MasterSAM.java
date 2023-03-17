package com.rclgroup.dolphin.web.igm.vo.sam;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSAM {
	
	private DecRefSAM  decRef;
	private AuthPrsnSAM authPrsn;
	private VesselDtlsSAM vesselDtls;
	private VoyageDtlsSAM voyageDtls;
	private List<MastrCnsgmtDecSAM> mastrCnsgmtDec;
	private List<PrsnOnBoardSAM> prsnOnBoard;
	private List<VoyageTransportEquipmentSAM> voyageTransportEquipment;
//	private List<TmSuprtDocsSAM> tmSuprtDocs;	//TODO
//	private List<TmAdtnlDecSAM> tmAdtnlDec;		//TODO

	 
	public AuthPrsnSAM getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(AuthPrsnSAM authPrsn) {
		this.authPrsn = authPrsn;
	}
	public VesselDtlsSAM getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(VesselDtlsSAM vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public VoyageDtlsSAM getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(VoyageDtlsSAM voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSAM> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSAM> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSAM> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSAM> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSAM> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSAM> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
//	public List<TmSuprtDocsSAM> getTmSuprtDocs() {
//		return tmSuprtDocs;
//	}
//	public void setTmSuprtDocs(List<TmSuprtDocsSAM> tmSuprtDocs) {
//		this.tmSuprtDocs = tmSuprtDocs;
//	}
//	public List<TmAdtnlDecSAM> getTmAdtnlDec() {
//		return tmAdtnlDec;
//	}
//	public void setTmAdtnlDec(List<TmAdtnlDecSAM> tmAdtnlDec) {
//		this.tmAdtnlDec = tmAdtnlDec;
//	}
	public DecRefSAM getDecRef() {
		return decRef;
	}
	public void setDecRef(DecRefSAM decRef) {
		this.decRef = decRef;
	}
	@Override
	public String toString() {
		return "MasterSAM [decRef=" + decRef + ", authPrsn=" + authPrsn + ", vesselDtls=" + vesselDtls + ", voyageDtls="
				+ voyageDtls + ", mastrCnsgmtDec=" + mastrCnsgmtDec + ", prsnOnBoard=" + prsnOnBoard
				+ ", voyageTransportEquipment=" + voyageTransportEquipment + "]";
	}
	

	
}

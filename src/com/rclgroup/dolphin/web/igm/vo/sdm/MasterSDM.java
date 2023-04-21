package com.rclgroup.dolphin.web.igm.vo.sdm;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSDM {
	
	private DecRefSDM decRef;
	private AuthPrsnSDM	 authPrsn;
	private VesselDtlsSDM vesselDtls;
	private VoyageDtlsSDM voyageDtls;
	private List<MastrCnsgmtDecSDM> mastrCnsgmtDec;
	private List<PrsnOnBoardSDM> prsnOnBoard;
	private List<ShipStoresSDM> shipStore;
	private List<VoyageTransportEquipmentSDM> voyageTransportEquipment;
//	private List<TmSuprtDocsSDM> tmSuprtDocs;
//	private List<TmAdtnlDecSDM> tmAdtnlDec;
	
	
	 
	public List<ShipStoresSDM> getShipStore() {
		return shipStore;
	}
	public void setShipStore(List<ShipStoresSDM> shipStore) {
		this.shipStore = shipStore;
	}
	public AuthPrsnSDM getAuthPrsn() {
		return authPrsn;
	}
	public DecRefSDM getDecRef() {
		return decRef;
	}
	public void setDecRef(DecRefSDM decRef) {
		this.decRef = decRef;
	}
	public void setAuthPrsn(AuthPrsnSDM authPrsn) {
		this.authPrsn = authPrsn;
	}
	public VesselDtlsSDM getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(VesselDtlsSDM vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public VoyageDtlsSDM getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(VoyageDtlsSDM voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSDM> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSDM> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSDM> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSDM> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSDM> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSDM> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
//	public List<TmSuprtDocsSDM> getTmSuprtDocs() {
//		return tmSuprtDocs;
//	}
//	public void setTmSuprtDocs(List<TmSuprtDocsSDM> tmSuprtDocs) {
//		this.tmSuprtDocs = tmSuprtDocs;
//	}
//	public List<TmAdtnlDecSDM> getTmAdtnlDec() {
//		return tmAdtnlDec;
//	}
//	public void setTmAdtnlDec(List<TmAdtnlDecSDM> tmAdtnlDec) {
//		this.tmAdtnlDec = tmAdtnlDec;
//	}
	@Override
	public String toString() {
		return "MasterSDM [decRef=" + decRef + ", authPrsn=" + authPrsn + ", vesselDtls=" + vesselDtls + ", voyageDtls="
				+ voyageDtls + ", mastrCnsgmtDec=" + mastrCnsgmtDec + ", prsnOnBoard=" + prsnOnBoard + ", shipStore="
				+ shipStore + ", voyageTransportEquipment=" + voyageTransportEquipment+ "]";
	}
	
	 
}

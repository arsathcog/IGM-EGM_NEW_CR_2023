package com.rclgroup.dolphin.web.igm.vo.saa;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSAA {
	private List<DecRefSAA> decRef;
	private List<AuthPrsnSAA> authPrsn;
	private List<VesselDtlsSAA> vesselDtls;
	private List<VoyageDtlsSAA> voyageDtls;
	private List<MastrCnsgmtDecSAA> mastrCnsgmtDec;
	private List<PrsnOnBoardSAA> prsnOnBoard;
	private List<VoyageTransportEquipmentSAA> voyageTransportEquipment;
	private List<ShipStoresSAA> shipStores;
	private List<TmSuprtDocsSAA> tmSuprtDocs;
	private List<TmAdtnlDecSAA> tmAdtnlDec;
	
	public List<DecRefSAA> getDecRef() {
		return decRef;
	}
	public void setDecRef(List<DecRefSAA> decRef) {
		this.decRef = decRef;
	}
	public List<AuthPrsnSAA> getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(List<AuthPrsnSAA> authPrsn) {
		this.authPrsn = authPrsn;
	}
	public List<VesselDtlsSAA> getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(List<VesselDtlsSAA> vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public List<VoyageDtlsSAA> getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(List<VoyageDtlsSAA> voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSAA> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSAA> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSAA> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSAA> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSAA> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSAA> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
	public List<ShipStoresSAA> getShipStores() {
		return shipStores;
	}
	public void setShipStores(List<ShipStoresSAA> shipStores) {
		this.shipStores = shipStores;
	}
	public List<TmSuprtDocsSAA> getTmSuprtDocs() {
		return tmSuprtDocs;
	}
	public void setTmSuprtDocs(List<TmSuprtDocsSAA> tmSuprtDocs) {
		this.tmSuprtDocs = tmSuprtDocs;
	}
	public List<TmAdtnlDecSAA> getTmAdtnlDec() {
		return tmAdtnlDec;
	}
	public void setTmAdtnlDec(List<TmAdtnlDecSAA> tmAdtnlDec) {
		this.tmAdtnlDec = tmAdtnlDec;
	}


}

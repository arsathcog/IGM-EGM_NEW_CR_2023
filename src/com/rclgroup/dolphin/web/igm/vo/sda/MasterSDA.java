package com.rclgroup.dolphin.web.igm.vo.sda;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSDA {
	private List<DecRefSDA> decRef;
	private List<AuthPrsnSDA> authPrsn;
	private List<VesselDtlsSDA> vesselDtls;
	private List<VoyageDtlsSDA> voyageDtls;
	private List<MastrCnsgmtDecSDA> mastrCnsgmtDec;
	private List<PrsnOnBoardSDA> prsnOnBoard;
	private List<VoyageTransportEquipmentSDA> voyageTransportEquipment;
	private List<ShipStoresSDA> shipStores;
	private List<TmSuprtDocsSDA> tmSuprtDocs;
	private List<TmAdtnlDecSDA> tmAdtnlDec;
	
	public List<DecRefSDA> getDecRef() {
		return decRef;
	}
	public void setDecRef(List<DecRefSDA> decRef) {
		this.decRef = decRef;
	}
	public List<AuthPrsnSDA> getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(List<AuthPrsnSDA> authPrsn) {
		this.authPrsn = authPrsn;
	}
	public List<VesselDtlsSDA> getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(List<VesselDtlsSDA> vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public List<VoyageDtlsSDA> getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(List<VoyageDtlsSDA> voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSDA> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSDA> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSDA> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSDA> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSDA> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSDA> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
	public List<ShipStoresSDA> getShipStores() {
		return shipStores;
	}
	public void setShipStores(List<ShipStoresSDA> shipStores) {
		this.shipStores = shipStores;
	}
	public List<TmSuprtDocsSDA> getTmSuprtDocs() {
		return tmSuprtDocs;
	}
	public void setTmSuprtDocs(List<TmSuprtDocsSDA> tmSuprtDocs) {
		this.tmSuprtDocs = tmSuprtDocs;
	}
	public List<TmAdtnlDecSDA> getTmAdtnlDec() {
		return tmAdtnlDec;
	}
	public void setTmAdtnlDec(List<TmAdtnlDecSDA> tmAdtnlDec) {
		this.tmAdtnlDec = tmAdtnlDec;
	}


}

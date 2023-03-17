package com.rclgroup.dolphin.web.igm.vo.scx;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSCX {
	private List<DecRefSCX> decRef;
	private List<AuthPrsnSCX> authPrsn;
	private List<VesselDtlsSCX> vesselDtls;
	private List<VoyageDtlsSCX> voyageDtls;
	private List<MastrCnsgmtDecSCX> mastrCnsgmtDec;
	private List<PrsnOnBoardSCX> prsnOnBoard;
	private List<VoyageTransportEquipmentSCX> voyageTransportEquipment;
	private List<ShipStoresSCX> shipStores;
	
	public List<DecRefSCX> getDecRef() {
		return decRef;
	}
	public void setDecRef(List<DecRefSCX> decRef) {
		this.decRef = decRef;
	}
	public List<AuthPrsnSCX> getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(List<AuthPrsnSCX> authPrsn) {
		this.authPrsn = authPrsn;
	}
	public List<VesselDtlsSCX> getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(List<VesselDtlsSCX> vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public List<VoyageDtlsSCX> getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(List<VoyageDtlsSCX> voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSCX> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSCX> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSCX> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSCX> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSCX> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSCX> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
	public List<ShipStoresSCX> getShipStores() {
		return shipStores;
	}
	public void setShipStores(List<ShipStoresSCX> shipStores) {
		this.shipStores = shipStores;
	}
}

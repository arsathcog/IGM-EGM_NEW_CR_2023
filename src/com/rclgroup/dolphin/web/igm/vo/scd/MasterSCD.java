package com.rclgroup.dolphin.web.igm.vo.scd;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSCD {
	private List<DecRefSCD> decRef;
	private List<AuthPrsnSCD> authPrsn;
	private List<VesselDtlsSCD> vesselDtls;
	private List<VoyageDtlsSCD> voyageDtls;
	private List<MastrCnsgmtDecSCD> mastrCnsgmtDec;
	private List<PrsnOnBoardSCD> prsnOnBoard;
	private List<VoyageTransportEquipmentSCD> voyageTransportEquipment;
	private List<ShipStoresSCD> shipStores;
	
	public List<DecRefSCD> getDecRef() {
		return decRef;
	}
	public void setDecRef(List<DecRefSCD> decRef) {
		this.decRef = decRef;
	}
	public List<AuthPrsnSCD> getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(List<AuthPrsnSCD> authPrsn) {
		this.authPrsn = authPrsn;
	}
	public List<VesselDtlsSCD> getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(List<VesselDtlsSCD> vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public List<VoyageDtlsSCD> getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(List<VoyageDtlsSCD> voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSCD> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSCD> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSCD> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSCD> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSCD> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSCD> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
	public List<ShipStoresSCD> getShipStores() {
		return shipStores;
	}
	public void setShipStores(List<ShipStoresSCD> shipStores) {
		this.shipStores = shipStores;
	}
	
}

package com.rclgroup.dolphin.web.igm.vo.sca;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSCA {
	private List<DecRefSCA> decRef;
	private List<AuthPrsnSCA> authPrsn;
	private List<VesselDtlsSCA> vesselDtls;
	private List<VoyageDtlsSCA> voyageDtls;
	private List<MastrCnsgmtDecSCA> mastrCnsgmtDec;
	private List<PrsnOnBoardSCA> prsnOnBoard;
	private List<VoyageTransportEquipmentSCA> voyageTransportEquipment;
	private List<ShipStoresSCA> shipStores;
	
	public List<DecRefSCA> getDecRef() {
		return decRef;
	}
	public void setDecRef(List<DecRefSCA> decRef) {
		this.decRef = decRef;
	}
	public List<AuthPrsnSCA> getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(List<AuthPrsnSCA> authPrsn) {
		this.authPrsn = authPrsn;
	}
	public List<VesselDtlsSCA> getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(List<VesselDtlsSCA> vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public List<VoyageDtlsSCA> getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(List<VoyageDtlsSCA> voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSCA> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSCA> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSCA> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSCA> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSCA> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSCA> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
	public List<ShipStoresSCA> getShipStores() {
		return shipStores;
	}
	public void setShipStores(List<ShipStoresSCA> shipStores) {
		this.shipStores = shipStores;
	}
}

package com.rclgroup.dolphin.web.igm.vo.sce;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;

public class MasterSCE {
	private DecRefSCE decRef;
	private AuthPrsnSCE authPrsn;
	private VesselDtlsSCE vesselDtls;
	private VoyageDtlsSCE voyageDtls;
	private List<MastrCnsgmtDecSCE> mastrCnsgmtDec;
//	private List<PrsnOnBoardSCE> prsnOnBoard;
//	private List<VoyageTransportEquipmentSCE> voyageTransportEent;
//	private List<ShipStoresSCE> shipStores;
	
	public DecRefSCE getDecRef() {
		return decRef;
	}
	public void setDecRef(DecRefSCE decRef) {
		this.decRef = decRef;
	}
	public AuthPrsnSCE getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(AuthPrsnSCE authPrsn) {
		this.authPrsn = authPrsn;
	}
	public VesselDtlsSCE getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(VesselDtlsSCE vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
//	public List<VoyageTransportEquipmentSCE> getVoyageTransportEent() {
//		return voyageTransportEent;
//	}
//	public void setVoyageTransportEent(List<VoyageTransportEquipmentSCE> voyageTransportEent) {
//		this.voyageTransportEent = voyageTransportEent;
//	}
	public VoyageDtlsSCE getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(VoyageDtlsSCE voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSCE> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSCE> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
//	public List<PrsnOnBoardSCE> getPrsnOnBoard() {
//		return prsnOnBoard;
//	}
//	public void setPrsnOnBoard(List<PrsnOnBoardSCE> prsnOnBoard) {
//		this.prsnOnBoard = prsnOnBoard;
//	}
//	public List<VoyageTransportEquipmentSCE> getVoyageTransportEquipment() {
//		return voyageTransportEquipment;
//	}
//	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSCE> voyageTransportEquipment) {
//		this.voyageTransportEquipment = voyageTransportEquipment;
//	}
//	public List<ShipStoresSCE> getShipStores() {
//		return shipStores;
//	}
//	public void setShipStores(List<ShipStoresSCE> shipStores) {
//		this.shipStores = shipStores;
//	}
	
}

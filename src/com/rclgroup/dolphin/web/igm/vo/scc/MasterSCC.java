package com.rclgroup.dolphin.web.igm.vo.scc;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;
import com.rclgroup.dolphin.web.igm.vo.scu.ArvlDtlsSCU;

public class MasterSCC {
	private List<DecRefSCC> decRef;
	private List<AuthPrsnSCC> authPrsn;
	private List<VesselDtlsSCC> vesselDtls;
	private List<VoyageDtlsSCC> voyageDtls;
	private List<MastrCnsgmtDecSCC> mastrCnsgmtDec;
	private List<PrsnOnBoardSCC> prsnOnBoard;
	private List<VoyageTransportEquipmentSCC> voyageTransportEquipment;
	private List<ArvlDtlsSCC> arvlDtls;
	public List<DecRefSCC> getDecRef() {
		return decRef;
	}
	public void setDecRef(List<DecRefSCC> decRef) {
		this.decRef = decRef;
	}
	public List<AuthPrsnSCC> getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(List<AuthPrsnSCC> authPrsn) {
		this.authPrsn = authPrsn;
	}
	public List<VesselDtlsSCC> getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(List<VesselDtlsSCC> vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public List<VoyageDtlsSCC> getVoyageDtls() {
		return voyageDtls;
	}
	public void setVoyageDtls(List<VoyageDtlsSCC> voyageDtls) {
		this.voyageDtls = voyageDtls;
	}
	public List<MastrCnsgmtDecSCC> getMastrCnsgmtDec() {
		return mastrCnsgmtDec;
	}
	public void setMastrCnsgmtDec(List<MastrCnsgmtDecSCC> mastrCnsgmtDec) {
		this.mastrCnsgmtDec = mastrCnsgmtDec;
	}
	public List<PrsnOnBoardSCC> getPrsnOnBoard() {
		return prsnOnBoard;
	}
	public void setPrsnOnBoard(List<PrsnOnBoardSCC> prsnOnBoard) {
		this.prsnOnBoard = prsnOnBoard;
	}
	public List<VoyageTransportEquipmentSCC> getVoyageTransportEquipment() {
		return voyageTransportEquipment;
	}
	public void setVoyageTransportEquipment(List<VoyageTransportEquipmentSCC> voyageTransportEquipment) {
		this.voyageTransportEquipment = voyageTransportEquipment;
	}
	public List<ArvlDtlsSCC> getArvlDtls() {
		return arvlDtls;
	}
	public void setArvlDtls(List<ArvlDtlsSCC> arvlDtls) {
		this.arvlDtls = arvlDtls;
	}

}

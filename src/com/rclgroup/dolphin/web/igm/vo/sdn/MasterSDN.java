package com.rclgroup.dolphin.web.igm.vo.sdn;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.AuthPrsn;
import com.rclgroup.dolphin.web.igm.vo.DecRef;
import com.rclgroup.dolphin.web.igm.vo.MastrCnsgmtDec;
import com.rclgroup.dolphin.web.igm.vo.PrsnOnBoard;
import com.rclgroup.dolphin.web.igm.vo.VesselDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageDtls;
import com.rclgroup.dolphin.web.igm.vo.VoyageTransportEquipment;
import com.rclgroup.dolphin.web.igm.vo.sda.ArvlDtlsSDA;

public class MasterSDN {
	
	private DecRefSDN decRef;
	private AuthPrsnSDN authPrsn;
	private VesselDtlsSDN vesselDtls;
	//private List<VoyageDtlsSDN> voyageDtls;
	//private List<MastrCnsgmtDecSDN> mastrCnsgmtDec;
	//private List<PrsnOnBoardSDN> prsnOnBoard;
	//private List<VoyageTransportEquipmentSDN> voyageTransportEquipment;
	private ArvlDtlsSDN arvlDtls;
	private List<TmSuprtDocsSDN> tmSuprtDocs;
	private List<TmAdtnlDecSDN> tmAdtnlDec;
	
	public DecRefSDN getDecRef() {
		return decRef;
	}
	public void setDecRef(DecRefSDN decRef) {
		this.decRef = decRef;
	}
	public AuthPrsnSDN getAuthPrsn() {
		return authPrsn;
	}
	public void setAuthPrsn(AuthPrsnSDN authPrsn) {
		this.authPrsn = authPrsn;
	}
	public VesselDtlsSDN getVesselDtls() {
		return vesselDtls;
	}
	public void setVesselDtls(VesselDtlsSDN vesselDtls) {
		this.vesselDtls = vesselDtls;
	}
	public ArvlDtlsSDN getArvlDtls() {
		return arvlDtls;
	}
	public void setArvlDtls(ArvlDtlsSDN arvlDtls) {
		this.arvlDtls = arvlDtls;
	}
	public List<TmSuprtDocsSDN> getTmSuprtDocs() {
		return tmSuprtDocs;
	}
	public void setTmSuprtDocs(List<TmSuprtDocsSDN> tmSuprtDocs) {
		this.tmSuprtDocs = tmSuprtDocs;
	}
	public List<TmAdtnlDecSDN> getTmAdtnlDec() {
		return tmAdtnlDec;
	}
	public void setTmAdtnlDec(List<TmAdtnlDecSDN> tmAdtnlDec) {
		this.tmAdtnlDec = tmAdtnlDec;
	}
	 
	
	
	
	

}

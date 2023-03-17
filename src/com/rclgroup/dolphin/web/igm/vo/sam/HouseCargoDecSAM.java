package com.rclgroup.dolphin.web.igm.vo.sam;

import java.util.List;

public class HouseCargoDecSAM {
	
	private HCRefSAM hCRef;
	private LocCstmSAM locCstm;
	private List<TrnshprSAM> trnshpr;
	private List<TrnsprtDocSAM> trnsprtDoc;
	private List<TrnsprtDocMsrSAM> trnsprtDocMsr;
	private List<ItemDtlsSAM> itemDtls;
	private List<TrnsprtEqmtSAM> trnsprtEqmt;
	private List<ItnrySAM> itnry;
	private List<PrevRefSAM> prevRef;
	private List<HCCrgoSuprtDocsSAM> hCCrgoSuprtDocs;
	private List<HCAdtnlDecSAM>hcAdtnlDec;
	private List<HCPrevRefSAM>hcPrevRef;
	
	
	
	public List<HCPrevRefSAM> getHcPrevRef() {
		return hcPrevRef;
	}
	public void setHcPrevRef(List<HCPrevRefSAM> hcPrevRef) {
		this.hcPrevRef = hcPrevRef;
	}
	
	public HCRefSAM gethCRef() {
		return hCRef;
	}
	public void sethCRef(HCRefSAM hCRef) {
		this.hCRef = hCRef;
	}
	public LocCstmSAM getLocCstm() {
		return locCstm;
	}
	public void setLocCstm(LocCstmSAM locCstm) {
		this.locCstm = locCstm;
	}
	public List<TrnshprSAM> getTrnshpr() {
		return trnshpr;
	}
	public void setTrnshpr(List<TrnshprSAM> trnshpr) {
		this.trnshpr = trnshpr;
	}
	public List<TrnsprtDocSAM> getTrnsprtDoc() {
		return trnsprtDoc;
	}
	public void setTrnsprtDoc(List<TrnsprtDocSAM> trnsprtDoc) {
		this.trnsprtDoc = trnsprtDoc;
	}
	public List<TrnsprtDocMsrSAM> getTrnsprtDocMsr() {
		return trnsprtDocMsr;
	}
	public void setTrnsprtDocMsr(List<TrnsprtDocMsrSAM> trnsprtDocMsr) {
		this.trnsprtDocMsr = trnsprtDocMsr;
	}
	public List<ItemDtlsSAM> getItemDtls() {
		return itemDtls;
	}
	public void setItemDtls(List<ItemDtlsSAM> itemDtls) {
		this.itemDtls = itemDtls;
	}
	public List<TrnsprtEqmtSAM> getTrnsprtEqmt() {
		return trnsprtEqmt;
	}
	public void setTrnsprtEqmt(List<TrnsprtEqmtSAM> trnsprtEqmt) {
		this.trnsprtEqmt = trnsprtEqmt;
	}
	public List<ItnrySAM> getItnry() {
		return itnry;
	}
	public void setItnry(List<ItnrySAM> itnry) {
		this.itnry = itnry;
	}
	public List<PrevRefSAM> getPrevRef() {
		return prevRef;
	}
	public void setPrevRef(List<PrevRefSAM> prevRef) {
		this.prevRef = prevRef;
	}
	public List<HCCrgoSuprtDocsSAM> gethCCrgoSuprtDocs() {
		return hCCrgoSuprtDocs;
	}
	public void sethCCrgoSuprtDocs(List<HCCrgoSuprtDocsSAM> hCCrgoSuprtDocs) {
		this.hCCrgoSuprtDocs = hCCrgoSuprtDocs;
	}
	public List<HCAdtnlDecSAM> getHcAdtnlDec() {
		return hcAdtnlDec;
	}
	public void setHcAdtnlDec(List<HCAdtnlDecSAM> hcAdtnlDec) {
		this.hcAdtnlDec = hcAdtnlDec;
	}



}
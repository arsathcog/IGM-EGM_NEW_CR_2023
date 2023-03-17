package com.rclgroup.dolphin.web.igm.vo.sda;

import java.util.List;

public class HouseCargoDecSDA {
	private List<HCRefSDA> hCRef;
	private List<LocCstmSDA> locCstm;
    private List<TrnshprSDA> trnshpr;
	private List<TrnsprtDocSDA> trnsprtDoc;
    private List<TrnsprtDocMsrSDA> trnsprtDocMsr;
	private List<ItemDtlsSDA> itemDtls;
	private List<TrnsprtEqmtSDA> trnsprtEqmt;
	private List<ItnrySDA> itnry;
	private List<PrevRefSDA> prevRef;
	private List<HCCrgoSuprtDocsSDA> hCCrgoSuprtDocs;
	private List<HCAdtnlDecSDA>hcAdtnlDec;
	
	public List<HCRefSDA> gethCRef() {
		return hCRef;
	}
	public void sethCRef(List<HCRefSDA> hCRef) {
		this.hCRef = hCRef;	
	}
	public List<LocCstmSDA> getLocCstm() {
		return locCstm;
	}
	public void setLocCstm(List<LocCstmSDA> locCstm) {
		this.locCstm = locCstm;
	}
	public List<TrnshprSDA> getTrnshpr() {
		return trnshpr;
	}
	public void setTrnshpr(List<TrnshprSDA> trnshpr) {
		this.trnshpr = trnshpr;
	}
	public List<TrnsprtDocSDA> getTrnsprtDoc() {
		return trnsprtDoc;
	}
	public void setTrnsprtDoc(List<TrnsprtDocSDA> trnsprtDoc) {
		this.trnsprtDoc = trnsprtDoc;
	}
	public List<TrnsprtDocMsrSDA> getTrnsprtDocMsr() {
		return trnsprtDocMsr;
	}
	public void setTrnsprtDocMsr(List<TrnsprtDocMsrSDA> trnsprtDocMsr) {
		this.trnsprtDocMsr = trnsprtDocMsr;
	}
	public List<ItemDtlsSDA> getItemDtls() {
		return itemDtls;
	}
	public void setItemDtls(List<ItemDtlsSDA> itemDtls) {
		this.itemDtls = itemDtls;
	}
	public List<TrnsprtEqmtSDA> getTrnsprtEqmt() {
		return trnsprtEqmt;
	}
	public void setTrnsprtEqmt(List<TrnsprtEqmtSDA> trnsprtEqmt) {
		this.trnsprtEqmt = trnsprtEqmt;
	}
	public List<ItnrySDA> getItnry() {
		return itnry;
	}
	public void setItnry(List<ItnrySDA> itnry) {
		this.itnry = itnry;
	}
	public List<PrevRefSDA> getPrevRef() {
		return prevRef;
	}
	public void setPrevRef(List<PrevRefSDA> prevRef) {
		this.prevRef = prevRef;
	}
	public List<HCCrgoSuprtDocsSDA> gethCCrgoSuprtDocs() {
		return hCCrgoSuprtDocs;
	}
	public void sethCCrgoSuprtDocs(List<HCCrgoSuprtDocsSDA> hCCrgoSuprtDocs) {
		this.hCCrgoSuprtDocs = hCCrgoSuprtDocs;
	}
	public List<HCAdtnlDecSDA> getHcAdtnlDec() {
		return hcAdtnlDec;
	}
	public void setHcAdtnlDec(List<HCAdtnlDecSDA> hcAdtnlDec) {
		this.hcAdtnlDec = hcAdtnlDec;
	}



}
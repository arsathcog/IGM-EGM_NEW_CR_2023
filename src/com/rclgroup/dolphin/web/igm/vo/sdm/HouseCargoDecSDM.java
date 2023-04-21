package com.rclgroup.dolphin.web.igm.vo.sdm;

import java.util.List;

public class HouseCargoDecSDM {
	private List<HCRefSDM> hCRef;
	private List<LocCstmSDM> locCstm;
//    private List<TrnshprSDM> trnshpr;
	private List<TrnsprtDocSDM> trnsprtDoc;
    private List<TrnsprtDocMsrSDM> trnsprtDocMsr;
	private List<ItemDtlsSDM> itemDtls;
	private List<TrnsprtEqmtSDM> trnsprtEqmt;
	private List<ItnrySDM> itnry;
//	private List<PrevRefSDM> prevRef;
	private List<HCCrgoSuprtDocsSDM> hCCrgoSuprtDocs;
	private List<HCAdtnlDecSDM>hcAdtnlDec;
	
	public List<HCRefSDM> gethCRef() {
		return hCRef;
	}
	public void sethCRef(List<HCRefSDM> hCRef) {
		this.hCRef = hCRef;	
	}
	public List<LocCstmSDM> getLocCstm() {
		return locCstm;
	}
	public void setLocCstm(List<LocCstmSDM> locCstm) {
		this.locCstm = locCstm;
	}
//	public List<TrnshprSDM> getTrnshpr() {
//		return trnshpr;
//	}
//	public void setTrnshpr(List<TrnshprSDM> trnshpr) {
//		this.trnshpr = trnshpr;
//	}
	public List<TrnsprtDocSDM> getTrnsprtDoc() {
		return trnsprtDoc;
	}
	public void setTrnsprtDoc(List<TrnsprtDocSDM> trnsprtDoc) {
		this.trnsprtDoc = trnsprtDoc;
	}
	public List<TrnsprtDocMsrSDM> getTrnsprtDocMsr() {
		return trnsprtDocMsr;
	}
	public void setTrnsprtDocMsr(List<TrnsprtDocMsrSDM> trnsprtDocMsr) {
		this.trnsprtDocMsr = trnsprtDocMsr;
	}
	public List<ItemDtlsSDM> getItemDtls() {
		return itemDtls;
	}
	public void setItemDtls(List<ItemDtlsSDM> itemDtls) {
		this.itemDtls = itemDtls;
	}
	public List<TrnsprtEqmtSDM> getTrnsprtEqmt() {
		return trnsprtEqmt;
	}
	public void setTrnsprtEqmt(List<TrnsprtEqmtSDM> trnsprtEqmt) {
		this.trnsprtEqmt = trnsprtEqmt;
	}
	public List<ItnrySDM> getItnry() {
		return itnry;
	}
	public void setItnry(List<ItnrySDM> itnry) {
		this.itnry = itnry;
	}
//	public List<PrevRefSDM> getPrevRef() {
//		return prevRef;
//	}
//	public void setPrevRef(List<PrevRefSDM> prevRef) {
//		this.prevRef = prevRef;
//	}
	public List<HCCrgoSuprtDocsSDM> gethCCrgoSuprtDocs() {
		return hCCrgoSuprtDocs;
	}
	public void sethCCrgoSuprtDocs(List<HCCrgoSuprtDocsSDM> hCCrgoSuprtDocs) {
		this.hCCrgoSuprtDocs = hCCrgoSuprtDocs;
	}
	public List<HCAdtnlDecSDM> getHcAdtnlDec() {
		return hcAdtnlDec;
	}
	public void setHcAdtnlDec(List<HCAdtnlDecSDM> hcAdtnlDec) {
		this.hcAdtnlDec = hcAdtnlDec;
	}



}
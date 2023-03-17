package com.rclgroup.dolphin.web.igm.vo.sdn;

import java.util.List;

public class MastrCnsgmtDecSDN {
	private List<MCRefSDN> mCRef;
	//public List<LocCstmSDN> locCstm;
	private List<TrnsprtDocSDN> trnsprtDoc;
	private List<TrnsprtDocMsrSDN> trnsprtDocMsr;
//	public List<ItemDtlsSDN> itemDtls;
//	public List<TrnsprtEqmtSDN> trnsprtEqmt;
//	public List<ItnrySDN> itnry;
	public List<MCRefSDN> getmCRef() {
		return mCRef;
	}
	public void setmCRef(List<MCRefSDN> mCRef) {
		this.mCRef = mCRef;
	}
	
	public List<TrnsprtDocSDN> getTrnsprtDoc() {
		return trnsprtDoc;
	}
	public void setTrnsprtDoc(List<TrnsprtDocSDN> trnsprtDoc) {
		this.trnsprtDoc = trnsprtDoc;
	}
	public List<TrnsprtDocMsrSDN> getTrnsprtDocMsr() {
		return trnsprtDocMsr;
	}
	public void setTrnsprtDocMsr(List<TrnsprtDocMsrSDN> trnsprtDocMsr) {
		this.trnsprtDocMsr = trnsprtDocMsr;
	}
}
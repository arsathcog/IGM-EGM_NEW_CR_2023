package com.rclgroup.dolphin.web.igm.vo.sdm;

import java.util.List;

public class MastrCnsgmtDecSDM {
	
	private MCRefSDM mCRef;
	private LocCstmSDM locCstm;
//    private List<TrnshprSDM> trnshpr;
	private TrnsprtDocSDM trnsprtDoc;
    private TrnsprtDocMsrSDM trnsprtDocMsr;
//    private ItemDtlsSDM itemDtls;
    private List<ItemDtlsSDM> itemDtls;
	private List<TrnsprtEqmtSDM> trnsprtEqmt;
	
//	private ItnrySDM itnry;
	private List<ItnrySDM> itnry;
	private PrevRefSDM prevRef;
	private HouseCargoDecSDM houseCargoDec;
//	private List<MCSuprtDocsSDM> mCSuprtDocs;
//	private List<MCAdtnlDecSDM> mCAdtnlDec;
	
	
	public MCRefSDM getmCRef() {
		return mCRef;
	}



//	public List<ItnrySDM> getItnryList() {
//		return itnryList;
//	}
//
//
//
//	public void setItnryList(List<ItnrySDM> itnryList) {
//		this.itnryList = itnryList;
//	}



	public void setmCRef(MCRefSDM mCRef) {
		this.mCRef = mCRef;
	}



	public LocCstmSDM getLocCstm() {
		return locCstm;
	}



	public void setLocCstm(LocCstmSDM locCstm) {
		this.locCstm = locCstm;
	}


//
//	public List<TrnshprSDM> getTrnshpr() {
//		return trnshpr;
//	}
//
//
//
//	public void setTrnshpr(List<TrnshprSDM> trnshpr) {
//		this.trnshpr = trnshpr;
//	}



	public TrnsprtDocSDM getTrnsprtDoc() {
		return trnsprtDoc;
	}



	public void setTrnsprtDoc(TrnsprtDocSDM trnsprtDoc) {
		this.trnsprtDoc = trnsprtDoc;
	}



	public TrnsprtDocMsrSDM getTrnsprtDocMsr() {
		return trnsprtDocMsr;
	}



	public void setTrnsprtDocMsr(TrnsprtDocMsrSDM trnsprtDocMsr) {
		this.trnsprtDocMsr = trnsprtDocMsr;
	}

//	public ItemDtlsSDM getItemDtls() {
//		return itemDtls;
//	}
//
//
//
//	public void setItemDtls(ItemDtlsSDM itemDtls) {
//		this.itemDtls = itemDtls;
//	}
	
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






	public PrevRefSDM getPrevRef() {
		return prevRef;
	}



	public void setPrevRef(PrevRefSDM prevRef) {
		this.prevRef = prevRef;
	}



	public List<ItnrySDM> getItnry() {
		return itnry;
	}



	public void setItnry(List<ItnrySDM> itnry) {
		this.itnry = itnry;
	}

	public HouseCargoDecSDM getHouseCargoDec() {
		return houseCargoDec;
	}



	public void setHouseCargoDec(HouseCargoDecSDM houseCargoDec) {
		this.houseCargoDec = houseCargoDec;
	}



	@Override
	public String toString() {
		return "MastrCnsgmtDecSDM [mCRef=" + mCRef + ", locCstm=" + locCstm + ", trnsprtDoc=" + trnsprtDoc
				+ ", trnsprtDocMsr=" + trnsprtDocMsr + ", itemDtls=" + itemDtls + ", trnsprtEqmt=" + trnsprtEqmt
				+ ", itnry=" + itnry + ", prevRef=" + prevRef + ", houseCargoDec=" + houseCargoDec + "]";
	}



	









//	public List<MCSuprtDocsSDM> getmCSuprtDocs() {
//		return mCSuprtDocs;
//	}
//
//
//
//	public void setmCSuprtDocs(List<MCSuprtDocsSDM> mCSuprtDocs) {
//		this.mCSuprtDocs = mCSuprtDocs;
//	}
//
//
//
//	public List<MCAdtnlDecSDM> getmCAdtnlDec() {
//		return mCAdtnlDec;
//	}
//
//
//
//	public void setmCAdtnlDec(List<MCAdtnlDecSDM> mCAdtnlDec) {
//		this.mCAdtnlDec = mCAdtnlDec;
//	}








	
}
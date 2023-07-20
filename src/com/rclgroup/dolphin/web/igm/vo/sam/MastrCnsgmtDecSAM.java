package com.rclgroup.dolphin.web.igm.vo.sam;

import java.util.List;

public class MastrCnsgmtDecSAM {
	
	private MCRefSAM mCRef;
	private LocCstmSAM locCstm;
    private TrnshprSAM trnshpr;		//TODO
	private TrnsprtDocSAM trnsprtDoc;
    private TrnsprtDocMsrSAM trnsprtDocMsr;
	private ItemDtlsSAM itemDtls;   //TODO
	private List<TrnsprtEqmtSAM> trnsprtEqmt;   //TODO
	private ItnrySAM itnry;				//TODO
	private PrevRefSAM prevRef = null;			    //TODO now 
	private HouseCargoDecSAM houseCargoDec;	
	
	//on hold
//	private List<MCSuprtDocsSAM> mCSuprtDocs;				//TODO
//	private List<MCAdtnlDecSAM> mCAdtnlDec;					//TODO
	
	public MCRefSAM getmCRef() {
		return mCRef;
	}
	public void setmCRef(MCRefSAM mCRef) {
		this.mCRef = mCRef;
	}
	public LocCstmSAM getLocCstm() {
		return locCstm;
	}
	public void setLocCstm(LocCstmSAM locCstm) {
		this.locCstm = locCstm;
	}

	public TrnshprSAM getTrnshpr() {
		return trnshpr;
	}
	public void setTrnshpr(TrnshprSAM trnshpr) {
		this.trnshpr = trnshpr;
	}
	public TrnsprtDocSAM getTrnsprtDoc() {
		return trnsprtDoc;
	}
	public void setTrnsprtDoc(TrnsprtDocSAM trnsprtDoc) {
		this.trnsprtDoc = trnsprtDoc;
	}
	public TrnsprtDocMsrSAM getTrnsprtDocMsr() {
		return trnsprtDocMsr;
	}
	public void setTrnsprtDocMsr(TrnsprtDocMsrSAM trnsprtDocMsr) {
		this.trnsprtDocMsr = trnsprtDocMsr;
	}

	public ItemDtlsSAM getItemDtls() {
		return itemDtls;
	}
	public void setItemDtls(ItemDtlsSAM itemDtls) {
		this.itemDtls = itemDtls;
	}
	public List<TrnsprtEqmtSAM> getTrnsprtEqmt() {
		return trnsprtEqmt;
	}
	public void setTrnsprtEqmt(List<TrnsprtEqmtSAM> trnsprtEqmt) {
		this.trnsprtEqmt = trnsprtEqmt;
	}

	public ItnrySAM getItnry() {
		return itnry;
	}
	public void setItnry(ItnrySAM itnry) {
		this.itnry = itnry;
	}
	public PrevRefSAM getPrevRef() {
		return prevRef;
	}
	public void setPrevRef(PrevRefSAM prevRef) {
		this.prevRef = prevRef;
	}

	
	//on hold
//	public List<MCSuprtDocsSAM> getmCSuprtDocs() {
//		return mCSuprtDocs;
//	}
//	public void setmCSuprtDocs(List<MCSuprtDocsSAM> mCSuprtDocs) {
//		this.mCSuprtDocs = mCSuprtDocs;
//	}
//	public List<MCAdtnlDecSAM> getmCAdtnlDec() {
//		return mCAdtnlDec;
//	}
//	public void setmCAdtnlDec(List<MCAdtnlDecSAM> mCAdtnlDec) {
//		this.mCAdtnlDec = mCAdtnlDec;
//	}
	
	

	public HouseCargoDecSAM getHouseCargoDec() {
		return houseCargoDec;
	}
	public void setHouseCargoDec(HouseCargoDecSAM houseCargoDec) {
		this.houseCargoDec = houseCargoDec;
	}

	@Override
	public String toString() {
		return "MastrCnsgmtDecSAM [mCRef=" + mCRef + ", locCstm=" + locCstm + ", trnshpr=" + trnshpr + ", trnsprtDoc="
				+ trnsprtDoc + ", trnsprtDocMsr=" + trnsprtDocMsr + ", itemDtls=" + itemDtls + ", trnsprtEqmt="
				+ trnsprtEqmt + ", itnry=" + itnry + ", prevRef=" + prevRef + ", houseCargoDec=" + houseCargoDec + "]";
	}
	

	 
	
	
}
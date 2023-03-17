package com.rclgroup.dolphin.web.igm.vo.sei;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.ItemDtls;
import com.rclgroup.dolphin.web.igm.vo.Itnry;
import com.rclgroup.dolphin.web.igm.vo.LocCstm;
import com.rclgroup.dolphin.web.igm.vo.MCRef;
import com.rclgroup.dolphin.web.igm.vo.TrnsprtDoc;
import com.rclgroup.dolphin.web.igm.vo.TrnsprtDocMsr;
import com.rclgroup.dolphin.web.igm.vo.TrnsprtEqmt;

public class MastrCnsgmtDecSEI {
	private List<MCRefSEI> mCRef;
//	private List<LocCstmSEI> locCstm;
	private List<TrnsprtDocSEI> trnsprtDoc;
        private List<TrnsprtDocMsrSEI> trnsprtDocMsr;
//	private List<ItemDtlsSEI> itemDtls;
//	private List<TrnsprtEqmtSEI> trnsprtEqmt;
//	private List<ItnrySEI> itnry;

    	public List<MCRefSEI> getmCRef() {
    		return mCRef;
    	}
    	public List<TrnsprtDocSEI> getTrnsprtDoc() {
    		return trnsprtDoc;
    	}

    	public void setTrnsprtDoc(List<TrnsprtDocSEI> trnsprtDoc) {
    		this.trnsprtDoc = trnsprtDoc;
    	}

    	public List<TrnsprtDocMsrSEI> getTrnsprtDocMsr() {
    		return trnsprtDocMsr;
    	}

    	public void setTrnsprtDocMsr(List<TrnsprtDocMsrSEI> trnsprtDocMsr) {
    		this.trnsprtDocMsr = trnsprtDocMsr;
    	}

    	public void setmCRef(List<MCRefSEI> mCRef) {
    		this.mCRef = mCRef;
    	}

	
}
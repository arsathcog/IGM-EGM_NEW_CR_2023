package com.rclgroup.dolphin.web.igm.vo.scc;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.ItemDtls;
import com.rclgroup.dolphin.web.igm.vo.Itnry;
import com.rclgroup.dolphin.web.igm.vo.LocCstm;
import com.rclgroup.dolphin.web.igm.vo.MCRef;
import com.rclgroup.dolphin.web.igm.vo.TrnsprtDoc;
import com.rclgroup.dolphin.web.igm.vo.TrnsprtDocMsr;
import com.rclgroup.dolphin.web.igm.vo.TrnsprtEqmt;
import com.rclgroup.dolphin.web.igm.vo.sam.MCAdtnlDecSAM;
import com.rclgroup.dolphin.web.igm.vo.sam.MCSuprtDocsSAM;
import com.rclgroup.dolphin.web.igm.vo.scu.SupRefSCU;

public class MastrCnsgmtDecSCC {
	private List<MCRefSCC> mCRef;
	private List<LocCstmSCC> locCstm;
	private List<ItemDtlsSCC> itemDtls;
	private List<TrnsprtEqmtSCC> trnsprtEqmt;
	private List<ItnrySCC> itnry;
	private List<SupRefSCC> supRef;
	
	public List<MCRefSCC> getmCRef() {
		return mCRef;
	}
	public void setmCRef(List<MCRefSCC> mCRef) {
		this.mCRef = mCRef;
	}
	public List<LocCstmSCC> getLocCstm() {
		return locCstm;
	}
	public void setLocCstm(List<LocCstmSCC> locCstm) {
		this.locCstm = locCstm;
	}
	public List<ItemDtlsSCC> getItemDtls() {
		return itemDtls;
	}
	public void setItemDtls(List<ItemDtlsSCC> itemDtls) {
		this.itemDtls = itemDtls;
	}
	public List<TrnsprtEqmtSCC> getTrnsprtEqmt() {
		return trnsprtEqmt;
	}
	public void setTrnsprtEqmt(List<TrnsprtEqmtSCC> trnsprtEqmt) {
		this.trnsprtEqmt = trnsprtEqmt;
	}
	public List<ItnrySCC> getItnry() {
		return itnry;
	}
	public void setItnry(List<ItnrySCC> itnry) {
		this.itnry = itnry;
	}
	public List<SupRefSCC> getSupRef() {
		return supRef;
	}
	public void setSupRef(List<SupRefSCC> supRef) {
		this.supRef = supRef;
	}

	
}
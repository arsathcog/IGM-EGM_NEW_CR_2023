package com.rclgroup.dolphin.web.igm.vo.sce;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSCE {
	private HeaderFieldSCE headerField;
	private MasterSCE master;
	
//	private List<DigSignSCE> digSign;
	
	
	public HeaderFieldSCE getHeaderField() {
		return headerField;
	}
	public void setHeaderField(HeaderFieldSCE headerField) {
		this.headerField = headerField;
	}
	public MasterSCE getMaster() {
		return master;
	}
	public void setMaster(MasterSCE master) {
		this.master = master;
	}

	
	
//	public List<DigSignSCE> getDigSign() {
//		return digSign;
//	}
//	public void setDigSign(List<DigSignSCE> digSign) {
//		this.digSign = digSign;
//	}

}
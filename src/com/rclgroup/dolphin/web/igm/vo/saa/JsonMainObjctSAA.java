package com.rclgroup.dolphin.web.igm.vo.saa;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSAA {
	
	private List<HeaderFieldSAA> headerField;
	private List<MasterSAA> master;
	private List<DigSignSAA> digSign;
	public List<HeaderFieldSAA> getHeaderField() {
		return headerField;
	}
	public void setHeaderField(List<HeaderFieldSAA> headerField) {
		this.headerField = headerField;
	}
	public List<MasterSAA> getMaster() {
		return master;
	}
	public void setMaster(List<MasterSAA> master) {
		this.master = master;
	}
	public List<DigSignSAA> getDigSign() {
		return digSign;
	}
	public void setDigSign(List<DigSignSAA> digSign) {
		this.digSign = digSign;
	}

	
}
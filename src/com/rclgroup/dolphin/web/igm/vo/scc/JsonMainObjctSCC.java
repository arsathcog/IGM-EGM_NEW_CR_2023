package com.rclgroup.dolphin.web.igm.vo.scc;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSCC {
	private List<HeaderFieldSCC> headerField;
	private List<MasterSCC> master;
	private List<DigSignSCC> digSign;
	
	public List<HeaderFieldSCC> getHeaderField() {
		return headerField;
	}
	public void setHeaderField(List<HeaderFieldSCC> headerField) {
		this.headerField = headerField;
	}
	public List<MasterSCC> getMaster() {
		return master;
	}
	public void setMaster(List<MasterSCC> master) {
		this.master = master;
	}
	public List<DigSignSCC> getDigSign() {
		return digSign;
	}
	public void setDigSign(List<DigSignSCC> digSign) {
		this.digSign = digSign;
	}

}
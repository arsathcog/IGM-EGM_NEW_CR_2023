package com.rclgroup.dolphin.web.igm.vo.scd;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSCD {
	private List<HeaderFieldSCD> headerField;
	private List<MasterSCD> master;
	private List<DigSignSCD> digSign;
	
	public List<HeaderFieldSCD> getHeaderField() {
		return headerField;
	}
	public void setHeaderField(List<HeaderFieldSCD> headerField) {
		this.headerField = headerField;
	}
	public List<MasterSCD> getMaster() {
		return master;
	}
	public void setMaster(List<MasterSCD> master) {
		this.master = master;
	}
	public List<DigSignSCD> getDigSign() {
		return digSign;
	}
	public void setDigSign(List<DigSignSCD> digSign) {
		this.digSign = digSign;
	}

}
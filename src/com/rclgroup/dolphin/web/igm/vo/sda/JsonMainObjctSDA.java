package com.rclgroup.dolphin.web.igm.vo.sda;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSDA {
	private List<HeaderFieldSDA> headerField;
	private List<MasterSDA> master;
	private List<DigSignSDA> digSign;

	public List<HeaderFieldSDA> getHeaderField() {
		return headerField;
	}

	public void setHeaderField(List<HeaderFieldSDA> headerFieldList) {
		this.headerField = headerFieldList;
	}

	public List<MasterSDA> getMaster() {
		return master;
	}

	public void setMaster(List<MasterSDA> master) {
		this.master = master;
	}

	public List<DigSignSDA> getDigSign() {
		return digSign;
	}

	public void setDigSign(List<DigSignSDA> digSign) {
		this.digSign = digSign;
	}
}
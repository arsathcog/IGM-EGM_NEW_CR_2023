package com.rclgroup.dolphin.web.igm.vo.sda;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSignSEI;
import com.rclgroup.dolphin.web.igm.vo.HeaderFieldSEI;
import com.rclgroup.dolphin.web.igm.vo.MasterSEI;

public class JsonSEIObjctSDA {
	private List<HeaderFieldSEI> headerField;
	private List<MasterSEI> master;
	private List<DigSignSEI> digSign;

	public List<HeaderFieldSEI> getHeaderField() {
		return headerField;
	}

	public void setHeaderField(List<HeaderFieldSEI> headerField) {
		this.headerField = headerField;
	}

	public List<MasterSEI> getMaster() {
		return master;
	}

	public void setMaster(List<MasterSEI> master) {
		this.master = master;
	}

	public List<DigSignSEI> getDigSign() {
		return digSign;
	}

	public void setDigSign(List<DigSignSEI> digSign) {
		this.digSign = digSign;
	}

}
package com.rclgroup.dolphin.web.igm.vo.sca;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSCA {
	private List<HeaderFieldSCA> headerField;
	private List<MasterSCA> master;
	private List<DigSignSCA> digSign;
	
	public List<HeaderFieldSCA> getHeaderField() {
		return headerField;
	}
	public void setHeaderField(List<HeaderFieldSCA> headerField) {
		this.headerField = headerField;
	}
	public List<MasterSCA> getMaster() {
		return master;
	}
	public void setMaster(List<MasterSCA> master) {
		this.master = master;
	}
	public List<DigSignSCA> getDigSign() {
		return digSign;
	}
	public void setDigSign(List<DigSignSCA> digSign) {
		this.digSign = digSign;
	}

}
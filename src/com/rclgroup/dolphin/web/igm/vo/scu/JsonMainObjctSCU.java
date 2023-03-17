package com.rclgroup.dolphin.web.igm.vo.scu;

import java.util.List;

public class JsonMainObjctSCU {
	private List<HeaderFieldSCU> headerField;
	private List<MasterSCU> master;
	private List<DigSignSCU> digSign;
	
	public List<HeaderFieldSCU> getHeaderField() {
		return headerField;
	}
	public void setHeaderField(List<HeaderFieldSCU> headerField) {
		this.headerField = headerField;
	}
	public List<MasterSCU> getMaster() {
		return master;
	}
	public void setMaster(List<MasterSCU> master) {
		this.master = master;
	}
	public List<DigSignSCU> getDigSign() {
		return digSign;
	}
	public void setDigSign(List<DigSignSCU> digSign) {
		this.digSign = digSign;
	}

}
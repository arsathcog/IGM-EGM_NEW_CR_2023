package com.rclgroup.dolphin.web.igm.vo.scx;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSCX {
	private HeaderFieldSCX headerField;
	private MasterSCX master;
//	private List<DigSignSCX> digSign;
	public HeaderFieldSCX getHeaderField() {
		return headerField;
	}
	public void setHeaderField(HeaderFieldSCX headerField) {
		this.headerField = headerField;
	}
	public MasterSCX getMaster() {
		return master;
	}
	public void setMaster(MasterSCX master) {
		this.master = master;
	}
	


}
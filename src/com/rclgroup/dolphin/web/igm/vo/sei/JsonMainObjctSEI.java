package com.rclgroup.dolphin.web.igm.vo.sei;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSEI {
	
	private HeaderFieldSEI headerField;
	private MasterSEI master;
	private DigSignSEI digSign;
	
	public HeaderFieldSEI getHeaderField() {
		return headerField;
	}
	public void setHeaderField(HeaderFieldSEI headerField) {
		this.headerField = headerField;
	}
	public MasterSEI getMaster() {
		return master;
	}
	public void setMaster(MasterSEI master) {
		this.master = master;
	}
	public DigSignSEI getDigSign() {
		return digSign;
	}
	public void setDigSign(DigSignSEI digSign) {
		this.digSign = digSign;
	}
	
	@Override
	public String toString() {
		return "JsonMainObjctSEI [headerField=" + headerField + ", master=" + master + ", digSign=" + digSign + "]";
	}
	
	

	
}
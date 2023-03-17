package com.rclgroup.dolphin.web.igm.vo.sdn;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSDN {
	
	private HeaderFieldSDN headerField;
	private MasterSDN master;
	//private List<DigSignSDN> digSign;
	
	
	public HeaderFieldSDN getHeaderField() {
		return headerField;
	}
	public void setHeaderField(HeaderFieldSDN headerField) {
		this.headerField = headerField;
	}
	public MasterSDN getMaster() {
		return master;
	}
	public void setMaster(MasterSDN master) {
		this.master = master;
	}
	
	 
		
}
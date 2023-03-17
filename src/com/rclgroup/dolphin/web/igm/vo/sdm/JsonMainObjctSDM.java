package com.rclgroup.dolphin.web.igm.vo.sdm;

import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.DigSign;
import com.rclgroup.dolphin.web.igm.vo.HeaderField;
import com.rclgroup.dolphin.web.igm.vo.Master;

public class JsonMainObjctSDM {
	
	private HeaderFieldSDM headerField;
	private MasterSDM master;
	//private List<DigSignSDM> digSign;
	
	public HeaderFieldSDM getHeaderField() {
		return headerField;
	}
	public void setHeaderField(HeaderFieldSDM headerField) {
		this.headerField = headerField;
	}
	public MasterSDM getMaster() {
		return master;
	}
	public void setMaster(MasterSDM master) {
		this.master = master;
	}
 
	
	
}
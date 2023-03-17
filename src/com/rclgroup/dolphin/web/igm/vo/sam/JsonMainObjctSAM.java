package com.rclgroup.dolphin.web.igm.vo.sam;

import java.util.List;

public class JsonMainObjctSAM {
	
	private HeaderFieldSAM headerField;
	private MasterSAM master;
	//private List<DigSignSAM> digSign;
	
	public HeaderFieldSAM getHeaderField() {
		return headerField;
	}
	public void setHeaderField(HeaderFieldSAM headerField) {
		this.headerField = headerField;
	}
	
	public MasterSAM getMaster() {
		return master;
	}
	public void setMaster(MasterSAM master) {
		this.master = master;
	}
	
	@Override
	public String toString() {
		return "JsonMainObjctSAM [headerField=" + headerField + ", master=" + master + "]";
	}
	
	
}
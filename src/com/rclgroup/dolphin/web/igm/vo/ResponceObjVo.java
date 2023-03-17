package com.rclgroup.dolphin.web.igm.vo;

import java.util.List;

public class ResponceObjVo {
	
	private Object  jsonFile;
	private List 	blListNew;
	
	public ResponceObjVo(Object jsonFile, List blListNew) {
		super();
		this.jsonFile = jsonFile;
		this.blListNew = blListNew;
	}
	
	public Object getJsonFile() {
		return jsonFile;
	}
	public void setJsonFile(Object jsonFile) {
		this.jsonFile = jsonFile;
	}
	public List getBlListNew() {
		return blListNew;
	}
	public void setBlListNew(List blListNew) {
		this.blListNew = blListNew;
	}	

}

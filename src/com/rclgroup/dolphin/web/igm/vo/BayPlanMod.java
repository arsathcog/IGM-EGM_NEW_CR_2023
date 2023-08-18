package com.rclgroup.dolphin.web.igm.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BayPlanMod {
	
	private String container;
	private String isoBayLocation;
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
	public String getIsoBayLocation() {
		return isoBayLocation;
	}
	public void setIsoBayLocation(String isoBayLocation) {
		this.isoBayLocation = isoBayLocation;
	}
	
	@Override
	public String toString() {
		return "BayPlanMod [container=" + container + ", isoBayLocation=" + isoBayLocation + "]";
	}

	
}

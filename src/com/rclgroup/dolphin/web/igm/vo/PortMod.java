package com.rclgroup.dolphin.web.igm.vo;

public class PortMod {

	private String port;
	
	private String portName;

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	@Override
	public String toString() {
		return "PortMod [port=" + port + ", PortName=" + portName + "]";
	}
	
	
	
}

package com.rclgroup.dolphin.web.igm.vo;


//siddharth
public class Consigner {

	private String blNO;
	private String customerCode;
	private String customerName;	
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressLine4;	
	private String city;
	private String state;
	private String countryCode;
	private String zip;
	private String stateName;
	private String notifyIec;
	private String notifyPan;
	private String consignerName;
	private String consignerIec;
	private String consignerPan;
	private String portOfDischarge;
	private String consigneCheckBox;
	private String consignerFwr;

	
	public String getBlNO() {
		return blNO;
	}
	public void setBlNO(String blNO) {
		this.blNO = blNO;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getAddressLine4() {
		return addressLine4;
	}
	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	
	public String getNotifyIec() {
		return notifyIec;
	}
	public void setNotifyIec(String notifyIec) {
		this.notifyIec = notifyIec;
	}
	public String getNotifyPan() {
		return notifyPan;
	}
	public void setNotifyPan(String notifyPan) {
		this.notifyPan = notifyPan;
	}
	public String getConsignerName() {
		return consignerName;
	}
	public void setConsignerName(String consignerName) {
		this.consignerName = consignerName;
	}
	public String getConsignerIec() {
		return consignerIec;
	}
	public void setConsignerIec(String consignerIec) {
		this.consignerIec = consignerIec;
	}
	public String getConsignerPan() {
		return consignerPan;
	}
	public void setConsignerPan(String consignerPan) {
		this.consignerPan = consignerPan;
	}
	public String getPortOfDischarge() {
		return portOfDischarge;
	}
	public void setPortOfDischarge(String portOfDischarge) {
		this.portOfDischarge = portOfDischarge;
	}
	public String getConsigneCheckBox() {
		return consigneCheckBox;
	}
	public void setConsigneCheckBox(String consigneCheckBox) {
		this.consigneCheckBox = consigneCheckBox;
	}
	public String getConsignerFwr() {
		return consignerFwr;
	}
	public void setConsignerFwr(String consignerFwr) {
		this.consignerFwr = consignerFwr;
	}
	@Override
	public String toString() {
		return "Consigner [blNO=" + blNO + ", customerCode=" + customerCode + ", customerName=" + customerName
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3
				+ ", addressLine4=" + addressLine4 + ", city=" + city + ", state=" + state + ", countryCode="
				+ countryCode + ", zip=" + zip + "]";
	}

}

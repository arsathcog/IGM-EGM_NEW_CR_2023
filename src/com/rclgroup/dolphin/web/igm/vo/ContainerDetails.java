package com.rclgroup.dolphin.web.igm.vo;

public class ContainerDetails {
	
	private String blNo;
	
	private String containerNumber;
	
	private String equipment_seal_type;
	
	private String containerSealNumber;
	
	private String containerAgentCode;
	
	private String agentCode;
	
	private String status;
	
	private String soc_flag;
	
	private String containerBondFlag;
	
	private String containerType;
	
	private String containerSize;
	
	private String typeDetail;
	
	private String totalNumberOfPackagesInContainer;
	
	private String containerWeight;
	
	private String cbm;
	
	private String isoCode;
	
	private String unos;
	
	private String imo;
	
	private String classification;
	
	private String remarks;
	
	private String equipmentLoadStatus;
	
	private String containerWeightUnit;
	
	public String getContainerBondFlag() {
		return containerBondFlag;
	}
	public void setContainerBondFlag(String containerBondFlag) {
		this.containerBondFlag = containerBondFlag;
	}
	public String getTypeDetail() {
		return typeDetail;
	}
	public void setTypeDetail(String typeDetail) {
		this.typeDetail = typeDetail;
	}
	public String getCbm() {
		return cbm;
	}
	public void setCbm(String cbm) {
		this.cbm = cbm;
	}
	public String getIsoCode() {
		return isoCode;
	}
	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}
	public String getUnos() {
		return unos;
	}
	public void setUnos(String unos) {
		this.unos = unos;
	}
	public String getImo() {
		return imo;
	}
	public void setImo(String imo) {
		this.imo = imo;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEquipment_seal_type() {
		return equipment_seal_type;
	}
	public void setEquipment_seal_type(String equipment_seal_type) {
		this.equipment_seal_type = equipment_seal_type;
	}
	public String getContainerNumber() {
		return containerNumber;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}
	public String getContainerSealNumber() {
		return containerSealNumber;
	}
	public void setContainerSealNumber(String containerSealNumber) {
		this.containerSealNumber = containerSealNumber;
	}
	public String getContainerAgentCode() {
		return containerAgentCode;
	}
	public void setContainerAgentCode(String containerAgentCode) {
		this.containerAgentCode = containerAgentCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotalNumberOfPackagesInContainer() {
		return totalNumberOfPackagesInContainer;
	}
	public void setTotalNumberOfPackagesInContainer(String totalNumberOfPackagesInContainer) {
		this.totalNumberOfPackagesInContainer = totalNumberOfPackagesInContainer;
	}
	public String getContainerWeight() {
		return containerWeight;
	}
	public void setContainerWeight(String containerWeight) {
		this.containerWeight = containerWeight;
	}
	public String getContainerSize() {
		return containerSize;
	}
	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getEquipmentLoadStatus() {
		return equipmentLoadStatus;
	}
	public void setEquipmentLoadStatus(String equipmentLoadStatus) {
		this.equipmentLoadStatus = equipmentLoadStatus;
	}
	public String getSoc_flag() {
		return soc_flag;
	}
	public void setSoc_flag(String soc_flag) {
		this.soc_flag = soc_flag;
	}
	public String getContainerWeightUnit() {
		return containerWeightUnit;
	}
	public void setContainerWeightUnit(String containerWeightUnit) {
		this.containerWeightUnit = containerWeightUnit;
	}
	
	@Override
	public String toString() {
		return "ContainerDetails [blNo=" + blNo + ", containerNumber=" + containerNumber + ", equipment_seal_type="
				+ equipment_seal_type + ", containerSealNumber=" + containerSealNumber + ", containerAgentCode="
				+ containerAgentCode + ", agentCode=" + agentCode + ", status=" + status + ", soc_flag=" + soc_flag
				+ ", containerBondFlag=" + containerBondFlag + ", containerType=" + containerType + ", containerSize="
				+ containerSize + ", typeDetail=" + typeDetail + ", totalNumberOfPackagesInContainer="
				+ totalNumberOfPackagesInContainer + ", containerWeight=" + containerWeight + ", cbm=" + cbm
				+ ", isoCode=" + isoCode + ", unos=" + unos + ", imo=" + imo + ", classification=" + classification
				+ ", remarks=" + remarks + ", equipmentLoadStatus=" + equipmentLoadStatus + ", containerWeightUnit="
				+ containerWeightUnit + "]";
	}

}

package com.rclgroup.dolphin.web.igm.vo;

import java.util.ArrayList;
import java.util.List;

import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ImportGeneralManifestMod.
 */
@XmlRootElement
public class ImportGeneralManifestMod implements Cloneable {

	public Object clone() throws CloneNotSupportedException {
		return (ImportGeneralManifestMod) super.clone();
	}

	/** The consignee */
	private List<Consignee> consignee = new ArrayList<>();

	/** The consigner */
	private List<Consigner> consigner = new ArrayList<>();

	/** The notify party */
	private List<NotifyParty> notifyParty = new ArrayList<>();

	/** The notify party Two */
	private List<NotifyPartyTwo> notifyPartyTwo = new ArrayList<>();

	/** The marks no. */
	private List<MarksNumber> marksNumber = new ArrayList<>();

	/** The container Details */
	private List<ContainerDetails> containerDetailes = new ArrayList<>();

	/** The Previous Declaration */
	private List<PreviousDeclaration> previousDeclaration = new ArrayList<>();

	private List<IGMPersonOnBoardMod> personOnBoardMod=new ArrayList<>();
	
	private List<IGMCrewEfctMod> crewEfctMods=new ArrayList<>();
	
	private List<IGMShipStoresMod> igmShipStoresMods=new ArrayList<>();
	
	private String roadCarrCodeVVS = "";

	private String tpbondnoVVS="";

	private String isBlSave;
	
	private String masterBl = "";
	
	private String masterBlDate = "";
	
	/** The bl. */
	private String bl;

	/** The service. */
	private String service;

	/** The vessel. */
	private String vessel;

	/** The vesselName. */
	private String vesselName;

	/** The voyage. */
	private String voyage;

	/** The pol. */
	private String pol;

	/** The pol terminal. */
	private String polTerminal;

	/** The del. */
	private String del;

	/** The depot. */
	private String depot;

	/** The pod. */
	private String pod;

	/** The pod terminal. */
	private String podTerminal;

	/** The Consignee Name. */
	private String consigneeName;
	
	/** The code code. */
	private String codeCode;

	/** The call sing. */
	private String callSing;

	/** The line code. */
	private String lineCode;

	/** The agent code. */
	private String agentCode;

	/** The port origin. */
	private String portOrigin;

	/** The port arrival. */
	private String portArrival;

	/** The last port 1. */
	private String lastPort1;

	/** The last port 2. */
	private String lastPort2;

	/** The last port 3. */
	private String lastPort3;

	/** The last port 1. */
	private String nextport1;

	/** The last port 2. */
	private String nextport2;

	/** The last port 3. */
	private String nextport3;
	


	/** The terminal. */
	private String terminal;

	/** The vessel type. */
	private String vesselType;

	/** The gen desc. */
	private String genDesc;

	/** The master name. */
	private String masterName;

	/** The vessel nation. */
	private String vesselNation;

	/** The igm number. */
	private String igmNumber;
	
	private String igmDateVal;

	private String igmYearVal;
	
	/** The igm date. */
	private String igmDate;

	/** The arrival date. */
	private String arrivalDate;

	/** The arrival time. */
	private String arrivalTime;

	/** The arrival time. */
	private String departureTime;
	
	/** The Departure time. */
	private String departureDate;
	
	/** The Ata arrival date. */
	private String ataarrivalDate;

	/** The Ata Arrival time. */
	private String ataarrivalTime;

	/** The total bls. */
	private String totalBls;

	/** The light due. */
	private String lightDue;

	/** The gross weight. */
	private String grossWeight;

	/** The net weight. */
	private String netWeight;

	/** The sm bt cargo. */
	private String smBtCargo;

	/** The ship str dect. */
	private String shipStrDect;

	/** The crew effect. */
	private String crewEffect;

	/** The mari time decl. */
	private String mariTimeDecl;

	/** The item number. */
	private String itemNumber;

	/** The cargo nature. */
	private String cargoNature;

	/** The cargo movmnt. */
	private String cargoMovmnt;

	/** The item type. */
	private String itemType;

	/** The cargo movmnt type. */
	private String cargoMovmntType;

	/** The transport mode. */
	private String transportMode;

	/** The road carr code. */
	private String roadCarrCode;

	/** The road TP bond no. */
	private String roadTPBondNo;

	/** The custom Terminal Code */
	private String customTerminalCode;

	/** The custom Terminal Code */
	private String customCode;

	/** The submit date time. */
	private String submitDateTime;

	/** The weight. */
	private String weight;

	/** The nhava sheva eta. */
	private String nhavaShevaEta;

	/** The final place delivery. */
	private String finalPlaceDelivery;

	/** The packages. */
	private String packages;

	/** The cfs name. */
	private String cfsName;

	/** The mbl no. */
	private String mblNo;

	/** The hbl no. */
	private String hblNo;
	
	private String houseBl;

	/** The bl date. */
	private String blDate;

	/** The from BL Status */
	private String blStatus;

	/** The from item no. */
	private String fromItemNo;

	/** The to item no. */
	private String toItemNo;

	/** The imo code. */
	private String imoCode;

	/** The new vessel */
	private String newVessel;

	/** The new vessel */
	private String newVoyage;

	/** The serial number */
	private String serialNumber;

	/** The Crew List Declaration */
	private String crewListDeclaration;

	/** The Cargo Declaration */
	private String cargoDeclaration;

	/** the Passenger List */
	private String passengerList;

	/** the DPD Movement */
	private String dpdMovement;

	/** the DPD code */
	private String dpdCode;

	/** the BL Version */
	private String blVersion;

	/** the custom Address1 */
	private String CusAdd1;

	/** the custom Address2 */
	private String CusAdd2;

	/** the custom Address3 */
	private String CusAdd3;

	/** the custom Address4 */
	private String CusAdd4;

	/** The IsValidateBL */
	private String IsValidateBL;

	/** The GrossCargoWeightBLlevel */
	private String GrossCargoWeightBLlevel;

	/** The PackageBLLevel */
	private String PackageBLLevel;

	// SID NEW IGM MANIFEST CR VESSEL VOYAGE SECTION 14/11/2019

	/** The PackageBLLevel */
	private String dep_manif_no;

	/** The PackageBLLevel */
	private String dep_manifest_date;

	/** The PackageBLLevel */
	private String submitter_type;

	/** The PackageBLLevel */
	private String submitter_code;

	/** The PackageBLLevel */
	private String authoriz_rep_code;

	/** The PackageBLLevel */
	private String shipping_line_bond_no_r;

	/** The PackageBLLevel */
	private String mode_of_transport;

	/** The PackageBLLevel */
	private String ship_type;

	/** The PackageBLLevel */
	private String conveyance_reference_no;

	/** The PackageBLLevel */
	private String cargo_description;

	/** The PackageBLLevel */
	private String tol_no_of_trans_equ_manif;

	/** The PackageBLLevel */
	private String brief_cargo_des;

	/** The PackageBLLevel */
	private String expected_date;

	/** The PackageBLLevel */
	private String time_of_dept;

	/** The PackageBLLevel */
	private String no_of_crew_manif;

	/** The PackageBLLevel */
	private String port_of_call_cod;

	/** The PackageBLLevel */
	private String total_no_of_tran_s_cont_repo_on_ari_dep;

	/** The PackageBLLevel */
	private String message_type;

	/** The PackageBLLevel */
	private String port_of_reporting;

	/** The PackageBLLevel */
	private String job_number;

	/** The PackageBLLevel */
	private String job_date;

	/** The PackageBLLevel */
	private String reporting_event;

	/** The PackageBLLevel */
	private String manifest_no_csn_no;

	/** The PackageBLLevel */
	private String manifest_date_csn_date;

	/** The PackageBLLevel */
	private String vessel_type_movement;

	/** The PackageBLLevel */
	private String shipping_line_code;

	/** The PackageBLLevel */
	private String authorized_sea_carrier_code;

	/** The PackageBLLevel */
	private String port_of_registry;

	/** The PackageBLLevel */
	private String registry_date;

	/** The PackageBLLevel */
	private String voyage_details_movement;

	/** The PackageBLLevel */
	private String ship_itinerary_sequence;

	/** The PackageBLLevel */
	private String ship_itinerary;

	/** The PackageBLLevel */
	private String port_of_call_name;

	/** The PackageBLLevel */
	private String arrival_departure_details;

	/** The PackageBLLevel */
	private String number_of_crew;

	/** The PackageBLLevel */
	private String total_no_of_transport_equipment_reported_on_arrival_departure;

	// END NEW IGM MANIFEST CR VESSEL VOYAGE SECTION 14/11/2019

	// start Bl section

	private String consolidated_indicator;

	// previousDeclaration
	private String previous_declaration = "N";
	
	private String split_indicator;
	
	private String csn_number;

	private String csn_date;

	private String previous_mcin;

	private String previous_pcin;

	//
	private String notifyPartyCode;

	private String consolidator_pan;

	private String cin_type;

	private String mcin;
	
	private String pcin;
	
	private String csn_submitted_type;

	private String csn_submitted_by;

	private String csn_reporting_type;

	private String csn_site_id;

	private String number_of_packages; // need to discussion ???

	private String type_of_package;

	private String first_port_of_entry_last_port_of_departure;

	private String type_of_cargo;

	private String split_indicator_list;

	private String port_of_acceptance;

	private String port_of_receipt;

	private String ucr_type;

	private String ucr_code;

	private String soc_flag;

	private String equipment_load_status;

	private String equipment_seal_type;

	private String port_of_acceptance_name;

	private String port_of_receipt_name;

	private String pan_of_notified_party;

	private String unit_of_weight;

	private String gross_volume;

	private String unit_of_volume;

	private String cargo_item_sequence_no;

	private String cargo_item_description;

	private String total_number_of_packages;

	private String number_of_packages_hidden;

	private String type_of_packages_hidden;

	private String mc_item_details;

	private String container_weight;

	private String port_of_call_sequence_number;

	private String port_of_call_coded;

	private String next_port_of_call_coded;

	private String mc_location_customs;

	private String uno_code;

	private String imdg_code;
	// BL's NewFields (26-05-2021)
	private String enblockMovement;
	
	private String carrierNo;
	
	private String tpBondNo;
	
	private String agencyType;
	
	private String invoiceValueFc;
	
	private String invoiceValueInr;
	
	private String currency;
	
	private String invoiceItems;
	
	private String modeOfTpFee;
	
	private String remark;
	
	private String subLineNumber;
	
	private String portOfDestination;
	
	private String portOfLoading;
	
	private String portOfDeschargedCfs;
	
	private String multipalPakages;
	
	private String cbm;
	
	private String hightValue;
	
	private String grosWeight;
	
	private String unit;
	
	private String volume;
	// end bl section

	// vessel's new fields(28-05-2021)
	private String vasselCode;
	
	private String edi;
	
	private String nonEdi;
	
	private String parentVoy;
	
	private String viaVcn;
	
	private String subTermil;
	
	private String typeTransportMeans;
	
	private String equimentType;
	
	private String igmYear;
	
	private String rotnNo;
	
	private String rotnDate;
	
	private String jobNo;
	
	private String jobDate;
	
	private String position;
	
	private String exchangeRate;
	
	private String cigmNo;
	
	private String cigmDate;
	
	private String smtpNo;
	
	private String smtpDate;
	
	private String noOfItemInPrior;
	
	private String noOfItemInFil;
	
	private String noOfItemInSupplimentary;
	
	private String totalWeight;
	
	private String noOfPassenger;
	
	private String noOfCrew;
	
	private String remarkVessel;
	
	private String dutyInr;
	
	private String totalIteamNo;
	
	private String totalItem;

	private String totalItems;
	
	private String podTerminalPort;
	
	private String polTerminalPort;
	
	private String blType;
	
	private String consolidatedIndicator;
	
	private String neCargoMovmnt;
	
	private String  senderId;
	
	private String recieverId;
	
	private String authRepCd;
	
	private String authReprsntvCd;
	
	private String shpngLineCd;
	
	private String totalNmbrOfLines;
	
	private String containerMsBl;
	
	private boolean isFetch;
	
	private String saveFlags;
	
	private boolean isHbl = true;
	
	private String dgFlag ; 
	
	private String commdity_code;
	
	private String package_kind;
	
	private int commodity_seq;
	
//	Name for port
	private String port_of_call_name_last3;
	
	private String port_of_call_name_last2;
	
	private String port_of_call_name_last1;
	
	private String port_of_call_name_nextport1;
	
	private String port_of_call_name_nextport2;
	
	private String port_of_call_name_nextport3;
	
	private String port_of_call_name_portOrigin;
	
	private String port_of_call_name_portArrival;
	
	private String hsCd;
	
	private String flagDg;
	
	private int cargo_msmt;
	
	private String gstStateCode;
	
	private String dn_plr;
	
	private String portName;
	
	private String pointName;
	
	private String dn_pld;
	
	private String stowageImport;
	
	private String stowageExport;
	
	private String acceptanceName;
	
	private String recieptName;
	
	private String notifyName;	
	private String notifyIec;
	private String notifyPan;
	
//FOR ROB IMPLEMENTATION 
	
	private String dnDischargePort;
	private String flag_discharge;
	

	
	public String getAcceptanceName() {
		return acceptanceName;
	}

	public void setAcceptanceName(String acceptanceName) {
		this.acceptanceName = acceptanceName;
	}

	public String getRecieptName() {
		return recieptName;
	}

	public void setRecieptName(String recieptName) {
		this.recieptName = recieptName;
	}

	public String getStowageExport() {
		return stowageExport;
	}

	public void setStowageExport(String stowageExport) {
		this.stowageExport = stowageExport;
	}

	public String getStowageImport() {
		return stowageImport;
	}

	public void setStowageImport(String stowageImport) {
		this.stowageImport = stowageImport;
	}

	public String getDn_pld() {
		return dn_pld;
	}

	public void setDn_pld(String dn_pld) {
		this.dn_pld = dn_pld;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public String getDn_plr() {
		return dn_plr;
	}

	public void setDn_plr(String dn_plr) {
		this.dn_plr = dn_plr;
	}

	public String getGstStateCode() {
		return gstStateCode;
	}

	public void setGstStateCode(String gstStateCode) {
		this.gstStateCode = gstStateCode;
	}

	public String getFlagDg() {
		return flagDg;
	}

	public void setFlagDg(String flagDg) {
		this.flagDg = flagDg;
	}

	public int getCargo_msmt() {
		return cargo_msmt;
	}

	public void setCargo_msmt(int cargo_msmt) {
		this.cargo_msmt = cargo_msmt;
	}

	public String getHsCd() {
		return hsCd;
	}

	public void setHsCd(String hsCd) {
		this.hsCd = hsCd;
	}

	public String getSaveFlags() {
		return saveFlags;
	}

	public void setSaveFlags(String saveFlags) {
		this.saveFlags = saveFlags;
	}

	public String getDgFlag() {
		return dgFlag;
	}

	public void setDgFlag(String dgFlag) {
		this.dgFlag = dgFlag;
	}
	
	public String getCommdity_code() {
		return commdity_code;
	}

	public void setCommdity_code(String commdity_code) {
		this.commdity_code = commdity_code;
	}


	public String getPackage_kind() {
		return package_kind;
	}

	public void setPackage_kind(String package_kind) {
		this.package_kind = package_kind;
	}

	public int getCommodity_seq() {
		return commodity_seq;
	}

	public void setCommodity_seq(int commodity_seq) {
		this.commodity_seq = commodity_seq;
	}

	public boolean isFetch() {
		return isFetch;
	}

	public void setFetch(boolean isFetch) {
		this.isFetch = isFetch;
	}


	public String getShpngLineCd() {
		return shpngLineCd;
	}
	

	public String getTotalNmbrOfLines() {
		return totalNmbrOfLines;
	}


	public void setTotalNmbrOfLines(String totalNmbrOfLines) {
		this.totalNmbrOfLines = totalNmbrOfLines;
	}

	public String getContainerMsBl() {
		return containerMsBl;
	}

	public void setContainerMsBl(String containerMsBl) {
		this.containerMsBl = containerMsBl;
	}

	public void setShpngLineCd(String shpngLineCd) {
		this.shpngLineCd = shpngLineCd;
	}

	public String getMasterBlDate() {
		return masterBlDate;
	}

	public void setMasterBlDate(String masterBlDate) {
		this.masterBlDate = masterBlDate;
	}

	public String getMasterBl() {
		return masterBl;
	}

	public void setMasterBl(String masterBl) {
		this.masterBl = masterBl;
	}

	public String getNeCargoMovmnt() {
		return neCargoMovmnt;
	}

	public void setNeCargoMovmnt(String neCargoMovmnt) {
		this.neCargoMovmnt = neCargoMovmnt;
	}
	
	public String getBlType() {
		return blType;
	}

	public void setBlType(String blType) {
		this.blType = blType;
	}

	public String getConsolidatedIndicator() {
		return consolidatedIndicator;
	}

	public void setConsolidatedIndicator(String consolidatedIndicator) {
		this.consolidatedIndicator = consolidatedIndicator;
	}

	public String getPodTerminalPort() {
		return podTerminalPort;
	}

	public void setPodTerminalPort(String podTerminalPort) {
		this.podTerminalPort = podTerminalPort;
	}

	public String getPolTerminalPort() {
		return polTerminalPort;
	}

	public void setPolTerminalPort(String polTerminalPort) {
		this.polTerminalPort = polTerminalPort;
	}

	public String getCustomCode() {
		return customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	public String getVasselCode() {
		return vasselCode;
	}

	public void setVasselCode(String vasselCode) {
		this.vasselCode = vasselCode;
	}

	public String getEdi() {
		return edi;
	}

	public void setEdi(String edi) {
		this.edi = edi;
	}

	public String getNonEdi() {
		return nonEdi;
	}

	public void setNonEdi(String nonEdi) {
		this.nonEdi = nonEdi;
	}

	public String getParentVoy() {
		return parentVoy;
	}

	public void setParentVoy(String parentVoy) {
		this.parentVoy = parentVoy;
	}

	public String getViaVcn() {
		return viaVcn;
	}

	public void setViaVcn(String viaVcn) {
		this.viaVcn = viaVcn;
	}

	public String getSubTermil() {
		return subTermil;
	}

	public void setSubTermil(String subTermil) {
		this.subTermil = subTermil;
	}

	public String getTypeTransportMeans() {
		return typeTransportMeans;
	}

	public void setTypeTransportMeans(String typeTransportMeans) {
		this.typeTransportMeans = typeTransportMeans;
	}

	public String getEquimentType() {
		return equimentType;
	}

	public void setEquimentType(String equimentType) {
		this.equimentType = equimentType;
	}

	public String getIgmYear() {
		return igmYear;
	}

	public void setIgmYear(String igmYear) {
		this.igmYear = igmYear;
	}

	public String getRotnNo() {
		return rotnNo;
	}

	public void setRotnNo(String rotnNo) {
		this.rotnNo = rotnNo;
	}

	public String getRotnDate() {
		return rotnDate;
	}

	public void setRotnDate(String rotnDate) {
		this.rotnDate = rotnDate;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public String getJobDate() {
		return jobDate;
	}

	public void setJobDate(String jobDate) {
		this.jobDate = jobDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCigmNo() {
		return cigmNo;
	}

	public void setCigmNo(String cigmNo) {
		this.cigmNo = cigmNo;
	}

	public String getCigmDate() {
		return cigmDate;
	}

	public void setCigmDate(String cigmDate) {
		this.cigmDate = cigmDate;
	}

	public String getSmtpNo() {
		return smtpNo;
	}

	public void setSmtpNo(String smtpNo) {
		this.smtpNo = smtpNo;
	}

	public String getSmtpDate() {
		return smtpDate;
	}

	public void setSmtpDate(String smtpDate) {
		this.smtpDate = smtpDate;
	}

	public String getNoOfItemInPrior() {
		return noOfItemInPrior;
	}

	public void setNoOfItemInPrior(String noOfItemInPrior) {
		this.noOfItemInPrior = noOfItemInPrior;
	}

	public String getNoOfItemInFil() {
		return noOfItemInFil;
	}

	public void setNoOfItemInFil(String noOfItemInFil) {
		this.noOfItemInFil = noOfItemInFil;
	}

	public String getNoOfItemInSupplimentary() {
		return noOfItemInSupplimentary;
	}

	public void setNoOfItemInSupplimentary(String noOfItemInSupplimentary) {
		this.noOfItemInSupplimentary = noOfItemInSupplimentary;
	}

	public String getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getNoOfPassenger() {
		return noOfPassenger;
	}

	public void setNoOfPassenger(String noOfPassenger) {
		this.noOfPassenger = noOfPassenger;
	}

	public String getNoOfCrew() {
		return noOfCrew;
	}

	public void setNoOfCrew(String noOfCrew) {
		this.noOfCrew = noOfCrew;
	}

	public String getRemarkVessel() {
		return remarkVessel;
	}

	public void setRemarkVessel(String remarkVessel) {
		this.remarkVessel = remarkVessel;
	}

	public String getUno_code() {
		return uno_code;
	}

	public void setUno_code(String uno_code) {
		this.uno_code = uno_code;
	}

	public String getImdg_code() {
		return imdg_code;
	}

	public void setImdg_code(String imdg_code) {
		this.imdg_code = imdg_code;
	}

	/**
	 * Gets the New Vessel.
	 *
	 * @return the New Vessel
	 */

	public String getNewVessel() {
		return newVessel;
	}

	/**
	 * Sets the New Vessel.
	 *
	 * @param newVessel the new New Vessel
	 */
	public void setNewVessel(String newVessel) {
		this.newVessel = newVessel;
	}

	/**
	 * Gets the New Voyage.
	 *
	 * @return the New Voyage
	 */

	public String getNewVoyage() {
		return newVoyage;
	}

	/**
	 * Sets the New Voyage.
	 *
	 * @param newVoyage the new New Voyage
	 */
	public void setNewVoyage(String newVoyage) {
		this.newVoyage = newVoyage;
	}

	/**
	 * Gets the Crew List Declaration.
	 *
	 * @return the Crew List Declaration
	 */

	public String getCrewListDeclaration() {
		return crewListDeclaration;
	}

	/**
	 * Sets the Crew List Declaration.
	 *
	 * @param crewListDeclaration the new Crew List Declaration
	 */
	public void setCrewListDeclaration(String crewListDeclaration) {
		this.crewListDeclaration = crewListDeclaration;
	}

	/**
	 * Gets the Cargo Declaration.
	 *
	 * @return the Cargo Declaration
	 */
	public String getCargoDeclaration() {
		return cargoDeclaration;
	}

	/**
	 * Sets the Cargo Declaration.
	 *
	 * @param cargoDeclaration the new Cargo Declaration
	 */
	public void setCargoDeclaration(String cargoDeclaration) {
		this.cargoDeclaration = cargoDeclaration;
	}

	/**
	 * Gets the Passenger List.
	 *
	 * @return the Passenger List
	 */
	public String getPassengerList() {
		return passengerList;
	}

	/**
	 * Sets the Passenger List.
	 *
	 * @param passengerList the new Passenger List
	 */
	public void setPassengerList(String passengerList) {
		this.passengerList = passengerList;
	}

	/**
	 * Gets the imo code.
	 *
	 * @return the imo code
	 */
	public String getImoCode() {
		return imoCode;
	}

	/**
	 * Sets the imo code.
	 *
	 * @param imoCode the new imo code
	 */
	public void setImoCode(String imoCode) {
		this.imoCode = imoCode;
	}

	/**
	 * Gets the serial number.
	 *
	 * @return the serial number
	 */

	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets the serial number.
	 *
	 * @param serialNumber the new serial number
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets the road TP bond no.
	 *
	 * @return the road TP bond no
	 */
	public String getRoadTPBondNo() {
		return roadTPBondNo;
	}

	/**
	 * Sets the road TP bond no.
	 *
	 * @param roadTPBondNo the new road TP bond no
	 */
	public void setRoadTPBondNo(String roadTPBondNo) {
		this.roadTPBondNo = roadTPBondNo;
	}

	/**
	 * Gets the from item no.
	 *
	 * @return the from item no
	 */
	public String getFromItemNo() {
		return fromItemNo;
	}

	/**
	 * Sets the from item no.
	 *
	 * @param fromItemNo the new from item no
	 */
	public void setFromItemNo(String fromItemNo) {
		this.fromItemNo = fromItemNo;
	}

	/**
	 * Gets the to item no.
	 *
	 * @return the to item no
	 */
	public String getToItemNo() {
		return toItemNo;
	}

	/**
	 * Sets the to item no.
	 *
	 * @param toItemNo the new to item no
	 */
	public void setToItemNo(String toItemNo) {
		this.toItemNo = toItemNo;
	}

	/**
	 * Gets the bl.
	 *
	 * @return the bl
	 */
	public String getBl() {
		return bl;
	}

	/**
	 * Sets the bl.
	 *
	 * @param bl the new bl
	 */
	public void setBl(String bl) {
		this.bl = bl;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public String getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * Gets the pod terminal.
	 *
	 * @return the pod terminal
	 */
	public String getPodTerminal() {
		return podTerminal;
	}

	/**
	 * Sets the pod terminal.
	 *
	 * @param podTerminal the new pod terminal
	 */
	public void setPodTerminal(String podTerminal) {
		this.podTerminal = podTerminal;
	}

	/**
	 * Gets the vessel.
	 *
	 * @return the vessel
	 */
	public String getVessel() {
		return vessel;
	}

	/**
	 * Sets the vessel.
	 *
	 * @param vessel the new vessel
	 */
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	/**
	 * Gets the voyage.
	 *
	 * @return the voyage
	 */
	public String getVoyage() {
		return voyage;
	}

	/**
	 * Sets the voyage.
	 *
	 * @param voyage the new voyage
	 */
	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	/**
	 * Gets the pol.
	 *
	 * @return the pol
	 */
	public String getPol() {
		return pol;
	}

	/**
	 * Sets the pol.
	 *
	 * @param pol the new pol
	 */
	public void setPol(String pol) {
		this.pol = pol;
	}

	/**
	 * Gets the pol terminal.
	 *
	 * @return the pol terminal
	 */
	public String getPolTerminal() {
		return polTerminal;
	}

	/**
	 * Sets the pol terminal.
	 *
	 * @param polTerminal the new pol terminal
	 */
	public void setPolTerminal(String polTerminal) {
		this.polTerminal = polTerminal;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getDepot() {
		return depot;
	}

	public void setDepot(String depot) {
		this.depot = depot;
	}

	/**
	 * Gets the pod.
	 *
	 * @return the pod
	 */
	public String getPod() {
		return pod;
	}

	/**
	 * Sets the pod.
	 *
	 * @param pod the new pod
	 */
	public void setPod(String pod) {
		this.pod = pod;
	}

	/**
	 * Gets the Custom Terminal Code.
	 *
	 * @return the CustomTerminalCode
	 */
	public String getCustomTerminalCode() {
		return customTerminalCode;
	}

	/**
	 * Sets the Custom Terminal Code.
	 *
	 * @param pod the new CustomTerminalCode
	 */
	public void setCustomTerminalCode(String customTerminalCode) {
		this.customTerminalCode = customTerminalCode;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	/*
	 * public String getRoadTPBondNo() { return roadTPBondNo; }
	 * 
	 *//**
		 * Sets the road TP bond no.
		 *
		 * @param roadTPBondNo the new road TP bond no
		 *//*
			 * public void setRoadTPBondNo(String roadTPBondNo) { this.roadTPBondNo =
			 * roadTPBondNo; }
			 */

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * Gets the bl date.
	 *
	 * @return the bl date
	 */
	public String getBlDate() {
		
		return blDate;
	}

	/**
	 * Sets the bl date.
	 *
	 * @param blDate the new bl date
	 */
	public void setBlDate(String blDate) {
		if(blDate == null || blDate.equals("")) {
			this.blDate = blDate;
		}else if(blDate.contains("/")) {
			this.blDate = blDate;
		}else if(blDate.contains("/")) {
			this.blDate = blDate;
		}else {
			this.blDate = blDate.substring(6,8)+"/"+blDate.substring(4,6)+"/"+blDate.substring(0,4);
		}
		
	}

	/**
	 * Gets the bl Status.
	 *
	 * @return the bl Status
	 */
	public String getBlStatus() {
		return blStatus;
	}

	/**
	 * Sets the bl Status.
	 *
	 * @param blDate the new bl Status
	 */
	public void setBlStatus(String blStatus) {
		this.blStatus = blStatus;
	}

	/**
	 * Gets the code code.
	 *
	 * @return the code code
	 */
	public String getCodeCode() {
		return codeCode;
	}

	/**
	 * Sets the code code.
	 *
	 * @param codeCode the new code code
	 */
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}

	/**
	 * Gets the call sing.
	 *
	 * @return the call sing
	 */
	public String getCallSing() {
		return callSing;
	}

	/**
	 * Sets the call sing.
	 *
	 * @param callSing the new call sing
	 */
	public void setCallSing(String callSing) {
		this.callSing = callSing;
	}

	/**
	 * Gets the line code.
	 *
	 * @return the line code
	 */
	public String getLineCode() {
		return lineCode;
	}

	/**
	 * Sets the line code.
	 *
	 * @param lineCode the new line code
	 */
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}

	/**
	 * Gets the agent code.
	 *
	 * @return the agent code
	 */
	public String getAgentCode() {
		return agentCode;
	}

	/**
	 * Sets the agent code.
	 *
	 * @param agentCode the new agent code
	 */
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	/**
	 * Gets the port origin.
	 *
	 * @return the port origin
	 */
	public String getPortOrigin() {
		return portOrigin;
	}

	/**
	 * Sets the port origin.
	 *
	 * @param portOrigin the new port origin
	 */
	public void setPortOrigin(String portOrigin) {
		this.portOrigin = portOrigin;
	}

	/**
	 * Gets the port arrival.
	 *
	 * @return the port arrival
	 */
	public String getPortArrival() {
		return portArrival;
	}

	/**
	 * Sets the port arrival.
	 *
	 * @param portArrival the new port arrival
	 */
	public void setPortArrival(String portArrival) {
		this.portArrival = portArrival;
	}

	/**
	 * Gets the last port 1.
	 *
	 * @return the last port 1
	 */
	public String getLastPort1() {
		return lastPort1;
	}

	/**
	 * Sets the last port 1.
	 *
	 * @param lastPort1 the new last port 1
	 */
	public void setLastPort1(String lastPort1) {
		this.lastPort1 = lastPort1;
	}

	/**
	 * Gets the last port 2.
	 *
	 * @return the last port 2
	 */
	public String getLastPort2() {
		return lastPort2;
	}

	/**
	 * Sets the last port 2.
	 *
	 * @param lastPort2 the new last port 2
	 */
	public void setLastPort2(String lastPort2) {
		this.lastPort2 = lastPort2;
	}

	/**
	 * Gets the last port 3.
	 *
	 * @return the last port 3
	 */
	public String getLastPort3() {
		return lastPort3;
	}

	/**
	 * Sets the last port 3.
	 *
	 * @param lastPort3 the new last port 3
	 */
	public void setLastPort3(String lastPort3) {
		this.lastPort3 = lastPort3;
	}

	/**
	 * Gets the terminal.
	 *
	 * @return the terminal
	 */
	public String getTerminal() {
		return terminal;
	}

	/**
	 * Sets the terminal.
	 *
	 * @param terminal the new terminal
	 */
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	/**
	 * Gets the vessel type.
	 *
	 * @return the vessel type
	 */
	public String getVesselType() {
		return vesselType;
	}

	/**
	 * Sets the vessel type.
	 *
	 * @param vesselType the new vessel type
	 */
	public void setVesselType(String vesselType) {
		this.vesselType = vesselType;
	}

	/**
	 * Gets the gen desc.
	 *
	 * @return the gen desc
	 */
	public String getGenDesc() {
		return genDesc;
	}

	/**
	 * Sets the gen desc.
	 *
	 * @param genDesc the new gen desc
	 */
	public void setGenDesc(String genDesc) {
		this.genDesc = genDesc;
	}

	/**
	 * Gets the master name.
	 *
	 * @return the master name
	 */
	public String getMasterName() {
		return masterName;
	}

	/**
	 * Sets the master name.
	 *
	 * @param masterName the new master name
	 */
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	/**
	 * Gets the vessel nation.
	 *
	 * @return the vessel nation
	 */
	public String getVesselNation() {
		return vesselNation;
	}

	/**
	 * Sets the vessel nation.
	 *
	 * @param vesselNation the new vessel nation
	 */
	public void setVesselNation(String vesselNation) {
		this.vesselNation = vesselNation;
	}

	/**
	 * Gets the igm number.
	 *
	 * @return the igm number
	 */
	public String getIgmNumber() {
		return igmNumber;
	}

	/**
	 * Sets the igm number.
	 *
	 * @param igmNumber the new igm number
	 */
	public void setIgmNumber(String igmNumber) {
		this.igmNumber = igmNumber;
	}

	/**
	 * Gets the igm date.
	 *
	 * @return the igm date
	 */
	public String getIgmDate() {
		return igmDate;
	}

	/**
	 * Sets the igm date.
	 *
	 * @param igmDate the new igm date
	 */
	public void setIgmDate(String igmDate) {
		this.igmDate = igmDate;
	}

	/**
	 * Gets the arrival date.
	 *
	 * @return the arrival date
	 */
	public String getArrivalDate() {
		return arrivalDate;
	}

	/**
	 * Sets the arrival date.
	 *
	 * @param arrivalDate the new arrival date
	 */
	public void setArrivalDate(String arrivalDate) {
		String arDate = "";
		if (arrivalDate != null && !arrivalDate.equals("") && !arrivalDate.contains("/")) {
			arDate = arrivalDate.substring(6, 8) + "/" + arrivalDate.substring(4, 6) + "/"
					+ arrivalDate.substring(0, 4);
		}else if(arrivalDate != null && !arrivalDate.equals("") && arrivalDate.contains("/")) {
			arDate=arrivalDate;
		}
		this.arrivalDate = arDate;
	}

	/**
	 * Gets the arrival time.
	 *
	 * @return the arrival time
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time.
	 *
	 * @param arrivalTime the new arrival time
	 */
	public void setArrivalTime(String arrivalTime) {
		String hrsMin = "";
		if (arrivalTime != null && !arrivalTime.equals("") && !arrivalTime.contains(":")) {
			if (arrivalTime.length() == 3) {
				hrsMin = arrivalTime.substring(0, 1) + ":" + arrivalTime.substring(1);
			} else if (arrivalTime.length() == 4) {
				hrsMin = arrivalTime.substring(0, 2) + ":" + arrivalTime.substring(2);
			} else {
				hrsMin = arrivalTime + ":00";
			}
		}else if(arrivalTime != null && !arrivalTime.equals("") && arrivalTime.contains(":")) {
			hrsMin=arrivalTime;
		}
		this.arrivalTime = hrsMin;
	}
	
	public String getDepartureDate() {
		return departureDate;
	}
	


	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	


	/**
	 * Gets the Ata arrival Date.
	 *
	 * @return the Ata arrival date
	 */
	public String getAtaarrivalDate() {
		return ataarrivalDate;
	}

	/**
	 * Sets the Ata arrival Date.
	 *
	 * @param AtaarrivalDate the new ata arrival date
	 */
	public void setAtaarrivalDate(String ataarrivalDate) {
		this.ataarrivalDate = ataarrivalDate;
	}

	/**
	 * Gets the Ata arrival time.
	 *
	 * @return the Ata arrival time
	 */
	public String getAtaarrivalTime() {
		return ataarrivalTime;
	}

	/**
	 * Sets the Ata arrival time.
	 *
	 * @param Ata arrivalTime the new ata arrival time
	 */
	public void setAtaarrivalTime(String ataarrivalTime) {
		this.ataarrivalTime = ataarrivalTime;
	}
	
	public String getDepartureTime() {
		return departureTime;
	}
	


	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	


	/**
	 * Gets the total bls.
	 *
	 * @return the total bls
	 */
	public String getTotalBls() {
		return totalBls;
	}

	/**
	 * Sets the total bls.
	 *
	 * @param totalBls the new total bls
	 */
	public void setTotalBls(String totalBls) {
		this.totalBls = totalBls;
	}

	/**
	 * Gets the light due.
	 *
	 * @return the light due
	 */
	public String getLightDue() {
		return lightDue;
	}

	/**
	 * Sets the light due.
	 *
	 * @param lightDue the new light due
	 */
	public void setLightDue(String lightDue) {
		this.lightDue = lightDue;
	}

	/**
	 * Gets the gross weight.
	 *
	 * @return the gross weight
	 */
	public String getGrossWeight() {
		return grossWeight;
	}

	/**
	 * Sets the gross weight.
	 *
	 * @param grossWeight the new gross weight
	 */
	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	/**
	 * Gets the net weight.
	 *
	 * @return the net weight
	 */
	public String getNetWeight() {
		return netWeight;
	}

	/**
	 * Sets the net weight.
	 *
	 * @param netWeight the new net weight
	 */
	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	/**
	 * Gets the sm bt cargo.
	 *
	 * @return the sm bt cargo
	 */
	public String getSmBtCargo() {
		return smBtCargo;
	}

	/**
	 * Sets the sm bt cargo.
	 *
	 * @param smBtCargo the new sm bt cargo
	 */
	public void setSmBtCargo(String smBtCargo) {
		this.smBtCargo = smBtCargo;
	}

	/**
	 * Gets the ship str dect.
	 *
	 * @return the ship str dect
	 */
	public String getShipStrDect() {
		return shipStrDect;
	}

	/**
	 * Sets the ship str dect.
	 *
	 * @param shipStrDect the new ship str dect
	 */
	public void setShipStrDect(String shipStrDect) {
		this.shipStrDect = shipStrDect;
	}

	/**
	 * Gets the crew effect.
	 *
	 * @return the crew effect
	 */
	public String getCrewEffect() {
		return crewEffect;
	}

	/**
	 * Sets the crew effect.
	 *
	 * @param crewEffect the new crew effect
	 */
	public void setCrewEffect(String crewEffect) {
		this.crewEffect = crewEffect;
	}

	/**
	 * Gets the mari time decl.
	 *
	 * @return the mari time decl
	 */
	public String getMariTimeDecl() {
		return mariTimeDecl;
	}

	/**
	 * Sets the mari time decl.
	 *
	 * @param mariTimeDecl the new mari time decl
	 */
	public void setMariTimeDecl(String mariTimeDecl) {
		this.mariTimeDecl = mariTimeDecl;
	}

	/**
	 * Gets the item number.
	 *
	 * @return the item number
	 */
	public String getItemNumber() {
		return itemNumber;
	}

	/**
	 * Sets the item number.
	 *
	 * @param itemNumber the new item number
	 */
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * Gets the cargo nature.
	 *
	 * @return the cargo nature
	 */
	public String getCargoNature() {
		return cargoNature;
	}

	/**
	 * Sets the cargo nature.
	 *
	 * @param cargoNature the new cargo nature
	 */
	public void setCargoNature(String cargoNature) {
		this.cargoNature = cargoNature;
	}

	/**
	 * Gets the cargo movmnt.
	 *
	 * @return the cargo movmnt
	 */
	public String getCargoMovmnt() {
		return cargoMovmnt;
	}

	/**
	 * Sets the cargo movmnt.
	 *
	 * @param cargoMovmnt the new cargo movmnt
	 */
	public void setCargoMovmnt(String cargoMovmnt) {
		this.cargoMovmnt = cargoMovmnt;
	}

	/**
	 * Gets the item type.
	 *
	 * @return the item type
	 */
	public String getItemType() {
		return itemType;
	}

	/**
	 * Sets the item type.
	 *
	 * @param itemType the new item type
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	/**
	 * Gets the cargo movmnt type.
	 *
	 * @return the cargo movmnt type
	 */
	public String getCargoMovmntType() {
		return cargoMovmntType;
	}

	/**
	 * Sets the cargo movmnt type.
	 *
	 * @param cargoMovmntType the new cargo movmnt type
	 */
	public void setCargoMovmntType(String cargoMovmntType) {
		this.cargoMovmntType = cargoMovmntType;
	}

	/**
	 * Gets the transport mode.
	 *
	 * @return the transport mode
	 */
	public String getTransportMode() {
		return transportMode;
	}

	/**
	 * Sets the transport mode.
	 *
	 * @param transportMode the new transport mode
	 */
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}

	/**
	 * Gets the road carr code.
	 *
	 * @return the road carr code
	 */
	public String getRoadCarrCode() {
		return roadCarrCode;
	}

	/**
	 * Sets the road carr code.
	 *
	 * @param roadCarrCode the new road carr code
	 */
	public void setRoadCarrCode(String roadCarrCode) {
		this.roadCarrCode = roadCarrCode;
	}

	/**
	 * Gets the road tp bond no.
	 *
	 * @return the road tp bond no
	 */
	public String getRoadTpBondNo() {
		return roadTPBondNo;
	}

	/**
	 * Sets the road tp bond no.
	 *
	 * @param roadTpBondNo the new road tp bond no
	 */
	public void setRoadTpBondNo(String roadTpBondNo) {
		this.roadTPBondNo = roadTpBondNo;
	}

	/**
	 * Gets the submit date time.
	 *
	 * @return the submit date time
	 */
	public String getSubmitDateTime() {
		return submitDateTime;
	}

	/**
	 * Sets the submit date time.
	 *
	 * @param submitDateTime the new submit date time
	 */
	public void setSubmitDateTime(String submitDateTime) {
		this.submitDateTime = submitDateTime;
	}

	/**
	 * Gets the weigh.
	 *
	 * @return the weigh
	 */
	public String getWeigh() {
		return weight;
	}

	/**
	 * Sets the weigh.
	 *
	 * @param weigh the new weigh
	 */
	public void setWeigh(String weigh) {
		this.weight = weigh;
	}

	/**
	 * Gets the nhava sheva eta.
	 *
	 * @return the nhava sheva eta
	 */
	public String getNhavaShevaEta() {
		return nhavaShevaEta;
	}

	/**
	 * Sets the nhava sheva eta.
	 *
	 * @param nhavaShevaEta the new nhava sheva eta
	 */
	public void setNhavaShevaEta(String nhavaShevaEta) {
		this.nhavaShevaEta = nhavaShevaEta;
	}

	/**
	 * Gets the final place delivery.
	 *
	 * @return the final place delivery
	 */
	public String getFinalPlaceDelivery() {
		return finalPlaceDelivery;
	}

	/**
	 * Sets the final place delivery.
	 *
	 * @param finalPlaceDelivery the new final place delivery
	 */
	public void setFinalPlaceDelivery(String finalPlaceDelivery) {
		this.finalPlaceDelivery = finalPlaceDelivery;
	}

	/**
	 * Gets the packages.
	 *
	 * @return the packages
	 */
	public String getPackages() {
		return packages;
	}

	/**
	 * Sets the packages.
	 *
	 * @param packages the new packages
	 */
	public void setPackages(String packages) {
		this.packages = packages;
	}

	/**
	 * Gets the cfs name.
	 *
	 * @return the cfs name
	 */
	public String getCfsName() {
		return cfsName;
	}

	/**
	 * Sets the cfs name.
	 *
	 * @param cfsName the new cfs name
	 */
	public void setCfsName(String cfsName) {
		this.cfsName = cfsName;
	}

	/**
	 * Gets the mbl no.
	 *
	 * @return the mbl no
	 */
	public String getMblNo() {
		return mblNo;
	}

	/**
	 * Sets the mbl no.
	 *
	 * @param mblNo the new mbl no
	 */
	public void setMblNo(String mblNo) {
		this.mblNo = mblNo;
	}

	/**
	 * Gets the hbl no.
	 *
	 * @return the hbl no
	 */
	public String getHblNo() {
		return hblNo;
	}

	/**
	 * Sets the hbl no.
	 *
	 * @param hblNo the new hbl no
	 */
	public void setHblNo(String hblNo) {
		this.hblNo = hblNo;
	}

	/**
	 * Gets the dpd Movement.
	 *
	 * @return the dpd Movement
	 */
	public String getDpdMovement() {
		return dpdMovement;
	}

	/**
	 * Sets the dpd Movement.
	 *
	 * @param dpdMovement the new dpd Movement
	 */
	public void setDpdMovement(String dpdMovement) {
		this.dpdMovement = dpdMovement;
	}

	/**
	 * Gets the DPD code.
	 *
	 * @return the DPD code
	 */
	public String getDpdCode() {
		return dpdCode;
	}

	/**
	 * Sets the DPD code.
	 *
	 * @param DPDcode the new DPD code
	 */
	public void setDpdCode(String dpdCode) {
		this.dpdCode = dpdCode;
	}

	/**
	 * Gets the bl Version.
	 *
	 * @return the bl Version
	 */
	public String getBlVersion() {
		return blVersion;
	}

	/**
	 * Sets the bl Version.
	 *
	 * @param blVersion the new bl Version
	 */
	public void setBlVersion(String blVersion) {
		this.blVersion = blVersion;
	}

	/**
	 * Gets the Consignee.
	 *
	 * @return the Consignee
	 */
	public List<Consignee> getConsignee() {
		return consignee;
	}

	/**
	 * Sets the consignee.
	 *
	 * @param consignee the new consignee.
	 */
	public void setConsignee(List<Consignee> consignee) {
		this.consignee = consignee;
	}

	/**
	 * Gets the nortify Party.
	 *
	 * @return the nortify Party.
	 */
	public List<NotifyParty> getNotifyParty() {
		return notifyParty;
	}

	/**
	 * Sets the nortify Party.
	 *
	 * @param nortifyParty the new nortify Party.
	 */
	public void setNotifyParty(List<NotifyParty> notifyParty) {
		this.notifyParty = notifyParty;
	}

	/**
	 * Gets the notify Party Two.
	 *
	 * @return the notify Party Two.
	 */
	public List<NotifyPartyTwo> getNotifyPartyTwo() {
		return notifyPartyTwo;
	}

	/**
	 * Sets the notify Party Two.
	 *
	 * @param nortifyParty the new notify Party Two.
	 */
	public void setNotifyPartyTwo(List<NotifyPartyTwo> notifyPartyTwo) {
		this.notifyPartyTwo = notifyPartyTwo;
	}

	/**
	 * Gets the marks Number.
	 *
	 * @return the marks Number.
	 */
	public List<MarksNumber> getMarksNumber() {
		return marksNumber;
	}

	/**
	 * Sets the marks Number.
	 *
	 * @param marksNumber the new marks Number.
	 */
	public void setMarksNumber(List<MarksNumber> marksNumber) {
		this.marksNumber = marksNumber;
	}

	/**
	 * Gets the Container Detailes.
	 *
	 * @return the Container Detailes.
	 */
	public List<ContainerDetails> getContainerDetailes() {
		return containerDetailes;
	}

	/**
	 * Sets the Container Detailes.
	 *
	 * @param containerDetailes the new Container Detailes.
	 */
	public void setContainerDetailes(List<ContainerDetails> containerDetailes) {
		this.containerDetailes = containerDetailes;
	}

	public List<PreviousDeclaration> getPreviousDeclaration() {
		return previousDeclaration;
	}

	public void setPreviousDeclaration(List<PreviousDeclaration> previousDeclaration) {
		this.previousDeclaration = previousDeclaration;
	}

	public String getCusAdd1() {
		return CusAdd1;
	}

	public void setCusAdd1(String cusAdd1) {
		CusAdd1 = cusAdd1;
	}

	public String getCusAdd2() {
		return CusAdd2;
	}

	public void setCusAdd2(String cusAdd2) {
		CusAdd2 = cusAdd2;
	}

	public String getCusAdd3() {
		return CusAdd3;
	}

	public void setCusAdd3(String cusAdd3) {
		CusAdd3 = cusAdd3;
	}

	public String getCusAdd4() {
		return CusAdd4;
	}

	public void setCusAdd4(String cusAdd4) {
		CusAdd4 = cusAdd4;
	}

	public String getIsValidateBL() {
		return IsValidateBL;
	}

	public void setIsValidateBL(String isValidateBL) {
		IsValidateBL = isValidateBL;
	}

	public String getGrossCargoWeightBLlevel() {
		return GrossCargoWeightBLlevel;
	}

	public void setGrossCargoWeightBLlevel(String grossCargoWeightBLlevel) {
		GrossCargoWeightBLlevel = grossCargoWeightBLlevel;
	}

	public String getPackageBLLevel() {
		return PackageBLLevel;
	}

	public void setPackageBLLevel(String packageBLLevel) {
		PackageBLLevel = packageBLLevel;
	}

	public String getDep_manif_no() {
		return dep_manif_no;
	}

	public void setDep_manif_no(String dep_manif_no) {
		this.dep_manif_no = dep_manif_no;
	}

	public String getDep_manifest_date() {
		return dep_manifest_date;
	}

	public void setDep_manifest_date(String dep_manifest_date) {
		this.dep_manifest_date = dep_manifest_date;
	}

	public String getSubmitter_type() {
		return submitter_type;
	}

	public void setSubmitter_type(String submitter_type) {
		this.submitter_type = submitter_type;
	}

	public String getSubmitter_code() {
		return submitter_code;
	}

	public void setSubmitter_code(String submitter_code) {
		this.submitter_code = submitter_code;
	}

	public String getAuthoriz_rep_code() {
		return authoriz_rep_code;
	}

	public void setAuthoriz_rep_code(String authoriz_rep_code) {
		this.authoriz_rep_code = authoriz_rep_code;
	}

	public String getShipping_line_bond_no_r() {
		return shipping_line_bond_no_r;
	}

	public void setShipping_line_bond_no_r(String shipping_line_bond_no_r) {
		this.shipping_line_bond_no_r = shipping_line_bond_no_r;
	}

	public String getMode_of_transport() {
		return mode_of_transport;
	}

	public void setMode_of_transport(String mode_of_transport) {
		this.mode_of_transport = mode_of_transport;
	}

	public String getShip_type() {
		return ship_type;
	}

	public void setShip_type(String ship_type) {
		this.ship_type = ship_type;
	}

	public String getConveyance_reference_no() {
		return conveyance_reference_no;
	}

	public void setConveyance_reference_no(String conveyance_reference_no) {
		this.conveyance_reference_no = conveyance_reference_no;
	}

	public String getTol_no_of_trans_equ_manif() {
		return tol_no_of_trans_equ_manif;
	}

	public void setTol_no_of_trans_equ_manif(String tol_no_of_trans_equ_manif) {
		this.tol_no_of_trans_equ_manif = tol_no_of_trans_equ_manif;
	}

	public String getBrief_cargo_des() {
		return brief_cargo_des;
	}

	public void setBrief_cargo_des(String brief_cargo_des) {
		this.brief_cargo_des = brief_cargo_des;
	}

	public String getExpected_date() {
		return expected_date;
	}

	public void setExpected_date(String expected_date) {
		this.expected_date = expected_date;
	}

	public String getTime_of_dept() {
		return time_of_dept;
	}

	public void setTime_of_dept(String time_of_dept) {
		this.time_of_dept = time_of_dept;
	}

	public String getNo_of_crew_manif() {
		return no_of_crew_manif;
	}

	public void setNo_of_crew_manif(String no_of_crew_manif) {
		this.no_of_crew_manif = no_of_crew_manif;
	}

	public String getPort_of_call_cod() {
		return port_of_call_cod;
	}

	public void setPort_of_call_cod(String port_of_call_cod) {
		this.port_of_call_cod = port_of_call_cod;
	}

	public String getTotal_no_of_tran_s_cont_repo_on_ari_dep() {
		return total_no_of_tran_s_cont_repo_on_ari_dep;
	}

	public void setTotal_no_of_tran_s_cont_repo_on_ari_dep(String total_no_of_tran_s_cont_repo_on_ari_dep) {
		this.total_no_of_tran_s_cont_repo_on_ari_dep = total_no_of_tran_s_cont_repo_on_ari_dep;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getPort_of_reporting() {
		return port_of_reporting;
	}

	public void setPort_of_reporting(String port_of_reporting) {
		this.port_of_reporting = port_of_reporting;
	}

	public String getJob_number() {
		return job_number;
	}

	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}

	public String getJob_date() {
		return job_date;
	}

	public void setJob_date(String job_date) {
		this.job_date = job_date;
	}

	public String getReporting_event() {
		return reporting_event;
	}

	public void setReporting_event(String reporting_event) {
		this.reporting_event = reporting_event;
	}

	public String getManifest_no_csn_no() {
		return manifest_no_csn_no;
	}

	public void setManifest_no_csn_no(String manifest_no_csn_no) {
		this.manifest_no_csn_no = manifest_no_csn_no;
	}

	public String getManifest_date_csn_date() {
		return manifest_date_csn_date;
	}

	public void setManifest_date_csn_date(String manifest_date_csn_date) {
		this.manifest_date_csn_date = manifest_date_csn_date;
	}

	public String getVessel_type_movement() {
		return vessel_type_movement;
	}

	public void setVessel_type_movement(String vessel_type_movement) {
		this.vessel_type_movement = vessel_type_movement;
	}

	public String getShipping_line_code() {
		return shipping_line_code;
	}

	public void setShipping_line_code(String shipping_line_code) {
		this.shipping_line_code = shipping_line_code;
	}

	public String getAuthorized_sea_carrier_code() {
		return authorized_sea_carrier_code;
	}

	public void setAuthorized_sea_carrier_code(String authorized_sea_carrier_code) {
		this.authorized_sea_carrier_code = authorized_sea_carrier_code;
	}

	public String getPort_of_registry() {
		return port_of_registry;
	}

	public void setPort_of_registry(String port_of_registry) {
		this.port_of_registry = port_of_registry;
	}

	public String getRegistry_date() {
		return registry_date;
	}

	public void setRegistry_date(String registry_date) {
		this.registry_date = registry_date;
	}

	public String getVoyage_details_movement() {
		return voyage_details_movement;
	}

	public void setVoyage_details_movement(String voyage_details_movement) {
		this.voyage_details_movement = voyage_details_movement;
	}

	public String getShip_itinerary_sequence() {
		return ship_itinerary_sequence;
	}

	public void setShip_itinerary_sequence(String ship_itinerary_sequence) {
		this.ship_itinerary_sequence = ship_itinerary_sequence;
	}

	public String getShip_itinerary() {
		return ship_itinerary;
	}

	public void setShip_itinerary(String ship_itinerary) {
		this.ship_itinerary = ship_itinerary;
	}

	public String getPort_of_call_name() {
		return port_of_call_name;
	}

	public void setPort_of_call_name(String port_of_call_name) {
		this.port_of_call_name = port_of_call_name;
	}

	public String getArrival_departure_details() {
		return arrival_departure_details;
	}

	public void setArrival_departure_details(String arrival_departure_details) {
		this.arrival_departure_details = arrival_departure_details;
	}

	public String getNumber_of_crew() {
		return number_of_crew;
	}

	public void setNumber_of_crew(String number_of_crew) {
		this.number_of_crew = number_of_crew;
	}

	public String getTotal_no_of_transport_equipment_reported_on_arrival_departure() {
		return total_no_of_transport_equipment_reported_on_arrival_departure;
	}

	public void setTotal_no_of_transport_equipment_reported_on_arrival_departure(
			String total_no_of_transport_equipment_reported_on_arrival_departure) {
		this.total_no_of_transport_equipment_reported_on_arrival_departure = total_no_of_transport_equipment_reported_on_arrival_departure;
	}

	public String getCargo_description() {
		return cargo_description;
	}

	public void setCargo_description(String cargo_description) {
		this.cargo_description = cargo_description;
	}

	public String getConsolidated_indicator() {
		return consolidated_indicator;
	}

	public void setConsolidated_indicator(String consolidated_indicator) {
		this.consolidated_indicator = consolidated_indicator;
	}

	public String getPrevious_declaration() {
		return previous_declaration;
	}

	public void setPrevious_declaration(String previous_declaration) {
		this.previous_declaration = previous_declaration;
	}

	public String getConsolidator_pan() {
		return consolidator_pan;
	}

	public void setConsolidator_pan(String consolidator_pan) {
		this.consolidator_pan = consolidator_pan;
	}

	public String getCin_type() {
		return cin_type;
	}

	public void setCin_type(String cin_type) {
		this.cin_type = cin_type;
	}

	public String getMcin() {
		return mcin;
	}

	public void setMcin(String mcin) {
		this.mcin = mcin;
	}
	
	public String getPcin() {
		return pcin;
	}

	public void setPcin(String pcin) {
		this.pcin = pcin;
	}

	public String getCsn_submitted_type() {
		return csn_submitted_type;
	}

	public void setCsn_submitted_type(String csn_submitted_type) {
		this.csn_submitted_type = csn_submitted_type;
	}

	public String getCsn_submitted_by() {
		return csn_submitted_by;
	}

	public void setCsn_submitted_by(String csn_submitted_by) {
		this.csn_submitted_by = csn_submitted_by;
	}

	public String getCsn_site_id() {
		return csn_site_id;
	}

	public void setCsn_site_id(String csn_site_id) {
		this.csn_site_id = csn_site_id;
	}

	public String getCsn_number() {
		return csn_number;
	}

	public void setCsn_number(String csn_number) {
		this.csn_number = csn_number;
	}

	public String getCsn_date() {
		return csn_date;
	}

	public void setCsn_date(String csn_date) {
		this.csn_date = csn_date;
	}

	public String getSplit_indicator() {
		return split_indicator;
	}

	public void setSplit_indicator(String split_indicator) {
		this.split_indicator = split_indicator;
	}

	public String getNumber_of_packages() {
		return number_of_packages;
	}

	public void setNumber_of_packages(String number_of_packages) {
		this.number_of_packages = number_of_packages;
	}

	public String getType_of_package() {
		return type_of_package;
	}

	public void setType_of_package(String type_of_package) {
		this.type_of_package = type_of_package;
	}

	public String getFirst_port_of_entry_last_port_of_departure() {
		return first_port_of_entry_last_port_of_departure;
	}

	public void setFirst_port_of_entry_last_port_of_departure(String first_port_of_entry_last_port_of_departure) {
		this.first_port_of_entry_last_port_of_departure = first_port_of_entry_last_port_of_departure;
	}

	public String getPort_of_acceptance() {
		return port_of_acceptance;
	}

	public void setPort_of_acceptance(String port_of_acceptance) {
		this.port_of_acceptance = port_of_acceptance;
	}

	public String getUcr_type() {
		return ucr_type;
	}

	public void setUcr_type(String ucr_type) {
		this.ucr_type = ucr_type;
	}

	public String getUcr_code() {
		return ucr_code;
	}

	public void setUcr_code(String ucr_code) {
		this.ucr_code = ucr_code;
	}

	/*
	 * public String getUcr_type_of_packages() { return ucr_type_of_packages; }
	 * 
	 * public void setUcr_type_of_packages(String ucr_type_of_packages) {
	 * this.ucr_type_of_packages = ucr_type_of_packages; }
	 */
	public String getEquipment_load_status() {
		return equipment_load_status;
	}

	public void setEquipment_load_status(String equipment_load_status) {
		this.equipment_load_status = equipment_load_status;
	}

	/*
	 * public String getEquipment_size() { return equipment_size; }
	 * 
	 * public void setEquipment_size(String equipment_size) { this.equipment_size =
	 * equipment_size; }
	 */
	public String getEquipment_seal_type() {
		return equipment_seal_type;
	}

	public void setEquipment_seal_type(String equipment_seal_type) {
		this.equipment_seal_type = equipment_seal_type;
	}

	public String getSoc_flag() {
		return soc_flag;
	}

	public void setSoc_flag(String soc_flag) {
		this.soc_flag = soc_flag;
	}

	/*
	 * public String getContainer_agent_code() { return container_agent_code; }
	 */

	/*
	 * public void setContainer_agent_code(String container_agent_code) {
	 * this.container_agent_code = container_agent_code; }
	 * 
	 * public String getContainer_weight() { return container_weight; }
	 */

	/*
	 * public void setContainer_weight(String container_weight) {
	 * this.container_weight = container_weight; }
	 */

	public String getTotal_number_of_packages() {
		return total_number_of_packages;
	}

	/*
	 * public void setTotal_number_of_packages(String total_number_of_packages) {
	 * this.total_number_of_packages = total_number_of_packages; }
	 * 
	 * public String getPort_of_acceptance_name() { return port_of_acceptance_name;
	 * }
	 */

	public void setPort_of_acceptance_name(String port_of_acceptance_name) {
		this.port_of_acceptance_name = port_of_acceptance_name;
	}

	public String getMc_item_details() {
		return mc_item_details;
	}

	public void setMc_item_details(String mc_item_details) {
		this.mc_item_details = mc_item_details;
	}

	public String getCargo_item_sequence_no() {
		return cargo_item_sequence_no;
	}

	public void setCargo_item_sequence_no(String cargo_item_sequence_no) {
		this.cargo_item_sequence_no = cargo_item_sequence_no;
	}

	public String getCargo_item_description() {
		return cargo_item_description;
	}

	public void setCargo_item_description(String cargo_item_description) {
		this.cargo_item_description = cargo_item_description;
	}

	/*
	 * public String getType_of_packages() { return type_of_packages; }
	 * 
	 * public void setType_of_packages(String type_of_packages) {
	 * this.type_of_packages = type_of_packages; }
	 */

	public String getCsn_reporting_type() {
		return csn_reporting_type;
	}

	public void setCsn_reporting_type(String csn_reporting_type) {
		this.csn_reporting_type = csn_reporting_type;
	}

	public String getPrevious_mcin() {
		return previous_mcin;
	}

	public void setPrevious_mcin(String previous_mcin) {
		this.previous_mcin = previous_mcin;
	}

	public String getType_of_cargo() {
		return type_of_cargo;
	}

	public void setType_of_cargo(String type_of_cargo) {
		this.type_of_cargo = type_of_cargo;
	}

	public String getSplit_indicator_list() {
		return split_indicator_list;
	}

	public void setSplit_indicator_list(String split_indicator_list) {
		this.split_indicator_list = split_indicator_list;
	}

	public String getPort_of_receipt() {
		return port_of_receipt;
	}

	public void setPort_of_receipt(String port_of_receipt) {
		this.port_of_receipt = port_of_receipt;
	}

	public String getPort_of_receipt_name() {
		return port_of_receipt_name;
	}

	public void setPort_of_receipt_name(String port_of_receipt_name) {
		this.port_of_receipt_name = port_of_receipt_name;
	}

	public String getPan_of_notified_party() {
		return pan_of_notified_party;
	}

	public void setPan_of_notified_party(String pan_of_notified_party) {
		this.pan_of_notified_party = pan_of_notified_party;
	}

	public String getUnit_of_weight() {
		return unit_of_weight;
	}

	public void setUnit_of_weight(String unit_of_weight) {
		this.unit_of_weight = unit_of_weight;
	}

	public String getGross_volume() {
		return gross_volume;
	}

	public void setGross_volume(String gross_volume) {
		this.gross_volume = gross_volume;
	}

	public String getUnit_of_volume() {
		return unit_of_volume;
	}

	public void setUnit_of_volume(String unit_of_volume) {
		this.unit_of_volume = unit_of_volume;
	}

	public String getNumber_of_packages_hidden() {
		return number_of_packages_hidden;
	}

	public void setNumber_of_packages_hidden(String number_of_packages_hidden) {
		this.number_of_packages_hidden = number_of_packages_hidden;
	}

	public String getType_of_packages_hidden() {
		return type_of_packages_hidden;
	}

	public void setType_of_packages_hidden(String type_of_packages_hidden) {
		this.type_of_packages_hidden = type_of_packages_hidden;
	}

	public String getContainer_weight() {
		return container_weight;
	}

	public void setContainer_weight(String container_weight) {
		this.container_weight = container_weight;
	}

	public String getPort_of_acceptance_name() {
		return port_of_acceptance_name;
	}

	public void setTotal_number_of_packages(String total_number_of_packages) {
		this.total_number_of_packages = total_number_of_packages;
	}

	public String getPort_of_call_sequence_number() {
		return port_of_call_sequence_number;
	}

	public void setPort_of_call_sequence_number(String port_of_call_sequence_number) {
		this.port_of_call_sequence_number = port_of_call_sequence_number;
	}

	public String getPort_of_call_coded() {
		return port_of_call_coded;
	}

	public void setPort_of_call_coded(String port_of_call_coded) {
		this.port_of_call_coded = port_of_call_coded;
	}

	public String getNext_port_of_call_coded() {
		return next_port_of_call_coded;
	}

	public void setNext_port_of_call_coded(String next_port_of_call_coded) {
		this.next_port_of_call_coded = next_port_of_call_coded;
	}

	public String getConsigneeName() {
		return consigneeName;
	}


	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}


	public String getMc_location_customs() {
		return mc_location_customs;
	}

	public void setMc_location_customs(String mc_location_customs) {
		this.mc_location_customs = mc_location_customs;
	}

	public List<Consigner> getConsigner() {
		return consigner;
	}

	public void setConsigner(List<Consigner> consigner) {
		this.consigner = consigner;
	}

	public String getNextport1() {
		return nextport1;
	}

	public void setNextport1(String nextport1) {
		this.nextport1 = nextport1;
	}

	public String getNextport2() {
		return nextport2;
	}

	public void setNextport2(String nextport2) {
		this.nextport2 = nextport2;
	}

	public String getNextport3() {
		return nextport3;
	}

	public void setNextport3(String nextport3) {
		this.nextport3 = nextport3;
	}
	
	public String getIgmDateVal() {
		return igmDateVal;
	}

	public void setIgmDateVal(String igmDateVal) {
		this.igmDateVal = igmDateVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pod == null) ? 0 : pod.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		result = prime * result + ((polTerminal == null) ? 0 : polTerminal.hashCode());
		result = prime * result + ((vessel == null) ? 0 : vessel.hashCode());
		result = prime * result + ((voyage == null) ? 0 : voyage.hashCode());

		result = prime * result + ((enblockMovement == null) ? 0 : enblockMovement.hashCode());
		result = prime * result + ((carrierNo == null) ? 0 : carrierNo.hashCode());
		result = prime * result + ((agencyType == null) ? 0 : agencyType.hashCode());
		result = prime * result + ((invoiceValueFc == null) ? 0 : invoiceValueFc.hashCode());
		result = prime * result + ((invoiceValueInr == null) ? 0 : invoiceValueInr.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((invoiceItems == null) ? 0 : invoiceItems.hashCode());
		result = prime * result + ((modeOfTpFee == null) ? 0 : modeOfTpFee.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((subLineNumber == null) ? 0 : subLineNumber.hashCode());
		result = prime * result + ((portOfDestination == null) ? 0 : portOfDestination.hashCode());
		result = prime * result + ((portOfLoading == null) ? 0 : portOfLoading.hashCode());
		result = prime * result + ((portOfDeschargedCfs == null) ? 0 : portOfDeschargedCfs.hashCode());
		result = prime * result + ((multipalPakages == null) ? 0 : multipalPakages.hashCode());
		result = prime * result + ((cbm == null) ? 0 : cbm.hashCode());
		result = prime * result + ((hightValue == null) ? 0 : hightValue.hashCode());
		result = prime * result + ((grosWeight == null) ? 0 : grosWeight.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());

		return result;
	}

	public String getIsBlSave() {
		return isBlSave;
	}

	public void setIsBlSave(String isBlSave) {
		this.isBlSave = isBlSave;
	}

	public String getEnblockMovement() {
		return enblockMovement;
	}

	public void setEnblockMovement(String enblockMovement) {
		this.enblockMovement = enblockMovement;
	}

	public String getCarrierNo() {
		return carrierNo;
	}

	public void setCarrierNo(String carrierNo) {
		this.carrierNo = carrierNo;
	}

	public String getTpBondNo() {
		return tpBondNo;
	}

	public void setTpBondNo(String tpBondNo) {
		this.tpBondNo = tpBondNo;
	}

	public String getAgencyType() {
		return agencyType;
	}

	public void setAgencyType(String agencyType) {
		this.agencyType = agencyType;
	}

	public String getInvoiceValueFc() {
		return invoiceValueFc;
	}

	public void setInvoiceValueFc(String invoiceValueFc) {
		this.invoiceValueFc = invoiceValueFc;
	}

	public String getInvoiceValueInr() {
		return invoiceValueInr;
	}

	public void setInvoiceValueInr(String invoiceValueInr) {
		this.invoiceValueInr = invoiceValueInr;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(String invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public String getModeOfTpFee() {
		return modeOfTpFee;
	}

	public void setModeOfTpFee(String modeOfTpFee) {
		this.modeOfTpFee = modeOfTpFee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSubLineNumber() {
		return subLineNumber;
	}

	public void setSubLineNumber(String subLineNumber) {
		this.subLineNumber = subLineNumber;
	}

	public String getPortOfDestination() {
		return portOfDestination;
	}

	public void setPortOfDestination(String portOfDestination) {
		this.portOfDestination = portOfDestination;
	}

	public String getPortOfLoading() {
		return portOfLoading;
	}

	public void setPortOfLoading(String portOfLoading) {
		this.portOfLoading = portOfLoading;
	}

	public String getPortOfDeschargedCfs() {
		return portOfDeschargedCfs;
	}

	public void setPortOfDeschargedCfs(String portOfDeschargedCfs) {
		this.portOfDeschargedCfs = portOfDeschargedCfs;
	}

	public String getMultipalPakages() {
		return multipalPakages;
	}

	public void setMultipalPakages(String multipalPakages) {
		this.multipalPakages = multipalPakages;
	}

	public String getCbm() {
		return cbm;
	}

	public void setCbm(String cbm) {
		this.cbm = cbm;
	}

	public String getHightValue() {
		return hightValue;
	}

	public void setHightValue(String hightValue) {
		this.hightValue = hightValue;
	}

	public String getGrosWeight() {
		return grosWeight;
	}

	public void setGrosWeight(String grosWeight) {
		this.grosWeight = grosWeight;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getNotifyPartyCode() {
		return notifyPartyCode;
	}

	public void setNotifyPartyCode(String notifyPartyCode) {
		this.notifyPartyCode = notifyPartyCode;
	}

	public String getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(String totalItems) {
		this.totalItems = totalItems;
	}
	
	public String getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}

	public String getDutyInr() {
		return dutyInr;
	}

	public void setDutyInr(String dutyInr) {
		this.dutyInr = dutyInr;
	}


	public String getTotalIteamNo() {
		return totalIteamNo;
	}

	public void setTotalIteamNo(String totalIteamNo) {
		this.totalIteamNo = totalIteamNo;
	}
	
	
	

	public String getIgmYearVal() {
		return igmYearVal;
	}

	public void setIgmYearVal(String igmYearVal) {
		this.igmYearVal = igmYearVal;
	}
	
	public List<IGMPersonOnBoardMod> getPersonOnBoardMod() {
		return personOnBoardMod;
	}

	public void setPersonOnBoardMod(List<IGMPersonOnBoardMod> personOnBoardMod) {
		this.personOnBoardMod = personOnBoardMod;
	}

	public List<IGMCrewEfctMod> getCrewEfctMods() {
		return crewEfctMods;
	}

	public void setCrewEfctMods(List<IGMCrewEfctMod> crewEfctMods) {
		this.crewEfctMods = crewEfctMods;
	}

	public List<IGMShipStoresMod> getIgmShipStoresMods() {
		return igmShipStoresMods;
	}

	public void setIgmShipStoresMods(List<IGMShipStoresMod> igmShipStoresMods) {
		this.igmShipStoresMods = igmShipStoresMods;
	}
	

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}

	public String getAuthRepCd() {
		return authRepCd;
	}

	public void setAuthRepCd(String authRepCd) {
		this.authRepCd = authRepCd;
	}
	
	

	public String getAuthReprsntvCd() {
		return authReprsntvCd;
	}

	public void setAuthReprsntvCd(String authReprsntvCd) {
		this.authReprsntvCd = authReprsntvCd;
	}
	
	
// this for checking house bl
	public boolean isHbl() {
		return isHbl;
	}

	public void setHbl(boolean isHbl) {
		this.isHbl = isHbl;
	}

	public String getPort_of_call_name_last3() {
		return port_of_call_name_last3;
	}

	public void setPort_of_call_name_last3(String port_of_call_name_last3) {
		this.port_of_call_name_last3 = port_of_call_name_last3;
	}

	public String getPort_of_call_name_last2() {
		return port_of_call_name_last2;
	}

	public void setPort_of_call_name_last2(String port_of_call_name_last2) {
		this.port_of_call_name_last2 = port_of_call_name_last2;
	}

	public String getPort_of_call_name_last1() {
		return port_of_call_name_last1;
	}

	public void setPort_of_call_name_last1(String port_of_call_name_last1) {
		this.port_of_call_name_last1 = port_of_call_name_last1;
	}

	public String getPort_of_call_name_nextport1() {
		return port_of_call_name_nextport1;
	}

	public void setPort_of_call_name_nextport1(String port_of_call_name_nextport1) {
		this.port_of_call_name_nextport1 = port_of_call_name_nextport1;
	}

	public String getPort_of_call_name_nextport2() {
		return port_of_call_name_nextport2;
	}

	public void setPort_of_call_name_nextport2(String port_of_call_name_nextport2) {
		this.port_of_call_name_nextport2 = port_of_call_name_nextport2;
	}

	public String getPort_of_call_name_nextport3() {
		return port_of_call_name_nextport3;
	}

	public void setPort_of_call_name_nextport3(String port_of_call_name_nextport3) {
		this.port_of_call_name_nextport3 = port_of_call_name_nextport3;
	}

	public String getPort_of_call_name_portOrigin() {
		return port_of_call_name_portOrigin;
	}

	public void setPort_of_call_name_portOrigin(String port_of_call_name_portOrigin) {
		this.port_of_call_name_portOrigin = port_of_call_name_portOrigin;
	}

	public String getPort_of_call_name_portArrival() {
		return port_of_call_name_portArrival;
	}

	public void setPort_of_call_name_portArrival(String port_of_call_name_portArrival) {
		this.port_of_call_name_portArrival = port_of_call_name_portArrival;
	}


	
	public String getDnDischargePort() {
		return dnDischargePort;
	}

	public void setDnDischargePort(String dnDischargePort) {
		this.dnDischargePort = dnDischargePort;
	}

	public String getFlag_discharge() {
		return flag_discharge;
	}

	public void setFlag_discharge(String flag_discharge) {
		this.flag_discharge = flag_discharge;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImportGeneralManifestMod other = (ImportGeneralManifestMod) obj;
		if (pod == null) {
			if (other.pod != null)
				return false;
		} else if (!pod.equals(other.pod))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		if (terminal == null) {
			if (other.terminal != null)
				return false;
		} else if (!terminal.equals(other.terminal))
			return false;
		if (vessel == null) {
			if (other.vessel != null)
				return false;
		} else if (!vessel.equals(other.vessel))
			return false;
		if (voyage == null) {
			if (other.voyage != null)
				return false;
		} else if (!voyage.equals(other.voyage))
			return false;
		if (enblockMovement == null) {
			if (other.enblockMovement != null)
				return false;
		} else if (!enblockMovement.equals(other.enblockMovement))
			return false;
		if (carrierNo == null) {
			if (other.carrierNo != null)
				return false;
		} else if (!carrierNo.equals(other.carrierNo))
			return false;
		if (agencyType == null) {
			if (other.agencyType != null)
				return false;
		} else if (!agencyType.equals(other.agencyType))
			return false;
		if (invoiceValueFc == null) {
			if (other.invoiceValueFc != null)
				return false;
		} else if (!invoiceValueFc.equals(other.invoiceValueFc))
			return false;
		if (invoiceValueInr == null) {
			if (other.invoiceValueInr != null)
				return false;
		} else if (!invoiceValueInr.equals(other.invoiceValueInr))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (invoiceItems == null) {
			if (other.invoiceItems != null)
				return false;
		} else if (!invoiceItems.equals(other.invoiceItems))
			return false;
		if (modeOfTpFee == null) {
			if (other.modeOfTpFee != null)
				return false;
		} else if (!modeOfTpFee.equals(other.modeOfTpFee))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (subLineNumber == null) {
			if (other.subLineNumber != null)
				return false;
		} else if (!subLineNumber.equals(other.subLineNumber))
			return false;
		if (portOfDestination == null) {
			if (other.portOfDestination != null)
				return false;
		} else if (!portOfDestination.equals(other.portOfDestination))
			return false;
		if (portOfLoading == null) {
			if (other.portOfLoading != null)
				return false;
		} else if (!portOfLoading.equals(other.portOfLoading))
			return false;
		if (portOfDeschargedCfs == null) {
			if (other.portOfDeschargedCfs != null)
				return false;
		} else if (!portOfDeschargedCfs.equals(other.portOfDeschargedCfs))
			return false;
		if (multipalPakages == null) {
			if (other.multipalPakages != null)
				return false;
		} else if (!multipalPakages.equals(other.multipalPakages))
			return false;
		if (cbm == null) {
			if (other.cbm != null)
				return false;
		} else if (!cbm.equals(other.cbm))
			return false;
		if (hightValue == null) {
			if (other.hightValue != null)
				return false;
		} else if (!hightValue.equals(other.hightValue))
			return false;
		if (grosWeight == null) {
			if (other.grosWeight != null)
				return false;
		} else if (!grosWeight.equals(other.grosWeight))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (volume == null) {
			if (other.volume != null)
				return false;
		} else if (!volume.equals(other.volume))
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	public String getPrevious_pcin() {
		return previous_pcin;
	}

	public void setPrevious_pcin(String previous_pcin) {
		this.previous_pcin = previous_pcin;
	}

	private String $$hashKey;

	public String get$$hashKey() {
		return $$hashKey;
	}

	public void set$$hashKey(String $$hashKey) {
		this.$$hashKey = $$hashKey;
	}

	public String getRoadCarrCodeVVS() {
		return roadCarrCodeVVS;
	}

	public void setRoadCarrCodeVVS(String roadCarrCodeVVS) {
		this.roadCarrCodeVVS = roadCarrCodeVVS;
	}

	/*
	 * public List<IGMPersonOnBoardMod> getPersonOnBoardMod() { return
	 * personOnBoardMod; }
	 * 
	 * public void setPersonOnBoardMod(List<IGMPersonOnBoardMod> personOnBoardMod) {
	 * this.personOnBoardMod = personOnBoardMod; }
	 */

	public String getTpbondnoVVS() {
		return tpbondnoVVS;
	}

	public void setTpbondnoVVS(String tpbondnoVVS) {
		this.tpbondnoVVS = tpbondnoVVS;
	}
	
	public String getHouseBl() {
		return houseBl;
	}

	public void setHouseBl(String houseBl) {
		this.houseBl = houseBl;
	}

	public String getNotifyName() {
		return notifyName;
	}

	public void setNotifyName(String notifyName) {
		this.notifyName = notifyName;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ImportGeneralManifestMod [consignee=");
		builder.append(consignee);
		builder.append(", consigner=");
		builder.append(consigner);
		builder.append(", notifyParty=");
		builder.append(notifyParty);
		builder.append(", notifyPartyTwo=");
		builder.append(notifyPartyTwo);
		builder.append(", marksNumber=");
		builder.append(marksNumber);
		builder.append(", containerDetailes=");
		builder.append(containerDetailes);
		builder.append(", previousDeclaration=");
		builder.append(previousDeclaration);
		builder.append(", isBlSave=");
		builder.append(isBlSave);
		builder.append(", bl=");
		builder.append(bl);
		builder.append(", service=");
		builder.append(service);
		builder.append(", vessel=");
		builder.append(vessel);
		builder.append(", voyage=");
		builder.append(voyage);
		builder.append(", pol=");
		builder.append(pol);
		builder.append(", polTerminal=");
		builder.append(polTerminal);
		builder.append(", del=");
		builder.append(del);
		builder.append(", depot=");
		builder.append(depot);
		builder.append(", pod=");
		builder.append(pod);
		builder.append(", podTerminal=");
		builder.append(podTerminal);
		builder.append(", codeCode=");
		builder.append(codeCode);
		builder.append(", callSing=");
		builder.append(callSing);
		builder.append(", lineCode=");
		builder.append(lineCode);
		builder.append(", agentCode=");
		builder.append(agentCode);
		builder.append(", portOrigin=");
		builder.append(portOrigin);
		builder.append(", portArrival=");
		builder.append(portArrival);
		builder.append(", lastPort1=");
		builder.append(lastPort1);
		builder.append(", lastPort2=");
		builder.append(lastPort2);
		builder.append(", lastPort3=");
		builder.append(lastPort3);
		builder.append(", nextport1=");
		builder.append(nextport1);
		builder.append(", nextport2=");
		builder.append(nextport2);
		builder.append(", nextport3=");
		builder.append(nextport3);
		builder.append(", terminal=");
		builder.append(terminal);
		builder.append(", vesselType=");
		builder.append(vesselType);
		builder.append(", genDesc=");
		builder.append(genDesc);
		builder.append(", masterName=");
		builder.append(masterName);
		builder.append(", vesselNation=");
		builder.append(vesselNation);
		builder.append(", igmNumber=");
		builder.append(igmNumber);
		builder.append(", igmDate=");
		builder.append(igmDate);
		builder.append(", arrivalDate=");
		builder.append(arrivalDate);
		builder.append(", arrivalTime=");
		builder.append(arrivalTime);
		builder.append(", ataarrivalDate=");
		builder.append(ataarrivalDate);
		builder.append(", ataarrivalTime=");
		builder.append(ataarrivalTime);
		builder.append(", totalBls=");
		builder.append(totalBls);
		builder.append(", lightDue=");
		builder.append(lightDue);
		builder.append(", grossWeight=");
		builder.append(grossWeight);
		builder.append(", netWeight=");
		builder.append(netWeight);
		builder.append(", smBtCargo=");
		builder.append(smBtCargo);
		builder.append(", shipStrDect=");
		builder.append(shipStrDect);
		builder.append(", crewEffect=");
		builder.append(crewEffect);
		builder.append(", mariTimeDecl=");
		builder.append(mariTimeDecl);
		builder.append(", itemNumber=");
		builder.append(itemNumber);
		builder.append(", cargoNature=");
		builder.append(cargoNature);
		builder.append(", cargoMovmnt=");
		builder.append(cargoMovmnt);
		builder.append(", itemType=");
		builder.append(itemType);
		builder.append(", cargoMovmntType=");
		builder.append(cargoMovmntType);
		builder.append(", transportMode=");
		builder.append(transportMode);
		builder.append(", roadCarrCode=");
		builder.append(roadCarrCode);
		builder.append(", roadTPBondNo=");
		builder.append(roadTPBondNo);
		builder.append(", customTerminalCode=");
		builder.append(customTerminalCode);
		builder.append(", submitDateTime=");
		builder.append(submitDateTime);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", nhavaShevaEta=");
		builder.append(nhavaShevaEta);
		builder.append(", finalPlaceDelivery=");
		builder.append(finalPlaceDelivery);
		builder.append(", packages=");
		builder.append(packages);
		builder.append(", cfsName=");
		builder.append(cfsName);
		builder.append(", mblNo=");
		builder.append(mblNo);
		builder.append(", hblNo=");
		builder.append(hblNo);
		builder.append(", blDate=");
		builder.append(blDate);
		builder.append(", blStatus=");
		builder.append(blStatus);
		builder.append(", fromItemNo=");
		builder.append(fromItemNo);
		builder.append(", toItemNo=");
		builder.append(toItemNo);
		builder.append(", imoCode=");
		builder.append(imoCode);
		builder.append(", newVessel=");
		builder.append(newVessel);
		builder.append(", newVoyage=");
		builder.append(newVoyage);
		builder.append(", serialNumber=");
		builder.append(serialNumber);
		builder.append(", crewListDeclaration=");
		builder.append(crewListDeclaration);
		builder.append(", cargoDeclaration=");
		builder.append(cargoDeclaration);
		builder.append(", passengerList=");
		builder.append(passengerList);
		builder.append(", dpdMovement=");
		builder.append(dpdMovement);
		builder.append(", dpdCode=");
		builder.append(dpdCode);
		builder.append(", blVersion=");
		builder.append(blVersion);
		builder.append(", CusAdd1=");
		builder.append(CusAdd1);
		builder.append(", CusAdd2=");
		builder.append(CusAdd2);
		builder.append(", CusAdd3=");
		builder.append(CusAdd3);
		builder.append(", CusAdd4=");
		builder.append(CusAdd4);
		builder.append(", IsValidateBL=");
		builder.append(IsValidateBL);
		builder.append(", GrossCargoWeightBLlevel=");
		builder.append(GrossCargoWeightBLlevel);
		builder.append(", PackageBLLevel=");
		builder.append(PackageBLLevel);
		builder.append(", dep_manif_no=");
		builder.append(dep_manif_no);
		builder.append(", dep_manifest_date=");
		builder.append(dep_manifest_date);
		builder.append(", submitter_type=");
		builder.append(submitter_type);
		builder.append(", submitter_code=");
		builder.append(submitter_code);
		builder.append(", authoriz_rep_code=");
		builder.append(authoriz_rep_code);
		builder.append(", shipping_line_bond_no_r=");
		builder.append(shipping_line_bond_no_r);
		builder.append(", mode_of_transport=");
		builder.append(mode_of_transport);
		builder.append(", ship_type=");
		builder.append(ship_type);
		builder.append(", conveyance_reference_no=");
		builder.append(conveyance_reference_no);
		builder.append(", cargo_description=");
		builder.append(cargo_description);
		builder.append(", tol_no_of_trans_equ_manif=");
		builder.append(tol_no_of_trans_equ_manif);
		builder.append(", brief_cargo_des=");
		builder.append(brief_cargo_des);
		builder.append(", expected_date=");
		builder.append(expected_date);
		builder.append(", time_of_dept=");
		builder.append(time_of_dept);
		builder.append(", no_of_crew_manif=");
		builder.append(no_of_crew_manif);
		builder.append(", port_of_call_cod=");
		builder.append(port_of_call_cod);
		builder.append(", total_no_of_tran_s_cont_repo_on_ari_dep=");
		builder.append(total_no_of_tran_s_cont_repo_on_ari_dep);
		builder.append(", message_type=");
		builder.append(message_type);
		builder.append(", port_of_reporting=");
		builder.append(port_of_reporting);
		builder.append(", job_number=");
		builder.append(job_number);
		builder.append(", job_date=");
		builder.append(job_date);
		builder.append(", reporting_event=");
		builder.append(reporting_event);
		builder.append(", manifest_no_csn_no=");
		builder.append(manifest_no_csn_no);
		builder.append(", manifest_date_csn_date=");
		builder.append(manifest_date_csn_date);
		builder.append(", vessel_type_movement=");
		builder.append(vessel_type_movement);
		builder.append(", shipping_line_code=");
		builder.append(shipping_line_code);
		builder.append(", authorized_sea_carrier_code=");
		builder.append(authorized_sea_carrier_code);
		builder.append(", port_of_registry=");
		builder.append(port_of_registry);
		builder.append(", registry_date=");
		builder.append(registry_date);
		builder.append(", voyage_details_movement=");
		builder.append(voyage_details_movement);
		builder.append(", ship_itinerary_sequence=");
		builder.append(ship_itinerary_sequence);
		builder.append(", ship_itinerary=");
		builder.append(ship_itinerary);
		builder.append(", port_of_call_name=");
		builder.append(port_of_call_name);
		builder.append(", arrival_departure_details=");
		builder.append(arrival_departure_details);
		builder.append(", number_of_crew=");
		builder.append(number_of_crew);
		builder.append(", total_no_of_transport_equipment_reported_on_arrival_departure=");
		builder.append(total_no_of_transport_equipment_reported_on_arrival_departure);
		builder.append(", consolidated_indicator=");
		builder.append(consolidated_indicator);
		builder.append(", previous_declaration=");
		builder.append(previous_declaration);
		builder.append(", split_indicator=");
		builder.append(split_indicator);
		builder.append(", csn_number=");
		builder.append(csn_number);
		builder.append(", csn_date=");
		builder.append(csn_date);
		builder.append(", previous_mcin=");
		builder.append(previous_mcin);
		builder.append(", previous_pcin=");
		builder.append(previous_pcin);
		builder.append(", notifyPartyCode=");
		builder.append(notifyPartyCode);
		builder.append(", consolidator_pan=");
		builder.append(consolidator_pan);
		builder.append(", cin_type=");
		builder.append(cin_type);
		builder.append(", mcin=");
		builder.append(mcin);
		builder.append(", csn_submitted_type=");
		builder.append(csn_submitted_type);
		builder.append(", csn_submitted_by=");
		builder.append(csn_submitted_by);
		builder.append(", csn_reporting_type=");
		builder.append(csn_reporting_type);
		builder.append(", csn_site_id=");
		builder.append(csn_site_id);
		builder.append(", number_of_packages=");
		builder.append(number_of_packages);
		builder.append(", type_of_package=");
		builder.append(type_of_package);
		builder.append(", first_port_of_entry_last_port_of_departure=");
		builder.append(first_port_of_entry_last_port_of_departure);
		builder.append(", type_of_cargo=");
		builder.append(type_of_cargo);
		builder.append(", split_indicator_list=");
		builder.append(split_indicator_list);
		builder.append(", port_of_acceptance=");
		builder.append(port_of_acceptance);
		builder.append(", port_of_receipt=");
		builder.append(port_of_receipt);
		builder.append(", ucr_type=");
		builder.append(ucr_type);
		builder.append(", ucr_code=");
		builder.append(ucr_code);
		builder.append(", soc_flag=");
		builder.append(soc_flag);
		builder.append(", equipment_load_status=");
		builder.append(equipment_load_status);
		builder.append(", equipment_seal_type=");
		builder.append(equipment_seal_type);
		builder.append(", port_of_acceptance_name=");
		builder.append(port_of_acceptance_name);
		builder.append(", port_of_receipt_name=");
		builder.append(port_of_receipt_name);
		builder.append(", pan_of_notified_party=");
		builder.append(pan_of_notified_party);
		builder.append(", unit_of_weight=");
		builder.append(unit_of_weight);
		builder.append(", gross_volume=");
		builder.append(gross_volume);
		builder.append(", unit_of_volume=");
		builder.append(unit_of_volume);
		builder.append(", cargo_item_sequence_no=");
		builder.append(cargo_item_sequence_no);
		builder.append(", cargo_item_description=");
		builder.append(cargo_item_description);
		builder.append(", total_number_of_packages=");
		builder.append(total_number_of_packages);
		builder.append(", number_of_packages_hidden=");
		builder.append(number_of_packages_hidden);
		builder.append(", type_of_packages_hidden=");
		builder.append(type_of_packages_hidden);
		builder.append(", mc_item_details=");
		builder.append(mc_item_details);
		builder.append(", container_weight=");
		builder.append(container_weight);
		builder.append(", port_of_call_sequence_number=");
		builder.append(port_of_call_sequence_number);
		builder.append(", port_of_call_coded=");
		builder.append(port_of_call_coded);
		builder.append(", next_port_of_call_coded=");
		builder.append(next_port_of_call_coded);
		builder.append(", mc_location_customs=");
		builder.append(mc_location_customs);
		builder.append(", uno_code=");
		builder.append(uno_code);
		builder.append(", imdg_code=");
		builder.append(imdg_code);
		builder.append(", enblockMovement=");
		builder.append(enblockMovement);
		builder.append(", carrierNo=");
		builder.append(carrierNo);
		builder.append(", agencyType=");
		builder.append(agencyType);
		builder.append(", invoiceValueFc=");
		builder.append(invoiceValueFc);
		builder.append(", invoiceValueInr=");
		builder.append(invoiceValueInr);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", invoiceItems=");
		builder.append(invoiceItems);
		builder.append(", modeOfTpFee=");
		builder.append(modeOfTpFee);
		builder.append(", remark=");
		builder.append(remark);
		builder.append(", subLineNumber=");
		builder.append(subLineNumber);
		builder.append(", portOfDestination=");
		builder.append(portOfDestination);
		builder.append(", portOfLoading=");
		builder.append(portOfLoading);
		builder.append(", portOfDeschargedCfs=");
		builder.append(portOfDeschargedCfs);
		builder.append(", multipalPakages=");
		builder.append(multipalPakages);
		builder.append(", cbm=");
		builder.append(cbm);
		builder.append(", hightValue=");
		builder.append(hightValue);
		builder.append(", grosWeight=");
		builder.append(grosWeight);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", volume=");
		builder.append(volume);
		builder.append(", vasselCode=");
		builder.append(vasselCode);
		builder.append(", edi=");
		builder.append(edi);
		builder.append(", nonEdi=");
		builder.append(nonEdi);
		builder.append(", parentVoy=");
		builder.append(parentVoy);
		builder.append(", viaVcn=");
		builder.append(viaVcn);
		builder.append(", subTermil=");
		builder.append(subTermil);
		builder.append(", typeTransportMeans=");
		builder.append(typeTransportMeans);
		builder.append(", equimentType=");
		builder.append(equimentType);
		builder.append(", igmYear=");
		builder.append(igmYear);
		builder.append(", rotnNo=");
		builder.append(rotnNo);
		builder.append(", rotnDate=");
		builder.append(rotnDate);
		builder.append(", jobNo=");
		builder.append(jobNo);
		builder.append(", jobDate=");
		builder.append(jobDate);
		builder.append(", position=");
		builder.append(position);
		builder.append(", exchangeRate=");
		builder.append(exchangeRate);
		builder.append(", cigmNo=");
		builder.append(cigmNo);
		builder.append(", cigmDate=");
		builder.append(cigmDate);
		builder.append(", smtpNo=");
		builder.append(smtpNo);
		builder.append(", smtpDate=");
		builder.append(smtpDate);
		builder.append(", noOfItemInPrior=");
		builder.append(noOfItemInPrior);
		builder.append(", noOfItemInFil=");
		builder.append(noOfItemInFil);
		builder.append(", noOfItemInSupplimentary=");
		builder.append(noOfItemInSupplimentary);
		builder.append(", totalWeight=");
		builder.append(totalWeight);
		builder.append(", noOfPassenger=");
		builder.append(noOfPassenger);
		builder.append(", noOfCrew=");
		builder.append(noOfCrew);
		builder.append(", remarkVessel=");
		builder.append(remarkVessel);
		builder.append(", dutyInr=");
	    builder.append(dutyInr);
		builder.append(", $$hashKey=");
		builder.append($$hashKey);
		builder.append("]");
		return builder.toString();
	}

}


package com.rclgroup.dolphin.web.igm.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;

import com.niit.control.common.exception.BusinessException;
import com.niit.control.common.exception.ExceptionFactory;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcRowMapper;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.jsonUtil.Utils;
import com.rclgroup.dolphin.web.igm.vo.CFSCustomCode;
import com.rclgroup.dolphin.web.igm.vo.DropDownMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.PortMod;

/**
 * The Class ImportGeneralManifestDaoImpl.
 */
public class IGMDaoImplNew extends AncestorJdbcDao implements IGMDaoNew {

	Set<String> blrNumber = null;

	Set<String> PodTerminal = null;

	private static final String roadCarrCodeQUerry = " select PARTNER_VALUE,DESCRIPTION from EDI_TRANSLATION_DETAIL where ETH_UID in ("
			+ "  select ETH_UID from EDI_TRANSLATION_HEADER where CODE_SET='ROADCARCOD' " + "  ) and SEALINER_VALUE= ";

	private static final String TPBondNoQuery = "  select PARTNER_VALUE ,DESCRIPTION,sealiner_value  from EDI_TRANSLATION_DETAIL where ETH_UID in ("
			+ "             select ETH_UID from EDI_TRANSLATION_HEADER where CODE_SET='TPBONDNO' ) and SEALINER_VALUE in (";

	private static final String CFSCustomcodeQuery = " select PARTNER_VALUE , SEALINER_VALUE from EDI_TRANSLATION_DETAIL where ETH_UID in ("
			+ "             select ETH_UID from EDI_TRANSLATION_HEADER where CODE_SET='IGM_CFS'"
			+ "           ) and SEALINER_VALUE in ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rclgroup.dolphin.ezl.web.ell.dao.ImportGeneralManifestDao#getIGMData(java
	 * .util.Map)
	 */
	@Override
	public Map getIGMData(Map amapParam) throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getIGMData() started..");
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		
		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), SQL_GET_IGM_DATA,new ImportGeneralManifestRowMapperNew(), arrParam);
				

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		System.out.println("#IGMLogger END OF getIGMData() ");
		return mapResult;
	}

	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class ImportGeneralManifestRowMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();
			
			objMod.setCustomCode(rs.getString("CUSTOM_CODE"));
			objMod.setVesselName(rs.getString("VESSEL_NAME"));			
			objMod.setService(rs.getString("SERVICE"));
			objMod.setBl(rs.getString("BL_NO"));
			blrNumber.add(objMod.getBl());
			objMod.setPod(rs.getString("POD"));
			objMod.setPodTerminal(rs.getString("POD_TERMINAL"));
			PodTerminal.add(objMod.getPodTerminal());
			objMod.setVessel(rs.getString("VESSEL"));
			objMod.setVoyage(rs.getString("VOYAGE"));
			objMod.setCodeCode(rs.getString("CUST_CODE"));
			objMod.setCallSing(rs.getString("CALL_SIGN"));
			objMod.setLineCode(rs.getString("LINE_CODE"));
			objMod.setAgentCode(rs.getString("AGENT_CODE"));
			objMod.setPortOrigin(rs.getString("PORT_ORIGIN"));
			objMod.setPortArrival(rs.getString("PORT_ARRIVAL"));
			objMod.setLastPort1(rs.getString("LAST_PORT_1"));
			objMod.setLastPort2(rs.getString("LAST_PORT_2"));
			objMod.setLastPort3(rs.getString("LAST_PORT_3"));
			objMod.setNextport1(rs.getString("NEXT_PORT_4"));
			objMod.setNextport2(rs.getString("NEXT_PORT_5"));
			objMod.setNextport3(rs.getString("NEXT_PORT_6"));
			objMod.setPort_of_call_name_last1(rs.getString("LAST_PORT_1_NAME"));
			objMod.setPort_of_call_name_last2(rs.getString("LAST_PORT_2_NAME"));
			objMod.setPort_of_call_name_last3(rs.getString("LAST_PORT_3_NAME"));
			objMod.setPort_of_call_name_nextport1(rs.getString("NEXT_PORT_4_NAME"));
			objMod.setPort_of_call_name_nextport2(rs.getString("NEXT_PORT_5_NAME"));
			objMod.setPort_of_call_name_nextport3(rs.getString("NEXT_PORT_6_NAME"));
			
			objMod.setTerminal(rs.getString("TERMINAL"));
			if(rs.getString("VESSEL_TYPE")==null || rs.getString("VESSEL_TYPE").equals("")) {
				objMod.setVesselType("C");
			} else {
				objMod.setVesselType(rs.getString("VESSEL_TYPE"));	
			}
			objMod.setGenDesc(rs.getString("GEN_DESC"));
			objMod.setMasterName(rs.getString("MASTER_NAME"));
			objMod.setVesselNation(rs.getString("VESSEL_NATION"));
			objMod.setIgmNumber(rs.getString("IGM_NUMBER"));
			objMod.setIgmDate(rs.getString("IGM_DATE"));
			if(rs.getString("IGM_DATE") != null && !rs.getString("IGM_DATE").equals("")) {
				objMod.setIgmYearVal(rs.getString("IGM_DATE").substring(6,10));
			}
			objMod.setIgmDateVal(rs.getString("IGM_DATE"));
			objMod.setArrivalDate(rs.getString("ARRIVAL_DATE"));
			objMod.setArrivalTime(rs.getString("ARRIVAL_TIME"));
			objMod.setAtaarrivalDate(rs.getString("ARRIVAL_DATE_ATA"));
			objMod.setAtaarrivalTime(rs.getString("ARRIVAL_TIME_ATA"));
			objMod.setTotalBls(rs.getString("TOTAL_BLS"));
			objMod.setLightDue(rs.getString("LIGHT_DUE"));
			objMod.setSmBtCargo(rs.getString("SM_BT_CARGO"));
			objMod.setShipStrDect(rs.getString("SHIP_STR_DECL"));
			objMod.setCrewEffect(rs.getString("CREW_EFFECT"));
			objMod.setMariTimeDecl(rs.getString("MARITIME_DECL"));
			objMod.setItemNumber(rs.getString("ITEM_NUMBER"));
			objMod.setCargoNature(rs.getString("CARGO_NATURE"));
			System.out.println(rs.getString("CARGO_MOVMNT"));
			objMod.setCargoMovmnt(rs.getString("CARGO_MOVMNT"));
			objMod.setItemType(rs.getString("ITEM_TYPE"));
			objMod.setCargoMovmntType(rs.getString("CARGO_MOVMNT_TYPE"));
			objMod.setTransportMode(rs.getString("TRANSPORT_MODE"));
			objMod.setRoadCarrCode(rs.getString("ROAD_CARR_CODE"));
			objMod.setRoadTpBondNo(rs.getString("ROAD_TP_BOND_NO"));
			
			if(rs.getString("NEW_VOYAGE")==null || rs.getString("NEW_VOYAGE").equals("")) {
				objMod.setNewVessel(rs.getString("VESSEL"));
			}else {
				objMod.setNewVessel(rs.getString("NEW_VOYAGE"));
			}
			
			objMod.setNewVoyage(rs.getString("NEW_VESSEL"));
			objMod.setSubmitDateTime(rs.getString("SUBMIT_DATE_TIME"));
			/*
			 * objMod.setNhavaShevaEta(rs.getString("NHAVA_SHEVA_ETA"));
			 * objMod.setFinalPlaceDelivery(rs.getString("FINAL_PLACE_DELIVERY"));
			 * objMod.setPackages(rs.getString("PACKAGES"));
			 * objMod.setCfsName(rs.getString("CFS_NAME"));
			 * objMod.setMblNo(rs.getString("MBL_NO"));
			 * objMod.setHblNo(rs.getString("HBL_NO"));
			 * objMod.setFromItemNo(rs.getString("FROM_ITEM_NO"));
			 * objMod.setToItemNo(rs.getString("TO_ITEM_NO"));
			 */
			
			objMod.setFromItemNo(rs.getString("FROM_ITEM_NO"));
			objMod.setToItemNo(rs.getString("TO_ITEM_NO"));
			objMod.setNetWeight(rs.getString("NET_WEIGHT"));
			objMod.setGrossWeight(rs.getString("GROSS_WEIGHT"));
			objMod.setImoCode(rs.getString("IMO_CODE"));
			objMod.setBlDate(rs.getString("BL_DATE"));
			objMod.setBlStatus(rs.getString("BL_STATUS"));
			objMod.setPol(rs.getString("POL"));
			objMod.setCustomTerminalCode(rs.getString("CUSTOM_TERMINAL_CODE"));
			objMod.setBlVersion(rs.getString("BL_VERSION"));
			objMod.setDpdCode(rs.getString("DPD_CODE"));
			objMod.setDpdMovement(rs.getString("DPD_MOVEMENT"));
			objMod.setPolTerminal(rs.getString("POL_TERMINAL"));
			objMod.setCargoDeclaration(rs.getString("CARGO_DECL"));
			objMod.setCrewListDeclaration(rs.getString("CREW_LST_DECL"));
			objMod.setPassengerList(rs.getString("PASSNGR_LIST"));
			objMod.setCusAdd1(rs.getString("CUSTOMERS_ADDRESS_1"));
			objMod.setCusAdd2(rs.getString("CUSTOMERS_ADDRESS_2"));
			objMod.setCusAdd3(rs.getString("CUSTOMERS_ADDRESS_3"));
			objMod.setCusAdd4(rs.getString("CUSTOMERS_ADDRESS_4"));
			objMod.setIsValidateBL(rs.getString("COLOR_FLAG"));
			objMod.setGrossCargoWeightBLlevel(rs.getString("NET_WEIGHT_METRIC"));
			objMod.setPackageBLLevel(rs.getString("NET_PACKAGE"));
			objMod.setDel(rs.getString("DEL_VLS"));
			objMod.setDepot(rs.getString("DEPOT_VLS"));

			// NEW ADDED ROW FOR VESSEL & VOYAGE

			objMod.setDep_manif_no(rs.getString("DEP_MANIF_NO"));
			objMod.setDep_manifest_date(rs.getString("DEP_MANIFEST_DATE"));
			objMod.setSubmitter_type(rs.getString("SUBMITTER_TYPE"));
			objMod.setSubmitter_code(rs.getString("SUBMITTER_CODE"));
			objMod.setAuthoriz_rep_code(rs.getString("AUTHORIZ_REP_CODE"));
			objMod.setShipping_line_bond_no_r(rs.getString("SHIPPING_LINE_BOND_NO_R"));
			objMod.setMode_of_transport(rs.getString("MODE_OF_TRANSPORT"));
			objMod.setShip_type(rs.getString("SHIP_TYPE"));
			objMod.setConveyance_reference_no(rs.getString("CONVEYANCE_REFERENCE_NO"));
			objMod.setCargo_description(rs.getString("CARGO_DESCRIPTION"));
			objMod.setTol_no_of_trans_equ_manif(rs.getString("TOL_NO_OF_TRANS_EQU_MANIF"));
			objMod.setBrief_cargo_des(rs.getString("BRIEF_CARGO_DES"));
			objMod.setExpected_date(rs.getString("EXPECTED_DATE"));
			objMod.setTime_of_dept(rs.getString("TIME_OF_DEPT"));
			objMod.setTotal_no_of_tran_s_cont_repo_on_ari_dep(rs.getString("TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP"));
			objMod.setMessage_type(rs.getString("MESSAGE_TYPE"));
			objMod.setVessel_type_movement(rs.getString("VESSEL_TYPE_MOVEMENT"));
			objMod.setAuthorized_sea_carrier_code(rs.getString("AUTHORIZED_SEA_CARRIER_CODE"));
			objMod.setPort_of_registry(rs.getString("PORT_OF_REGISTRY"));
			objMod.setRegistry_date(rs.getString("REGISTRY_DATE"));
			objMod.setVoyage_details_movement(rs.getString("VOYAGE_DETAILS"));
			objMod.setShip_itinerary_sequence(rs.getString("SHIP_ITINERARY_SEQUENCE"));
			objMod.setShip_itinerary(rs.getString("SHIP_ITINERARY"));
			objMod.setArrival_departure_details(rs.getString("ARRIVAL_DEPARTURE_DETAILS"));
			objMod.setTotal_no_of_transport_equipment_reported_on_arrival_departure(
					rs.getString("TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE"));
			// vessel&voyage section ended.....

			// vessel's new fields(28-05-2021)by janmejaya
			objMod.setVasselCode(rs.getString("VASSELCODE"));
			objMod.setEdi(rs.getString("EDI"));
			objMod.setNonEdi(rs.getString("NON_EDI"));
			objMod.setParentVoy(rs.getString("PARENT_VOY"));
			objMod.setViaVcn(rs.getString("VIA_VCN"));
			objMod.setSubTermil(rs.getString("SUB_TERMIL"));
			objMod.setTypeTransportMeans(rs.getString("TYPE_TRANSPORT_MEANS"));
			objMod.setEquimentType(rs.getString("EQUIMENT_TYPE"));
			objMod.setIgmYear(rs.getString("IGM_YEAR"));
			objMod.setRotnNo(rs.getString("ROTN_NO"));
			objMod.setRotnDate(rs.getString("ROTN_DATE"));
			// objMod.setJobNo(rs.getString("JOB_NO"));
			// objMod.setJobDate(rs.getString("JOB_DATE"));
			objMod.setPosition(rs.getString("POSITION"));
			objMod.setExchangeRate(rs.getString("EXCHANGE_RATE"));
			objMod.setCigmNo(rs.getString("CIGM_NO"));
			objMod.setCigmDate(rs.getString("CIGM_DATE"));
			objMod.setSmtpNo(rs.getString("SMTP_NO"));
			objMod.setSmtpDate(rs.getString("SMTP_DATE"));
			objMod.setNoOfItemInPrior(rs.getString("NO_OF_ITEM_IN_PRIOR"));
			objMod.setNoOfItemInFil(rs.getString("NO_OF_ITEM_IN_FIL"));
			objMod.setNoOfItemInSupplimentary(rs.getString("NO_OF_ITEM_IN_SUPPLIMENTARY"));
			objMod.setTotalWeight(rs.getString("TOTAL_WEIGHT"));
			objMod.setNoOfPassenger(rs.getString("NO_OF_PASSENGER"));
			objMod.setNoOfCrew(rs.getString("NO_OF_CREW"));
			objMod.setRemarkVessel(rs.getString("REMARK_VESSEL"));
			objMod.setJobNo(Utils.GenrateJobAndSerialNumber());
			objMod.setSerialNumber(Utils.GenrateJobAndSerialNumber());
			objMod.setJobDate(Utils.JobDate());
			objMod.setTotalItem(rs.getString("TOTAL_BLS"));
			
			objMod.setPodTerminalPort(rs.getString("POD_TERMINAL_PORT"));
			objMod.setPolTerminalPort(rs.getString("POL_TERMINAL_PORT"));
			   
			objMod.setSenderId(rs.getString("SENDER_ID"));
			objMod.setRecieverId(rs.getString("RECIEVER_ID"));
			objMod.setAuthReprsntvCd(rs.getString("AUTHREPRSNTVCD"));
		    objMod.setNoOfCrew(rs.getString("noOfCrew"));
					
	// ============================More  details===================
//			objMod.setHblNo(rs.getString("FK_HOUSE_BL_NO"));
			//objMod.setNeCargoMovmnt(rs.getString("NE_CARGO_MOVMNT"));
			return objMod;
		}
	}

	@Override
	public Map getRefreshIGMData(Map amapParam) throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getIGMData() started..");
		System.out.println("dao getRefresh()" + amapParam.toString());
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();
		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), SQL_GET_REFRESH_IGM_DATA,
				new ImportGeneralManifestRowMapper(), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		return mapResult;
	}

	@Override
	public Map getSerialNumber(String slNo) throws BusinessException, DataAccessException {
		System.out.println("Method started....... getSerialNumber");
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK },
				{ KEY_REF_IGM_DATA_JOB, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK },
				{ KEY_IGM_SRL_NO, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) slNo },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };
		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), SQL_SERIAL_NUMBER_DATA,
				new ImportGeneralManifestRowMapper(), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		Map<String, String> data = new HashMap<String, String>();
		data.put("selno", mapResult.get(KEY_REF_IGM_DATA).toString());
		data.put("jobno", mapResult.get(KEY_REF_IGM_DATA_JOB).toString());
		return data;
	}

	@Override
	public String saveContainerData(String contDtl, Map amapParam) throws BusinessException, DataAccessException {
		System.out.println("saveContainerData() started");
		String[][] arrParam = { { KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_CONTAINER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) contDtl }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), SQL_SAVE_CONTAINOR,
				new ImportGeneralManifestRowMapper(), arrParam);

		objSP.execute();
		return null;
	}

	@Override
	public Map getroadCarrCodeData(Map amapParam) throws BusinessException, DataAccessException {

		Map dropDownMap = new HashMap();
		String podVal = (String) amapParam.get(KEY_IGM_POD);

		Connection con = null;
		Statement pre = null;
		ResultSet rs = null;

		try {

			// Map<String,List<DropDownMod>> DropDownRoadCarrCodeMap= new HashMap<String,
			// List<DropDownMod>>();
			List<DropDownMod> DropDownRoadCarrCodeList = new ArrayList<>();
			List tepmListTPBondNo = new ArrayList();

			con = getDataSource().getConnection();
 
			String queryRoadCarrCode = roadCarrCodeQUerry;
			queryRoadCarrCode += "'" + podVal + "'";
			
			pre = con.createStatement();
			rs = pre.executeQuery(queryRoadCarrCode);
			
			DropDownMod dropdown  = new DropDownMod();
			dropdown.setPodValue(podVal);
			dropdown.setPartnerValuedre("");
			dropdown.setPartnerviews("Select One Value");
			dropdown.setDescriptiondrw("Select One Value");
			DropDownRoadCarrCodeList.add(dropdown);
			DropDownMod dropdownDB = null;
			while (rs.next()) {
				dropdownDB  = new DropDownMod();
				dropdownDB.setPodValue(podVal);
				dropdownDB.setPartnerValuedre(rs.getString("PARTNER_VALUE"));
				dropdownDB.setPartnerviews(rs.getString("PARTNER_VALUE"));
				dropdownDB.setDescriptiondrw(rs.getString("DESCRIPTION"));
				tepmListTPBondNo.add(rs.getString("PARTNER_VALUE"));
				DropDownRoadCarrCodeList.add(dropdownDB);
			}
			dropDownMap.put("roadCarrCoadDropdown", DropDownRoadCarrCodeList);
			if (rs != null)
				rs.close();
				pre.close();
			Map<String, List<DropDownMod>> tpBondNoMap = new HashMap<String, List<DropDownMod>>();
			//con = getDataSource().getConnection();
			// con = DataSourceUtils.getConnection(getDataSource());
			
			 
			String queryTPBondNo = TPBondNoQuery;
			queryTPBondNo += "'" + StringUtils.join((tepmListTPBondNo.iterator()), "','") + "' )";
			
			pre = con.createStatement();
			rs = pre.executeQuery(queryTPBondNo);
			 
			DropDownMod dropdowntp = null;
			int i = 0;
			while (rs.next()) {
				dropdowntp = new DropDownMod();
				dropdowntp.setPodValue(rs.getString("SEALINER_VALUE"));
				dropdowntp.setPartnerValuedre(rs.getString("PARTNER_VALUE"));
				dropdowntp.setPartnerviews(rs.getString("PARTNER_VALUE"));
				dropdowntp.setDescriptiondrw(rs.getString("DESCRIPTION"));
				i++;
				if (tpBondNoMap.get(dropdowntp.getPodValue()) == null) {
					List<DropDownMod> DropDownTPBondNoList = new ArrayList<DropDownMod>();
					tpBondNoMap.put(dropdowntp.getPodValue(), DropDownTPBondNoList);
					DropDownMod dropdowntpOpt = new DropDownMod();
					dropdowntpOpt.setPodValue(rs.getString("SEALINER_VALUE"));
					dropdowntpOpt.setPartnerValuedre("");
					dropdowntpOpt.setPartnerviews("Select One Option");
					dropdowntpOpt.setDescriptiondrw("");
					tpBondNoMap.get(dropdowntp.getPodValue()).add(dropdowntpOpt);
				}
				tpBondNoMap.get(dropdowntp.getPodValue()).add(dropdowntp);
			}
			dropDownMap.put("TPBondNoDropdown", tpBondNoMap);

			if (rs != null)
				rs.close();
				pre.close();
				
			Map<String, List<CFSCustomCode>> cfsCustomCodeMap = new HashMap<String, List<CFSCustomCode>>();
			//con = getDataSource().getConnection();
			// con = DataSourceUtils.getConnection(getDataSource());
			

			String querycfscustomcode = CFSCustomcodeQuery + " (";
			querycfscustomcode += "'" + StringUtils.join(PodTerminal.iterator(), "','") + "')";
			
			pre = con.createStatement();
			rs = pre.executeQuery(querycfscustomcode);
			CFSCustomCode dropdownCfs = null;
			while (rs.next()) {
				dropdownCfs = new CFSCustomCode();
				dropdownCfs.setCfsCustomCode(rs.getString("PARTNER_VALUE"));
				dropdownCfs.setPodTerminal(rs.getString("SEALINER_VALUE"));

				if (cfsCustomCodeMap.get(dropdownCfs.getPodTerminal()) == null) {
					List<CFSCustomCode> listOfCfsCode = new ArrayList<CFSCustomCode>();

					cfsCustomCodeMap.put(dropdownCfs.getPodTerminal(), listOfCfsCode);
				}

				cfsCustomCodeMap.get(dropdownCfs.getPodTerminal()).add(dropdownCfs);
			}
			dropDownMap.put("CFSCustomDropdown", cfsCustomCodeMap);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pre != null)
					pre.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}

		}

		return dropDownMap;
	}

	public Map getBLData(Map amapParam, String procedureName, boolean isSave,boolean isUpdateSaved,String saveBlList)
			throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getBLData() started.." + isSave);
		
		String blCountLoop  =  "0";
		
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) saveBlList },
				{ KEY_IGM_BL_COUNT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String)  blCountLoop},
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new ImportGeneralManifestRowMapperBL(isSave,isUpdateSaved), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		
		return mapResult;
	}

	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class ImportGeneralManifestRowMapperNew extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();

			objMod.setService(rs.getString("SERVICE"));
			objMod.setBl(rs.getString("BL_NO"));
			//blrNumber.add(objMod.getBl());
			objMod.setPod(rs.getString("POD"));
			objMod.setPol(rs.getString("POL"));
			objMod.setPodTerminal(rs.getString("POD_TERMINAL"));
			//PodTerminal.add(objMod.getPodTerminal());
			objMod.setVessel(rs.getString("VESSEL"));
			objMod.setVoyage(rs.getString("VOYAGE"));
			objMod.setTerminal(rs.getString("TERMINAL"));
			// vessel&voyage section ended.....

			// BL SECTION
			objMod.setCustomTerminalCode(rs.getString("CUSTOM_TERMINAL_CODE"));
			objMod.setDel(rs.getString("DEL_VLS"));
			objMod.setDepot(rs.getString("DEPOT_VLS"));
			// END BL SECTION

			return objMod;
		}
	}

	public Map getVesselVoyagData(Map amapParam, String procedure) throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getIGMData() started..");
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedure,
				new ImportGeneralManifestRowMapper(), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		return mapResult;
	}

	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class ImportGeneralManifestRowMapperBL extends JdbcRowMapper {
		private boolean isSaved;
		private boolean isUpdateSaved;
		public ImportGeneralManifestRowMapperBL(boolean isSaved,boolean isUpdateSaved) {
			this.isSaved = isSaved;
			this.isUpdateSaved = isUpdateSaved;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();
		
			 
			objMod.setIsBlSave(String.valueOf(isSaved));
			objMod.setService(rs.getString("SERVICE"));
			objMod.setBl(rs.getString("BL_NO"));
			blrNumber.add(objMod.getBl());
			objMod.setPod(rs.getString("POD"));
			objMod.setPodTerminal(rs.getString("POD_TERMINAL"));
			PodTerminal.add(objMod.getPodTerminal());
			objMod.setVessel(rs.getString("VESSEL"));
			objMod.setVoyage(rs.getString("VOYAGE"));

			objMod.setLineCode(rs.getString("LINE_CODE"));
			objMod.setCargoMovmnt(rs.getString("CARGO_MOVMNT"));
			objMod.setTerminal(rs.getString("POD_TERMINAL"));

			objMod.setNhavaShevaEta(rs.getString("NHAVA_SHEVA_ETA"));
			objMod.setFinalPlaceDelivery(rs.getString("FINAL_PLACE_DELIVERY"));
			objMod.setPackages(rs.getString("PACKAGES"));
			objMod.setCfsName(rs.getString("CFS_NAME"));
			objMod.setMblNo(rs.getString("MBL_NO"));
			objMod.setHblNo(rs.getString("HBL_NO"));
			objMod.setFromItemNo(rs.getString("FROM_ITEM_NO"));
			objMod.setToItemNo(rs.getString("TO_ITEM_NO"));
			// BL SECTION
			objMod.setConsolidatedIndicator(rs.getString("CONSOLIDATED_INDICATOR"));
			objMod.setBlType(rs.getString("BL_TYPE"));
			objMod.setPrevious_declaration(rs.getString("PREVIOUS_DECLARATION"));
			objMod.setConsolidator_pan(rs.getString("CONSOLIDATOR_PAN"));
			objMod.setCin_type(rs.getString("CIN_TYPE"));
			objMod.setMcin(rs.getString("MCIN"));
			objMod.setCsn_submitted_type(rs.getString("CSN_SUBMITTED_TYPE"));
			objMod.setCsn_submitted_by(rs.getString("CSN_SUBMITTED_BY"));
			objMod.setCsn_reporting_type(rs.getString("CSN_REPORTING_TYPE"));
			objMod.setCsn_site_id(rs.getString("CSN_SITE_ID"));
			objMod.setCsn_number(rs.getString("CSN_NUMBER"));
			objMod.setCsn_date(rs.getString("CSN_DATE"));
			objMod.setPrevious_mcin(rs.getString("PREVIOUS_MCIN"));
			objMod.setSplit_indicator(rs.getString("SPLIT_INDICATOR"));
			objMod.setTotal_number_of_packages(rs.getString("NUMBER_OF_PACKAGES"));
			objMod.setType_of_package(rs.getString("TYPE_OF_PACKAGE"));
			objMod.setFirst_port_of_entry_last_port_of_departure(
					rs.getString("FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE"));
			objMod.setType_of_cargo(rs.getString("TYPE_OF_CARGO"));
			objMod.setSplit_indicator_list(rs.getString("SPLIT_INDICATOR_LIST"));
//			objMod.setPort_of_acceptance(rs.getString("PORT_OF_ACCEPTANCE"));
//			objMod.setPort_of_receipt(rs.getString("PORT_OF_RECEIPT"));
			objMod.setUcr_type(rs.getString("UCR_TYPE"));
			objMod.setUcr_code(rs.getString("UCR_CODE"));
//			objMod.setAcceptanceName(rs.getString("PORT_OF_ACCEPTANCE_NAME"));
//			objMod.setPort_of_receipt_name(rs.getString("PORT_OF_RECEIPT_NAME"));
			objMod.setPan_of_notified_party(rs.getString("PAN_OF_NOTIFIED_PARTY"));
			objMod.setUnit_of_weight(rs.getString("UNIT_OF_WEIGHT"));
			if(null == rs.getString("GROSS_VOLUME")) {
				objMod.setGross_volume("");
			}else {
				objMod.setGross_volume(rs.getString("GROSS_VOLUME"));
			}
			objMod.setCargo_msmt(rs.getInt("cargo_msmt"));
			if(null == rs.getString("UNIT_OF_VOLUME")) {
				objMod.setUnit_of_volume("");
			}else {
				objMod.setUnit_of_volume(rs.getString("UNIT_OF_VOLUME"));
			}
			objMod.setCargo_item_sequence_no(rs.getString("CARGO_ITEM_SEQUENCE_NO"));
			objMod.setCargo_item_description(rs.getString("CARGO_ITEM_DESCRIPTION"));
			objMod.setContainer_weight(rs.getString("CONTAINER_WEIGHT"));
			objMod.setNumber_of_packages_hidden(rs.getString("NUMBER_OF_PACKAGES_HID"));
			objMod.setType_of_packages_hidden(rs.getString("TYPE_OF_PACKAGES_HID"));
			objMod.setPort_of_call_sequence_number(rs.getString("PORT_OF_CALL_SEQUENCE_NUMBER"));
			objMod.setPort_of_call_coded(rs.getString("PORT_OF_CALL_CODED"));
			objMod.setPort_of_call_name(rs.getString("port_OF_call_name"));
			objMod.setNext_port_of_call_coded(rs.getString("NEXT_PORT_OF_CALL_CODED"));
			objMod.setNext_port_of_call_name(rs.getString("next_port_of_call_name"));
			
			objMod.setMc_location_customs(rs.getString("MC_LOCATION_CUSTOMS"));
			if(rs.getString("FLAG_DG") == null || rs.getString("FLAG_DG").equals("N")) {
				objMod.setUno_code("ZZZZZ");
			}else {
				objMod.setUno_code(rs.getString("UNO_CODE"));
			}
			objMod.setImdg_code(rs.getString("IMDG_CODE"));
			objMod.setItemNumber(rs.getString("ITEM_NUMBER"));
			
			objMod.setDpdCode(rs.getString("DPD_CODE"));
			objMod.setDpdMovement(rs.getString("DPD_MOVEMENT"));
			objMod.setEnblockMovement(rs.getString("ENBLOCK_MOVEMENT"));
			objMod.setAgencyType(rs.getString("AGENCY_TYPE"));
			objMod.setInvoiceValueFc(rs.getString("INVOICE_VALUE_FC"));
			objMod.setInvoiceValueInr(rs.getString("INVOICE_VALUE_INR"));
			objMod.setCurrency(rs.getString("CURRENCY"));
			objMod.setInvoiceItems(rs.getString("CSN_SUBMITTED_BY"));
			objMod.setModeOfTpFee(rs.getString("MODEOF_TP_FEE"));
			objMod.setDgFlag(rs.getString("FLAG_DG"));
			objMod.setCommdity_code(rs.getString("COMMODITY_CODE"));
			objMod.setPackage_kind(rs.getString("PACKAGE_KIND"));
			objMod.setCommodity_seq(rs.getInt("COMMODITY_SEQ"));
//			objMod.setNotifyName(rs.getString("NOTIFY_NAME"));
//			objMod.setNotifyIec(rs.getString("NOTIFY_IEC"));
//			objMod.setNotifyPan(rs.getString("NOTIFY_PAN"));

//			if(StringUtils.substringAfterLast(rs.getString("REMARK"), "(s)") != null) {
//				System.out.println("success");
//			}
//			
//			
			objMod.setRemark(rs.getString("REMARK"));
			
			if(rs.getString("SUB_LINE_NUMBER")==null || rs.getString("SUB_LINE_NUMBER").equals("")) {
				objMod.setSubLineNumber("0");
			}else {
				objMod.setSubLineNumber(rs.getString("SUB_LINE_NUMBER"));
			}
			
			objMod.setPortOfDestination(rs.getString("PORT_OF_DESTINATION"));
			objMod.setPortOfLoading(rs.getString("PORT_OF_LOADING"));
			objMod.setPortOfDeschargedCfs(rs.getString("PORT_OF_DESCHARGED_CFS"));
			objMod.setMultipalPakages(rs.getString("MULTIPAL_PAKAGES"));
			objMod.setCbm(rs.getString("CBM"));
			objMod.setHightValue(rs.getString("HIGHT_VALUE"));
			if(null != rs.getString("GROS_WEIGHT")  || !("").equals( rs.getString("GROS_WEIGHT"))) {
				objMod.setGrosWeight(rs.getString("GROS_WEIGHT"));
			}else {
				objMod.setGrosWeight(rs.getString(""));
			}		
			objMod.setUnit(rs.getString("UNIT"));
			objMod.setVolume(rs.getString("VOLUME"));
			objMod.setBlVersion(rs.getString("BL_VERSION"));
			objMod.setBlDate(rs.getString("BL_DATE"));
			objMod.setMode_of_transport("Sea");
			objMod.setItemType("OT");
			objMod.setCargoNature("C");
			objMod.setCargoMovmnt(rs.getString("cargo_movmnt"));
			objMod.setDutyInr(rs.getString("DUTY_INR"));
			if(null == rs.getString("ROAD_CARR_CODE")) {
				objMod.setCarrierNo("");
			}else {
				objMod.setCarrierNo(rs.getString("ROAD_CARR_CODE"));
			}
			objMod.setTpBondNo(rs.getString("ROAD_TP_BOND_NO"));
			objMod.setNeCargoMovmnt(rs.getString("NE_CARGO_MOVMNT"));
			objMod.setConsigneeName(rs.getString("CONSIGNEE_NAME"));
			
			objMod.setFlag_discharge(rs.getString("FLAG_DISCHARGE")); 
			objMod.setBlDischargedStatus(rs.getString("BL_DISCHARGE_STATUS"));
			objMod.setFlagRob(rs.getString("FLAG_ROB"));
			
			objMod.setFetch(true);
			if(isUpdateSaved) {
				objMod.setSaveFlags("I");
			}else if(isSaved){
				objMod.setSaveFlags("U");
			}else {
				objMod.setSaveFlags("N");
			}
			objMod.setHblCount(rs.getInt("HBLCOUNT"));
			if(rs.getInt("HBLCOUNT")>0) {
				objMod.setHbl(true);
			}else {
				objMod.setHbl(false);
			}
			objMod.setBlCriteria("MBL"); 
			objMod.setPort_of_receipt(rs.getString("DN_PLD")); 
			objMod.setPort_of_acceptance(rs.getString("DN_PLR"));
			objMod.setDn_plr(rs.getString("DN_PLR")); 
			objMod.setDn_pld(rs.getString("DN_PLD"));
			objMod.setAcceptanceName(rs.getString("ACCEPTANCE_NAME")); // nava sheva
			objMod.setRecieptName(rs.getString("RECIEPT_NAME"));
			objMod.setStowagePosition(rs.getString("STOWAGE_POSITION"));
			objMod.setGstStateCode(rs.getString("GST_STATE_CODE"));
 
			 SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		     SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
			if(rs.getString("MASTER_BL_DATE").contains("/")) {
			objMod.setMasterBlDate(rs.getString("MASTER_BL_DATE"));
			}else {
				 Date date;
				try {
					date = inputFormat.parse(rs.getString("MASTER_BL_DATE"));
					 String formattedDate = outputFormat.format(date);
					 objMod.setMasterBlDate(formattedDate);
				} catch (ParseException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
			}
			objMod.setTrshprFlag(rs.getString("TRNSHPR_FLAG"));
			
			return objMod;
		}
	} 	
	
	private class ImportGeneralManifestRowMapperBLDetails extends JdbcRowMapper {
		private boolean isSaved;

		public ImportGeneralManifestRowMapperBLDetails(boolean isSaved) {
			this.isSaved = isSaved;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();

			objMod.setBl(rs.getString("BL_NO")); 
			objMod.setBlDate(rs.getString("BL_DATE"));
			objMod.setVessel(rs.getString("VESSEL"));
			objMod.setVoyage(rs.getString("VOYAGE"));
			objMod.setItemNumber(rs.getString("ITEM_NUMBER"));
//			System.out.println("ITEM_NUMBER : "+rs.getString("ITEM_NUMBER"));
			if(rs.getString("ITEM_NUMBER")!=null && !rs.getString("ITEM_NUMBER").equals("")) {
				objMod.setIsBlSave(String.valueOf(true));
			}else {
				objMod.setIsBlSave(String.valueOf(false));
			}
			if(rs.getString("CARGO_MOVMNT") != null ) {
				objMod.setCargoMovmnt(rs.getString("CARGO_MOVMNT"));
			}else {
				objMod.setCargoMovmnt("");
			}
			
			objMod.setPod(rs.getString("POD"));
			objMod.setPortOfLoading(rs.getString("POL"));
			
			try {
			if(rs.getString("BL_TYPE").equalsIgnoreCase("C")) {
				objMod.setBlType("COC");
			}else if (rs.getString("BL_TYPE").equalsIgnoreCase("S")){
				objMod.setBlType("SOC");
			}else {
				objMod.setBlType(rs.getString("BL_TYPE"));
			}
			}catch (Exception e) {
				objMod.setBlType(rs.getString("BL_TYPE"));
			}
			
//			objMod.setBlType(rs.getString("BL_TYPE"));
			objMod.setConsigneeName(rs.getString("CONSIGNEE_NAME"));
			objMod.setFetch(false);
			objMod.setSaveFlags("N");
			objMod.setBlCriteria("MBL");
			objMod.setHblCount(rs.getInt("HBLCOUNT"));
			return objMod;
		}
	}
	private class ImportGeneralManifestRowMapperHBLDetails extends JdbcRowMapper {
		private boolean isSaved;

		public ImportGeneralManifestRowMapperHBLDetails(boolean isSaved) {
			this.isSaved = isSaved;
		}
		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Object mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();

			objMod.setBl(rs.getString("BL_NO"));
			objMod.setHblNo(rs.getString("HBL_NO"));
			objMod.setBlDate(rs.getString("BL_DATE"));
			objMod.setVessel(rs.getString("VESSEL"));
			objMod.setVoyage(rs.getString("VOYAGE"));
			objMod.setItemNumber(rs.getString("ITEM_NUMBER"));
			if (rs.getString("ITEM_NUMBER") != null && !rs.getString("ITEM_NUMBER").equals("")) {
				objMod.setIsBlSave(String.valueOf(true));
			} else {
				objMod.setIsBlSave(String.valueOf(false));
			}
			objMod.setCargoMovmnt(rs.getString("CARGO_MOVMNT"));
			objMod.setPod(rs.getString("POD"));
			objMod.setPortOfLoading(rs.getString("POL"));

			if (rs.getString("BL_TYPE").equalsIgnoreCase("C")) {
				objMod.setBlType("COC");
			} else if (rs.getString("BL_TYPE").equalsIgnoreCase("S")) {
				objMod.setBlType("SOC");
			} else {
				objMod.setBlType(rs.getString("BL_TYPE"));
			}

//			objMod.setBlType(rs.getString("BL_TYPE"));
			objMod.setConsigneeName(rs.getString("CONSIGNEE_NAME"));
			objMod.setFetch(false);
			objMod.setSaveFlags("N");
			objMod.setBlCriteria("HBL");
			//objMod.setHblCount(rs.getInt("HBLCOUNT"));
			return objMod;
		}
	}

	@Override
	public List<PortMod> getPortDetailsJdbc() {
		 
		String query= "select PK_PORT_CODE,PORT_NAME from rcltbls.cam_port";
		
		List<PortMod> list = getJdbcTemplate().query(query, new PortRowmapper());
		
		return list;
	}
	
	private class PortRowmapper extends JdbcRowMapper {

		@Override
		public PortMod mapRow(ResultSet rs, int row) throws SQLException {
			 
			PortMod port = new PortMod();
			
			port.setPort(rs.getString("PK_PORT_CODE"));
			port.setPortName(rs.getString("PORT_NAME"));
			
			return port;
		}
		
	}

	@Override
	public Map<Object, Object> getBLDataNew(Map<String, String> amapParam, String procedureName, boolean isSave)
			throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getBLData() started.." + isSave);

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new ImportGeneralManifestRowMapperBLDetails(isSave), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		
		return mapResult;
	}

	@Override
	public Map<Object, Object> getBLCarogoDetails(Map<String, String> amapParam, String procedureName) throws BusinessException {
		// TODO Auto-generated method stub
		System.out.println("#IGMLogger getBLData() started..");

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
				{ KEY_IGM_SERVICE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_SERVICE) },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
				{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
				{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
				{ KEY_IGM_POD_SCREEN, BLANK + ORACLE_VARCHAR, PARAM_IN,
							(String) amapParam.get(KEY_IGM_POD_SCREEN) },
				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null},
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new ImportGeneralManifestRowMapperBL(true,false), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		
		return mapResult;
	}

	@Override
	public int getSeqNoJdbc(ImportGeneralManifestMod service,String type,String fileName) {
		
		int seqNo = 0; 
		StringBuilder getSeqQuery = new StringBuilder();
		
		getSeqQuery.append("SELECT GENERATE_COUNT FROM JSON_FILE_GENERATE_DETAILS WHERE 1=1");
		if(service.getVessel()!=null && !service.getVessel().equals("")) {
			getSeqQuery.append(" AND VESSEL = '"+ service.getVessel()+"'");
		}
		if(service.getVoyage()!=null && !service.getVoyage().equals("")) {
			getSeqQuery.append(" AND VOYAGE = '"+ service.getVoyage()+"'");
		}
		if(service.getPod()!=null && !service.getPod().equals("") && type.equals("IGM")) {
			getSeqQuery.append(" AND PORT = '"+ service.getPod()+"'");
		}
		if(service.getPol()!=null && !service.getPol().equals("") && type.equals("EGM")) {
			
			getSeqQuery.append(" AND PORT = '"+ service.getPol()+"'");
		}
		if(fileName!=null && !fileName.equals("")) {
			getSeqQuery.append(" AND FILE_NAME = '"+ fileName+"'");
		}
		
		try {
			seqNo  = getJdbcTemplate().queryForInt(getSeqQuery.toString());
		}catch (Exception e) {
			seqNo = 0;
		}
		return seqNo;
	}

	@Override
	public void updateSqnNoForJsonFile(ImportGeneralManifestMod service,int getSeqNo,String tyep,String fileName) {
		StringBuilder getSeqQuery = new StringBuilder();
		
		int seqNo = getSeqNo+1;
		
		if(tyep.equals("IGM")) {
			if(getSeqNo == 0) {
				getSeqQuery.append("INSERT INTO JSON_FILE_GENERATE_DETAILS  (VESSEL,VOYAGE,PORT,GENERATE_COUNT,FILE_NAME) values('"+service.getVessel()+"','"+service.getVoyage()+"','"+service.getPod()+"',"+seqNo+",'"+fileName+"')");
			}else {
				getSeqQuery.append("update JSON_FILE_GENERATE_DETAILS set GENERATE_COUNT = "+seqNo+" where VESSEL='"+service.getVessel()+"' and VOYAGE = '"+service.getVoyage()+"' and PORT= '"+service.getPod()+"' and FILE_NAME='"+fileName+"' ");
			}
		}else {
			if(getSeqNo == 0) {
				getSeqQuery.append("INSERT INTO JSON_FILE_GENERATE_DETAILS  (VESSEL,VOYAGE,PORT,GENERATE_COUNT,FILE_NAME) values('"+service.getVessel()+"','"+service.getVoyage()+"','"+service.getPol()+"',"+seqNo+",'"+fileName+"')");
			}else {
				getSeqQuery.append("update JSON_FILE_GENERATE_DETAILS set GENERATE_COUNT = "+seqNo+" where VESSEL='"+service.getVessel()+"' and VOYAGE = '"+service.getVoyage()+"' and PORT= '"+service.getPol()+"' and FILE_NAME='"+fileName+"' ");
			}
		}
		
		getJdbcTemplate().update(getSeqQuery.toString());
		
	}

	@Override
	public Map<Object, Object> getBLDataNewForSome(Map<String, String> amapParam, String procedureName,List<ImportGeneralManifestMod> listOfBL)
			throws BusinessException, DataAccessException {
		System.out.println("#IGMLogger getBLDataNewForSome() started..");
		Map<String, ImportGeneralManifestMod> mapBlWithContainerDetails = new HashMap<String, ImportGeneralManifestMod>();
		String blsInput = null;
		for (ImportGeneralManifestMod bl : listOfBL) {
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
			mapBlWithContainerDetails.put(bl.getBl(), bl);
		}

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
//				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL) },
//				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN,"SINCB1101118203" },
//    		    { KEY_IGM_CONTAINER_NUM, BLANK + ORACLE_VARCHAR, PARAM_IN,null},
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD)},
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL)},
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE)},
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) amapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_CONSIGNEE_COUNTRYCODE, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) amapParam.get(KEY_IGM_CONSIGNEE_COUNTRYCODE)},
				{ KEY_IGM_CONSIGNEE_STATE, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) amapParam.get(KEY_IGM_CONSIGNEE_STATE)},
////	    	{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
////			{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
////			{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
//				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
//				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
//				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
//				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
//						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
//				{ KEY_IGM_CONSIGNEE_STATE, BLANK + ORACLE_VARCHAR, PARAM_IN,
//							(String) amapParam.get(KEY_IGM_CONSIGNEE_STATE) },
//				{ KEY_IGM_PORT, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) amapParam.get(KEY_IGM_PORT) },
//				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new MoreDataRowMapper(), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();
		
		System.out.println("executed result...............");

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		return mapResult;
	}
	
	
		private class MoreDataRowMapper extends JdbcRowMapper {
	

			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// System.out.println("#IGMLogger mapRow() started..");
				ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();
				objMod.setHouseBl(rs.getString("FK_HOUSE_BL_NO"));
				objMod.setGstStateCode(rs.getString("GST_STATE_CODE"));
				objMod.setDn_plr(rs.getString("DN_PLR"));
				objMod.setPortName(rs.getString("PORT_NAME"));
				objMod.setDn_pld(rs.getString("DN_PLD"));
				objMod.setAcceptanceName(rs.getString("ACCEPTANCE_NAME"));	
				objMod.setRecieptName(rs.getString("RECIEPT_NAME"));	
				
				return objMod;
			}
	 }

		@Override
		public Map<Object, Object> getStowageImport(Map<String, String> mapParam, String procedureName)
				throws BusinessException, DataAccessException {
			String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
					{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_BL) },
					{ KEY_IGM_CONTAINER_NUM, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_CONTAINER_NUM)},
					{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_POD)},
					{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_VESSEL)},
					{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_VOYAGE)},
					{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_POD_TERMINAL) },
					{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };
			
			/* stored procedure object */
			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
					new StowageImport(), arrParam);
			
			/* Return Result from SP execute */
			Map mapResult = objSP.execute();

			/* Stores return error code from SP */
			String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
			/* If return error code is Failure, throw Business Exception */
			if (isErrorCode(strRetError)) {
				System.out.println("Error while getting igm data from DB : " + strRetError);
				throw ExceptionFactory.createApplicationException(strRetError);
			}
			
			return mapResult;
		}
		
		private class StowageImport extends JdbcRowMapper {
			
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// System.out.println("#IGMLogger mapRow() started..");
				ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();
				
				if(null == rs.getString("STOWAGE_POSITION")) {
					
					objMod.setStowageImport(null);
				}
				objMod.setStowageImport(rs.getString("STOWAGE_POSITION"));
				return objMod;
			}
	 }

		@Override
		public Map<Object, Object> getStowageExport(Map<String, String> mapParam, String procedureName)
				throws BusinessException, DataAccessException {

				String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
						{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_BL) },
						{ KEY_IGM_CONTAINER_NUM, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_CONTAINER_NUM)},
						{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_POL)},
						{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_VESSEL)},
						{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_VOYAGE)},
						{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_POL_TERMINAL) },
						{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };
				
				/* stored procedure object */
				JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
						new StowageExport(), arrParam);
				
				/* Return Result from SP execute */
				Map mapResult = objSP.execute();
				
				/* Stores return error code from SP */
				String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
				/* If return error code is Failure, throw Business Exception */
				if (isErrorCode(strRetError)) {
					System.out.println("Error while getting igm data from DB : " + strRetError);
					throw ExceptionFactory.createApplicationException(strRetError);
				}
	
			return mapResult;
		}
		
	private class StowageExport extends JdbcRowMapper {
			
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// System.out.println("#IGMLogger mapRow() started..");
				ImportGeneralManifestMod objMod = new ImportGeneralManifestMod();
				objMod.setStowageExport(rs.getString("STOWAGE_POSITION"));
				return objMod;
			}
	 }

//	@Override
//	public List<ImportGeneralManifestMod> getCommodityJdbc(List<ImportGeneralManifestMod> listOfBL) {
//		String blsInput = null;
//		Map<String, ImportGeneralManifestMod> mapBlWithContainerDetails = new HashMap<String, ImportGeneralManifestMod>();
//		for (ImportGeneralManifestMod bl : listOfBL) {
//			if (blsInput == null)
//				blsInput = "'" + bl.getBl() + "'";
//			else
//				blsInput += ",'" + bl.getBl() + "'";
//			mapBlWithContainerDetails.put(bl.getBl(), bl);
//		}
//	
//		String getCommodityQuery = "select DN_PACKAGE_KIND,FLAG_DG,DN_COMMODITY_CODE FROM RCLTBLS.DEX_BL_COMMODITY  where FK_BL_NO = ?";
//		
//				 if(! "".equals(blsInput) && blsInput!= null ) {
//					 List<ImportGeneralManifestMod> commodityList = getJdbcTemplate().query(getCommodityQuery, new CommodityRowmapper());
//						return commodityList;
//				 }
//				return null;		
//	}
//	private class CommodityRowmapper extends JdbcRowMapper {
//
//		@Override
//		public ImportGeneralManifestMod mapRow(ResultSet rs, int row) throws SQLException {
//			 
//			ImportGeneralManifestMod mod = new ImportGeneralManifestMod();
//			
//			mod.setType_of_package(rs.getString("DN_PACKAGE_KIND"));
//			mod.setFlagDg(rs.getString("FLAG_DG"));
//			mod.setHsCd(rs.getString("DN_COMMODITY_CODE"));
//			
//			
//			return mod;
//		}
//		
//	}
	
	@Override
	public String getTEMP_UPLOAD_DIR() {
		 
			String TEMP_UPLOAD_DIR_QUERY = "SELECT CONFIG_VALUE FROM VASAPPS.VCM_CONFIG_MST WHERE STATUS = 'A' AND CONFIG_TYP IN ('UPLOAD_DIR') and config_cd = 'TEMP_UPLOAD_DIR' AND ROWNUM < 2";
			String path = (String) getJdbcTemplate().queryForObject(TEMP_UPLOAD_DIR_QUERY, Object.class);
			System.out.println(" getTEMP_UPLOAD_DIR() "+path);
			
		return path;
	}
	
	@Override
	public Map<Object, Object> getOneBLDataNewFor(Map<String, String> mapParam, String procedureName) throws BusinessException {
		

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
//				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POD) },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_BL) },
//				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN,"SINCB1101118203" },
//    		    { KEY_IGM_CONTAINER_NUM, BLANK + ORACLE_VARCHAR, PARAM_IN,null},
				{ KEY_IGM_POD, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_POD)},
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_VESSEL)},
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) mapParam.get(KEY_IGM_VOYAGE)},
				{ KEY_IGM_POD_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_POD_TERMINAL) },
				{ KEY_IGM_CONSIGNEE_COUNTRYCODE, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_CONSIGNEE_COUNTRYCODE)},
				{ KEY_IGM_CONSIGNEE_STATE, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) mapParam.get(KEY_IGM_CONSIGNEE_STATE)},
////	    	{ KEY_IGM_FROM_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_FROM_DATE) },
////			{ KEY_IGM_TO_DATE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_TO_DATE) },
////			{ KEY_IGM_BL_STATUS, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_BL_STATUS) },
//				{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
//				{ KEY_IGM_DEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEL) },
//				{ KEY_IGM_DEPOT, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_DEPOT) },
//				{ KEY_IGM_POL_TERMINAL, BLANK + ORACLE_VARCHAR, PARAM_IN,
//						(String) amapParam.get(KEY_IGM_POL_TERMINAL) },
//				{ KEY_IGM_CONSIGNEE_STATE, BLANK + ORACLE_VARCHAR, PARAM_IN,
//							(String) amapParam.get(KEY_IGM_CONSIGNEE_STATE) },
//				{ KEY_IGM_PORT, BLANK + ORACLE_VARCHAR, PARAM_IN,(String) amapParam.get(KEY_IGM_PORT) },
//				{ KEY_IGM_DIRECTION, BLANK + ORACLE_VARCHAR, PARAM_IN, null },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		this.blrNumber = new HashSet<String>();

		this.PodTerminal = new HashSet<String>();

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new MoreDataRowMapper(), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();
		
		System.out.println("executed result...............");

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		return mapResult;
	
	}

	@Override
	public Map getHblListJdbc(ImportGeneralManifestUim objForm, String blType) throws BusinessException {

		String procedure = "";

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, "LCHSB22035374" },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK } };

		
		if(blType.equals("saved")) {
			procedure = SQL_RCL_IGM_BL_MASTER;
		}else { 
			procedure = SQL_RCL_IGM_BL_SAVE;
			  }

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedure,
				new ImportGeneralManifestRowMapperHBLDetails(true), arrParam);

		/* Return Result from SP execute */
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}

		return mapResult;

	}


}

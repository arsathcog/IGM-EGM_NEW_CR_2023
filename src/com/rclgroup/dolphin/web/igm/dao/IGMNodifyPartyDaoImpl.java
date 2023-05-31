package com.rclgroup.dolphin.web.igm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.control.common.exception.ExceptionFactory;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcRowMapper;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.NotifyParty;
import com.rclgroup.dolphin.web.igm.vo.NotifyPartyTwo;

public class IGMNodifyPartyDaoImpl extends AncestorJdbcDao implements IGMNodifyPartyDao {

	public void saveNodifyData(List<NotifyParty> listOfMarks,  String  blsInput,String procedureName) throws Exception {
		if(!CollectionUtils.isEmpty(listOfMarks)) {
			
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(listOfMarks);
		System.out.println("saveNodifyTwoData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_NODIFY_PARTY_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				arrParam);

		objSP.execute();
		}
	}

	@Override
	public void setNotifyPartyData(List<ImportGeneralManifestMod> listOfBL, String procedureName)
			throws Exception {
		Map<String, ImportGeneralManifestMod> mapBlWithContainerDetails = new HashMap<String, ImportGeneralManifestMod>();
		String blsInput = null;
		for (ImportGeneralManifestMod bl : listOfBL) {
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
			mapBlWithContainerDetails.put(bl.getBl(), bl);
		}
		// TODO Auto-generated method stub

		System.out.println("#IGMLogger setNotifyPartyData() started..");

	

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMNotifyPartyMapper(), arrParam);

		/* Return Result from SP execute */
		if(blsInput== null) {
			return ;
		}
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while setNotifyPartyData igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		List<NotifyParty> listOfContainer = (List<NotifyParty>) mapResult.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
				

		if (listOfContainer != null) {
			for (NotifyParty notifyparty : listOfContainer) {
				mapBlWithContainerDetails.get(notifyparty.getBlNo()).getNotifyParty().add(notifyparty);
			}

		}
		return ;
		
	}

	
	
	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class IGMNotifyPartyMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public NotifyParty mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			//ContainerDetails contDetails = new ContainerDetails();
			NotifyParty notifyparty = new NotifyParty();
			notifyparty.setBlNo(rs.getString("FK_BL_NO"));
			notifyparty.setCostumerCode(rs.getString("CUSTOMER_CODE"));
			notifyparty.setCostumerName(rs.getString("CUSTOMER_NAME"));
			notifyparty.setAddressLine1(rs.getString("ADDRESS_LINE_1"));
			notifyparty.setAddressLine2(rs.getString("ADDRESS_LINE_2"));
			notifyparty.setAddressLine3(rs.getString("ADDRESS_LINE_3"));
			notifyparty.setAddressLine4(rs.getString("ADDRESS_LINE_4"));
			notifyparty.setCity(rs.getString("CITY"));
			notifyparty.setState(rs.getString("STATE"));
			notifyparty.setCountryCode(rs.getString("DN_COUNTRY_CODE"));
			notifyparty.setZip(rs.getString("ZIP"));
//			notifyparty.setNotify2(rs.getString("NOTIFY2"));
			notifyparty.setNotifyName(rs.getString("NOTIFY_NAME"));
			notifyparty.setNotifyIec(rs.getString("NOTIFY_IEC"));
			notifyparty.setNotifyPan(rs.getString("NOTIFY_PAN"));
			notifyparty.setPortOfDischarge(rs.getString("PORT_OF_DISCHARGE"));
			notifyparty.setConsigneCheckBox(rs.getString("CONSIGNEE_CHECK_BOX"));
			notifyparty.setNotifyFwr(rs.getString("CONSIGNEE_FWR"));
			notifyparty.setStateName(rs.getString("STATE_NAME"));
			
	
			return notifyparty;
		}
	}

	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class IGMNotifyPartyTwoMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public NotifyPartyTwo mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			//ContainerDetails contDetails = new ContainerDetails();
			NotifyPartyTwo notifyparty = new NotifyPartyTwo();
			notifyparty.setBlNo(rs.getString("FK_BL_NO"));
			notifyparty.setCostumerCode(rs.getString("CUSTOMER_CODE"));
			notifyparty.setCostumerName(rs.getString("CUSTOMER_NAME"));
			notifyparty.setAddressLine1(rs.getString("ADDRESS_LINE_1"));
			notifyparty.setAddressLine2(rs.getString("ADDRESS_LINE_2"));
			notifyparty.setAddressLine3(rs.getString("ADDRESS_LINE_3"));
			notifyparty.setAddressLine4(rs.getString("ADDRESS_LINE_4"));
			notifyparty.setCity(rs.getString("CITY"));
			notifyparty.setState(rs.getString("STATE"));
			notifyparty.setCountryCode(rs.getString("DN_COUNTRY_CODE"));
			notifyparty.setZip(rs.getString("ZIP"));
	
			return notifyparty;
		}
	}

	@Override
	public void setNotifyPartyTwoData(List<ImportGeneralManifestMod> listOfBL, String procedureName) throws Exception {
		Map<String, ImportGeneralManifestMod> mapBlWithContainerDetails = new HashMap<String, ImportGeneralManifestMod>();
		String blsInput = null;
		for (ImportGeneralManifestMod bl : listOfBL) {
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
			mapBlWithContainerDetails.put(bl.getBl(), bl);
		}
		// TODO Auto-generated method stub

		System.out.println("#IGMLogger setNotifyPartyTwoData() started..");

	

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMNotifyPartyTwoMapper(), arrParam);

		/* Return Result from SP execute */
		if(blsInput== null) {
			return ;
		}
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while setNotifyPartyData igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		List<NotifyPartyTwo> listOfNotifyParty = (List<NotifyPartyTwo>) mapResult.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
				

		if (listOfNotifyParty != null) {
			for (NotifyPartyTwo notifyparty : listOfNotifyParty) {
				mapBlWithContainerDetails.get(notifyparty.getBlNo()).getNotifyPartyTwo().add(notifyparty);
			}

		}
		return ;
		
		
	}

	@Override
	public void saveNodifyTwoData(List<NotifyPartyTwo> listOfNodifyTwo, String blsInput,String procedureName) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(listOfNodifyTwo);
		System.out.println("saveNodifyTwoData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_NODIFY_PARTY_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				arrParam);

		objSP.execute();
		
	}

	@Override
	public void deleteNodifyData(List<NotifyParty> notifyParty, String blsInput, String procedureName) throws JsonProcessingException {
		if(blsInput!=null) {
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(notifyParty);
		System.out.println("saveNodifyTwoData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_NODIFY_PARTY_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				arrParam);

		objSP.execute();
		}
	}

}

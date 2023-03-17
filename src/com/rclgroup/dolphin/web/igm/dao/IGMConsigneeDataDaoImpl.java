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
 
import com.rclgroup.dolphin.web.igm.vo.Consignee;
import com.rclgroup.dolphin.web.igm.vo.Consigner;
import com.rclgroup.dolphin.web.igm.vo.ContainerDetails;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public class IGMConsigneeDataDaoImpl extends AncestorJdbcDao implements IGMConsigneeDataDao {

	

	public void saveConsigneeData(List<Consignee> listOfConsignee,  String blsInput,String procedureName) throws Exception {
		if(!CollectionUtils.isEmpty(listOfConsignee)) {
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(listOfConsignee);
		System.out.println("saveConsigneeData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONSIGNEE_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();
		}
	}

	
	public void setConsigneeData(List<ImportGeneralManifestMod> listOfBL,
			String procedureName) throws Exception {
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

		System.out.println("#IGMLogger setConsigneeData() started..");

	

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMConsigneeMapper(), arrParam);

		/* Return Result from SP execute */
		if(blsInput== null) {
			return ;
		}
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while setConsigneeData igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		List<Consignee> listOfContainer = (List<Consignee>) mapResult.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
				

		if (listOfContainer != null) {
			for (Consignee consignee : listOfContainer) {
				mapBlWithContainerDetails.get(consignee.getBlNO()).getConsignee().add(consignee);
			}

		}
		return ;

	}
	
	
	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class IGMConsigneeMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public Consignee mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			//ContainerDetails contDetails = new ContainerDetails();
			Consignee consignee = new Consignee();
			consignee.setBlNO(rs.getString("FK_BL_NO"));
			consignee.setCustomerCode(rs.getString("CONSIGNEE_CODE"));
			consignee.setCustomerName(rs.getString("consignee_name"));
			consignee.setAddressLine1(rs.getString("ADDRESS_LINE_1"));
			consignee.setAddressLine2(rs.getString("ADDRESS_LINE_2"));
			consignee.setAddressLine3(rs.getString("ADDRESS_LINE_3"));
			consignee.setAddressLine4(rs.getString("ADDRESS_LINE_4"));
			consignee.setCity(rs.getString("CITY"));
			consignee.setState(rs.getString("STATE"));
			consignee.setCountryCode(rs.getString("DN_COUNTRY_CODE"));
			consignee.setZip(rs.getString("ZIP"));
	
			return consignee;
		}
	}


	@Override
	public void deleteConsigneeData(List<Consignee> consignee, String blsInput, String procedureName) throws JsonProcessingException {
		if(blsInput!=null) {
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(consignee);
		System.out.println("saveConsigneeData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONSIGNEE_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();
		}
	}


}
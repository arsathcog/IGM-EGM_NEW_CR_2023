package com.rclgroup.dolphin.web.igm.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import com.rclgroup.dolphin.web.igm.vo.ContainerDetails;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public class IGMContainerDaoImpl extends AncestorJdbcDao implements IGMContainerDao {

	public static final String SQL_SAVE_CONTAINOR = "RCL_IGM_BL_INFO.RCL_IGM_SAVE_CONTAINOR_NEW";

	/** The Constant KEY_IGM_CONTAINER_DTLS. */
	public static final String KEY_IGM_CONTAINER_DTLS = "P_I_V_CONTAINER_DTLS";

	/** The Constant KEY_REF_IGM_DATA. */
	public static final String KEY_REF_IGM_DATA = "P_O_REFIGMTABFIND";

	/** The Constant KEY_IGM_ERROR. */
	public static final String KEY_IGM_ERROR = "P_O_V_ERROR";

	@Override
	public Map saveContainer( List<ContainerDetails> containerDetailes, String blsInput,String procedureName) throws Exception {
		// TODO Auto-generated method stub
	
		if(!CollectionUtils.isEmpty(containerDetailes)) {
		
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(containerDetailes);
		System.out.println("saveContainerData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONTAINER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();
		}
		return null;
		
	}

	@Override
	public Map<String, List<ContainerDetails>> setContainerDetails(List<ImportGeneralManifestMod> listOfBL,
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

		System.out.println("#IGMLogger getContainerDetails() started..");

		

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMContainerDetailsMapper(), arrParam);

		/* Return Result from SP execute */
		if(blsInput== null) {
			return null;
		}
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		List<ContainerDetails> listOfContainer = (List<ContainerDetails>) mapResult
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		if (listOfContainer != null) {
			for (ContainerDetails containerDetails : listOfContainer) {
			
				if (containerDetails != null) {
					mapBlWithContainerDetails.get(containerDetails.getBlNo()).getContainerDetailes().add(containerDetails);
				}
			}

		}
		return null;

	}
	
	@Override
	public List<ContainerDetails> setContainerDetailsNew(List<ImportGeneralManifestMod> listOfBL, String procedureName)
			throws Exception {
		System.out.println("#IGMLogger getContainerDetails() started..");
		String blsInput = null;
		for (ImportGeneralManifestMod bl : listOfBL) {
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
		 
		}

		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_ERROR, BLANK + ORACLE_VARCHAR, PARAM_OUT, BLANK }

		};

		/* stored procedure object */
		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName,
				new IGMContainerDetailsMapper(), arrParam);

		/* Return Result from SP execute */
		if(blsInput== null) {
			return null;
		}
		Map mapResult = objSP.execute();

		/* Stores return error code from SP */
		String strRetError = (String) mapResult.get(KEY_IGM_ERROR);
		/* If return error code is Failure, throw Business Exception */
		if (strRetError != null && isErrorCode(strRetError)) {
			System.out.println("Error while getting igm data from DB : " + strRetError);
			throw ExceptionFactory.createApplicationException(strRetError);
		}
		List<ContainerDetails> listOfContainer = (List<ContainerDetails>) mapResult
				.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		 
		return listOfContainer;

	}

	/**
	 * The Class ImportGeneralManifestRowMapper.
	 */
	private class IGMContainerDetailsMapper extends JdbcRowMapper {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.niit.control.dao.JdbcRowMapper#mapRow(java.sql.ResultSet, int)
		 */

		public ContainerDetails mapRow(ResultSet rs, int row) throws SQLException {
			// System.out.println("#IGMLogger mapRow() started..");
			ContainerDetails contDetails = new ContainerDetails();
	
			contDetails.setBlNo(rs.getString("FK_BL_NO"));
			contDetails.setContainerNumber(rs.getString("DN_CONTAINER_NO"));
			if(rs.getString("SEALTYPE")==null || rs.getString("SEALTYPE").equals("") || rs.getString("SEALTYPE").equals("0") || rs.getString("SEALTYPE").equals("NONE")) {
				contDetails.setEquipment_seal_type("BTSL");
			}else {
				contDetails.setEquipment_seal_type(rs.getString("SEALTYPE"));
			}
			
			contDetails.setContainerSealNumber(rs.getString("SEAL_NO"));
			contDetails.setContainerAgentCode(rs.getString("CONTAINER_AGENT"));
			if(rs.getString("CONTAINER_STATUS")== null || rs.getString("CONTAINER_STATUS").equals("")) {
				contDetails.setStatus("FCL");
			}else {
				contDetails.setStatus(rs.getString("CONTAINER_STATUS"));
			}
			if(rs.getString("SOC_FLAG")==null || rs.getString("SOC_FLAG").equals("")) {
				contDetails.setSoc_flag("N");
			}else {
				contDetails.setSoc_flag(rs.getString("SOC_FLAG"));
			}
			if(rs.getString("CONTAINER_BONDFLAG") == null || rs.getString("CONTAINER_BONDFLAG").equals("")) {
				contDetails.setContainerBondFlag("D");
			}else {
				contDetails.setContainerBondFlag(rs.getString("CONTAINER_BONDFLAG"));
			}
			contDetails.setContainerType(rs.getString("CONTAINERTYPE"));
			contDetails.setTypeDetail(rs.getString("TYPE_DETAIL"));
			contDetails.setTotalNumberOfPackagesInContainer(rs.getString("TOTAL_PACKAGES"));
			contDetails.setContainerWeight(rs.getString("CONTAINER_WEIGHT"));
			contDetails.setCbm(rs.getString("CBM"));
			String ConSize=rs.getString("CONTAINERSIZE");
			String ConType=rs.getString("CONTAINERTYPE");
			String isoCode="";
            if(ConSize.isEmpty()){
				 isoCode="";
				 }
			else if(ConSize.equals("20")){
				 isoCode= "2000";
				 }
			else if(ConSize.equals("40")){
				if(ConType != null && ConType.equals("HC")){
					isoCode= "4200";
					}else {
						isoCode= "4200";
					}
				}
			else{
				 isoCode="4000";}
			contDetails.setIsoCode(isoCode);
			contDetails.setUnos(rs.getString("UNOS"));
			contDetails.setImo(rs.getString("IMO"));
			contDetails.setClassification(rs.getString("CLASSIFICATION"));
			contDetails.setRemarks(rs.getString("REMARKS"));
			contDetails.setContainerSize(rs.getString("CONTAINERSIZE"));
			//contDetails.setEquipmentLoadStatus(rs.getString("EQUIPMENT_LOAD_STATUS"));
			if(rs.getString("EQUIPMENT_LOAD_STATUS").equals(" ") || rs.getString("EQUIPMENT_LOAD_STATUS").isEmpty()){
				contDetails.setEquipmentLoadStatus("FCL");
            }else{
            	contDetails.setEquipmentLoadStatus(rs.getString("EQUIPMENT_LOAD_STATUS"));
            }
			contDetails.setContainerWeightUnit(waightIntoMTS(rs.getString("CONTAINER_WEIGHT")));
			// END BL SECTION

			return contDetails;
		}
	}
	
	public String waightIntoMTS(String waight) {
		if(waight==null || waight.equals("")) {
			return "0";
		}
		String result = Double.valueOf(waight)/1000 + "";
		return result;
	}

	@Override
	public void deleteContainer(List<ContainerDetails> containerDetailes, String blsInput, String procedureName) throws JsonProcessingException {
		if(blsInput!=null) {
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(containerDetailes);
		System.out.println("saveContainerData() started");
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_CONTAINER_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();
		}
	}

}

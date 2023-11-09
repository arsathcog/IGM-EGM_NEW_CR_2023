package com.rclgroup.dolphin.web.igm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.control.dao.AncestorJdbcDao;
import com.niit.control.dao.JdbcStoredProcedure;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;

public class IGMBLDataDaoImpl extends AncestorJdbcDao implements IGMBLDataDao {

	public void saveBLData(List<ImportGeneralManifestMod> listOfBL,String blsInput,String procedureName) throws Exception{
		 
		System.out.println("saveBLData() Called .. [Started.] procedure Name " +procedureName);
		
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(listOfBL);
		System.out.println("saveBLData() started json  : "+containeer);
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_BL_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();

		}

	@Override
	public void deleteBLData(List<ImportGeneralManifestMod> deleteBL,String blsDeleteInput, String procedureName) throws CloneNotSupportedException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			String containeer = mapper.writeValueAsString(deleteBL);
			System.out.println("saveBLData() started");
			String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsDeleteInput },
					{ KEY_IGM_BL_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

			objSP.execute();
		
	}

	@Override
	public void saveUnfetchedBlData(String unFetchedinsertBLList, String procedureName,Map amapParam,List<ImportGeneralManifestMod> insertBL) throws Exception {
	
//		String blCountLoop  =  Integer.toString(blcount);
		List<ImportGeneralManifestMod> onlyBL= new ArrayList<ImportGeneralManifestMod>();
		 System.out.println("unfetched bl list "+unFetchedinsertBLList);
		if(unFetchedinsertBLList != null) {
			
			ObjectMapper mapper = new ObjectMapper();
			String blJson = mapper.writeValueAsString(insertBL);
			System.out.println(blJson);
			String blInput = unFetchedinsertBLList.substring(1, unFetchedinsertBLList.length()-1);
			String[][] arrParam = { { KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) unFetchedinsertBLList },
					{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
					{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) },
					{ KEY_IGM_POL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_POL) },
					{ KEY_IGM_BL_JSON, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) blJson }};
			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);
			objSP.execute();
		}
	}
		
}

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

	public void saveBLData(List<ImportGeneralManifestMod> listOfBL,String procedureName) throws Exception{
		 
		System.out.println("saveBLData() Called .. [Started.] procedure Name " +procedureName);
		
		if(!CollectionUtils.isEmpty(listOfBL)) {
		List<ImportGeneralManifestMod> onlyBL= new ArrayList<ImportGeneralManifestMod>();
		String blsInput = null;
		for (ImportGeneralManifestMod bl : listOfBL) {
			System.out.println("BL NO. "+bl.getBl() +" item No. "+bl.getItemNumber());
			if (blsInput == null)
				blsInput = "'" + bl.getBl() + "'";
			else
				blsInput += ",'" + bl.getBl() + "'";
			
			ImportGeneralManifestMod newBL =(ImportGeneralManifestMod) bl.clone();
			newBL.setContainerDetailes(null);
			newBL.setMarksNumber(null);
			newBL.setConsignee(null);
			newBL.setConsigner(null);
			newBL.setNotifyParty(null);
			newBL.setPreviousDeclaration(null);
			newBL.setNotifyPartyTwo(null);
			onlyBL.add(newBL);
		}
		ObjectMapper mapper = new ObjectMapper();
		String containeer = mapper.writeValueAsString(onlyBL);
		System.out.println("saveBLData() started json  : "+containeer);
		String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
				{ KEY_IGM_BL_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();

		}
		
	}

	@Override
	public void deleteBLData(List<ImportGeneralManifestMod> deleteBL, String procedureName) throws CloneNotSupportedException, JsonProcessingException {
		if(!CollectionUtils.isEmpty(deleteBL)) {
			List<ImportGeneralManifestMod> onlyBL= new ArrayList<ImportGeneralManifestMod>();
			String blsInput = null;
			for (ImportGeneralManifestMod bl : deleteBL) {
				if (blsInput == null)
					blsInput = "'" + bl.getBl() + "'";
				else
					blsInput += ",'" + bl.getBl() + "'";
				
				ImportGeneralManifestMod newBL =(ImportGeneralManifestMod) bl.clone();
				newBL.setContainerDetailes(null);
				newBL.setMarksNumber(null);
				newBL.setConsignee(null);
				newBL.setConsigner(null);
				newBL.setNotifyParty(null);
				newBL.setPreviousDeclaration(null);
				newBL.setNotifyPartyTwo(null);
				onlyBL.add(newBL);
			}
			
			ObjectMapper mapper = new ObjectMapper();
			String containeer = mapper.writeValueAsString(onlyBL);
			System.out.println("saveBLData() started");
			String[][] arrParam = { { "P_I_V_BL", BLANK + ORACLE_VARCHAR, PARAM_IN, blsInput },
					{ KEY_IGM_BL_DTLS, BLANK + ORACLE_VARCHAR, PARAM_IN, containeer }, };

			JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

			objSP.execute();
		
			}
	}

	@Override
	public void saveUnfetchedBlData(String unFetchedinsertBLList, String procedureName,Map amapParam) throws Exception {
	
//		String blCountLoop  =  Integer.toString(blcount);
		List<ImportGeneralManifestMod> onlyBL= new ArrayList<ImportGeneralManifestMod>();
		String blsInput = null;
		
		String[][] arrParam = { { KEY_REF_IGM_DATA, BLANK + ORACLE_CURSOR, PARAM_OUT, BLANK },
				{ KEY_IGM_BL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) unFetchedinsertBLList },
				{ KEY_IGM_VESSEL, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VESSEL) },
				{ KEY_IGM_VOYAGE, BLANK + ORACLE_VARCHAR, PARAM_IN, (String) amapParam.get(KEY_IGM_VOYAGE) }};

		JdbcStoredProcedure objSP = new JdbcStoredProcedure(getDataSource(), procedureName, arrParam);

		objSP.execute();
		
	}
		
}

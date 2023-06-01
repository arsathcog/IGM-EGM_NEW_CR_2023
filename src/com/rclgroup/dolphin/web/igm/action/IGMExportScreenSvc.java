package com.rclgroup.dolphin.web.igm.action;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.control.common.exception.BusinessException;
import com.niit.control.web.action.BaseAction;
import com.rclgroup.dolphin.web.igm.actionform.ImportGeneralManifestUim;
import com.rclgroup.dolphin.web.igm.dao.IGMBLDataDao;
import com.rclgroup.dolphin.web.igm.dao.IGMConsigneeDataDao;
import com.rclgroup.dolphin.web.igm.dao.IGMConsignerDataDao;
import com.rclgroup.dolphin.web.igm.dao.IGMContainerDao;
import com.rclgroup.dolphin.web.igm.dao.IGMContainerDaoImpl;
import com.rclgroup.dolphin.web.igm.dao.IGMDaoNew;
import com.rclgroup.dolphin.web.igm.dao.IGMExportDao;
import com.rclgroup.dolphin.web.igm.dao.IGMMarksAndDescDao;
import com.rclgroup.dolphin.web.igm.dao.IGMNodifyPartyDao;
import com.rclgroup.dolphin.web.igm.dao.IGMPPreviousDeclarationDao;
import com.rclgroup.dolphin.web.igm.dao.IGMPreviousDeclarationDaoImpl;
import com.rclgroup.dolphin.web.igm.dao.IGMVesselVoyageSaveDao;
import com.rclgroup.dolphin.web.igm.dao.ImportGeneralManifestDao;
import com.rclgroup.dolphin.web.igm.dao.PersonOnBoardDao;
import com.rclgroup.dolphin.web.igm.jsonUtil.CreatingJSON;
import com.rclgroup.dolphin.web.igm.vo.Consignee;
import com.rclgroup.dolphin.web.igm.vo.Consigner;
import com.rclgroup.dolphin.web.igm.vo.ContainerDetails;
import com.rclgroup.dolphin.web.igm.vo.IGMCrewEfctMod;
import com.rclgroup.dolphin.web.igm.vo.IGMPersonOnBoardMod;
import com.rclgroup.dolphin.web.igm.vo.IGMShipStoresMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestExportMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestInput;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestMod;
import com.rclgroup.dolphin.web.igm.vo.ImportGeneralManifestResultSet;
import com.rclgroup.dolphin.web.igm.vo.MarksNumber;
import com.rclgroup.dolphin.web.igm.vo.NotifyParty;
import com.rclgroup.dolphin.web.igm.vo.NotifyPartyTwo;
import com.rclgroup.dolphin.web.igm.vo.PortMod;
import com.rclgroup.dolphin.web.igm.vo.PreviousDeclaration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class IGMExportScreenSvc extends BaseAction {
	/** The Constant DAO_BEAN_ID. */
	private static final String DAO_BEAN_ID = "iGMDaoNew";

	private static final String DAO_BEAN_BL_ID = "igmBLDao";
	
	private static final String DAO_BEAN_PERSON= "personOnBoardDao";

	private static final String DAO_BEAN_VESSEL_VOYOGE_ID = "igmVesseVoyogeDao";
	
	private static final String DAO_BEAN_EXPORT = "iGMExportDaoImpl";

	private static final String DAO_BEAN_CONSIGNEE_ID = "igmConsigneeDao";

	private static final String DAO_BEAN_CONSIGNER_ID = "igmConsignerDao";

	private static final String DAO_BEAN_MARK_AND_DESC_ID = "igmMarksDao";

	private static final String DAO_BEAN_NODIFY_ID = "igmNodifyDao";

	private static final String DAO_BEAN_PREV_DECLARATION_DESC_ID = "igmPrevDeclarDao";
	
	/** The Constant DAO_BEAN_ID. */
	private static final String DAO_BEAN_CONTAINER_ID = "igmContainerDao";


	/** The Constant ONLOAD. */
	private static final String GET_VESSEL_VOYAGE_DATA = "vesselVoyagData";

	/** The Constant ONLOAD. */
	private static final String SAVE = "VesselVoyagSave";
	
	private static final String BL_DATA_SAVE = "blDataSaveExport";
	/** The Constant ONLOAD. */
	private static final String UPLOAD = "csvFilePrsnOnBord";
	/** The Constant ONLOAD. */
	private static final String DELETE_CSV = "deleteCsvFile";
	
	private static final String DOWNLOAD_CSV = "downloadCsvFile";
	
	private static final String UPDATE_IGM_DETAILS="updateIgmDetails";
	
	private static final String PORT_DETAILS = "portData";
	
	private static final String GET_PERSON_ONBOARD = "getPersonOnBoard";
	
	private static final String GET_CREW_EFFECT = "getCrewEffect";
	
	private static final String GET_SHIP_STOR = "getShipStor";
	
	private static final String GET_CAROGO_DETAILS = "getCarogoDetails";
	
	private static final String GET_CONTAINER_DETAILS = "getContainerDetails";
	
	private static final String GET_SELECT_ALL_OPTION = "getSelectAllOptionExport";
	
	private static final String GET_STOWAGE_EXPORT = "getStowageExport";
	
	private static final String UPLOAD_SHIPPING = "shippingFileUpload";
	

	/** Define Parameter End. */
	 
	
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String strAction = mapping.getParameter();

		if (ONLOAD.equals(strAction)) {
			return onload(mapping, form, request, response);
		} else if (SEARCH.equals(strAction)) {
			return onSearch(mapping, form, request, response);
		} else if (GET_VESSEL_VOYAGE_DATA.equals(strAction)) {
			return getVesselVoyagData(mapping, form, request, response);
		} else if (SAVE.equals(strAction)) {
			return vesselVoyagSave(mapping, form, request, response);
		} else if (BL_DATA_SAVE.equals(strAction)) {
			return blDataSave(mapping, form, request, response);
		} else if (DOWNLOAD.equals(strAction)) {
			return downloadJson(mapping, form, request, response);
		} else if (UPLOAD.equals(strAction)) {
			return onCsvUpload(mapping, form, request, response);
		} else if (DELETE_CSV.equals(strAction)) {
			return deleteCsvData(mapping, form, request, response);
		} else if(UPDATE_IGM_DETAILS.equals(strAction)) {
			return applyIgm(mapping,form, request, response);
		} else if(DOWNLOAD_CSV.equals(strAction)) {
			return downloadCSV(mapping,form, request, response);
		}else if(PORT_DETAILS.equals(strAction)) {
			return getPortDetails(mapping,form, request, response);
		}else if(GET_PERSON_ONBOARD.equals(strAction)) {
			return getPersonOnboard(mapping,form, request, response);
		}else if(GET_CREW_EFFECT.equals(strAction)) {
			return getCrewEffect(mapping,form, request, response);
		}else if(GET_SHIP_STOR.equals(strAction)) {
			return getShipStor(mapping,form, request, response);
		}else if(GET_CAROGO_DETAILS.equals(strAction)) {
			return getCarogoDetails(mapping,form, request, response);
		}else if(GET_CONTAINER_DETAILS.equals(strAction)) {
			return getContainerDetails(mapping,form, request, response);
		}else if(GET_SELECT_ALL_OPTION.equals(strAction)) {
			return getSelectAllOption(mapping,form, request, response);
		}else if(GET_STOWAGE_EXPORT.equals(strAction)) {
            return getStowageExport(mapping,form, request, response);
		}else if(UPLOAD_SHIPPING.equals(strAction)) {
            return uploadShipping(mapping,form, request, response);
		}
		return null;
	}
	
	


	private ActionForward getStowageExport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws DataAccessException, BusinessException, IOException {
System.out.println("getCarogoDetails() Called.");
		
		IGMDaoNew 				 		objDao 				=   (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim 		objForm 			= 	(ImportGeneralManifestUim) form;
		List<ImportGeneralManifestMod>  listOfBL 			= 	new ArrayList<ImportGeneralManifestMod>(); 
		Map<String, String> 			mapParam 			=   createHeaderParams(objForm);
		List<ImportGeneralManifestMod>  blObj 				=   null;

		Map<Object, Object> mapSaveBL = objDao.getStowageExport(mapParam, IGMDaoNew.SQL_STOWAGE_EXPORT);
		blObj = (List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
	    jsonObj.put("blDetails", blObj);
	    jsonObj.write(response.getWriter());
		return null;
		
	}

	/**
	 * Onload.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * 
	 */
	private ActionForward onload(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}
	//Update
	private ActionForward applyIgm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger ApplyIgm() is called..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		ImportGeneralManifestInput saveParam = mapper.readValue(data, ImportGeneralManifestInput.class);
		ImportGeneralManifestMod service = saveParam.getService();
		// update IgmDetails
		IGMVesselVoyageSaveDao vesselVoyageDao = (IGMVesselVoyageSaveDao) getDao(DAO_BEAN_VESSEL_VOYOGE_ID);
		// vesselVoyageDao.updateIgmdetails(service);
		return null;
	}

	/**
	 * On search.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private ActionForward onSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger onSearch() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		
		Map<String, String> mapParam = createHeaderParams(objForm);
		IGMExportDao objExportDao = (IGMExportDao) getDao(DAO_BEAN_EXPORT);
		Map<Object, Object> mapReturn = new HashMap<Object, Object>();
		mapReturn = objExportDao.getIGMData(mapParam);
		List<ImportGeneralManifestExportMod> result = (List<ImportGeneralManifestExportMod>) mapReturn.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);

		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", getUniqueRecords(result));
		jsonObj.write(response.getWriter());
		return null;

	}

	/**
	 * Creates the header params.
	 *
	 * @param aobjForm the aobj form
	 * @return the map
	 */
	private Map<String, String> createHeaderParams(ImportGeneralManifestUim aobjForm) {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, aobjForm.getBl());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, aobjForm.getPod());
		if(aobjForm.getService() != null && !aobjForm.getService().equals("")) {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getService());
		}else {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getIgmservice());
		}
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, aobjForm.getVessel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, aobjForm.getVoyage());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD_TERMINAL, aobjForm.getPodTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_FROM_DATE, aobjForm.getBlCreationDateFrom());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_TO_DATE, aobjForm.getBlCreationDateTo());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL_STATUS, aobjForm.getInStatus());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL, aobjForm.getPol());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL_TERMINAL, aobjForm.getPolTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DIRECTION, aobjForm.getDirection());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEPOT, aobjForm.getDepot());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEL, aobjForm.getDel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_COUNTRYCODE, aobjForm.getConsigneeCountryCode());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_STATE, aobjForm.getConsigneeState());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONTAINER_NUM, aobjForm.getContainer());
		
		
		
		return mapParam;
	}

	private Map<String, String> createHeaderParamsJson(ImportGeneralManifestMod aobjForm) {
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, aobjForm.getBl());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, aobjForm.getPod());
		if(aobjForm.getService()!=null && !aobjForm.getService().equals("")) {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getService());
		}else {
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, aobjForm.getService());
		}
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, aobjForm.getVessel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, aobjForm.getVoyage());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD_TERMINAL, aobjForm.getPodTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_FROM_DATE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_TO_DATE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL_STATUS, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL, aobjForm.getPol());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL_TERMINAL, aobjForm.getPolTerminal());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DIRECTION, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEPOT, aobjForm.getDepot());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_DEL, aobjForm.getDel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_COUNTRYCODE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONSIGNEE_STATE, "");
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_CONTAINER_NUM, "");
		return mapParam;
	}
	
	/**
	 * Gets the unique records.
	 *
	 * @param list the list
	 * @return the unique records
	 */
	public List<ImportGeneralManifestExportMod> getUniqueRecords(List<ImportGeneralManifestExportMod> list) {
		Set<ImportGeneralManifestExportMod> uniqueRecords = new LinkedHashSet<>(list);
		list = new ArrayList<>(uniqueRecords);
		return list;
	}

	

	@SuppressWarnings("unchecked")
	private ActionForward vesselVoyagSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("#IGMLogger vesselVoyagSave() is called..");
		IGMVesselVoyageSaveDao 		   vesselVoyageDao  	= (IGMVesselVoyageSaveDao) getDao(DAO_BEAN_VESSEL_VOYOGE_ID);
		
		ImportGeneralManifestUim 	   objForm 		    	= (ImportGeneralManifestUim) form;
		
		String 						   data 				= objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper 				   mapper 				= new ObjectMapper();
//		ImportGeneralManifestInput     saveParam 			= mapper.readValue(data, ImportGeneralManifestInput.class);
		ImportGeneralManifestMod       service 				= mapper.readValue(data, ImportGeneralManifestMod.class);
		
		vesselVoyageDao.saveVesselVoyageData(service,IGMVesselVoyageSaveDao.RCL_IGM_SAVE_VESSEL_VOYOAGE);
	
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private ActionForward blDataSave(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("#IGMLogger blDataSave() is called..");
		IGMVesselVoyageSaveDao 		   vesselVoyageDao  	= (IGMVesselVoyageSaveDao) getDao(DAO_BEAN_VESSEL_VOYOGE_ID);
		IGMDaoNew 					   objDao 				= (IGMDaoNew) getDao(DAO_BEAN_ID);
		IGMBLDataDao 				   objBlDao 			= (IGMBLDataDao) getDao(DAO_BEAN_BL_ID);
		IGMConsigneeDataDao 		   objConsigneeDao  	= (IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMContainerDaoImpl 		   containerDao 		= (IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		IGMConsignerDataDao 		   objConsignerDao  	= (IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMNodifyPartyDao 			   objNodifyDao 		= (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao 			   objMarksDescDao  	= (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao     objPreviousDao   	= (IGMPreviousDeclarationDaoImpl) getDao(DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod> blsForSavingCont 	= new ArrayList<>();
		ImportGeneralManifestUim 	   objForm 		    	= (ImportGeneralManifestUim) form;
		
		String 						   data 				= objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper 				   mapper 				= new ObjectMapper();
		ImportGeneralManifestInput     saveParam 			= mapper.readValue(data, ImportGeneralManifestInput.class);

		List<ImportGeneralManifestMod> deleteBL 			=  new ArrayList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> insertBL 			=  new ArrayList<ImportGeneralManifestMod>();
		List<ImportGeneralManifestMod> insertBLFetch 		=  new ArrayList<ImportGeneralManifestMod>();
		
		
		List<Consignee> 			   consignee 			= new ArrayList<>();
		List<Consigner> 			   consigner 			= new ArrayList<>();
		List<NotifyParty> 			   notifyParty 			= new ArrayList<>();
		List<MarksNumber> 			   marksNumber 			= new ArrayList<>();
		List<ContainerDetails> 		   containerDetailes    = new ArrayList<>();
		List<PreviousDeclaration> 	   previousDeclarations = new ArrayList<>();
		
		ImportGeneralManifestMod 		service 			= saveParam.getService();
		List<ImportGeneralManifestMod>  blList 				= saveParam.getBls();
		int 							fromItemNoTemp  	= 0;
		int 							i 					= 0;
										fromItemNoTemp  	= Integer.valueOf(service.getFromItemNo());
		int 							maxIteamNo			= 0;
		//vesselVoyageDao.saveVesselVoyageData(service,IGMVesselVoyageSaveDao.RCL_IGM_SAVE_VESSEL_VOYOAGE);
		/*
		 * maxIteamNo = Integer.parseInt(service.getFromItemNo()); int fstBL = 0; for
		 * (ImportGeneralManifestMod blObj : blList) { if (blObj != null &&
		 * "true".equalsIgnoreCase(blObj.getIsBlSave())) { if (blObj.getItemNumber() ==
		 * null || blObj.getItemNumber().equals("")) { blObj.setItemNumber(maxIteamNo+
		 * ""); System.out.println("ItemNum : " + blObj.getItemNumber() + " BL NO. " +
		 * blObj.getBl()); blsForSavingCont.add(blObj); maxIteamNo++; } else { if
		 * (Integer.parseInt(blObj.getItemNumber()) > maxIteamNo || maxIteamNo ==
		 * Integer.valueOf(service.getFromItemNo())) { if(fstBL==0) {
		 * blObj.setItemNumber(maxIteamNo+ ""); }else { maxIteamNo =
		 * Integer.parseInt(blObj.getItemNumber()); }
		 * 
		 * blsForSavingCont.add(blObj); maxIteamNo++; }else {
		 * blObj.setItemNumber(maxIteamNo+ ""); maxIteamNo++;
		 * blsForSavingCont.add(blObj); } } fstBL++; } else if (blObj != null &&
		 * !("").equals(blObj.getItemNumber()) && blObj.getItemNumber()!=null) { if
		 * (Integer.parseInt(blObj.getItemNumber()) >= maxIteamNo) { maxIteamNo =
		 * Integer.parseInt(blObj.getItemNumber()); blsForSavingCont.add(blObj); } } }
		 */
		System.out.println(">>>"+blsForSavingCont.toString());
		getSaveDataList(blList,deleteBL,insertBL,insertBLFetch);

		String blsConInput = null;
		consignee.clear();
		consigner.clear(); 
		notifyParty.clear(); 
		marksNumber.clear(); 
		containerDetailes.clear();  
		previousDeclarations.clear();
		for (ImportGeneralManifestMod mod : blsForSavingCont) {

			if (blsConInput == null)
				blsConInput = "'" + mod.getBl() + "'";
			else
				blsConInput += ",'" + mod.getBl() + "'";

		}
		 
		objBlDao.deleteBLData(deleteBL,IGMBLDataDao.RCL_IGM_DELETE_BL);
		containerDao.deleteContainer(containerDetailes, blsConInput,IGMContainerDao.RCL_IGM_DELETE_CONTAINOR);
		objConsigneeDao.deleteConsigneeData(consignee, blsConInput,IGMConsigneeDataDao.RCL_IGM_DELETE_CONSIGNEE);
		objConsignerDao.deleteConsignerData(consigner, blsConInput,IGMConsignerDataDao.RCL_IGM_DELETE_CONSIGNER);
		objNodifyDao.deleteNodifyData(notifyParty, blsConInput,IGMNodifyPartyDao.RCL_IGM_DELETE_NODIFY_PARTY_DESCRIPTION);
		objMarksDescDao.deleteMarkDescData(marksNumber, blsConInput,IGMMarksAndDescDao.RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION);
		objPreviousDao.deletePreviousDeclData(previousDeclarations,IGMPPreviousDeclarationDao.RCL_IGM_DELETE_PREV_DECLARATION, blsConInput);
		
		String blsInput = null;
		for (ImportGeneralManifestMod mod : insertBLFetch) {

			if (blsInput == null)
				blsInput = "'" + mod.getBl() + "'";
			else
				blsInput += ",'" + mod.getBl() + "'";

			if (!CollectionUtils.isEmpty(mod.getConsignee())) {
				consignee.addAll(mod.getConsignee());
			}
			if (!CollectionUtils.isEmpty(mod.getConsigner())) {
				consigner.addAll(mod.getConsigner());
			}
			if (!CollectionUtils.isEmpty(mod.getNotifyParty())) {
				notifyParty.addAll(mod.getNotifyParty());
			}
			if (!CollectionUtils.isEmpty(mod.getMarksNumber())) {
				marksNumber.addAll(mod.getMarksNumber());
			}
			if (!CollectionUtils.isEmpty(mod.getContainerDetailes())) {
				containerDetailes.addAll(mod.getContainerDetailes());
			}
			if (!CollectionUtils.isEmpty(mod.getPreviousDeclaration())) {
				previousDeclarations.addAll(mod.getPreviousDeclaration());
			}
		}
		System.out.println(blsInput);
		objBlDao.saveBLData(insertBLFetch,IGMBLDataDao.RCL_IGM_SAVE_BL);
		containerDao.saveContainer(containerDetailes, blsInput,IGMContainerDao.RCL_IGM_SAVE_CONTAINOR);
		objConsigneeDao.saveConsigneeData(consignee, blsInput,IGMConsigneeDataDao.RCL_IGM_SAVE_CONSIGNEE);
		objConsignerDao.saveConsignerData(consigner, blsInput,IGMConsignerDataDao.RCL_IGM_SAVE_CONSIGNER);
		objNodifyDao.saveNodifyData(notifyParty, blsInput,IGMNodifyPartyDao.RCL_IGM_SAVE_NODIFY_PARTY_DESCRIPTION);
		objMarksDescDao.saveMarkDescData(marksNumber, blsInput,IGMMarksAndDescDao.RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION);
		objPreviousDao.savePreviousDeclData(previousDeclarations,IGMPPreviousDeclarationDao.RCL_IGM_SAVE_PREV_DECLARATION, blsInput);
		
		
		String blsInputFetch = null;
		Map<String, String> mapParam = new HashMap<>();
		for (ImportGeneralManifestMod mod : insertBL) {

			if (blsInput == null)
				blsInput = "'" + mod.getBl() + "'";
			else
				blsInput += ",'" + mod.getBl() + "'";

			if (!CollectionUtils.isEmpty(mod.getConsignee())) {
				consignee.addAll(mod.getConsignee());
			}
			if (!CollectionUtils.isEmpty(mod.getConsigner())) {
				consigner.addAll(mod.getConsigner());
			}
			if (!CollectionUtils.isEmpty(mod.getNotifyParty())) {
				notifyParty.addAll(mod.getNotifyParty());
			}
			if (!CollectionUtils.isEmpty(mod.getMarksNumber())) {
				marksNumber.addAll(mod.getMarksNumber());
			}
			if (!CollectionUtils.isEmpty(mod.getContainerDetailes())) {
				containerDetailes.addAll(mod.getContainerDetailes());
			}
			if (!CollectionUtils.isEmpty(mod.getPreviousDeclaration())) {
				previousDeclarations.addAll(mod.getPreviousDeclaration());
			}
		}
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
		mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
		System.out.println(blsInput);
		objBlDao.saveUnfetchedBlData(blsInput,IGMBLDataDao.RCL_IGM_UNFETCHED_SAVE_BL,mapParam,insertBL);
		containerDao.saveUnfetchedContainer(blsInput,IGMContainerDao.RCL_IGM_SAVE_UNFETCHED_CONTAINOR);
		objConsigneeDao.saveUnfetchedConsigneeData(blsInput,IGMConsigneeDataDao.RCL_IGM_SAVE_UNFETCHED_CONSIGNEE);
		objConsignerDao.saveUnfetchedConsignerData(blsInput,IGMConsignerDataDao.RCL_IGM_SAVE_UNFETCHED_CONSIGNER);
		objNodifyDao.saveUnfetchedNodifyData(blsInput,IGMNodifyPartyDao.RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DESCRIPTION);
		objMarksDescDao.saveUnfetchedMarkDescData(blsInput,IGMMarksAndDescDao.RCL_IGM_SAVE_UNFETCHED_MARKS_NUMBER_DESCRIPTION);
		objPreviousDao.saveUnfetchedPreviousDeclData(IGMPPreviousDeclarationDao.RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION, blsInput);
		
		
		/*
		 * net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject(); jsonObj = new
		 * net.sf.json.JSONObject(); jsonObj.put("resultSave", insertBL);
		 * jsonObj.put("resultDelete", deleteBL); jsonObj.write(response.getWriter());
		 */
		
		return null;
		 
	}

	private ActionForward getSelectAllOption(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 
		System.out.println("IGMNewScreenSvc getSelectAllOption()... [STARTED..]");	
		
		ImportGeneralManifestUim 	    objForm 		    	= (ImportGeneralManifestUim) form;  ;
		IGMDaoNew 				 		objDao 				=   (IGMDaoNew) getDao(DAO_BEAN_ID);
		IGMConsignerDataDao 			objConsignerDao 	= 	(IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMConsigneeDataDao 			objConsigneeDao 	= 	(IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMNodifyPartyDao 				objNotifyPartyDao   =   (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao 				objMarksDescDao 	=   (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao 		objPreviousDao 		=   (IGMPreviousDeclarationDaoImpl) getDao(DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod>  listOfBL 			= 	new ArrayList<ImportGeneralManifestMod>(); 
		ImportGeneralManifestMod 		manifestMod 		= 	new ImportGeneralManifestMod();
		IGMContainerDaoImpl 			containerDao 		= 	(IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		List<ContainerDetails>  		containerList 		=	null;
		List<ImportGeneralManifestMod>  blObj 				=   new LinkedList<ImportGeneralManifestMod>();
		
		
		
		
		if (objForm.getSavedBlList() != null && !objForm.getSavedBlList().equals("")) {
			Map<String, String> 		mapParam	 = 	new HashMap<>();
			String 						blsInput 	 =  null;
			Map<Object, Object> 		mapSaveBL 	 = 	null;
			String 						blNos[] 	 =  objForm.getSavedBlList().split(",");
			
			int savedBlCount = 0;
			for (String bl : blNos) {
				savedBlCount++;
				if (blsInput == null)
					blsInput = "'" + bl + "'";
				else
					blsInput += ",'" + bl + "'";
				
			}
			
			if(savedBlCount>999) {
				int modularOfBlCount1 = savedBlCount/999;
				System.out.println(modularOfBlCount1);
				String[] blsInputArray = blsInput.split(",");
				for(int i = 0;i<=modularOfBlCount1;i++ ) {	
					blsInput = "";
					for(int j = i*900;j<(i*i+1)*900;j++ ) {
						if (StringUtils.isEmpty(blsInput))
							blsInput =   blsInputArray[j] ;
						else
							blsInput += "," + blsInputArray[j];
					}
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, objForm.getPod());
			if(objForm.getIgmservice()!=null && !objForm.getIgmservice().equals("")) {
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
			}else {
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getService());
			}
			List<ImportGeneralManifestMod>  blObjTmp 	=   new LinkedList<ImportGeneralManifestMod>();
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
			
			mapSaveBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_EXPORT_NEW, true,false,savedBlCount);
			blObj.addAll((List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
			containerDao.setContainerDetails(blObjTmp, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR_EXPORT);
			objConsignerDao.setConsignerData(blObjTmp, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER_EXPORT);
			objConsigneeDao.setConsigneeData(blObjTmp, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE_EXPORT);
			objNotifyPartyDao.setNotifyPartyData(blObjTmp, IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_EXPORT);
			objMarksDescDao.setMarksDescriptionData(blObjTmp, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION_EXPORT);
			objPreviousDao.setPreviousDeclData(blObjTmp, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION_EXPORT);
			blObj.addAll(blObjTmp);
				}
			}else {
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
				
				mapSaveBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_EXPORT_NEW, true,false,savedBlCount);
				blObj.addAll((List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
				containerDao.setContainerDetails(blObj, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR_EXPORT);
				objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER_EXPORT);
				objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE_EXPORT);
				objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_EXPORT);
				objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION_EXPORT);
				objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION_EXPORT);	
			}
		}
		if (objForm.getUnSavedBlList() != null && !objForm.getUnSavedBlList().equals("")) {
			Map<String, String> mapParam = new HashMap<>();
			String blsInput = null;
			Map<Object, Object> mapReturnBL = null;
			String blNos[] = objForm.getUnSavedBlList().split(",");
			 int blUnsavedCount = 0;
			for (String bl : blNos) {
				blUnsavedCount++;
				if (blsInput == null)
					blsInput = "'" + bl + "'";
				else
					blsInput += ",'" + bl + "'";
			}
			
			if(blUnsavedCount>999) {
				int modularOfBlCount = blUnsavedCount/999;
				System.out.println(modularOfBlCount);
				String[] blsInputArr = blsInput.split(",");
				for(int i = 0;i<=modularOfBlCount;i++ ) {	
					blsInput = "";
					for(int j = i*900;j<(i*i+1)*900;j++ ) {
						if (StringUtils.isEmpty(blsInput))
							blsInput =   blsInputArr[j] ;
						else
							blsInput += "," + blsInputArr[j];
					}
			List<ImportGeneralManifestMod>  blObjTmp 	=   new LinkedList<ImportGeneralManifestMod>();
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL, objForm.getPol());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
			
			mapReturnBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA_EXPORT_NEW, true,true,blUnsavedCount);
			blObj.addAll((List<ImportGeneralManifestMod>) mapReturnBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
			containerDao.setContainerDetails(blObjTmp, IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR_EXPORT);
			objConsignerDao.setConsignerData(blObjTmp, IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER_EXPORT);
			objConsigneeDao.setConsigneeData(blObjTmp, IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE_EXPORT);
			objNotifyPartyDao.setNotifyPartyData(blObjTmp, IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION_EXPORT);
			objMarksDescDao.setMarksDescriptionData(blObjTmp, IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION_EXPORT);
			objPreviousDao.setPreviousDeclData(blObjTmp, IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION_EXPORT);
			blObj.addAll(blObjTmp);
			}
		}else {

			mapParam.put(ImportGeneralManifestDao.KEY_IGM_POL, objForm.getPol());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, objForm.getIgmservice());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, objForm.getVessel());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, objForm.getVoyage());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
			
			mapReturnBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA_EXPORT_NEW, true,true,blUnsavedCount);
			blObj.addAll((List<ImportGeneralManifestMod>) mapReturnBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
			containerDao.setContainerDetails(blObj, IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR_EXPORT);
			objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER_EXPORT);
			objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE_EXPORT);
			objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION_EXPORT);
			objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION_EXPORT);
			objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION_EXPORT);	
			
		}
	}
		
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("blDetails", blObj);
		jsonObj.write(response.getWriter());
		System.out.println("getContainerDetails() End.");
		
		return null;
	}
	
	/**
	 * On search.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private ActionForward getVesselVoyagData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		System.out.println("#IGMLogger getVesselVoyagData() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		
		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		objForm = mapper.readValue(data, ImportGeneralManifestUim.class);

		Map<String, String> mapParam = createHeaderParams(objForm);

		//get data form our local DB tabel
		Map<Object, Object> mapReturn = objDao.getVesselVoyagData(mapParam ,IGMExportDao.SQL_GET_SAVED_IGM_VESSLE_VOYAGE_DATA);
		List<ImportGeneralManifestMod> resultVesel = (List<ImportGeneralManifestMod>) mapReturn.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		
		if( resultVesel.size() == 0) {
		 mapReturn = objDao.getVesselVoyagData(mapParam, IGMExportDao.SQL_GET_IGM_VESSLE_VOYAGE_DATA);
		 resultVesel = (List<ImportGeneralManifestMod>) mapReturn.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		}
		
		/* BL Start.. */
		 Map<Object, Object> mapReturnBL = objDao.getBLDataNew(mapParam, IGMExportDao.SQL_GET_IGM_BL_MASTER_SAVE_DATA_EXPORT, false);
		List<ImportGeneralManifestMod> resultBL = (List<ImportGeneralManifestMod>) mapReturnBL	.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
		 /* BL End.. */
		
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		 
		List<ImportGeneralManifestResultSet> finalResult =  getFinalData(resultVesel.get(0),1,resultBL);
       
		List<IGMCrewEfctMod>  crewEfctMod = new ArrayList();
        List<IGMPersonOnBoardMod>  personOnBoardMod = new ArrayList();
        List<IGMShipStoresMod>  shipStoresMod = new ArrayList();
         
		
      	jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", finalResult);  
		jsonObj.put("DropDown", objDao.getroadCarrCodeData(mapParam));
		jsonObj.put("personOnBoardMod", personOnBoardMod); 
		jsonObj.put("crewEfctMod", crewEfctMod);
		jsonObj.put("shipStoresMod", shipStoresMod);
		jsonObj.write(response.getWriter());
		return null;
	}

	private ActionForward getPersonOnboard(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getPersonOnboard() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map<String, String> mapParam = createHeaderParams(objForm);
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		
		List<IGMPersonOnBoardMod>  personOnBoardMod = new ArrayList();
        personOnBoardMod=objPersonDao.getPersonOnBoard(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD);
        net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
        jsonObj.put("personOnBoardMod", personOnBoardMod); 
        jsonObj.write(response.getWriter());
        System.out.println("getPersonOnboard() end.");
		return null;
	}
	private ActionForward getShipStor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getShipStor() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map<String, String> mapParam = createHeaderParams(objForm);
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		List<IGMShipStoresMod>  shipStoresMod = new ArrayList();
        shipStoresMod=objPersonDao.getShipStore(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_SHIP_STORE);
        net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
        jsonObj.put("shipStoresMod", shipStoresMod);
        jsonObj.write(response.getWriter());
        System.out.println("getShipStor() End.");
		return null;
	}

	private ActionForward getCrewEffect(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getCrewEffect() Start.");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		Map<String, String> mapParam = createHeaderParams(objForm);
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON); 
		List<IGMCrewEfctMod>  crewEfctMod = new ArrayList();
        crewEfctMod=objPersonDao.getCrewEffect(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_CREW_EFFECT);
        net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
        jsonObj.put("crewEfctMod", crewEfctMod);
        jsonObj.write(response.getWriter());
        System.out.println("getCrewEffect() End.");
		return null;
	}
	private List<ImportGeneralManifestResultSet> getFinalData(ImportGeneralManifestMod vesselObj,int sqn,
			List<ImportGeneralManifestMod> uniqueRecords) {
		    List<ImportGeneralManifestResultSet> finalResult = new ArrayList<>();
		 
			List<ImportGeneralManifestMod> bls = new ArrayList<ImportGeneralManifestMod>();
			
			List<ImportGeneralManifestMod> withOutDischargeUniqueBlList = new ArrayList<ImportGeneralManifestMod>();
			uniqueRecords = uniqueRecords.stream().filter(o->o.getBlDischargedStatus() == null || o.getBlDischargedStatus().trim().equals("")
					|| !o.getBlDischargedStatus().trim().equals("BL DISCHARGED")).collect(Collectors.toList()); 
			 
			// first cargomovement to be sorted as per LC and TI
			List<ImportGeneralManifestMod> listWithItemNumbers = uniqueRecords.stream()
					.filter(o -> o.getItemNumber() != null && !o.getItemNumber().trim().equals(""))
					.collect(Collectors.toList());
			listWithItemNumbers.sort((o1, o2) -> {
				return Integer.valueOf(o1.getItemNumber()).compareTo(Integer.valueOf(o2.getItemNumber()));
			});
			List<ImportGeneralManifestMod> listWithCargoMovement = uniqueRecords.stream()
					.filter(o -> o.getItemNumber() == null || o.getItemNumber().trim().equals(""))
					.collect(Collectors.toList());
			listWithCargoMovement.sort((c1, c2) -> {
				return c1.getCargoMovmnt().compareTo(c2.getCargoMovmnt());
			});
			// cargoMovmnt
			// Get BL#
			List<ImportGeneralManifestMod> listWithOutCargoMovementLC = listWithCargoMovement.stream()
					.filter(c -> c.getCargoMovmnt().trim().equals("LC")).collect(Collectors.toList());
			listWithOutCargoMovementLC.sort((c1, c2) -> {
				return c1.getBl().compareTo(c2.getBl());
			});
			System.out.println(listWithOutCargoMovementLC);
			List<ImportGeneralManifestMod> listWithOutCargoMovementTI = listWithCargoMovement.stream()
					.filter(c -> c.getCargoMovmnt().trim().equals("TI")).collect(Collectors.toList());
			listWithOutCargoMovementTI.sort((o1, o2) -> {
				return o1.getBl().compareTo(o2.getBl());
			});

			bls.clear();
			bls.addAll(listWithItemNumbers);
			bls.addAll(listWithOutCargoMovementLC);
			bls.addAll(listWithOutCargoMovementTI);

			finalResult.add(new ImportGeneralManifestResultSet(vesselObj, sqn, bls));
		
		return finalResult;
	}
	private void getSaveDataList(List<ImportGeneralManifestMod> blList,List<ImportGeneralManifestMod> deleteBL,
			List<ImportGeneralManifestMod> insertBL, List<ImportGeneralManifestMod> insertBLFtch){
		     
		System.out.println("getSaveDataList() Start."); 
		
		List<ImportGeneralManifestMod> bl = new ArrayList<ImportGeneralManifestMod>();
		
			bl = blList.stream()
					.filter(c -> c.getSaveFlags().trim().equals("D")).collect(Collectors.toList());
			deleteBL.addAll(bl);
			bl.clear(); 
			bl = blList.stream()
					.filter(c -> c.getSaveFlags().trim().equals("I") || c.getSaveFlags().trim().equals("U")).collect(Collectors.toList());
			insertBL.addAll(bl);
			bl.clear(); 
			//updateBL = blList.stream()
					//.filter(c -> c.getCargoMovmnt().trim().equals("U")).collect(Collectors.toList());
			System.out.println("getSaveDataList() end."); 
			 
	}
	private ActionForward getContainerDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 System.out.println("getContainerDetails() Start.");
		 ImportGeneralManifestUim 			objForm 		= 		(ImportGeneralManifestUim) form;
		 IGMContainerDaoImpl 				containerDao 	= 		(IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		 List<ImportGeneralManifestMod> 	listOfBL 		= 		new ArrayList<ImportGeneralManifestMod>(); 
		 ImportGeneralManifestMod 			manifestMod 	= 		new ImportGeneralManifestMod();
		 List<ContainerDetails>  			containerList 	=		null;
		 manifestMod.setBl(objForm.getBl());
		 listOfBL.add(manifestMod);
		 if(objForm.getIsBlSave().equals("false") || objForm.getItemNumber() == null || objForm.getItemNumber().equals("")) {
			 containerList = containerDao.setContainerDetailsNew(listOfBL, IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR_EXPORT);
		 }else {
			 containerList = containerDao.setContainerDetailsNew(listOfBL, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR_EXPORT);
		 }
		 net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
	     jsonObj.put("containerList", containerList);
	     jsonObj.write(response.getWriter());
	     System.out.println("getContainerDetails() End.");
		 return null;
	}

	private ActionForward getCarogoDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("getCarogoDetails() Called.");
		
		IGMDaoNew 				 		objDao 				=   (IGMDaoNew) getDao(DAO_BEAN_ID);
		ImportGeneralManifestUim 		objForm 			= 	(ImportGeneralManifestUim) form;
		IGMConsignerDataDao 			objConsignerDao 	= 	(IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMConsigneeDataDao 			objConsigneeDao 	= 	(IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMNodifyPartyDao 				objNotifyPartyDao   =   (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao 				objMarksDescDao 	=   (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao 		objPreviousDao 		=   (IGMPreviousDeclarationDaoImpl) getDao(DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod>  listOfBL 			= 	new ArrayList<ImportGeneralManifestMod>(); 
		Map<String, String> 			mapParam 			=   createHeaderParams(objForm);
		List<ImportGeneralManifestMod>  blObj 				=   null;
		
		if(objForm.getIsBlSave().equals("false") || objForm.getItemNumber() == null || objForm.getItemNumber().equals("")) {
			Map<Object, Object> mapReturnBL = objDao.getBLCarogoDetails(mapParam, IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA_EXPORT);
			blObj = (List<ImportGeneralManifestMod>) mapReturnBL	.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
			objConsignerDao.setConsignerData(blObj,IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER_EXPORT);
			objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE_EXPORT);
			objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION_EXPORT);
			objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION_EXPORT);
			objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION_EXPORT);
		}else {		
			Map<Object, Object> mapSaveBL = objDao.getBLCarogoDetails(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_EXPORT);
			blObj = (List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA);
			objConsignerDao.setConsignerData(blObj,IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER_EXPORT);
			objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE_EXPORT);
			objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_EXPORT);
			objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION_EXPORT);
			objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION_EXPORT);
		}		
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
	    jsonObj.put("blDetails", blObj);
	    jsonObj.write(response.getWriter());
		return null;
	}
	/**
	 * On save file.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */

	private ActionForward downloadJson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		 System.out.println("#IGMLogger downloadJson() started............");
		 IGMDaoNew 				 		objDao 				=   (IGMDaoNew) getDao(DAO_BEAN_ID);
		 ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		 String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		 String dataPersonOnBord = objForm.getPersonOnBoardMod();
		 String dataCrewEfctMod = objForm.getCrewEfctMod();
		 String dataShipStoresMod = objForm.getShipStoresMod();
		 
		 ObjectMapper mapper = new ObjectMapper();

		 ImportGeneralManifestInput saveParam = mapper.readValue(data, ImportGeneralManifestInput.class);
		 ImportGeneralManifestMod service = saveParam.getService();

		 List<ImportGeneralManifestMod> blList = saveParam.getBls();
		 Map<String, String> mapParam = createHeaderParamsJson(service);
		 mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD,service.getPol());
		 List<IGMPersonOnBoardMod> personOnBoardMod = mapper.readValue(dataPersonOnBord,
					new TypeReference<List<IGMPersonOnBoardMod>>() {
					});
			if(personOnBoardMod.isEmpty()) {
				
				PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		        personOnBoardMod=objPersonDao.getPersonOnBoard(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD);
			}
			
			List<IGMCrewEfctMod> crewEfctMod = mapper.readValue(dataCrewEfctMod, new TypeReference<List<IGMCrewEfctMod>>() {
			});
			
			if(crewEfctMod.isEmpty()) {
				PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON); 
		        crewEfctMod=objPersonDao.getCrewEffect(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_CREW_EFFECT);
			}
			
			List<IGMShipStoresMod> shipStoresMod = mapper.readValue(dataShipStoresMod,
					new TypeReference<List<IGMShipStoresMod>>() {
					});
			if(shipStoresMod.isEmpty()) {
				PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		        shipStoresMod=objPersonDao.getShipStore(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_SHIP_STORE);
			}

		 
		 List<ImportGeneralManifestMod> blListNew = new ArrayList<ImportGeneralManifestMod>();
		 
		 	for(int l=0;l<blList.size();l++) {
			ImportGeneralManifestMod obj = blList.get(l);
			if(obj.isFetch() == true) {
				blListNew.add(obj);
			} 
		 }
		 blListNew.addAll(getBlDetails(service,objForm));
		 
		 System.out.println("Object Done..... 0");
		 int getSeqNo = objDao.getSeqNoJdbc(service,"EGM",objForm.getFileType());

			 Object manifestFile = CreatingJSON.getJsonFile(blListNew, objForm.getFileType(), service, personOnBoardMod,
					crewEfctMod, shipStoresMod, getSeqNo);
			 
				objDao.updateSqnNoForJsonFile(service, getSeqNo, "EGM",objForm.getFileType());
			 
				try{	
				net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			    jsonObj.put("jsonFile",manifestFile );
			    jsonObj.write(response.getWriter());
			    
			    System.out.println("#IGMLogger downloadJson() completed..");
			}catch (Exception e) {
					e.printStackTrace();
			}finally {
				Runtime. getRuntime(). gc();
			}
				return null;
			      
	}
	/**
	 * onExcelUpload.
	 *
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 * @throws Exception
	 */
	private ActionForward onCsvUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("onCsvUpload() calling....");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		InputStream inputStream = objForm.getFileExl().getInputStream();
		List list = new ArrayList();
			
		String flage = null;
		if(objForm.getCheckCSV() !=null && objForm.getCheckCSV().equals("P")) {
			flage = "P";
			list =	new ArrayList<IGMPersonOnBoardMod>();
		}                    
		else if(objForm.getCheckCSV() !=null && objForm.getCheckCSV().equals("C")) {
			flage = "C";
			list =	new ArrayList<IGMCrewEfctMod>();
		}
		else {
			flage = "S";
			list =	new ArrayList<IGMShipStoresMod>();
		}
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader
		      (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
		    	String str = null;
			    int i = 0;
				while ((str = reader.readLine()) != null) {
					if(i != 0) {
						createList(str ,objForm.getBl(),objForm.getVessel(),objForm.getVoyage(),objForm.getPol(),flage,list);
					}
					i++;
				}
		}
		
		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		if(flage.equals("P"))
		    objPersonDao.savePersonOnBoard(list, objForm);
		else if(flage.equals("S"))
			objPersonDao.saveShipStore(list, objForm);
		else
			objPersonDao.saveCrewEfect(list, objForm);
		
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", list);
		jsonObj.write(response.getWriter());
	//	System.out.println("#IGMLogger EXCEL JSON obj: " + jsonObj.write(new StringWriter()));
		return null;
	}
	
	
	/**
	 * On upload shipping file excel.
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 *
	 */
	
	private ActionForward uploadShipping(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException, IOException {

		System.out.println("Shipping Bill upload() calling....");
		List list = new ArrayList();
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		InputStream inputStream = objForm.getShippingFile().getInputStream();
		
		  XSSFWorkbook workbook = new XSSFWorkbook(inputStream 	);

	        Sheet sheet = workbook.getSheetAt(0);
	        JSONArray rows = new JSONArray();
	        
	        for (Row row : sheet) {
	            JSONObject obj = new JSONObject();
	            for (Cell cell : row) {
	                obj.put(cell.getColumnIndex(), cell.getStringCellValue());
	            }
	            list.add(obj);
	    		
	}  
	        net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
    		jsonObj = new net.sf.json.JSONObject();
    		jsonObj.put("result", list);
    		jsonObj.write(response.getWriter());
    	//	System.out.println("#IGMLogger EXCEL JSON obj: " + jsonObj.write(new StringWriter()));
    		return null;
	      
	        
	}

	private void createShippingList(String str, String bl, String vessel, String voyage, String pod, List<ImportGeneralManifestMod> list) {
		
		String[] words = str.split(",");
		ImportGeneralManifestMod 		manifestMod 		= 	new ImportGeneralManifestMod();
		manifestMod.setBl(words[1]);
		manifestMod.setCin_type(words[2]);
		manifestMod.setPcin(words[3]);
		list.add(manifestMod);
	}




	/**
	 * On Delete csv file.
	 *
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private ActionForward deleteCsvData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger deleteCsvData() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
		String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
		ImportGeneralManifestUim saveParam = mapper.readValue(data, ImportGeneralManifestUim.class);

		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		objPersonDao.deleteCsv(saveParam);
		net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
		jsonObj.put("result", "delete");
		jsonObj.write(response.getWriter());
		return null;

	}  
	
	@SuppressWarnings("unchecked")
	private ActionForward downloadCSV(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("#IGMLogger deleteCsvData() started..");
		ImportGeneralManifestUim objForm = (ImportGeneralManifestUim) form;
	//	String data = objForm.getRequestParam().replace("\"BLS\"", "\"bls\"");
		ObjectMapper mapper = new ObjectMapper();
	//	ImportGeneralManifestUim saveParam = mapper.readValue(data, ImportGeneralManifestUim.class);

		PersonOnBoardDao objPersonDao = (PersonOnBoardDao) getDao(DAO_BEAN_PERSON);
		Map<String, String> mapParam = createHeaderParams(objForm);
	    List<IGMPersonOnBoardMod>  personOnBoardMod = new ArrayList();
	    List<IGMCrewEfctMod>  crewEfctMod = new ArrayList();
	    List<IGMShipStoresMod>  shipStoresMod = new ArrayList();
	    net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		jsonObj = new net.sf.json.JSONObject();
	   	
	    if(objForm.getCheckCSV() !=null && objForm.getCheckCSV().equals("P")) {
			 personOnBoardMod=objPersonDao.getPersonOnBoard(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_PERSON_ON_BOARD);
			 jsonObj.put("result",personOnBoardMod);
		}                    
		else if(objForm.getCheckCSV() !=null && objForm.getCheckCSV().equals("C")) {
			 crewEfctMod=objPersonDao.getCrewEffect(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_CREW_EFFECT);
			 jsonObj.put("result", crewEfctMod);
		}
		else {
		    shipStoresMod=objPersonDao.getShipStore(mapParam ,PersonOnBoardDao.SQL_RCL_IGM_GET_SAVE_SHIP_STORE);
		    jsonObj.put("result", shipStoresMod);
		}
		
		
		jsonObj.write(response.getWriter());
		return null;

	}
	
	/*
	 * Use to map CSV file value to respective pojo class variables.
	 */
	public static void createList(String line ,String bl,String vessel,String voyage,String pod,String flage,List list) throws Exception {
		String[] words = line.split(",");
		
		if(flage.equals("P") && words.length == 22) {
				IGMPersonOnBoardMod personOnBoardMod = new IGMPersonOnBoardMod();
				personOnBoardMod.setBlNo(bl);
				personOnBoardMod.setVessel(vessel);
				personOnBoardMod.setVoyage(voyage);
				personOnBoardMod.setPod(pod);
				personOnBoardMod.setPrsnTypCdd(words[1]);
				personOnBoardMod.setPrsnFamilyName(words[2]);
				personOnBoardMod.setPrsnGivenName(words[3]);
				personOnBoardMod.setPrsnNatnltyCdd(words[4]);
				personOnBoardMod.setPsngrInTransitIndctr(words[5]);
				personOnBoardMod.setCrewmemberRankOrRating(words[6]);
				personOnBoardMod.setCrewmemberRankOrRatingCdd(words[7]);
				personOnBoardMod.setPsngrPrtOfEmbrktnCdd(words[8]);
				personOnBoardMod.setPsngrPrtOfEmbrktnName(words[9]);
				personOnBoardMod.setPsngrPrtOfDsmbrktnCdd(words[10]);
				personOnBoardMod.setPsngrPrtOfDsmbrktnName(words[11]);
				personOnBoardMod.setPrsnGenderCdd(words[12]);
				personOnBoardMod.setPrsnDtOfBirth(words[13]);
				personOnBoardMod.setPrsnPlaceOfBirthName(words[14]);
				personOnBoardMod.setPrsnCntryOfBirthCdd(words[15]);
				personOnBoardMod.setPrsnIdDocExpiryDt(words[16]);
				personOnBoardMod.setPrsnIdOrTravelDocIssuingNationCdd(words[17]);
				personOnBoardMod.setPrsnIdOrTravelDocNmbr(words[18]);
				personOnBoardMod.setPrsnIdOrTravelDocTypCdd(words[19]);
				list.add(personOnBoardMod);
		}
		if(flage.equals("C") && words.length == 7) {
			IGMCrewEfctMod crewEfctMod= 	new IGMCrewEfctMod();
			    crewEfctMod.setBlNo(bl);
			    crewEfctMod.setVessel(vessel);
			    crewEfctMod.setVoyage(voyage);
			    crewEfctMod.setPod(pod);
			    crewEfctMod.setPersonOnBoardSequenceNo(words[0]);
			    crewEfctMod.setSequenceNo(words[1]);
				crewEfctMod.setCrewEfctDescCdd(words[2]);
				crewEfctMod.setCrewEfctsDesc(words[3]);
				crewEfctMod.setCrewEfctQntyOnbrd(words[4]);
				crewEfctMod.setCrewEfctQntyCdOnbrd(words[5]);
				crewEfctMod.setCrewEfctsVsslSeqNmbr(words[6]);
				list.add(crewEfctMod);
		}
	       if(flage.equals("S") && words.length == 7) {
	    	   IGMShipStoresMod shipStoresMod = new IGMShipStoresMod();
	    	   shipStoresMod.setBlNo(bl);
	    	   shipStoresMod.setVessel(vessel);
	    	    shipStoresMod.setVoyage(voyage);
	    	    shipStoresMod.setPod(pod);
		   		shipStoresMod.setArticleNameCdd(words[1]);
		   		shipStoresMod.setArticleNameText(words[2]);
		   		shipStoresMod.setLocOnbrdText(words[3]);
		   		shipStoresMod.setQntyOnbrd(words[4]);
		   		shipStoresMod.setQntyCdOnbrd(words[5]);
		   		shipStoresMod.setVesselSrno(words[6]);
		   		list.add(shipStoresMod);
			}
	}
	
	private List<ImportGeneralManifestMod> getBlDetails(ImportGeneralManifestMod mod ,ImportGeneralManifestUim objForm) throws Exception {
		
		IGMDaoNew 				 		objDao 				=   (IGMDaoNew) getDao(DAO_BEAN_ID);
		IGMConsignerDataDao 			objConsignerDao 	= 	(IGMConsignerDataDao) getDao(DAO_BEAN_CONSIGNER_ID);
		IGMConsigneeDataDao 			objConsigneeDao 	= 	(IGMConsigneeDataDao) getDao(DAO_BEAN_CONSIGNEE_ID);
		IGMNodifyPartyDao 				objNotifyPartyDao   =   (IGMNodifyPartyDao) getDao(DAO_BEAN_NODIFY_ID);
		IGMMarksAndDescDao 				objMarksDescDao 	=   (IGMMarksAndDescDao) getDao(DAO_BEAN_MARK_AND_DESC_ID);
		IGMPPreviousDeclarationDao 		objPreviousDao 		=   (IGMPreviousDeclarationDaoImpl) getDao(DAO_BEAN_PREV_DECLARATION_DESC_ID);
		List<ImportGeneralManifestMod>  listOfBL 			= 	new ArrayList<ImportGeneralManifestMod>(); 
		ImportGeneralManifestMod 		manifestMod 		= 	new ImportGeneralManifestMod();
		IGMContainerDaoImpl 			containerDao 		= 	(IGMContainerDaoImpl) getDao(DAO_BEAN_CONTAINER_ID);
		List<ContainerDetails>  		containerList 		=	null;
		List<ImportGeneralManifestMod>  blObj 				=   new LinkedList<ImportGeneralManifestMod>();
		
		
		
		
		if (objForm.getSavedBlList() != null && !objForm.getSavedBlList().equals("")) {
			Map<String, String> 		mapParam	 = 	new HashMap<>();
			String 						blsInput 	 =  null;
			Map<Object, Object> 		mapSaveBL 	 = 	null;
			String 						blNos[] 	 =  objForm.getSavedBlList().split(",");
			int savedBlCount = 0;
			for (String bl : blNos) {
				savedBlCount++;
				if(bl.contains("'")) {
					if (blsInput == null ) {
						blsInput =   bl ;
					}else {
						blsInput += "," + bl + "";
					} 
				}else {
					if (blsInput == null)
						blsInput = "'" + bl + "'";
					else
						blsInput += ",'" + bl + "'";
				}
			}
			if(savedBlCount>999) {
				int modularOfBlCount = savedBlCount/999;
				System.out.println(modularOfBlCount);
				String[] blsInputArr = blsInput.split(",");
				for(int i = 0;i<=modularOfBlCount;i++ ) {	
					blsInput = "";
					for(int j = i*900;j<(i*i+1)*900;j++ ) {
						if (StringUtils.isEmpty(blsInput))
							blsInput =   blsInputArr[j] ;
						else
							blsInput += "," + blsInputArr[j];
					}

			List<ImportGeneralManifestMod>  blObjTmp 	=   new LinkedList<ImportGeneralManifestMod>();
		
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, mod.getPod());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, mod.getService());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, mod.getVessel());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, mod.getVoyage());
			mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
			
			mapSaveBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_EXPORT_NEW, true,false,savedBlCount);
			blObj.addAll((List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
			containerDao.setContainerDetails(blObjTmp, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR_EXPORT);
			objConsignerDao.setConsignerData(blObjTmp, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER_EXPORT);
			objConsigneeDao.setConsigneeData(blObjTmp, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE_EXPORT);
			objNotifyPartyDao.setNotifyPartyData(blObjTmp, IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
			objMarksDescDao.setMarksDescriptionData(blObjTmp, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION_EXPORT);
			objPreviousDao.setPreviousDeclData(blObjTmp, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION_EXPORT);
			blObj.addAll(blObjTmp);	
				}
			}else {
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, mod.getPod());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE, mod.getService());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, mod.getVessel());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, mod.getVoyage());
				mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
				
				mapSaveBL = objDao.getBLData(mapParam, IGMDaoNew.SQL_GET_IGM_BL_SAVE_DATA_EXPORT_NEW, true,false,savedBlCount);
				blObj.addAll((List<ImportGeneralManifestMod>) mapSaveBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
				containerDao.setContainerDetails(blObj, IGMContainerDao.RCL_IGM_GET_SAVE_CONTAINOR_EXPORT);
				objConsignerDao.setConsignerData(blObj, IGMConsignerDataDao.RCL_IGM_GET_SAVE_CONSIGNER_EXPORT);
				objConsigneeDao.setConsigneeData(blObj, IGMConsigneeDataDao.RCL_IGM_GET_SAVE_CONSIGNEE_EXPORT);
				objNotifyPartyDao.setNotifyPartyData(blObj, IGMNodifyPartyDao.RCL_IGM_GET_SAVE_NODIFY_PARTY_DESCRIPTION_IMPORT);
				objMarksDescDao.setMarksDescriptionData(blObj, IGMMarksAndDescDao.RCL_IGM_GET_SAVE_MARKS_DESCRIPTION_EXPORT);
				objPreviousDao.setPreviousDeclData(blObj, IGMPPreviousDeclarationDao.RCL_IGM_GET_SAVE_PREV_DECLARATION_EXPORT);
			}
		}

		/*
		 * if (objForm.getUnSavedBlList() != null &&
		 * !objForm.getUnSavedBlList().equals("")) { Map<String, String> mapParam = new
		 * HashMap<>(); String blsInput = objForm.getUnSavedBlList(); Map<Object,
		 * Object> mapReturnBL = null;
		 * 
		 * mapParam.put(ImportGeneralManifestDao.KEY_IGM_POD, mod.getPod());
		 * mapParam.put(ImportGeneralManifestDao.KEY_IGM_SERVICE,
		 * objForm.getIgmservice());
		 * mapParam.put(ImportGeneralManifestDao.KEY_IGM_VESSEL, mod.getVessel());
		 * mapParam.put(ImportGeneralManifestDao.KEY_IGM_VOYAGE, mod.getVoyage());
		 * mapParam.put(ImportGeneralManifestDao.KEY_IGM_BL, blsInput);
		 * 
		 * mapReturnBL = objDao.getBLData(mapParam,
		 * IGMDaoNew.SQL_GET_IGM_BL_MSTR_DATA_EXPORT_NEW, true,true);
		 * blObj.addAll((List<ImportGeneralManifestMod>)
		 * mapReturnBL.get(ImportGeneralManifestDao.KEY_REF_IGM_DATA));
		 * containerDao.setContainerDetails(blObj,
		 * IGMContainerDao.RCL_IGM_GET_MASTER_CONTAINOR);
		 * objConsignerDao.setConsignerData(blObj,
		 * IGMConsignerDataDao.RCL_IGM_GET_MASTER_CONSIGNER);
		 * objConsigneeDao.setConsigneeData(blObj,
		 * IGMConsigneeDataDao.RCL_IGM_GET_MASTER_CONSIGNEE);
		 * objNotifyPartyDao.setNotifyPartyData(blObj,
		 * IGMNodifyPartyDao.RCL_IGM_MASTER_NODIFY_PARTY_DESCRIPTION);
		 * objMarksDescDao.setMarksDescriptionData(blObj,
		 * IGMMarksAndDescDao.RCL_IGM_GET_MASTER_MARKS_DESCRIPTION);
		 * objPreviousDao.setPreviousDeclData(blObj,
		 * IGMPPreviousDeclarationDao.RCL_IGM_GET_MASTER_PREV_DECLARATION); }
		 */		
		
		return blObj; 
	}
	
	private ActionForward getPortDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("getPortDetails() Called..");
		
		IGMDaoNew objDao = (IGMDaoNew) getDao(DAO_BEAN_ID);
		List<PortMod> list = objDao.getPortDetailsJdbc();
		
		 net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
			jsonObj = new net.sf.json.JSONObject();
			jsonObj.put("result",list);
			jsonObj.write(response.getWriter());
		return null;
	}
	
}

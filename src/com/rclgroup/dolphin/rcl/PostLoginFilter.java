package com.rclgroup.dolphin.rcl;
/*
 * @(#)PostLoginFilter.java
 *
 * Copyright 2009 by NIIT.,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of NIIT Tech. Ltd ("Confidential Information").
 */

 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.niit.control.common.Debug;
import com.niit.control.common.GlobalConstants;
import com.niit.control.common.StringUtil;
import com.niit.control.common.exception.BusinessException;
import com.niit.control.eut.dao.EutCommonDao;
import com.niit.control.web.UserAccountBean;
import com.niit.control.web.action.JdbcServiceLocator;


/**
 * @version 1.0
 * @author NIIT
 */
public class PostLoginFilter implements Filter, GlobalConstants  {
	        
	/**
	 * @see javax.servlet.Filter#void ()
	 */
	public void destroy() {

	}

	/**
	 * @see javax.servlet.Filter#void (javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(
		ServletRequest req,
		ServletResponse resp,
		FilterChain chain)
		throws ServletException, IOException  {

		Debug.logFramework("##############Start Postlogin Filter#############");
                
                HttpServletRequest    hreq            = (HttpServletRequest) req;
                HttpServletResponse   httpResponse    = (HttpServletResponse) resp;
                HttpSession           session         = hreq.getSession(true); 
                Locale.setDefault(Locale.ENGLISH);
                /* Get UAB from Session */
                UserAccountBean account               = (UserAccountBean)session.getAttribute( USER_ACCOUNT_BEAN);
	        
                /* Get Calling Application ID and Security Ticket ID */                            
                String                lstrTicketId     = BLANK;
                String                lstrCallAppId    = req.getParameter(CALLING_APP_ID);
                Debug.logFramework("lstrCallAppId="+lstrCallAppId);
                String lstrPathInfo = hreq.getPathInfo();
                if(lstrTicketId == null && account != null){
                    lstrTicketId = account.getTicketId();
                }
                if(lstrCallAppId == null && account != null){
                    lstrCallAppId = account.getCallAppId();
                }
            
                /* Variable to Store User Profile */
                String lstrUserId       = null;
                String lstrPassword     = null;
                String lstrOpenMode     = null;
                
                /* To store Cookie Value came from Dolphin */
                String lstrRCLCookie    = null;
                Cookie[] cookies        = null;
                String[] arrCookieTokens= null;
            
                Debug.logFramework("########Start#############ESM Account Details.");
                Debug.logFramework("ESM.lstrCallAppId="+lstrCallAppId);
                Debug.logFramework("########End#############ESM Account Details.");              
                
                 
                
                /* Read Cookie from Dolphin */
                cookies = hreq.getCookies();                
                if (cookies != null && cookies.length > 0) {
                   for (int i = 0; i < cookies.length; i++) {
                          Debug.logFramework("########cookies["+i+"]"+cookies[i].getName()); 
                          if(cookies[i].getName().equals(KEY_USER_INFO)){
                              lstrRCLCookie = cookies[i].getValue();
                              break;
                          }
                   }
                } 
                
                /* If No Auth cookie, Error */
                if(lstrCallAppId!= null && lstrCallAppId.equalsIgnoreCase(DOLPHIN)){
                    if(lstrRCLCookie == null || lstrRCLCookie.length() == 0){
                        Debug.logFramework("Cookie Absent from Dolphin, showing session timeout"); 
                        httpResponse.sendRedirect(NO_SESSION_PATH);
                    }
                }
                
                /* Incase cookie present, refresh the UAB in EZL if not present in session before */
                if(lstrRCLCookie != null) {
                    if(session.getAttribute(KEY_USER_INFO) == null || !session.getAttribute(KEY_USER_INFO).equals(lstrRCLCookie)){
                       session.setAttribute(KEY_USER_INFO, lstrRCLCookie);                   
                       account = null;
                    }
                }
                
                /* Split the cookie value to individual values */
                if(lstrRCLCookie != null) {
                    arrCookieTokens = StringUtil.extractTokensWithSpaces(lstrRCLCookie,TILDE);
                }
                
                /* If cookie found, Error incase invalid cookie */
                if(arrCookieTokens != null && arrCookieTokens.length < TOTAL_COOKIE_ITEMS){
                    Debug.logFramework("Invalid Cookie ..."); 
                    httpResponse.sendRedirect(NO_SESSION_PATH);
                }
                Debug.logFramework("########before setting variables###");
                /*If called from Dolphin read user id from cookie else read from request */
                if(arrCookieTokens != null){
                    lstrUserId = arrCookieTokens[IDX_USER_ID];
                } else {
                    lstrUserId = req.getParameter(CALL_APP_USER_ID);
                    if(account == null){
                        lstrUserId = req.getParameter(CALL_APP_USER_ID);
                    } else {
                        //reset UAB if login id changed from non-dolphin...
                        if(lstrUserId != null && !lstrUserId.equals(account.getUserId())){
                            account = null;
                        } else {
                            lstrUserId = account.getUserId();
                        }
                    }
                }
                        
                Debug.logFramework("########Start#############After cookie split & set.");
                Debug.logFramework("lstrUserId="+lstrUserId);
                Debug.logFramework("########End#############After cookie split & set.");
                
            
                /* If UAB is null & User ID is also Null */
//                System.out.println("lstrPathInfo >>>>>>>>>>>>>>> "+lstrPathInfo);
//                if ( account    == null && 
//                   ( lstrUserId == null || lstrUserId.length() == 0  )
//                   ) {
//                    if(lstrPathInfo.equalsIgnoreCase("/setw001")){
//                        try{ 
//                        
//                        //account = AccountUtil.getUserAccountBean(hreq, lstrUserId, new String[]{"ELL001"});
//                         account = AccountUtil.getUserAccountBean(hreq, "DEV_TEAM", new String[]{"ELL001"});
//
//                        session.setAttribute(USER_ACCOUNT_BEAN, account);
//                            //chain.doFilter(req, resp);
//                        }catch (Exception ex1){
//                            System.out.println("Error WhileUSER_ACCOUNT_BEAN***************** "+ex1.getMessage());
//                            //Debug.logFramework("Error While creating UAB ");
//                            //Debug.logFramework(ex1);
//                            //httpResponse.sendRedirect(ERROR_PATH);
//                           
//                        }
//                    }else{
//                    Debug.logFramework("UAB & User ID both are Blank");
//                    httpResponse.sendRedirect(NO_SESSION_PATH);
//                    }
//                }
                
                if(hreq.getPathInfo().equals("/setw001")){
                    chain.doFilter(req, resp);
                }
                
                //Converting User Id to Upper case
                lstrUserId      = lstrUserId.toUpperCase();
                  
                
	    
                // If UAB is still null, means first time...
                if (account == null) {
                    Debug.logFramework("UAB IS NULL FOR THIS USER SESSION, CREATING...");
                    try{                    
                        //Create instance of UserAccountBean
//                        if(lstrPathInfo.equals("/setw001")){
//                            account = AccountUtil.getUserAccountBean(hreq, "DEV_TEAM", new String[]{"ELL001"});
//
//                            session.setAttribute(USER_ACCOUNT_BEAN, account);
//                            //chain.doFilter(req, resp);
//                        }else{
                            account = (UserAccountBean) java.beans.Beans.instantiate(getClass().getClassLoader(),USER_ACCOUNT_BEAN_CLASS);                        
//                        }
                    }catch (Exception ex1){
                        Debug.logFramework("Error While creating UAB ");
                        Debug.logFramework(ex1);
                        //httpResponse.sendRedirect(ERROR_PATH);
                    }
                    Debug.logFramework("UAB IS INSTANTIATED-1...");
                    /* If Not able to create UAB */
                    if ( account    == null ) {
                        Debug.logFramework("Error While creating UAB ");
                        httpResponse.sendRedirect(NO_SESSION_PATH);
                    }
                    Debug.logFramework("UAB IS INSTANTIATED-2...");
                    //Pick UAB Info from DB
                    Map mapReturn = new HashMap(0);
                    try{
                        EutCommonDao objEutCommonDao = (EutCommonDao)JdbcServiceLocator.getDAO("commonDAO");
                        Map mapParam = new HashMap(0);
                        
                        mapParam.put(KEY_USER_ID,lstrUserId);
                        mapReturn = objEutCommonDao.getUserInfo(mapParam);
                        Debug.logFramework("UAB Size ...="+((List)mapReturn.get(EutCommonDao.KEY_REF_UAB)).size());
                        if(((List)mapReturn.get(EutCommonDao.KEY_REF_UAB)).size() != 1){
                            httpResponse.sendRedirect(NO_AUTH_PATH);
                        }
                    } catch(BusinessException ex1){
                        Debug.logFramework("Error While fetching UAB from DB");
                        Debug.logFramework(ex1);
                        httpResponse.sendRedirect(NO_AUTH_PATH);
                    } catch(Exception ex2){
                        Debug.logFramework("Error While fetching UAB from DB");
                        Debug.logFramework(ex2);
                        httpResponse.sendRedirect(ERROR_PATH);
                    }
                    Debug.logFramework("EutCommonDao IS INSTANTIATED...");
                    lstrPassword    = BLANK;
                       if(DOLPHIN.equalsIgnoreCase(lstrCallAppId)) {
                        lstrOpenMode    = ORG_TYPE_IN_HOUSE;
                    } else {
                        lstrOpenMode    = BLANK;
                    }        
                       
                    Debug.logFramework("getting UAB info from DB for user...");
                    if(((List)mapReturn.get(EutCommonDao.KEY_REF_UAB)).size() != 0){
                    account = (UserAccountBean)((List)mapReturn.get(EutCommonDao.KEY_REF_UAB)).get(0);
                    Debug.logFramework("Got  UAB info from DB for user...account="+account);
                    account.setTicketId(lstrTicketId);
                    account.setCallAppId(lstrCallAppId);
                    account.setPassword(lstrPassword);
                    account.setLoginMode(lstrOpenMode);  
                    }    
                    //Set Auth Matrix of User
                    Debug.logFramework("Set Auth. MAtrix in UAB="+mapReturn.get(EutCommonDao.KEY_REF_AUTH_LIST));
                    account.setAuthInfo((List)mapReturn.get(EutCommonDao.KEY_REF_AUTH_LIST));
                    if(((List)mapReturn.get(EutCommonDao.KEY_REF_UAB)).size() != 0){
                    // Put the UserAccountBean in Session object
                    Debug.logFramework("Set the UAB in Session");
                    session.setAttribute( USER_ACCOUNT_BEAN,account);
                    }
                }
            
                
                Debug.logFramework("##############End Postlogin Filter############# hreq.getRequestURI()="+hreq.getRequestURI());
                chain.doFilter(req, resp);
                
	}

	/**
	 * Method init.
	 * 
	 * @param config
	 * @throws javax.servlet.ServletException
	 */
	public void init(FilterConfig config) throws ServletException {

	}

}

/* Modification History
 *
 * 2005-07: Modified for Tops
 * 2009-11: Moified for EZL
 *
 */

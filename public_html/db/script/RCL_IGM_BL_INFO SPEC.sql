create or replace PACKAGE         RCL_IGM_BL_INFO 
IS
TYPE CONTAINER_CURSOR_REF IS REF CURSOR; 

PROCEDURE RCL_IGM_SAVE_CONTAINOR_NEW(
                            P_I_V_BL VARCHAR2,
                            P_I_C_CONT_LST   IN      CLOB 
);  

PROCEDURE RCL_IGM_SAVE_UNFETCHED_CONTAINOR_NEW(
                            P_I_V_BL VARCHAR2 
);  

PROCEDURE RCL_IGM_DELETE_CONTAINOR_NEW(
                            P_I_V_BL VARCHAR2,
                            P_I_C_CONT_LST   IN      CLOB
); 

PROCEDURE RCL_IGM_GET_MASTER_CONTAINOR(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                               
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);
PROCEDURE RCL_IGM_GET_SAVE_CONTAINOR(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);


---BL

-- this for master 
PROCEDURE  RCL_IGM_GET_MASTER_BL_DATA (   
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,    
                            P_I_V_POD		    VARCHAR2,
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_I_V_SERVICE       VARCHAR2 DEFAULT NULL,
                            P_I_V_VESSEL        VARCHAR2 DEFAULT NULL,
                            P_I_V_VOYAGE        VARCHAR2 DEFAULT NULL,
                            P_I_V_POD_TERMINAL  VARCHAR2 DEFAULT NULL,
                            P_I_V_FROM_DATE     VARCHAR2 DEFAULT NULL,
                            P_I_V_TO_DATE       VARCHAR2 DEFAULT NULL,
                            P_I_V_BL_STATUS     VARCHAR2 DEFAULT NULL,
                            P_I_V_POL           VARCHAR2 DEFAULT NULL,
                            P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                            P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                            P_I_V_POL_TERMINAL  VARCHAR2 DEFAULT NULL,
                            P_I_V_DIRECTION     VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);


PROCEDURE  RCL_IGM_GET_MASTER_BL_DATA_NEW (P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
                             P_I_V_POD          VARCHAR2, 
                             P_I_V_BL           VARCHAR2 DEFAULT NULL, 
                             P_I_V_BL_COUNT      VARCHAR2 DEFAULT NULL,
                             P_I_V_SERVICE      VARCHAR2 DEFAULT NULL, 
                             P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
                             P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
                             P_I_V_POD_TERMINAL VARCHAR2 DEFAULT NULL,  
                             P_I_V_FROM_DATE    VARCHAR2 DEFAULT NULL,     
                             P_I_V_TO_DATE      VARCHAR2 DEFAULT NULL, 
                             P_I_V_BL_STATUS    VARCHAR2 DEFAULT NULL, 
                             P_I_V_POL          VARCHAR2 DEFAULT NULL, 
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL, 
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL, 
                             P_O_V_ERROR        OUT VARCHAR2
);               

PROCEDURE  RCL_IGM_SAVE_UNFETCHED_BL_DATA_SUSHIL ( 
                            P_I_V_BL           VARCHAR2 DEFAULT NULL,
                            P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,
                            P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL,
                            P_I_V_BL_JSON      IN      CLOB);      

PROCEDURE  RCL_IGM_GET_BL_DATA_MASTER_SAVE_DATA (   
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,    
                            P_I_V_POD		    VARCHAR2,
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_I_V_SERVICE       VARCHAR2 DEFAULT NULL,
                            P_I_V_VESSEL        VARCHAR2 DEFAULT NULL,
                            P_I_V_VOYAGE        VARCHAR2 DEFAULT NULL,
                            P_I_V_POD_TERMINAL  VARCHAR2 DEFAULT NULL,
                            P_I_V_FROM_DATE     VARCHAR2 DEFAULT NULL,
                            P_I_V_TO_DATE       VARCHAR2 DEFAULT NULL,
                            P_I_V_BL_STATUS     VARCHAR2 DEFAULT NULL,
                            P_I_V_POL           VARCHAR2 DEFAULT NULL,
                            P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                            P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                            P_I_V_POL_TERMINAL  VARCHAR2 DEFAULT NULL,
                            P_I_V_DIRECTION     VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);


PROCEDURE  RCL_IGM_GET_SAVE_BL_DATA (  
                             P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,    
                             P_I_V_POD		    VARCHAR2,
                             P_I_V_BL            VARCHAR2 DEFAULT NULL,
                             P_I_V_SERVICE       VARCHAR2 DEFAULT NULL,
                             P_I_V_VESSEL        VARCHAR2 DEFAULT NULL,
                             P_I_V_VOYAGE        VARCHAR2 DEFAULT NULL,
                             P_I_V_POD_TERMINAL  VARCHAR2 DEFAULT NULL,
                             P_I_V_FROM_DATE     VARCHAR2 DEFAULT NULL,
                             P_I_V_TO_DATE       VARCHAR2 DEFAULT NULL,
                             P_I_V_BL_STATUS     VARCHAR2 DEFAULT NULL,
                             P_I_V_POL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL  VARCHAR2 DEFAULT NULL,
                             P_I_V_DIRECTION     VARCHAR2 DEFAULT NULL,
                             P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE  RCL_IGM_GET_SAVE_BL_DATA_NEW (   
                             P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
                             P_I_V_POD          VARCHAR2, 
                             P_I_V_BL           VARCHAR2 DEFAULT NULL, 
                             P_I_V_BL_COUNT      VARCHAR2 DEFAULT NULL,
                             P_I_V_SERVICE      VARCHAR2 DEFAULT NULL, 
                             P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
                             P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
                             P_I_V_POD_TERMINAL VARCHAR2 DEFAULT NULL,  
                             P_I_V_FROM_DATE    VARCHAR2 DEFAULT NULL,     
                             P_I_V_TO_DATE      VARCHAR2 DEFAULT NULL, 
                             P_I_V_BL_STATUS    VARCHAR2 DEFAULT NULL, 
                             P_I_V_POL          VARCHAR2 DEFAULT NULL, 
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL, 
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL, 

                             P_O_V_ERROR        OUT VARCHAR2
);   


---sushil
PROCEDURE RCL_IGM_MASTER_HBL(   
                            P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
                            P_I_V_BL	    VARCHAR2,
                            P_O_V_ERROR        OUT VARCHAR2
);

PROCEDURE RCL_IGM_SAVE_BL_DATA_SUSHIL(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_BL_DTLS   IN      CLOB
);

PROCEDURE RCL_IGM_SAVE_CONSIGNEE_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_CONSIGNEE_DTLS   IN      CLOB
);

PROCEDURE RCL_IGM_SAVE_UNFETCHED_CONSIGNEE_DATA(   
                            P_I_V_BL	    VARCHAR2 
);

PROCEDURE RCL_IGM_DELETE_CONSIGNEE_DATA(   
                             P_I_V_BL	    VARCHAR2,
                            P_I_V_CONSIGNER_DTLS   IN      CLOB
); 

PROCEDURE RCL_IGM_SAVE_CONSIGNER_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_CONSIGNER_DTLS   IN      CLOB
);

PROCEDURE RCL_IGM_SAVE_UNFETCHED_CONSIGNER_DATA(   
                            P_I_V_BL	    VARCHAR2 
);

PROCEDURE RCL_IGM_DELETE_CONSIGNER_DATA(    
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_CONSIGNER_DTLS   IN      CLOB
);

PROCEDURE RCL_IGM_GET_SAVE_NOTIFYPARTY(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_MASTER_NODIFY_PARTY_DATA(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_SAVE_NODIFY_PARTY_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_NODIFY_PARTY_DTLS   IN      CLOB
);

PROCEDURE RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DATA(   
                            P_I_V_BL	    VARCHAR2 
);

PROCEDURE RCL_IGM_DELETE_NODIFY_PARTY_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_NODIFY_PARTY_DTLS   IN      CLOB
);


PROCEDURE RCL_IGM_GET_MASTER_CONSIGNER(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_GET_SAVE_CONSIGNER(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_GET_MASTER_CONSIGNEE(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);
PROCEDURE RCL_IGM_GET_SAVE_CONSIGNEE(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_GET_MARKS_DESCRIPTION(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_GET_SAVE_MARKS_DESCRIPTION(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS   IN      CLOB
) ;

PROCEDURE RCL_IGM_SAVE_UNFETCHED_MARKS_NUMBER_DESCRIPTION_DATA(   
                            P_I_V_BL	    VARCHAR2 
) ;

PROCEDURE RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS   IN      CLOB
) ;

--Added by janmejaya 
PROCEDURE RCL_IGM_GET_PREV_DECLARATION(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
                            P_I_V_BL   VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR        OUT VARCHAR2
);  

PROCEDURE RCL_IGM_GET_SAVE_PREV_DECLARATION(
                            P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
                            P_I_V_BL            VARCHAR2 DEFAULT NULL,
                            P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_SAVE_PREV_DECLARATION_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_PREV_DECLARATION_DTLS   IN      CLOB
);  

PROCEDURE RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION_DATA(   
                            P_I_V_BL	    VARCHAR2
); 

PROCEDURE RCL_IGM_DELETE_PREV_DECLARATION_DATA(   
                            P_I_V_BL	    VARCHAR2,
                            P_I_V_PREV_DECLARATION_DTLS   IN      CLOB
);

END RCL_IGM_BL_INFO;